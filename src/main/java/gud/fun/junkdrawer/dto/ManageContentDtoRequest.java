package gud.fun.junkdrawer.dto;

import lombok.Data;

@Data
public class ManageContentDtoRequest {
    private String contentName;
    private Long contentAmount;
}