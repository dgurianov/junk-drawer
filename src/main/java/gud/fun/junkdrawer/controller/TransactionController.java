package gud.fun.junkdrawer.controller;


import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.transaction.TransactionRequestDto;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.service.data.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Endpoints.TRANSACTION)
public class TransactionController implements JunkDataController<TransactionRequestDto, TransactionResponseDto> {

    @Autowired
    private TransactionService transactionService;

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<PagedModel<TransactionResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(transactionService.getAll(pageable));
    }

    @GetMapping(value = "/chain/{id}", produces = "application/json")
    public ResponseEntity<List<TransactionResponseDto>> getAllByCorrelationId(@PathVariable UUID id) {
        return ResponseEntity.ok(transactionService.getAllByCorrelationId(id));
    }

    @Override
    @GetMapping(value = "/one/{id}",produces = "application/json")
    public ResponseEntity<TransactionResponseDto> getOneById(@PathVariable UUID id) {
        return ResponseEntity.ok(transactionService.getById(id));
    }

    @Override
    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<TransactionResponseDto> createOrUpdate (@RequestBody @Valid TransactionRequestDto transactionDto) {
        return ResponseEntity.ok(transactionService.update(transactionDto));
    }

    @Override
    @DeleteMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<TransactionResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(transactionService.delete(id));
    }
}
