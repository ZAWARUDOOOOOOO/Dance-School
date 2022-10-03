package school.danceSite.controller;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import school.danceSite.config.exceptionProcessing.apiExceptions.ClientNotFoundException;
import school.danceSite.controller.view.mapper.ClientMapper;
import school.danceSite.dao.entity.Client;
import school.danceSite.dao.entityService.ClientService;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static school.danceSite.utils.TestDataProvider.newClient;

@RunWith(DataProviderRunner.class)
public class ClientControllerTest {
    private ClientController clientController;
    private ClientMapper clientMapper;
    private ClientService clientService;

    @Before
    public void commonSetUp() {
        clientMapper = new ClientMapper();
        clientService = Mockito.mock(ClientService.class);
        clientController = new ClientController(clientMapper, clientService);
    }

    @Test
    public void getClientIfNotExist() {
        // given
        long nonExistentClientId = 1L;
        when(clientService.getById(nonExistentClientId)).thenThrow(ClientNotFoundException.class);
        // when
        ResponseEntity<?> actualClientRs = clientController.getClient(nonExistentClientId);
        // then
        assertEquals(NOT_FOUND, actualClientRs.getStatusCode(), "Expected status equals 404 not found");
    }

    @Test
    public void getClientIfExists() {
        // given
        Client existentClient = newClient(2L);
        // and
        when(clientService.getById(existentClient.getId())).thenReturn(existentClient);
        // when
        ResponseEntity<?> actualClientRs = clientController.getClient(existentClient.getId());
        // then
        assertEquals(OK, actualClientRs.getStatusCode(), "Expected status equals 200 OK");
        assertEquals(existentClient, actualClientRs.getBody(), "Expected client should be returned");
    }

    @DataProvider
    public static Object[][] clientsList() {
        Client client1 = newClient(1L);
        Client client2 = newClient(2L);

        return new Object[][]{
                { emptyList() },
                { singletonList(client1) },
                { asList(client1, client2) }
        };
    }

    @Test
    @UseDataProvider("clientsList")
    public void getClientsIfEmpty(List<Client> expectedClients) {
        // given
        when(clientService.getClients()).thenReturn(expectedClients);
        // when
        List<Client> actualClients = clientController.allClients();
        // then
        assertEquals(expectedClients, actualClients, "Should return expected clients list");
    }

}

