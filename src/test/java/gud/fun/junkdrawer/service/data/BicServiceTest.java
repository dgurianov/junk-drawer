package gud.fun.junkdrawer.service.data;


import gud.fun.junkdrawer.dto.assembler.BicResponseDtoAssembler;
import gud.fun.junkdrawer.dto.transaction.BicRequestDto;
import gud.fun.junkdrawer.dto.transaction.BicResponseDto;
import gud.fun.junkdrawer.persistance.model.Bic;
import gud.fun.junkdrawer.persistance.repository.BicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class BicServiceTest {

    @Mock
    private BicRepository bicRepository;

    @Mock
    private BicResponseDtoAssembler bicDtoAssembler;

    @Mock
    private PagedResourcesAssembler<Bic> pagedResourcesAssembler;

    @InjectMocks
    private BicService bicService;

    private Bic bic;
    private BicRequestDto bicRequestDto;
    private BicResponseDto bicResponseDto;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        bic = new Bic(id, "BIC123", "Institution");
        bicRequestDto = new BicRequestDto(id, "BIC123", "Institution");
        bicResponseDto = new BicResponseDto(id, "BIC123", "Institution");

    }

    @Test
    void testGetAll() {
        when(bicRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(bic)));
        when(pagedResourcesAssembler.toModel(any(Page.class), any(BicResponseDtoAssembler.class))).thenReturn(PagedModel.of(List.of(bicResponseDto), new PagedModel.PageMetadata(1, 1, 1, 1)));
        PagedModel<BicResponseDto> bics = bicService.getAll(PageRequest.of(0,1));
        assertEquals(1, bics.getMetadata().getNumber());
        assertEquals("BIC123", bics.getContent().iterator().next().getValue());
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
