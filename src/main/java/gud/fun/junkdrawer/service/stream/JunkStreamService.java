package gud.fun.junkdrawer.service.stream;

import java.util.List;

public interface JunkStreamService<RESP> {
    List<RESP> getAllStream(int limit);
}