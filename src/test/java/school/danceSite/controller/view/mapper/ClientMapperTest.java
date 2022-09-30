package school.danceSite.controller.view.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.danceSite.controller.view.ClientView;
import school.danceSite.dao.entity.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {

    ClientMapper mapper;
    Client client;
    ClientView clientView;

    @BeforeEach
    void defineData() {
        mapper = new ClientMapper();
        client = new Client();
        client.setClientname("Test client");
        client.setContactNumber("88888888888");
        client.setEmail("test@mail.ru");
        clientView = new ClientView();
        clientView.setClientname("Test client");
        clientView.setContactNumber("88888888888");
        clientView.setEmail("test@mail.ru");
    }

    @Test
    void mapToView() {
        assertEquals(clientView, mapper.mapToView(client));
    }

    @Test
    void mapFromView() {
        assertEquals(client, mapper.mapFromView(clientView));
    }
}