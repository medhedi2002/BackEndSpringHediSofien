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
import soa.controller.FactureRESTController;
import soa.entities.Facture;
import soa.metier.FactureMetierInterface;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class FactureRESTControllerTest {

    @Mock
    private FactureMetierInterface factureMetier;

    @InjectMocks
    private FactureRESTController factureRESTController;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(factureRESTController).build();
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Test
    void testGetAllFactures() throws Exception {
        // Arrange
        Facture facture1 = new Facture("123", 100.0, new Date(), null, null);
        Facture facture2 = new Facture("456", 200.0, new Date(), null, null);
        List<Facture> factures = Arrays.asList(facture1, facture2);

        when(factureMetier.listeFactures()).thenReturn(factures);

        // Act & Assert
        mockMvc.perform(get("/factures/"))
                .andExpect(status().isOk());
    }

    @Test
    void testAjouterFacture() throws Exception {
        // Arrange
        Facture facture = new Facture("789", 150.0, new Date(), null, null);

        // Act & Assert
        mockMvc.perform(post("/factures/ajouter")
                        .content(objectMapper.writeValueAsString(facture))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(factureMetier, times(1)).ajouterFacture(any(Facture.class));
    }

    @Test
    void testModifierFacture() throws Exception {
        // Arrange
        Facture facture = new Facture("123", 100.0, new Date(), null, null);

        // Act & Assert
        mockMvc.perform(put("/factures/modifier")
                        .content(objectMapper.writeValueAsString(facture))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(factureMetier, times(1)).updateFacture(any(Facture.class));
    }

    @Test
    void testSupprimerFacture() throws Exception {
        // Arrange
        String factureNumber = "123";

        // Act & Assert
        mockMvc.perform(delete("/factures/supprimer/{factureNumber}", factureNumber))
                .andExpect(status().isOk());

        verify(factureMetier, times(1)).deleteFacture(Long.valueOf(factureNumber));
    }
}
