package gud.fun.junkdrawer.controller;


import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.transaction.TransactionRequestDto;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.service.data.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Endpoints.TRANSACTION)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        List<TransactionResponseDto> transactions = transactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<TransactionResponseDto> getTransactionById(@PathVariable UUID id) {
        TransactionResponseDto transaction = transactionService.getById(id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionRequestDto transactionDto) {
        TransactionResponseDto createdTransaction = transactionService.create(transactionDto);
        return ResponseEntity.ok(createdTransaction);
    }

    @PutMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<TransactionResponseDto> updateTransaction(@PathVariable UUID id, @RequestBody TransactionRequestDto transactionDto) {
        TransactionResponseDto updatedTransaction = transactionService.update(id, transactionDto);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<TransactionResponseDto> deleteTransaction(@PathVariable UUID id) {
        TransactionResponseDto deletedTransaction = transactionService.delete(id);
        return ResponseEntity.ok(deletedTransaction);
    }
}
