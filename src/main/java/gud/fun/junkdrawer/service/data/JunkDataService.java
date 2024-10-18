package gud.fun.junkdrawer.service.data;

import java.util.List;
import java.util.UUID;

public interface JunkDataService<REQ,NEWREQ,RESP,ENT> {
    RESP create(NEWREQ dto);

    RESP getById(UUID id);

    List<RESP> getAll();

    RESP update(REQ dto);

    RESP delete(UUID id);

    RESP toResponseDTO(ENT entity);

    ENT toEntity(REQ dto);

    /*
    *  This method is used to convert a new request dto to an entity
     */
    ENT newToEntity(NEWREQ dto);

    /*
    *  This method is used to convert a new request dto to a response dto
     */
    RESP newToResponseDto(NEWREQ dto);
}