package gud.fun.junkdrawer.controller;


import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.transaction.TransactionRequestDto;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.service.data.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Endpoints.TRANSACTION)
public class TransactionController implements JunkDataController<TransactionRequestDto, TransactionResponseDto> {

    @Autowired
    private TransactionService transactionService;

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TransactionResponseDto>> getAll() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @Override
    @GetMapping(value = "/{id}",produces = "application/json")
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
