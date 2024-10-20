package gud.fun.junkdrawer.service.data;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.dto.transaction.MerchantRequestDto;
import gud.fun.junkdrawer.dto.transaction.MerchantResponseDto;
import gud.fun.junkdrawer.persistance.model.Merchant;
import gud.fun.junkdrawer.persistance.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MerchantService implements JunkDataService<MerchantRequestDto, MerchantResponseDto, Merchant> {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public List<MerchantResponseDto> getAll() {
        List<Merchant> merchants = merchantRepository.findAll();
        return merchants.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MerchantResponseDto getById(UUID id) {
        Merchant merchant = merchantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Merchant not found for id: " + id));
        return toResponseDTO(merchant);
    }

    @Override
    public MerchantResponseDto create(MerchantRequestDto merchantDto) {
        log.debug("Create was called  from Merchant service , redirecting to update.");
        return update(merchantDto);
    }

    @Override
    public MerchantResponseDto update(MerchantRequestDto merchantDto) {
        Merchant merchant = new Merchant();
        if(merchantDto.getId() != null) {
            log.debug("Id {} received in Merchant request. Fetching Merchant from repository", merchantDto.getId());
            merchant = merchantRepository.findById(merchantDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Merchant not found for id: " + merchantDto.getId()));
        }else{
            log.debug("No Id for the Merchant received. Merchant will be a new entity.");
        }

        merchant.setName(merchantDto.getName());
        merchant.setCountry(CountryCode.getByAlpha3Code(merchantDto.getCountryCode()));
        merchant.setAddress(merchantDto.getAddress());
        return toResponseDTO(merchantRepository.save(merchant));
    }

    @Override
    public MerchantResponseDto delete(UUID id) {
        merchantRepository.deleteById(id);
        MerchantResponseDto response = new MerchantResponseDto();
        response.setId(id);
        return response;
    }

    @Override
    public MerchantResponseDto toResponseDTO(Merchant merchant) {
        return new MerchantResponseDto(
                merchant.getId(),
                merchant.getName(),
                merchant.getCountry().getAlpha3(),
                merchant.getAddress());
    }

    @Override
    public Merchant toEntity(MerchantRequestDto dto) {
        return new Merchant(
                dto.getId() != null ? dto.getId() : null,
                dto.getName(),
                CountryCode.getByAlpha3Code(dto.getCountryCode()),
                dto.getAddress()
        );
    }
}