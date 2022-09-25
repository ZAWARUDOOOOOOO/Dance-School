package school.danceSite.dao.entityservice;

import org.springframework.stereotype.Service;
import school.danceSite.dao.entity.Client;
import school.danceSite.dao.entityrepository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    /*next 4 methods are single CRUD operations*/
    public Client createClient(Client client) {//create
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

    public Client getById(Long id) {//read
        return clientRepository.findClientById(id);
    }

    public void updateClient(Long id, Client client) {//update
        Client clientToBeUpdated = client;
        client.setId(id);
        clientRepository.save(clientToBeUpdated);
    }

    public void deleteClient(Client client) {//delete
        clientRepository.deleteById(client.getId());
    }
}
