package com.rizamo.goldStore.features.user.DTO;

import com.rizamo.goldStore.features.user.Authority;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserDTO {
    private Long userId;
    private String username;
    private String invoiceName;
    private Authority authority;
    private boolean disabled;
}
