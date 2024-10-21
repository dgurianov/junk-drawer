package gud.fun.junkdrawer.persistance.converter;

import gud.fun.junkdrawer.persistance.model.TransactionState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionState, String> {
    @Override
    public String convertToDatabaseColumn(TransactionState transactionState) {
        if (transactionState == null) {
            return null;
        }
        return transactionState.getId() + transactionState.name();
    }

    @Override
    public TransactionState convertToEntityAttribute(String s) {
       if(s == null) return TransactionState.UNKNOWN;

       switch (s){
           case "0NEW" -> {
               return TransactionState.NEW;
           }
           case "1PRE_AUTH" -> {
               return TransactionState.PRE_AUTH;
           }
           case "2AUTH" -> {
               return TransactionState.AUTH;
           }
           case "3CAPTURE" -> {
               return TransactionState.CAPTURE;
           }
           case "4PURCHASE" -> {
               return TransactionState.PURCHASE;
           }
           case "5REFUND" -> {
               return TransactionState.REFUND;
           }
           case "6VOID" -> {
               return TransactionState.VOID;
           }
           case "7CHARGEBACK" -> {
               return TransactionState.CHARGEBACK;
           }
           case "8SETTLEMENT" -> {
               return TransactionState.SETTLEMENT;
           }
           case "9COMPLETE" -> {
               return TransactionState.COMPLETE;
           }
           case "999UNKNOWN" -> {
               return TransactionState.UNKNOWN;
           }
           default -> {
               return TransactionState.UNKNOWN;
           }
       }
    }
}
