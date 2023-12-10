package soa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import soa.controller.ClientRESTController;
import soa.entities.Client;
import soa.metier.ClientMetierImpl;
import soa.repository.ClientRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientRESTControllerTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMetierImpl clientMetier;

    @InjectMocks
    private ClientRESTController clientRESTController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientRESTController).build();
    }

    @Test
    void testGetAllClients() throws Exception {
        // Arrange
        when(clientRepository.findAll()).thenReturn(Arrays.asList(new Client(), new Client()));

        // Act & Assert
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetClientById() throws Exception {
        // Arrange
        when(clientMetier.getClientById("1")).thenReturn(new Client());

        // Act & Assert
        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void testAjouterClient() throws Exception {
        // Arrange
        Client client = new Client();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonClient = objectMapper.writeValueAsString(client);

        // Act & Assert
        mockMvc.perform(post("/clients/ajouter")
                        .content(jsonClient)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(clientMetier, times(1)).ajouterClient(any(Client.class));
    }

    @Test
    void testModifierClient() throws Exception {
        // Arrange
        Client client = new Client();
        client.setId(1L); // Assuming you set the ID for the client

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonClient = objectMapper.writeValueAsString(client);

        // Mocking the void method
        doNothing().when(clientMetier).updateClient(any(Client.class));

        // Act & Assert
        mockMvc.perform(put("/clients/modifier")
                        .content(jsonClient)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the method was called
        verify(clientMetier, times(1)).updateClient(any(Client.class));
    }


    @Test
    void testSupprimerClient() throws Exception {
        // Arrange
        String clientId = "1";

        // Act & Assert
        mockMvc.perform(delete("/clients/supprimer/{id}", clientId))
                .andExpect(status().isOk());
        verify(clientMetier, times(1)).deleteClient(clientId);
    }
}
