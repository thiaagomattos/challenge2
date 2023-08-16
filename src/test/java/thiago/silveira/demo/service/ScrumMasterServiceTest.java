package thiago.silveira.demo.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import thiago.silveira.demo.dtos.ScrumMasterDtoRequest;
import thiago.silveira.demo.dtos.ScrumMasterDtoResponse;
import thiago.silveira.demo.entity.ScrumMaster;
import thiago.silveira.demo.repository.ScrumMasterRepository;

import static org.junit.jupiter.api.Assertions.*;

public class ScrumMasterServiceTest {

    @Mock
    private ScrumMasterRepository scrumMasterRepository;

    @InjectMocks
    private ScrumMasterService scrumMasterService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveScrumMaster() {
        ScrumMasterDtoRequest request = new ScrumMasterDtoRequest();
        ScrumMaster expectedScrumMaster = new ScrumMaster(1L, "Julia", "Fernandes", "julia@gmail.com", "Rua Koesa, 22");

        when(scrumMasterRepository.save(any(ScrumMaster.class))).thenReturn(expectedScrumMaster);

        ScrumMaster savedScrumMaster = scrumMasterService.save(request);

        assertEquals(expectedScrumMaster, savedScrumMaster);
    }

    @Test
    public void testGetScrumMasterById() {
        Long scrumMasterId = 1L;
        ScrumMaster scrumMaster = new ScrumMaster();
        when(scrumMasterRepository.findById(scrumMasterId)).thenReturn(java.util.Optional.of(scrumMaster));

        ScrumMasterDtoResponse result = scrumMasterService.getById(scrumMasterId);

        assertNotNull(result);
        assertEquals(scrumMasterId, result.getId());
        verify(scrumMasterRepository, times(1)).findById(scrumMasterId);
    }

    @Test
    public void testUpdateScrumMaster() {
        ScrumMaster scrumMasterToUpdate = new ScrumMaster();
        when(scrumMasterRepository.save(any())).thenReturn(scrumMasterToUpdate);

        ScrumMaster result = scrumMasterService.updateScrumMaster(scrumMasterToUpdate);

        assertNotNull(result);
        assertEquals(scrumMasterToUpdate, result);
        verify(scrumMasterRepository, times(1)).save(scrumMasterToUpdate);
    }

    @Test
    public void testDeleteScrumMaster() {
        Long scrumMasterId = 1L;

        scrumMasterService.deleteScrumMaster(scrumMasterId);

        verify(scrumMasterRepository, times(1)).deleteById(scrumMasterId);
    }
}