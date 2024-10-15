package gud.fun.junkdrawer.service.data;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.dto.transaction.BicRequestDto;
import gud.fun.junkdrawer.dto.transaction.CreditCardRequestDto;
import gud.fun.junkdrawer.dto.transaction.MerchantRequestDto;
import gud.fun.junkdrawer.dto.transaction.TransactionRequestDto;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.persistance.model.Bic;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import gud.fun.junkdrawer.persistance.model.Merchant;
import gud.fun.junkdrawer.persistance.model.Transaction;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import gud.fun.junkdrawer.persistance.model.TransactionType;
import gud.fun.junkdrawer.persistance.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private MerchantService merchantService;

    @Mock
    private CreditCardService creditCardService;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction;
    private TransactionRequestDto transactionRequestDto;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        Bic bic = new Bic(id, "BicCode", "BicName");
        BicRequestDto bicDto = new BicRequestDto(
                id.toString(),
                "BicCode",
                "BicName");
        CreditCard cc = new CreditCard(id, "Issuer", "CCN", bic);
        CreditCardRequestDto ccDto = new CreditCardRequestDto(
                id.toString(),
                "Issuer",
                "CCN",
                bicDto);
        Merchant merchant =  new Merchant(id, "Merchant name " ,CountryCode.US, "MerchantCountry");
        MerchantRequestDto merchantDto = new MerchantRequestDto(
                id.toString(),
                "Merchant name",
                CountryCode.US.getAlpha3(),
                "MerchantCountry");
        transaction = new Transaction(
                id,
                new Date(),
                TransactionEntryType.MANUAL,
                TransactionType.AUTH,
                merchant,
                100L,
                "USD",
                 cc);
        transactionRequestDto = new TransactionRequestDto(
                id.toString(),
                new Date(),
                TransactionEntryType.MANUAL,
                TransactionType.AUTH,
                merchantDto,
                100L,
            "USD",
            ccDto);
    }

    @Test
    void testGetAll() {
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction));
        List<TransactionResponseDto> transactions = transactionService.getAll();
        assertEquals(1, transactions.size());
        assertEquals(TransactionType.AUTH, transactions.get(0).getType());
    }

    @Test
    void testGetById() {
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        TransactionResponseDto response = transactionService.getById(id);
        assertEquals(TransactionType.AUTH, response.getType());
    }

    @Test
    void testCreate() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        TransactionResponseDto response = transactionService.create(transactionRequestDto);
        assertEquals(TransactionType.AUTH, response.getType());
    }

    @Test
    void testUpdate() {
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        TransactionResponseDto response = transactionService.update(id, transactionRequestDto);
        assertEquals(TransactionType.AUTH, response.getType());
    }

    @Test
    void testDelete() {
        doNothing().when(transactionRepository).deleteById(id);
        TransactionResponseDto response = transactionService.delete(id);
        assertEquals(id.toString(), response.getId());
    }
}
