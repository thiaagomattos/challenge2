package thiago.silveira.demo.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import thiago.silveira.demo.dtos.CoordinatorDtoRequest;
import thiago.silveira.demo.dtos.CoordinatorDtoResponse;
import thiago.silveira.demo.entity.Coordinator;
import thiago.silveira.demo.repository.CoordinatorRepository;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatorServiceTest {

    @Mock
    private CoordinatorRepository coordinatorRepository;

    @InjectMocks
    private CoordinatorService coordinatorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCoordinator() {
        CoordinatorDtoRequest request = new CoordinatorDtoRequest();
        Coordinator expectedCoordinator = new Coordinator(1L, "Pedro", "Marcos", "pedro@gmail.com", "Rua Afonso pena, 942");

        when(coordinatorRepository.save(any(Coordinator.class))).thenReturn(expectedCoordinator);

        Coordinator savedCoordinator = coordinatorService.save(request);

        assertEquals(expectedCoordinator, savedCoordinator);
    }

    @Test
    public void testGetCoordinatorById() {
        Long coordinatorId = 1L;
        Coordinator coordinator = new Coordinator();
        when(coordinatorRepository.findById(coordinatorId)).thenReturn(java.util.Optional.of(coordinator));

        CoordinatorDtoResponse result = coordinatorService.getById(coordinatorId);

        assertNotNull(result);
        assertEquals(coordinatorId, result.getId());
        verify(coordinatorRepository, times(1)).findById(coordinatorId);
    }

    @Test
    public void testUpdateCoordinator() {
        Coordinator coordinatorToUpdate = new Coordinator();
        when(coordinatorRepository.save(any())).thenReturn(coordinatorToUpdate);

        Coordinator result = coordinatorService.updateCoordinator(coordinatorToUpdate);

        assertNotNull(result);
        assertEquals(coordinatorToUpdate, result);
        verify(coordinatorRepository, times(1)).save(coordinatorToUpdate);
    }

    @Test
    public void testDeleteCoordinator() {
        Long coordinatorId = 1L;

        coordinatorService.deleteCoordinator(coordinatorId);

        verify(coordinatorRepository, times(1)).deleteById(coordinatorId);
    }
}