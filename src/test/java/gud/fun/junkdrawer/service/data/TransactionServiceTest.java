package gud.fun.junkdrawer.service.data;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.dto.assembler.TransactionResponseDtoAssembler;
import gud.fun.junkdrawer.dto.transaction.BicRequestDto;
import gud.fun.junkdrawer.dto.transaction.BicResponseDto;
import gud.fun.junkdrawer.dto.transaction.CreditCardRequestDto;
import gud.fun.junkdrawer.dto.transaction.CreditCardResponseDto;
import gud.fun.junkdrawer.dto.transaction.MerchantRequestDto;
import gud.fun.junkdrawer.dto.transaction.MerchantResponseDto;
import gud.fun.junkdrawer.dto.transaction.TransactionRequestDto;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.persistance.model.Bic;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import gud.fun.junkdrawer.persistance.model.Merchant;
import gud.fun.junkdrawer.persistance.model.Transaction;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import gud.fun.junkdrawer.persistance.model.TransactionState;
import gud.fun.junkdrawer.persistance.model.TransactionType;
import gud.fun.junkdrawer.persistance.repository.TransactionRepository;
import org.joda.money.BigMoney;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private MerchantService merchantService;

    @Mock
    private CreditCardService creditCardService;

    @Mock
    private TransactionResponseDtoAssembler transactionDtoAssembler;

    @Mock
    private PagedResourcesAssembler<Transaction> pagedResourcesAssembler;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction;
    private TransactionRequestDto transactionRequestDto;
    private TransactionResponseDto transactionResponseDto;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        Bic bic = new Bic(id, "BicCode", "BicName");
        BicRequestDto bicDto = new BicRequestDto(
                id,
                "BicCode",
                "BicName");
        BicResponseDto bicResponseDto = new BicResponseDto(
                id,
                "BicCode",
                "BicName");
        CreditCard cc = new CreditCard(id, "Issuer", "CCN", bic);
        CreditCardRequestDto ccDto = new CreditCardRequestDto(
                id,
                "Issuer",
                "CCN",
                bicDto);
        CreditCardResponseDto ccResponseDto = new CreditCardResponseDto(
                id,
                "Issuer",
                "CCN",
                bicResponseDto);
        Merchant merchant =  new Merchant(id, "Merchant name " ,CountryCode.US, "MerchantCountry");
        MerchantResponseDto merchantResponseDto = new MerchantResponseDto(
                id,
                "Merchant name",
                CountryCode.US.getAlpha3(),
                "MerchantCountry");
        MerchantRequestDto merchantDto = new MerchantRequestDto(
                id,
                "Merchant name",
                CountryCode.US.getAlpha3(),
                "MerchantCountry");
        transaction = new Transaction(
                id,
                UUID.randomUUID(),
                new Date(),
                TransactionEntryType.MANUAL,
                TransactionState.AUTH,
                TransactionType.CREDIT,
                merchant,
                BigMoney.parse("USD 100").getAmount(),
                "USD",
                 cc);
        transactionRequestDto = new TransactionRequestDto(
                id,
                UUID.randomUUID(),
                new Date(),
                TransactionEntryType.MANUAL,
                TransactionState.AUTH,
                TransactionType.CREDIT,
                merchantDto,
                BigMoney.parse("USD 100").getAmount(),
            "USD",
            ccDto);
        transactionResponseDto = new TransactionResponseDto(
                id,
                UUID.randomUUID(),
                new Date(),
                TransactionEntryType.MANUAL,
                TransactionState.AUTH,
                TransactionType.CREDIT,
                merchantResponseDto,
                BigMoney.parse("USD 100").getAmount(),
                "USD",
                ccResponseDto);
    }

    @Test
    void testGetAll() {
        when(transactionRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(transaction)));
        when(pagedResourcesAssembler.toModel(any(Page.class), any(TransactionResponseDtoAssembler.class))).thenReturn(PagedModel.of(List.of(transactionResponseDto), new PagedModel.PageMetadata(1, 1, 1, 1)));
        PagedModel<TransactionResponseDto> transactions = transactionService.getAll(PageRequest.of(0, 1));
        TransactionResponseDto response = transactions.getContent().iterator().next();
        assertEquals(1, transactions.getMetadata().getTotalElements());
        assertEquals(TransactionState.AUTH, response.getState());
    }

    @Test
    void testGetById() {
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        TransactionResponseDto response = transactionService.getById(id);
        assertEquals(TransactionState.AUTH, response.getState());
    }

    @Test
    void testCreate() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        transactionRequestDto.setId(null);
        TransactionResponseDto response = transactionService.create(transactionRequestDto);
        assertEquals(TransactionState.AUTH, response.getState());
    }

    @Test
    void testUpdate() {
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        TransactionResponseDto response = transactionService.update(transactionRequestDto);
        assertEquals(TransactionState.AUTH, response.getState());
    }

    @Test
    void testDelete() {
        doNothing().when(transactionRepository).deleteById(id);
        TransactionResponseDto response = transactionService.delete(id);
        assertEquals(id, response.getId());
    }
}
