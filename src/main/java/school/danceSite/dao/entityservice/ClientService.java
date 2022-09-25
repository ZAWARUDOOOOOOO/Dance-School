package school.danceSite.dao.entityservice;

import org.springframework.stereotype.Service;
import school.danceSite.dao.entity.Client;
import school.danceSite.dao.entityrepository.ClientRepository;

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

    public Client createClient(Client client) {
        Client clientToBeSaved;
        synchronized (this) {
            Boolean missedIdExists = clientRepository.missedIdExists();
            missedIdExists = missedIdExists == null ? true : missedIdExists;
            if (missedIdExists) {
                clientRepository.setAutoIncrement(clientRepository.getFirstMissedId());
            }
            clientToBeSaved = clientRepository.save(client);
            if (missedIdExists) {
                clientRepository.setMaxAutoIncrement();
            }
        }
        return clientToBeSaved;
    }

    public Client getById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    public Client getByContactNumberAndEmail(String contactNumber, String email) {
        return clientRepository.findByContactNumberAndEmail(contactNumber, email);
    }

    public void updateClient(Long id, Client client) {
        client.setId(id);
        clientRepository.save(client);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public boolean credentialsExist(Client client) {
        return getByContactNumberAndEmail(client.getContactNumber(), client.getEmail()) != null;
    }
}
