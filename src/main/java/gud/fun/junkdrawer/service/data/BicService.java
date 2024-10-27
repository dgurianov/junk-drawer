package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.assembler.BicResponseDtoAssembler;
import gud.fun.junkdrawer.dto.transaction.BicRequestDto;
import gud.fun.junkdrawer.dto.transaction.BicResponseDto;
import gud.fun.junkdrawer.persistance.model.Bic;
import gud.fun.junkdrawer.persistance.repository.BicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BicService implements JunkDataService<BicRequestDto, BicResponseDto, Bic> {

    @Autowired
    private BicRepository bicRepository;

    @Autowired
    private BicResponseDtoAssembler bicDtoAssembler;

    @Autowired
    private PagedResourcesAssembler<Bic> pagedResourcesAssembler;

    @Override
    public PagedModel<BicResponseDto> getAll(Pageable pageable) {
        return pagedResourcesAssembler.toModel(bicRepository.findAll(pageable),bicDtoAssembler );
    }

    @Override
    public BicResponseDto getById(UUID id) {
        Bic bic = bicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BIC not found for id: " + id));
        return toResponseDTO(bic);
    }

    @Override
    public BicResponseDto create(BicRequestDto bicDto) {
        log.debug("Create was called  from Bic service , redirecting to update.");
        return update(bicDto);
    }

    @Override
    public BicResponseDto update(BicRequestDto dto) {
        Bic bic = new Bic();
        if(dto.getId() != null) {
            log.debug("Id {} received in Bic request. Fetching Bic from repository", dto.getId());
            bic = bicRepository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("BIC not found for id: " + dto.getId()));
        }else{
            log.debug("No Id for the Bic received. Bic will be a new entity.");
        }
        bic.setIdentifier(dto.getValue());
        bic.setInstitution(dto.getInstitution());
        return toResponseDTO(bicRepository.save(bic));
    }

    @Override
    public BicResponseDto delete(UUID id) {
        log.debug("Deleting Bic with id: {}", id);
        bicRepository.deleteById(id);
        BicResponseDto response = new BicResponseDto();
        response.setId(id);
        return response;
    }

    @Override
    public BicResponseDto toResponseDTO(Bic bic) {
        return new BicResponseDto(
                bic.getId(),
                bic.getIdentifier(),
                bic.getInstitution()
        );
    }

    @Override
    public Bic toEntity(BicRequestDto dto) {
        return new Bic(
                dto.getId() != null ? dto.getId() : null,
                dto.getValue(),
                dto.getInstitution()
        );
    }
}
