package gud.fun.junkdrawer.service.data;

import java.util.List;

public interface JunkDataService<REQ,RESP> {
    RESP create(REQ dto);
    RESP getById(Long id);
    List<RESP> getAll();
    RESP update(Long id, REQ dto);
    RESP delete(Long id);
}