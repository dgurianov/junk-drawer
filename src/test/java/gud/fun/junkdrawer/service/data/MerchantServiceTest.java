package gud.fun.junkdrawer.service.data;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.dto.assembler.MerchantResponseDtoAssembler;
import gud.fun.junkdrawer.dto.transaction.MerchantRequestDto;
import gud.fun.junkdrawer.dto.transaction.MerchantResponseDto;
import gud.fun.junkdrawer.persistance.model.Merchant;
import gud.fun.junkdrawer.persistance.repository.MerchantRepository;
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

class MerchantServiceTest {

    @Mock
    private MerchantRepository merchantRepository;

    @InjectMocks
    private MerchantService merchantService;

    @Mock
    private MerchantResponseDtoAssembler countryDtoAssembler;

    @Mock
    private PagedResourcesAssembler<Merchant> pagedResourcesAssembler;

    private Merchant merchant;
    private MerchantRequestDto merchantRequestDto;
    private MerchantResponseDto merchantResponseDto;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();

        merchant = new Merchant(id, "MerchantName", CountryCode.US, "MerchantCategory");
        merchantRequestDto = new MerchantRequestDto(id, "MerchantName", "USA", "MerchantCategory");
        merchantResponseDto = new MerchantResponseDto(id, "MerchantName", "USA", "MerchantCategory");
    }

    @Test
    void testGetAll() {
        when(merchantRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(merchant)));
        when(pagedResourcesAssembler.toModel(any(Page.class), any(MerchantResponseDtoAssembler.class))).thenReturn(PagedModel.of(List.of(merchantResponseDto), new PagedModel.PageMetadata(1, 1, 1, 1)));

        PagedModel<MerchantResponseDto> merchants = merchantService.getAll(PageRequest.of(0, 1));
        MerchantResponseDto merchant = merchants.getContent().iterator().next();
        assertEquals(1, merchants.getMetadata().getTotalElements());
        assertEquals("MerchantName", merchant.getName());
    }

    @Test
    void testGetById() {
        when(merchantRepository.findById(id)).thenReturn(Optional.of(merchant));
        MerchantResponseDto response = merchantService.getById(id);
        assertEquals("MerchantName", response.getName());
    }

    @Test
    void testCreate() {
        when(merchantRepository.save(any(Merchant.class))).thenReturn(merchant);
        merchantRequestDto.setId(null);
        MerchantResponseDto response = merchantService.create(merchantRequestDto);
        assertEquals("MerchantName", response.getName());
    }

    @Test
    void testUpdate() {
        when(merchantRepository.findById(id)).thenReturn(Optional.of(merchant));
        when(merchantRepository.save(any(Merchant.class))).thenReturn(merchant);
        MerchantResponseDto response = merchantService.update(merchantRequestDto);
        assertEquals("MerchantName", response.getName());
    }

    @Test
    void testDelete() {
        doNothing().when(merchantRepository).deleteById(id);
        MerchantResponseDto response = merchantService.delete(id);
        assertEquals(id, response.getId());
    }
}