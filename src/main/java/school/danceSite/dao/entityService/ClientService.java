package school.danceSite.dao.entityService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.danceSite.config.exceptionProcessing.apiExceptions.ClientNotFoundException;
import school.danceSite.config.exceptionProcessing.apiExceptions.CredentialsExistException;
import school.danceSite.dao.entity.Client;
import school.danceSite.dao.entityRepository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client createClient(Client client) {
        Client clientToBeSaved;
        if (clientRepository.existsByContactNumberOrEmail(
                client.getContactNumber(), client.getEmail())
        ) {
            throw new CredentialsExistException("Such email or contact number is already taken.");
        }
        Boolean missedIdExists = clientRepository.missedIdExists();
        missedIdExists = missedIdExists == null || missedIdExists;
        if (missedIdExists) {
            clientRepository.setAutoIncrement(clientRepository.getFirstMissedId());
        }
        clientToBeSaved = clientRepository.save(client);
        if (missedIdExists) {
            clientRepository.setMaxAutoIncrement();
        }
        return clientToBeSaved;
    }

    public Client getById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(() -> new ClientNotFoundException(
                String.format("Client with id = %s doesn't exist!", id))
        );
    }

    @Transactional
    public Client updateClient(Long id, Client client) {
        Client clientToBeUpdated = getById(id);
        if (clientRepository.areNewCredUnique(
                id, client.getContactNumber(), client.getEmail())
        ) {
            throw new CredentialsExistException("Such email or contact number is already taken.");
        }
        clientToBeUpdated.setClientname(client.getClientname());
        clientToBeUpdated.setContactNumber(client.getContactNumber());
        clientToBeUpdated.setEmail(client.getEmail());
        return clientRepository.save(clientToBeUpdated);
    }

    public void deleteById(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ClientNotFoundException(
                    String.format("Client with id = %s doesn't exist!", id)
            );
        }
    }
}
