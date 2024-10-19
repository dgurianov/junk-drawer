package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.transaction.CreditCardRequestDto;
import gud.fun.junkdrawer.dto.transaction.CreditCardResponseDto;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import gud.fun.junkdrawer.persistance.repository.CreditCardRepository;
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

class CreditCardServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    private BicService bicService;

    @InjectMocks
    private CreditCardService creditCardService;

    private CreditCard creditCard;
    private CreditCardRequestDto creditCardRequestDto;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        creditCard = new CreditCard(id, "1234567890123456", "Issuer", null);
        creditCardRequestDto = new CreditCardRequestDto(id, "1234567890123456", "Issuer", null);
    }

    @Test
    void testGetAll() {
        when(creditCardRepository.findAll()).thenReturn(Arrays.asList(creditCard));
        List<CreditCardResponseDto> creditCards = creditCardService.getAll();
        assertEquals(1, creditCards.size());
        assertEquals("1234567890123456", creditCards.get(0).getCcn());
    }

    @Test
    void testGetById() {
        when(creditCardRepository.findById(id)).thenReturn(Optional.of(creditCard));
        CreditCardResponseDto response = creditCardService.getById(id);
        assertEquals("1234567890123456", response.getCcn());
    }

    @Test
    void testCreate() {
        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCard);
        CreditCardResponseDto response = creditCardService.create(creditCardRequestDto);
        assertEquals("1234567890123456", response.getCcn());
    }

    @Test
    void testUpdate() {
        when(creditCardRepository.findById(id)).thenReturn(Optional.of(creditCard));
        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCard);
        CreditCardResponseDto response = creditCardService.update(creditCardRequestDto);
        assertEquals("1234567890123456", response.getCcn());
    }

    @Test
    void testDelete() {
        doNothing().when(creditCardRepository).deleteById(id);
        CreditCardResponseDto response = creditCardService.delete(id);
        assertEquals(id, response.getId());
    }
}