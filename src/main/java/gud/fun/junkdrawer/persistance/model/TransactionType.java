package gud.fun.junkdrawer.persistance.model;

public enum TransactionType{
    NEW("New",0),
    PRE_AUTH("Pre-authorisation",1),
    AUTH("Authorization",2),
    CAPTURE("Capture",3),
    PURCHASE("Purchase",4),
    REFUND("Refund",5),
    VOID("Void",6),
    CHARGEBACK("Chargeback",7),
    SETTLEMENT("Settlement",8),
    COMPLETE("Complete",9),
    UNKNOWN("UNKNOWN",999)
    ;

    TransactionType(String value,  int order) {
        this.value = value;
        this.order = order;
    }

    private String value;
    private int order;

    public String getValue() {
        return value;
    }

    public int getOrder() {
        return order;
    }
}
