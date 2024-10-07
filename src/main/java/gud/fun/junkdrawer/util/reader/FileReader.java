package gud.fun.junkdrawer.util.reader;

import java.io.IOException;
import java.util.List;

public interface FileReader<T> {
    public List<T> read() throws IOException;
}
