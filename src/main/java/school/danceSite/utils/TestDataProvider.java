package school.danceSite.utils;

import school.danceSite.dao.entity.Client;

public class TestDataProvider {

    public static Client newClient(Long clientId) {
        Client client = new Client();
        client.setId(clientId);
        client.setEmail("email-" + clientId);
        client.setContactNumber("number-" + clientId);
        client.setClientname("name-" + clientId);

        return client;
    }
}
