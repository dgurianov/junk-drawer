package gud.fun.junkdrawer.service.data;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.dto.transaction.MerchantRequestDto;
import gud.fun.junkdrawer.dto.transaction.MerchantResponseDto;
import gud.fun.junkdrawer.persistance.model.Merchant;
import gud.fun.junkdrawer.persistance.repository.MerchantRepository;
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

class MerchantServiceTest {

    @Mock
    private MerchantRepository merchantRepository;

    @InjectMocks
    private MerchantService merchantService;

    private Merchant merchant;
    private MerchantRequestDto merchantRequestDto;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();

        merchant = new Merchant(id, "MerchantName", CountryCode.US, "MerchantCategory");
        merchantRequestDto = new MerchantRequestDto(id, "MerchantName", "US", "MerchantCategory");
    }

    @Test
    void testGetAll() {
        when(merchantRepository.findAll()).thenReturn(Arrays.asList(merchant));
        List<MerchantResponseDto> merchants = merchantService.getAll();
        assertEquals(1, merchants.size());
        assertEquals("MerchantName", merchants.get(0).getName());
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