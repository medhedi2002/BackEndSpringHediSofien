package soa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import soa.controller.DeviseRESTController;
import soa.entities.Client;
import soa.entities.Devise;
import soa.metier.DeviseMetierInterface;
import soa.repository.DeviseRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class DeviseRESTControllerTest {

    @Mock
    private DeviseRepository deviseRepository;

    @Mock
    private DeviseMetierInterface deviseMetier;

    @InjectMocks
    private DeviseRESTController deviseRESTController;

    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(deviseRESTController).build();
    }


    @Test
    void testGetAllDevises() throws Exception {
        // Arrange
        Devise devise1 = new Devise();
        devise1.setId(1L);

        Devise devise2 = new Devise();
        devise2.setId(2L);

        List<Devise> devises = Arrays.asList(devise1, devise2);

        when(deviseRepository.findAll()).thenReturn(devises);

        // Act & Assert
        mockMvc.perform(get("/devises/"))
                .andExpect(status().isOk());
    }

    @Test
    void testAjouterDevise() throws Exception {
        // Arrange
        Devise devise = new Devise();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonDevise = objectMapper.writeValueAsString(devise);
        // Act & Assert
        mockMvc.perform(post("/devises/ajouter")
                        .content(jsonDevise)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        // Verify that ajouterDevise method was called
        verify(deviseMetier, times(1)).ajouterDevise(any(Devise.class));
    }




    @Test
    void testModifierDevise() throws Exception {
        // Arrange
        Devise devise = new Devise();
        devise.setId(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonDevise = objectMapper.writeValueAsString(devise);

        doNothing().when(deviseMetier).updateDevise(any(Devise.class));

        // Act & Assert
        mockMvc.perform(put("/devises/modifier")
                        .content(jsonDevise)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(deviseMetier, times(1)).updateDevise(any(Devise.class));
    }

    @Test
    void testSupprimerDevise() throws Exception {
        // Arrange
        Long deviseId = 1L;

        // Act & Assert
        mockMvc.perform(delete("/devises/supprimer/{deviseId}", deviseId))
                .andExpect(status().isOk());

        verify(deviseMetier, times(1)).deleteDevise(deviseId);
    }
}
