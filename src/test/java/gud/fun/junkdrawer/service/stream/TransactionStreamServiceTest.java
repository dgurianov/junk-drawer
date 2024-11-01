package gud.fun.junkdrawer.service.stream;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.persistance.model.Bic;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import gud.fun.junkdrawer.persistance.model.Merchant;
import gud.fun.junkdrawer.persistance.model.Transaction;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import gud.fun.junkdrawer.persistance.model.TransactionState;
import gud.fun.junkdrawer.persistance.model.TransactionType;
import gud.fun.junkdrawer.util.generator.TransactionGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class TransactionStreamServiceTest {

    @Mock
    private TransactionGenerator transactionGenerator;

    @Autowired
    @InjectMocks
    private TransactionStreamService transactionStreamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllStream() {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setCorrelationId(UUID.randomUUID());
        transaction.setAmount(new BigDecimal(100));
        transaction.setState(TransactionState.COMPLETE);
        transaction.setType(TransactionType.CREDIT);
        transaction.setEntryType(TransactionEntryType.MANUAL);
        transaction.setDateTime(new Date());
        transaction.setCurrency("USD");
        Merchant merchant = new Merchant();
        merchant.setId(UUID.randomUUID());
        merchant.setName("Test Merchant");
        merchant.setCountry(CountryCode.US);
        transaction.setMerchant(merchant);
        CreditCard creditCard = new CreditCard();
        creditCard.setId(UUID.randomUUID());
        Bic bic = new Bic();
        bic.setId(UUID.randomUUID());
        creditCard.setBic(bic);
        transaction.setCreditCard(creditCard);


        when(transactionGenerator.generateRandom()).thenReturn(transaction);

        List<TransactionResponseDto> result = transactionStreamService.getAllStream(5);

        assertEquals(5, result.size());
        for (TransactionResponseDto dto : result) {
            assertEquals(transaction.getId(), dto.getId());
            assertEquals(transaction.getCorrelationId(), dto.getCorrelationId());
            assertEquals(transaction.getAmount(), dto.getAmount());
            assertEquals(transaction.getState(), dto.getState());
            assertEquals(transaction.getType(), dto.getType());
            assertEquals(transaction.getEntryType(), dto.getEntryType());
            assertEquals(transaction.getDateTime(), dto.getDateTime());
            assertEquals(transaction.getCurrency(), dto.getCurrency());
        }
    }

    @Test
    void testGetAllStreamWithLimitZero() {
        List<TransactionResponseDto> result = transactionStreamService.getAllStream(0);
        assertEquals(0, result.size());
    }
}