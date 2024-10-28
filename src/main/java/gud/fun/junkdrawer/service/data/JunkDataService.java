package gud.fun.junkdrawer.service.data;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface JunkDataService<REQ,RESP,ENT> {
    RESP create(REQ dto);
    RESP getById(UUID id);
    PagedModel<RESP> getAll(Pageable pageable);
    RESP update(REQ dto);
    RESP delete(UUID id);
    RESP toResponseDTO(ENT entity);
    ENT toEntity(REQ dto);
}