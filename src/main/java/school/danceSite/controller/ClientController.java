package school.danceSite.controller;

import org.springframework.web.bind.annotation.*;
import school.danceSite.dao.entity.Client;
import school.danceSite.dao.entityservice.ClientService;
import school.danceSite.service.EmailSenderService;

import java.util.List;

@RestController
@RequestMapping("")
public class ClientController {

    private final EmailSenderService emailSenderService;
    private final ClientService clientService;

    public ClientController(EmailSenderService emailSenderService, ClientService clientService) {
        this.emailSenderService = emailSenderService;
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> allClients() {
        return clientService.getClients();
    }

    @PostMapping("/addclient") // добавить проверку на повторяющиеся записи
    public String addClient(@RequestBody Client body) {
        if (body.getId() != null) {//нужна проверка на бади при создании сущности
            return String.format("id is not allowed here, but received id = %s.", body.getId());
        }
        clientService.createClient(body);
        return "Successfully added!";
    }

    @PutMapping("/updateclient{id}")
    public String updateClient(@PathVariable Long id, @RequestBody Client client) {
        if (clientService.getById(id) == null) {
            return String.format("Client with id = %s doesn't exist!", id);
        }
        clientService.updateClient(id, client);
        return "Successfully updated!";
    }

    @DeleteMapping("/deleteclient{id}")
    public String deleteClient(@PathVariable Long id) {
        Client client = clientService.getById(id);
        if (client == null) {
            return "This user doesn't exist!";
        }
        clientService.deleteClient(client);
        return "Successfully deleted!";
    }
}
