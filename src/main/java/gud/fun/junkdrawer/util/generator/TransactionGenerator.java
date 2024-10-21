package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import gud.fun.junkdrawer.persistance.model.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.BigMoney;
import gud.fun.junkdrawer.persistance.model.Transaction;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import gud.fun.junkdrawer.persistance.model.TransactionState;
import gud.fun.junkdrawer.persistance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class TransactionGenerator implements JunkDataGenerator<Transaction, CurrencyCode> {

    Random random = new Random();

//    List<TransactionEntryType> transactionEntryTypes = Arrays.asList(TransactionEntryType.values());

    @Autowired
    CurrencyGenerator currencyGenerator;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    MerchantGenerator merchantGenerator;

    @Autowired
    CreditCardGenerator creditCardGenerator;

    @Override
    public Transaction generateRandom() {
        CountryCode cc = getRandomCountryCode();

        Transaction transaction = new Transaction();
        transaction.setAmount(BigMoney.parse(cc.getCurrency().getCurrencyCode() + " " +random.nextInt(1000) + "." + random.nextInt(100)).getAmount());
        transaction.setCurrency(currencyGenerator.generateRandomAsStringByCriteria(cc));
        transaction.setState(TransactionState.getById(random.nextInt(10)));
        log.info("Transaction PARENT state: " + transaction.getState());
        switch (transaction.getState()){
            case VOID, REFUND, CHARGEBACK:
                transaction.setType(TransactionType.CREDIT);
                break;
            default:
                transaction.setType(TransactionType.DEBIT);
                break;
        }
        transaction.setEntryType(TransactionEntryType.getById(random.nextInt(3)));
        transaction.setDateTime(new Date());
        transaction.setMerchant(merchantGenerator.generateRandomByCriteria(cc));
        transaction.setCreditCard(creditCardGenerator.generateRandomByCriteria(cc));


        /*Has to be reworker. SO far  it require to change jpa mapping between  transaction to credit card  and so on.
        Populate history chain  before returning*/
        populateTransactionHistory(transaction);
        return transaction;

    }

    @Override
    public Transaction generateRandomByCriteria(CurrencyCode criteria) {
        return null;
    }

    @Override
    public String generateRandomAsString() {
        return "";
    }

    @Override
    public String generateRandomAsStringByCriteria(CurrencyCode criteria) {
        return "";
    }


    private CountryCode getRandomCountryCode(){
        CountryCode cc = null;
        while(cc == null){
            cc = CountryCode.getByCode(random.nextInt(999));
        }
        return cc;
    }

    private void populateTransactionHistory(Transaction parent){
        int order = parent.getState().getId();
        if(order > 10) return; //Do not act on UNKNOWN type

        while(order-- > 0){  //Decrement immediately, cause order transaction was created before the call of this method
            Transaction transaction = new Transaction();
            transaction.setDateTime(parent.getDateTime()); //TODO: Move date time to the past
            transaction.setAmount(parent.getAmount());
            transaction.setCurrency(parent.getCurrency());
            transaction.setState(TransactionState.getById(order));
            log.info("Transaction CHILD state: " + transaction.getState());
            switch (transaction.getState()){
                case VOID, REFUND, CHARGEBACK:
                    transaction.setType(TransactionType.CREDIT);
                    break;
                default:
                    transaction.setType(TransactionType.DEBIT);
                    break;
            }
            transaction.setEntryType(parent.getEntryType());
            transaction.setMerchant(parent.getMerchant());
            transaction.setCreditCard(parent.getCreditCard());

            transactionRepository.save(transaction);
        }


    }
}
