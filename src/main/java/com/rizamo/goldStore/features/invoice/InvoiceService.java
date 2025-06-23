package com.rizamo.goldStore.features.invoice;

import com.rizamo.goldStore.features.invoice.DTO.*;
import com.rizamo.goldStore.features.invoice.invoiceDetails.InvoiceDetails;
import com.rizamo.goldStore.features.invoice.invoiceDetails.TransactionType;
import com.rizamo.goldStore.features.invoice.invoiceInfo.InvoiceInfo;
import com.rizamo.goldStore.features.invoice.invoiceInfo.InvoiceInfoRepository;
import com.rizamo.goldStore.features.store.StoreService;
import com.rizamo.goldStore.features.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceInfoRepository invoiceInfoRepo;
    private final UserService userService;
    private final StoreService storeService;

    public InvoiceService(InvoiceInfoRepository invoiceInfoRepo, UserService userService, StoreService storeService) {
        this.invoiceInfoRepo = invoiceInfoRepo;
        this.userService = userService;
        this.storeService = storeService;
    }

    @Transactional
    public InvoiceDTO createInvoice(InvoiceDTO reqInvoiceInfo, String username) {
        InvoiceInfo invoiceInfo = DTOMapper.mapperInvoiceDtoToInvoiceInfo(
                reqInvoiceInfo,
                userService.getUserByUsername(username));
        invoiceInfo.setInvoiceGeneratedDateTime(LocalDateTime.now());
        InvoiceInfo savedInvoiceInfo = invoiceInfoRepo.save(invoiceInfo);

        List<InvoiceDetails> invoiceDetails = invoiceInfo.getInvoiceDetails();

        invoiceDetails.forEach(invoiceDetail ->{
            if(invoiceDetail.getTransactionType() == TransactionType.purchase){
                storeService.updateGoldStoreValuePurchase(1L, invoiceDetail.getWeightGm(), invoiceDetail.getWeightMg());
            }else{
                storeService.updateGoldStoreValueSale(1L, invoiceDetail.getWeightGm(), invoiceDetail.getWeightMg());
            }
        });

        return DTOMapper.mapperInvoiceInfoToInvoiceDTO(savedInvoiceInfo);
    }


    public InvoiceDTO updateInvoice(InvoiceDTO reqInvoice) {

        return null;
    }
    @Transactional
    public GetListInvoiceDTO getInvoiceList(Long invoiceId, LocalDate date, String phoneNumber) {
        List<InvoiceInfo> invoiceInfos = invoiceInfoRepo.findInvoiceInfoByInvoiceIdOrInvoiceDateOrCustomerPhone(invoiceId,date,"+"  + phoneNumber);
        return DTOMapper.mapperInvoiceInfoListToGetInvoiceDTO(invoiceInfos);


    }
}
