package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.transaction.CreditCardRequestDto;
import gud.fun.junkdrawer.dto.transaction.CreditCardResponseDto;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import gud.fun.junkdrawer.persistance.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreditCardService implements JunkDataService<CreditCardRequestDto, CreditCardResponseDto, CreditCard> {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private BicService bicService;

    @Override
    public List<CreditCardResponseDto> getAll() {
        List<CreditCard> creditCards = creditCardRepository.findAll();
        return creditCards.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreditCardResponseDto getById(UUID id) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Credit card not found for id: " + id));
        return toResponseDTO(creditCard);
    }

    @Override
    public CreditCardResponseDto create(CreditCardRequestDto creditCardDto) {
        CreditCard creditCard = toEntity(creditCardDto);
        creditCard = creditCardRepository.save(creditCard);
        return toResponseDTO(creditCard);
    }

    @Override
    public CreditCardResponseDto update(UUID id, CreditCardRequestDto creditCardDto) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Credit card not found for id: " + id));
        creditCard.setCcn(creditCardDto.getCcn());
        creditCard.setIssuer(creditCardDto.getIssuer());
        creditCard = creditCardRepository.save(creditCard);
        return toResponseDTO(creditCard);
    }

    @Override
    public CreditCardResponseDto delete(UUID id) {
        creditCardRepository.deleteById(id);
        CreditCardResponseDto response = new CreditCardResponseDto();
        response.setId(id.toString());
        return response;
    }

    @Override
    public CreditCardResponseDto toResponseDTO(CreditCard creditCard) {
        return new CreditCardResponseDto(
                creditCard.getId().toString(),
                creditCard.getCcn(),
                creditCard.getIssuer(),
                bicService.toResponseDTO(creditCard.getBic())

        );
    }

    @Override
    public CreditCard toEntity(CreditCardRequestDto dto) {
        return new CreditCard(
                dto.getId() != null ? UUID.fromString(dto.getId()) : null,
                dto.getCcn(),
                dto.getIssuer(),
                bicService.toEntity(dto.getBic())
        );
    }
}