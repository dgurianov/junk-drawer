package gud.fun.junkdrawer.persistance.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum TransactionEntryType {
    MANUAL,
    CONTACTLESS,
    POS
    ;

    public static TransactionEntryType getById(int id) {
        List<TransactionEntryType> values = Arrays.asList(values());
        return Objects.isNull(values.get(id))? MANUAL : values.get(id);
    }
}
