package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.assembler.CreditCardResponseDtoAssembler;
import gud.fun.junkdrawer.dto.transaction.CreditCardRequestDto;
import gud.fun.junkdrawer.dto.transaction.CreditCardResponseDto;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import gud.fun.junkdrawer.persistance.repository.CreditCardRepository;
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

class CreditCardServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    private BicService bicService;

    @InjectMocks
    private CreditCardService creditCardService;

    @Mock
    private CreditCardResponseDtoAssembler creditCardDtoAssembler;

    @Mock
    private PagedResourcesAssembler<CreditCard> pagedResourcesAssembler;

    private CreditCard creditCard;
    private CreditCardRequestDto creditCardRequestDto;
    private CreditCardResponseDto creditCardResponseDto;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        creditCard = new CreditCard(id, "1234567890123456", "Issuer", null);
        creditCardRequestDto = new CreditCardRequestDto(id, "1234567890123456", "Issuer", null);
        creditCardResponseDto = new CreditCardResponseDto(id, "1234567890123456", "Issuer", null);
    }

    @Test
    void testGetAll() {
        when(creditCardRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(creditCard)));
        when(pagedResourcesAssembler.toModel(any(Page.class), any(CreditCardResponseDtoAssembler.class))).thenReturn(PagedModel.of(List.of(creditCardResponseDto), new PagedModel.PageMetadata(1, 1, 1, 1)));

        PagedModel<CreditCardResponseDto> creditCards = creditCardService.getAll(PageRequest.of(0, 1));
        CreditCardResponseDto creditCardResponseDto = creditCards.getContent().iterator().next();
        assertEquals(1, creditCards.getMetadata().getTotalElements());
        assertEquals("1234567890123456", creditCardResponseDto.getCcn());
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
        creditCardRequestDto.setId(null);
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