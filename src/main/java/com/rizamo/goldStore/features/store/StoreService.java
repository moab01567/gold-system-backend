package com.rizamo.goldStore.features.store;

import com.rizamo.goldStore.features.store.DTO.GetStoreDTO;
import com.rizamo.goldStore.features.user.userException.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepo;

    @Autowired
    public StoreService(StoreRepository storeRepo) {
        this.storeRepo = storeRepo;
    }

    public GetStoreDTO getStoreInfo() {
        Optional<Store> storeOptional = storeRepo.findById(1L);
        Store store = storeOptional.orElseThrow(()->new BadRequest("Store Not found / المتجر غير موجود"));
        return GetStoreDTO.builder()
                .storeId(store.getStoreId())
                .goldInMg(store.getGoldInMg())
                .build();
    }

    public void updateGoldStoreValueSale(Long storeId, int goldGm, int goldMg){



        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new BadRequest("The store is not found / المتجر غير موجود "));
        store.setGoldInMg(store.getGoldInMg() - ((goldGm*1000) + goldMg));
        storeRepo.save(store);

    }
    public void updateGoldStoreValuePurchase(Long storeId, int goldGm, int goldMg){
        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new BadRequest("The store is not found / المتجر غير موجود "));
        store.setGoldInMg(store.getGoldInMg() + ((goldGm*1000) + goldMg));
        storeRepo.save(store);

    }
}
