package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.transaction.BicRequestDto;
import gud.fun.junkdrawer.dto.transaction.BicResponseDto;
import gud.fun.junkdrawer.persistance.model.Bic;
import gud.fun.junkdrawer.persistance.repository.BicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BicService implements JunkDataService<BicRequestDto, BicResponseDto, Bic> {

    @Autowired
    private BicRepository bicRepository;

    @Override
    public List<BicResponseDto> getAll() {
        List<Bic> bics = bicRepository.findAll();
        return bics.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BicResponseDto getById(UUID id) {
        Bic bic = bicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BIC not found for id: " + id));
        return toResponseDTO(bic);
    }

    @Override
    public BicResponseDto create(BicRequestDto bicDto) {
        Bic bic = toEntity(bicDto);
        bic = bicRepository.save(bic);
        return toResponseDTO(bic);
    }

    @Override
    public BicResponseDto update(UUID id, BicRequestDto bicDto) {
        Bic bic = bicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BIC not found for id: " + id));
        bic.setIdentifier(bicDto.getValue());
        bic.setInstitution(bicDto.getInstitution());
        bic = bicRepository.save(bic);
        return toResponseDTO(bic);
    }

    @Override
    public BicResponseDto delete(UUID id) {
        bicRepository.deleteById(id);
        BicResponseDto response = new BicResponseDto();
        response.setId(id.toString());
        return response;
    }

    @Override
    public BicResponseDto toResponseDTO(Bic bic) {
        return new BicResponseDto(
                bic.getId().toString(),
                bic.getIdentifier(),
                bic.getInstitution()
        );
    }

    @Override
    public Bic toEntity(BicRequestDto dto) {
        return new Bic(
                dto.getId() != null ? UUID.fromString(dto.getId()) : null,
                dto.getValue(),
                dto.getInstitution()
        );
    }
}
