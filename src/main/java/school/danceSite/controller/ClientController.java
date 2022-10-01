package school.danceSite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.danceSite.config.exceptionProcessing.apiExceptions.ClientNotFoundException;
import school.danceSite.controller.view.ClientView;
import school.danceSite.controller.view.mapper.ClientMapper;
import school.danceSite.dao.entity.Client;
import school.danceSite.dao.entityService.ClientService;
import school.danceSite.utils.ErrorDescription;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientMapper clientMapper;
    private final ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> allClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(clientService.getById(id));
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorDescription.builder().userMessage("Client not found"));
        }
    }

    @PostMapping
    public Client addClient(@RequestBody ClientView body) {
        Client client = clientMapper.mapFromView(body);
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody ClientView body) {
        Client client = clientMapper.mapFromView(body);
        return clientService.updateClient(id, client);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Successfully deleted.")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
