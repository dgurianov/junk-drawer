package gud.fun.junkdrawer.service.stream;

import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.util.generator.PhoneNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneNumberStreamService implements JunkStreamService<PhoneNumberResponseDto> {

    @Autowired
    private PhoneNumberGenerator phoneNumberGenerator;


    private List<PhoneNumberResponseDto> responseDtos = new ArrayList<>();
    private PhoneNumber phoneNumber;

    @Override
    public List<PhoneNumberResponseDto> getAllStream(int limit) {
        if(responseDtos.size() > 0) responseDtos.clear();
        while(limit > 0) {
            phoneNumber = phoneNumberGenerator.generateRandom();

            responseDtos.add(
                    PhoneNumberResponseDto.builder()
                            .id(phoneNumber.getId())
                            .phoneNumber(phoneNumber.getPhoneNumber())
                            .countryCode(phoneNumber.getCountryCode())
                            .build()
            );
            limit--;
        }

        return responseDtos;
    }


}
