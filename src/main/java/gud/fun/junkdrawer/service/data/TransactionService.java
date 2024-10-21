package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.transaction.TransactionRequestDto;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.persistance.model.Transaction;
import gud.fun.junkdrawer.persistance.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
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
        log.debug("Create was called  from Transaction service , redirecting to update.");
        return update(transactionDto);
    }

    @Override
    public TransactionResponseDto update(TransactionRequestDto dto) {
        Transaction transaction = new Transaction();
        if(dto.getId() != null) {
            log.debug("Id {} received in Transaction request. Fetching Transaction from repository", dto.getId());
            transaction = transactionRepository.findById(dto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Transaction not found for id: " + dto.getId()));
            transaction.setDateTime(Objects.isNull(dto.getDateTime()) ? transaction.getDateTime(): dto.getDateTime() );
        }else{
            log.debug("No Id for the Transaction received. Transaction will be a new entity.");
            //If the line below is  removed , then next exception arise, while save : detached entity passed to persist. It does not clear why it is happening
            transaction.setId(UUID.randomUUID());
            transaction.setDateTime(new Date());
        }
        transaction.setEntryType(dto.getEntryType());
        transaction.setState(dto.getState());
        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setCurrency(dto.getCurrency());

        transaction.setMerchant(merchantService.toEntity(dto.getMerchant()));
        transaction.setCreditCard(creditCardService.toEntity(dto.getCreditCard()));

        return toResponseDTO(transactionRepository.save(transaction));
    }

    @Override
    public TransactionResponseDto delete(UUID id) {
        transactionRepository.deleteById(id);
        TransactionResponseDto response = new TransactionResponseDto();
        response.setId(id);
        return response;
    }

    @Override
    public TransactionResponseDto toResponseDTO(Transaction transaction) {
        TransactionResponseDto response = new TransactionResponseDto();
        response.setId(transaction.getId());
        response.setDateTime(transaction.getDateTime());
        response.setState(transaction.getState());
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
        entity.setId(dto.getId() != null ? dto.getId(): null);
        entity.setAmount(dto.getAmount());
        entity.setDateTime(dto.getDateTime() !=null  ? dto.getDateTime() : new Date());
        entity.setType(dto.getType());
        entity.setState(dto.getState());
        log.info("Transaction Entry State: {}", dto.getState());
        entity.setEntryType(dto.getEntryType());
        entity.setMerchant(merchantService.toEntity(dto.getMerchant()));
        entity.setAmount(dto.getAmount());
        entity.setCurrency(dto.getCurrency());
        entity.setCreditCard(creditCardService.toEntity(dto.getCreditCard()));
        return entity;
    }
}
