package gud.fun.junkdrawer.persistance.model.transaction;

import org.aspectj.apache.bcel.classfile.Unknown;

import java.util.Objects;

public enum TransactionType {
    PRE_AUTH("Pre-authorisation",1),
    AUTH("Authorization",2),
    CAPTURE("Capture",3),
    PURCHASE("Purchase",4),
    REFUND("Refund",5),
    VOID("Void",6),
    CHARGEBACK("Chargeback",7),
    SETTLEMENT("Settlement",8),
    UNKNOWN("UNKNOWN",999)
    ;

    TransactionType(String value,  int weight) {
        this.value = value;
        this.weight = weight;
    }

    private String value;
    private int weight;

    public String getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}
