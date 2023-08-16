package thiago.silveira.demo.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import thiago.silveira.demo.dtos.SquadDtoRequest;
import thiago.silveira.demo.dtos.SquadDtoResponse;
import thiago.silveira.demo.entity.Squad;
import thiago.silveira.demo.repository.SquadRepository;

import static org.junit.jupiter.api.Assertions.*;

public class SquadServiceTest {

    @Mock
    private SquadRepository squadRepository;

    @InjectMocks
    private SquadService squadService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveSquad() {
        SquadDtoRequest request = new SquadDtoRequest();
        Squad expectedSquad = new Squad();

        when(squadRepository.save(any(Squad.class))).thenReturn(expectedSquad);

        Squad savedSquad = squadService.save(request);

        assertEquals(expectedSquad, savedSquad);
    }

    @Test
    public void testGetSquadById() {
        Long squadId = 1L;
        Squad squad = new Squad();
        when(squadRepository.findById(squadId)).thenReturn(java.util.Optional.of(squad));

        SquadDtoResponse result = squadService.getById(squadId);

        assertNotNull(result);
        assertEquals(squadId, result.getId());
        verify(squadRepository, times(1)).findById(squadId);
    }

    @Test
    public void testUpdateSquad() {
        Squad squadToUpdate = new Squad();
        when(squadRepository.save(any())).thenReturn(squadToUpdate);

        Squad result = squadService.updateSquad(squadToUpdate);

        assertNotNull(result);
        assertEquals(squadToUpdate, result);
        verify(squadRepository, times(1)).save(squadToUpdate);
    }

    @Test
    public void testDeleteSquad() {
        Long squadId = 1L;

        squadService.deleteSquad(squadId);

        verify(squadRepository, times(1)).deleteById(squadId);
    }
}