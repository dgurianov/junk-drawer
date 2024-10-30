package gud.fun.junkdrawer.service.stream;

import gud.fun.junkdrawer.dto.transaction.BicResponseDto;
import gud.fun.junkdrawer.dto.transaction.CreditCardResponseDto;
import gud.fun.junkdrawer.dto.transaction.MerchantResponseDto;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.persistance.model.Transaction;
import gud.fun.junkdrawer.util.generator.BicGenerator;
import gud.fun.junkdrawer.util.generator.CountryGenerator;
import gud.fun.junkdrawer.util.generator.MerchantGenerator;
import gud.fun.junkdrawer.util.generator.TransactionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionStreamService implements JunkStreamService<TransactionResponseDto> {

    @Autowired
    private TransactionGenerator transactionGenerator;

    @Autowired
    private MerchantGenerator merchantGenerator;

    @Autowired
    private BicGenerator bicGenerator;

    @Autowired
    private CountryGenerator countryGenerator;


    private int limit;


    private List<TransactionResponseDto> responseDtos = new ArrayList<>();
    private MerchantResponseDto merchantResponseDto;
    private BicResponseDto bicResponseDto;
    private CreditCardResponseDto creditCardResponseDto;
    private Transaction transaction;

    @Override
    public List<TransactionResponseDto> getAllStream(int limit) {
        if(responseDtos.size() > 0) responseDtos.clear();
        if (limit > 100) limit = 100;
        while(limit > 0) {
            transaction = transactionGenerator.generateRandom();

            bicResponseDto = BicResponseDto.builder()
                    .id(transaction.getCreditCard().getBic().getId())
                    .value(transaction.getCreditCard().getBic().getIdentifier())
                    .institution(transaction.getCreditCard().getBic().getInstitution())
                    .build();

            creditCardResponseDto = CreditCardResponseDto.builder()
                    .id(transaction.getCreditCard().getId())
                    .Ccn(transaction.getCreditCard().getCcn())
                    .bic(bicResponseDto)
                    .issuer(transaction.getCreditCard().getIssuer())
                    .build();

            merchantResponseDto = MerchantResponseDto.builder()
                            .id(transaction.getMerchant().getId())
                            .name(transaction.getMerchant().getName())
                            .countryCode(transaction.getMerchant().getCountry().getAlpha3())
                            .build();

            responseDtos.add(
                    TransactionResponseDto.builder()
                            .id(transaction.getId())
                            .correlationId(transaction.getCorrelationId())
                            .amount(transaction.getAmount())
                            .state(transaction.getState())
                            .type(transaction.getType())
                            .entryType(transaction.getEntryType())
                            .dateTime(transaction.getDateTime())
                            .currency(transaction.getCurrency())
                            .merchant(merchantResponseDto)
                            .creditCard(creditCardResponseDto)
                            .build()
            );
            limit--;
        }

        return responseDtos;
    }


}
