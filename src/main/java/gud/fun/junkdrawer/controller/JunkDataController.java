package gud.fun.junkdrawer.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface JunkDataController<REQ,RESP> {

     ResponseEntity<RESP> getOneById(UUID id) ;
    ResponseEntity<List<RESP>> getAll() ;
    ResponseEntity<RESP> createOrUpdate(REQ dto) ;
    ResponseEntity<RESP> delete(UUID id) ;

}
