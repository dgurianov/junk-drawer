package gud.fun.junkdrawer.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface JunkDataController<REQ,RESP> {

    ResponseEntity<RESP> getOneById(UUID id) ;
    ResponseEntity<PagedModel<RESP>> getAll(Pageable pageable) ;
    ResponseEntity<RESP> createOrUpdate(REQ dto) ;
    ResponseEntity<RESP> delete(UUID id) ;

}
