package com.rizamo.goldStore.features.store;

import com.rizamo.goldStore.features.store.DTO.GetStoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/info")
    public ResponseEntity<GetStoreDTO> getStoreInfo(){
        GetStoreDTO getStoreDTO = storeService.getStoreInfo();
        return new ResponseEntity<>(getStoreDTO, HttpStatus.OK);
    }

}
