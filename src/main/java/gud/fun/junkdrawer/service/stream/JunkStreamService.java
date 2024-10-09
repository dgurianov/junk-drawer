package gud.fun.junkdrawer.service.stream;

import java.util.List;

public interface JunkStreamService<REQ,RESP> {
    List<RESP> getAllStream();
}