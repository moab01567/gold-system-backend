package com.rizamo.goldStore.features.user.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PutUserDTO {
    private Long userId;
    private String username;
    private String invoiceName;
    private boolean disabled;
}
