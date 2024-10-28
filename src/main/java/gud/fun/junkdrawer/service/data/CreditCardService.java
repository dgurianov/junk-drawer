package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.assembler.CreditCardResponseDtoAssembler;
import gud.fun.junkdrawer.dto.transaction.CreditCardRequestDto;
import gud.fun.junkdrawer.dto.transaction.CreditCardResponseDto;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import gud.fun.junkdrawer.persistance.repository.CreditCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CreditCardService implements JunkDataService<CreditCardRequestDto, CreditCardResponseDto, CreditCard> {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private BicService bicService;

    @Autowired
    private CreditCardResponseDtoAssembler creditCardResponseDtoAssembler;

    @Autowired
    private PagedResourcesAssembler<CreditCard> pagedResourcesAssembler;

    @Override
    public PagedModel<CreditCardResponseDto> getAll(Pageable pageable) {
        return pagedResourcesAssembler.toModel(creditCardRepository.findAll(pageable), creditCardResponseDtoAssembler);
    }

    @Override
    public CreditCardResponseDto getById(UUID id) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Credit card not found for id: " + id));
        return toResponseDTO(creditCard);
    }

    @Override
    public CreditCardResponseDto create(CreditCardRequestDto creditCardDto) {
        log.debug("Create was called  from CreditCard service , redirecting to update.");
        return update(creditCardDto);
    }

    @Override
    public CreditCardResponseDto update(CreditCardRequestDto creditCardDto) {
        CreditCard creditCard = new CreditCard();
        if(creditCardDto.getId() != null) {
            log.debug("Id {} received in CreditCard request. Fetching CreditCard from repository", creditCardDto.getId());
            creditCard = creditCardRepository.findById(creditCardDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Credit card not found for id: " + creditCardDto.getId()));
        }else{
            log.debug("No Id for the CreditCard received. CreditCard will be a new entity.");
        }
        creditCard.setCcn(creditCardDto.getCcn());
        creditCard.setIssuer(creditCardDto.getIssuer());
        creditCard.setBic(bicService.toEntity(creditCardDto.getBic()));
        return toResponseDTO(creditCardRepository.save(creditCard));
    }

    @Override
    public CreditCardResponseDto delete(UUID id) {
        creditCardRepository.deleteById(id);
        CreditCardResponseDto response = new CreditCardResponseDto();
        response.setId(id);
        return response;
    }

    @Override
    public CreditCardResponseDto toResponseDTO(CreditCard creditCard) {
        return new CreditCardResponseDto(
                creditCard.getId(),
                creditCard.getCcn(),
                creditCard.getIssuer(),
                bicService.toResponseDTO(creditCard.getBic())

        );
    }

    @Override
    public CreditCard toEntity(CreditCardRequestDto dto) {
        return new CreditCard(
                dto.getId() != null ? dto.getId() : null,
                dto.getCcn(),
                dto.getIssuer(),
                bicService.toEntity(dto.getBic())
        );
    }
}