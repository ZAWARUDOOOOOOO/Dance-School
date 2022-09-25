package school.danceSite.controller;

import org.springframework.web.bind.annotation.*;
import school.danceSite.dao.entity.Client;
import school.danceSite.dao.entityservice.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> allClients() {
        return clientService.getClients();
    }

    @PostMapping // реализовать через маппер
    public String addClient(@RequestBody Client body) {
        if (body.getId() != null) {
            return String.format("id is not allowed here, but received id = %s.", body.getId());
        } else if (clientService.credentialsExist(body)) {
            return "Client with such email or contact number already exists.";
        }
        clientService.createClient(body);
        return "Successfully added!";
    }

    @PutMapping("/{id}")//реализовать через маппер
    public String updateClient(@PathVariable Long id, @RequestBody Client body) {
        if (clientService.getById(id) == null) {
            return String.format("Client with id = %s doesn't exist!", id);
        } else if (clientService.credentialsExist(body)) {
            return "Client with such email or contact number already exists.";
        }
        clientService.updateClient(id, body);
        return "Successfully updated!";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id) {
        if (clientService.getById(id) == null) {
            return "This user doesn't exist!";
        }
        clientService.deleteById(id);
        return "Successfully deleted!";
    }
}
