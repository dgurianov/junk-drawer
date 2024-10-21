package gud.fun.junkdrawer.persistance.model;

public enum TransactionState {
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

    TransactionState(String value, int id) {
        this.value = value;
        this.id = id;
    }

    private String value;
    private int id;

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public static TransactionState getById(int id) {
        for(TransactionState e : values()) {
            if(e.id == id) return e;
        }
        return UNKNOWN;
    }

}
