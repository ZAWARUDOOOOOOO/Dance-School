package school.danceSite.controller.view.mapper;

import org.springframework.stereotype.Component;
import school.danceSite.controller.view.ClientView;
import school.danceSite.dao.entity.Client;

@Component
public class ClientMapper {

    public ClientView mapToView(Client client) {
        ClientView clientView = new ClientView();
        clientView.setClientname(client.getClientname());
        clientView.setContactNumber(client.getContactNumber());
        clientView.setEmail(client.getEmail());
        return clientView;
    }

    public Client mapFromView(ClientView clientView) {
        Client client = new Client();
        client.setClientname(clientView.getClientname());
        client.setContactNumber(clientView.getContactNumber());
        client.setEmail(clientView.getEmail());
        return client;
    }
}
