package gud.fun.junkdrawer.service.data;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.dto.transaction.MerchantRequestDto;
import gud.fun.junkdrawer.dto.transaction.MerchantResponseDto;
import gud.fun.junkdrawer.persistance.model.Merchant;
import gud.fun.junkdrawer.persistance.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Merchant merchant = toEntity(merchantDto);
        merchant = merchantRepository.save(merchant);
        return toResponseDTO(merchant);
    }

    @Override
    public MerchantResponseDto update(MerchantRequestDto dto) {
        Merchant merchant = merchantRepository.findById(UUID.fromString(dto.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Merchant not found for id: " + dto.getId()));
        merchant.setName(dto.getName());
        merchant = merchantRepository.save(merchant);
        return toResponseDTO(merchant);
    }

    @Override
    public MerchantResponseDto delete(UUID id) {
        merchantRepository.deleteById(id);
        MerchantResponseDto response = new MerchantResponseDto();
        response.setId(id.toString());
        return response;
    }

    @Override
    public MerchantResponseDto toResponseDTO(Merchant merchant) {
        return new MerchantResponseDto(
                merchant.getId().toString(),
                merchant.getName(),
                merchant.getCountry().getAlpha3(),
                merchant.getAddress());
    }

    @Override
    public Merchant toEntity(MerchantRequestDto dto) {
        return new Merchant(
                dto.getId() != null ? UUID.fromString(dto.getId()) : null,
                dto.getName(),
                CountryCode.getByAlpha3Code(dto.getCountryCode()),
                dto.getAddress()
        );
    }
}