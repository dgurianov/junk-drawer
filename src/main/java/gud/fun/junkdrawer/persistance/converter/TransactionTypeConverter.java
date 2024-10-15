package gud.fun.junkdrawer.persistance.converter;

import gud.fun.junkdrawer.persistance.model.TransactionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
    @Override
    public String convertToDatabaseColumn(TransactionType transactionType) {
        if (transactionType == null) {
            return null;
        }
        return transactionType.getOrder() + transactionType.name();
    }

    @Override
    public TransactionType convertToEntityAttribute(String s) {
       if(s == null) return TransactionType.UNKNOWN;

       switch (s){
           case "1PRE_AUTH" -> {
               return TransactionType.PRE_AUTH;
           }
           case "2AUTH" -> {
               return TransactionType.AUTH;
           }
           case "3CAPTURE" -> {
               return TransactionType.CAPTURE;
           }
           case "4PURCHASE" -> {
               return TransactionType.PURCHASE;
           }
           case "5REFUND" -> {
               return TransactionType.REFUND;
           }
           case "6VOID" -> {
               return TransactionType.VOID;
           }
           case "7CHARGEBACK" -> {
               return TransactionType.CHARGEBACK;
           }
           case "8SETTLEMENT" -> {
               return TransactionType.SETTLEMENT;
           }
           case "999UNKNOWN" -> {
               return TransactionType.UNKNOWN;
           }
           default -> {
               return TransactionType.UNKNOWN;
           }
       }
    }
}
