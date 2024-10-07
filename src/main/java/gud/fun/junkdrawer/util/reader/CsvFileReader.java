package gud.fun.junkdrawer.util.reader;

import java.util.List;

public interface CsvFileReader<T> extends FileReader<T> {
    @Override
    public List<T> read();
}
