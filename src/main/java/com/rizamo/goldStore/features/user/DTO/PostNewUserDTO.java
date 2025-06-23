package com.rizamo.goldStore.features.user.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostNewUserDTO {
    private String username;
    private String invoiceName;
    private String password;
    private String confirmPassword;
}
