package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.transaction.TransactionRequestDto;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.persistance.model.Transaction;
import gud.fun.junkdrawer.persistance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService implements JunkDataService<TransactionRequestDto, TransactionResponseDto, Transaction> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CreditCardService creditCardService;

    @Override
    public List<TransactionResponseDto> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponseDto getById(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found for id: " + id));
        return toResponseDTO(transaction);
    }

    @Override
    public TransactionResponseDto create(TransactionRequestDto transactionDto) {
        Transaction transaction = toEntity(transactionDto);
        transaction = transactionRepository.save(transaction);
        return toResponseDTO(transaction);
    }

    @Override
    public TransactionResponseDto update(TransactionRequestDto dto) {
        Transaction transaction = transactionRepository.findById(UUID.fromString(dto.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found for id: " + dto.getId()));
        transaction.setDateTime(Objects.isNull(dto.getDateTime()) ? transaction.getDateTime(): dto.getDateTime() );
        transaction.setEntryType(Objects.isNull(dto.getEntryType())? transaction.getEntryType() : dto.getEntryType());
        transaction.setType(Objects.isNull(dto.getType())? transaction.getType() : dto.getType());
        transaction.setMerchant(Objects.isNull(dto.getMerchant())? transaction.getMerchant(): merchantService.toEntity(dto.getMerchant()));
        transaction.setAmount(Objects.isNull(dto.getAmount())? transaction.getAmount() : dto.getAmount());
        transaction.setCurrency(Objects.isNull(dto.getCurrency())? transaction.getCurrency(): dto.getCurrency());
        transaction.setCreditCard(Objects.isNull(dto.getCreditCard())? transaction.getCreditCard(): creditCardService.toEntity(dto.getCreditCard()));

        transaction = transactionRepository.save(transaction);
        return toResponseDTO(transaction);
    }

    @Override
    public TransactionResponseDto delete(UUID id) {
        transactionRepository.deleteById(id);
        TransactionResponseDto response = new TransactionResponseDto();
        response.setId(id.toString());
        return response;
    }

    @Override
    public TransactionResponseDto toResponseDTO(Transaction transaction) {
        TransactionResponseDto response = new TransactionResponseDto();
        response.setId(transaction.getId().toString());
        response.setDateTime(transaction.getDateTime());
        response.setType(transaction.getType());
        response.setEntryType(transaction.getEntryType());
        response.setMerchant(merchantService.toResponseDTO(transaction.getMerchant()));
        response.setAmount(transaction.getAmount());
        response.setCurrency(transaction.getCurrency());
        response.setCreditCard(creditCardService.toResponseDTO(transaction.getCreditCard()));
        return response;
    }

    @Override
    public Transaction toEntity(TransactionRequestDto dto) {
        Transaction entity = new Transaction();
        entity.setId(dto.getId() != null ? UUID.fromString(dto.getId()) : null);
        entity.setAmount(dto.getAmount());
        entity.setDateTime(dto.getDateTime() !=null  ? dto.getDateTime() : new Date());
        entity.setType(dto.getType());
        entity.setType(dto.getType());
        entity.setEntryType(dto.getEntryType());
        entity.setMerchant(merchantService.toEntity(dto.getMerchant()));
        entity.setAmount(dto.getAmount());
        entity.setCurrency(dto.getCurrency());
        entity.setCreditCard(creditCardService.toEntity(dto.getCreditCard()));
        return entity;
    }
}
