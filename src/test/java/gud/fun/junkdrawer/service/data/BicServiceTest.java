package gud.fun.junkdrawer.service.data;


import gud.fun.junkdrawer.dto.transaction.BicRequestDto;
import gud.fun.junkdrawer.dto.transaction.BicResponseDto;
import gud.fun.junkdrawer.persistance.model.Bic;
import gud.fun.junkdrawer.persistance.repository.BicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BicServiceTest {

    @Mock
    private BicRepository bicRepository;

    @InjectMocks
    private BicService bicService;

    private Bic bic;
    private BicRequestDto bicRequestDto;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        bic = new Bic(id, "BIC123", "Institution");
        bicRequestDto = new BicRequestDto(id, "BIC123", "Institution");
    }

    @Test
    void testGetAll() {
        when(bicRepository.findAll()).thenReturn(Arrays.asList(bic));
        List<BicResponseDto> bics = bicService.getAll();
        assertEquals(1, bics.size());
        assertEquals("BIC123", bics.get(0).getValue());
    }

    @Test
    void testGetById() {
        when(bicRepository.findById(id)).thenReturn(Optional.of(bic));
        BicResponseDto response = bicService.getById(id);
        assertEquals("BIC123", response.getValue());
    }

    @Test
    void testCreate() {
        when(bicRepository.save(any(Bic.class))).thenReturn(bic);
        bicRequestDto.setId(null);
        BicResponseDto response = bicService.create(bicRequestDto);
        assertEquals("BIC123", response.getValue());
    }

    @Test
    void testUpdate() {
        when(bicRepository.findById(id)).thenReturn(Optional.of(bic));
        when(bicRepository.save(any(Bic.class))).thenReturn(bic);
        BicResponseDto response = bicService.update(bicRequestDto);
        assertEquals("BIC123", response.getValue());
    }

    @Test
    void testDelete() {
        doNothing().when(bicRepository).deleteById(id);
        BicResponseDto response = bicService.delete(id);
        assertEquals(id, response.getId());
    }
}
