package com.rizamo.goldStore.features.store.DTO;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class GetStoreDTO {
    private Long storeId;
    private int goldInMg;
}
