package gud.fun.junkdrawer.dto.assembler;

import gud.fun.junkdrawer.controller.TransactionController;
import gud.fun.junkdrawer.dto.transaction.BicResponseDto;
import gud.fun.junkdrawer.dto.transaction.CreditCardResponseDto;
import gud.fun.junkdrawer.dto.transaction.MerchantResponseDto;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.persistance.model.Bic;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import gud.fun.junkdrawer.persistance.model.Merchant;
import gud.fun.junkdrawer.persistance.model.Transaction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TransactionResponseDtoAssembler extends RepresentationModelAssemblerSupport<Transaction, TransactionResponseDto> {

    public TransactionResponseDtoAssembler() {
        super(TransactionController.class, TransactionResponseDto.class);
    }

    @Override
    public TransactionResponseDto toModel(Transaction entity) {

        TransactionResponseDto dto = instantiateModel(entity);
        dto.setId(entity.getId());
        dto.setCorrelationId(entity.getCorrelationId());
        dto.setDateTime(entity.getDateTime());
        dto.setAmount(entity.getAmount());
        dto.setCurrency(entity.getCurrency());
        dto.setEntryType(entity.getEntryType());
        dto.setState(entity.getState());
        dto.setType(entity.getType());
        dto.setMerchant(toMerchantResponseDto(entity.getMerchant()));
        dto.setCreditCard(toCreditCardResponseDto(entity.getCreditCard()));
        return dto;
    }

    @Override
    public CollectionModel<TransactionResponseDto> toCollectionModel(Iterable<? extends Transaction> entities) {
        return super.toCollectionModel(entities);
    }

    private MerchantResponseDto toMerchantResponseDto(Merchant merchant) {
        return  MerchantResponseDto.builder()
                .id(merchant.getId())
                .name(merchant.getName())
                .countryCode(merchant.getCountry().getAlpha3())
                .build();
    }

    private CreditCardResponseDto toCreditCardResponseDto(CreditCard creditCard) {
        return  CreditCardResponseDto.builder()
                .id(creditCard.getId())
                .Ccn(creditCard.getCcn())
                .issuer(creditCard.getIssuer())
                .bic(toBicResponseDto(creditCard.getBic()))
                .build();
    }

    private BicResponseDto toBicResponseDto(Bic bic) {
        return  BicResponseDto.builder()
                .id(bic.getId())
                .value(bic.getIdentifier())
                .institution(bic.getInstitution())
                .build();
    }
}
