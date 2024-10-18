package gud.fun.junkdrawer.service.data;

import java.util.List;
import java.util.UUID;

public interface JunkDataService<REQ,RESP,ENT> {
    RESP create(REQ dto);
    RESP getById(UUID id);
    List<RESP> getAll();
    RESP update(REQ dto);
    RESP delete(UUID id);
    RESP toResponseDTO(ENT entity);
    ENT toEntity(REQ dto);
}