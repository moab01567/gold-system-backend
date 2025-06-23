package com.rizamo.goldStore.features.invoice;


import com.rizamo.goldStore.features.invoice.DTO.GetListInvoiceDTO;
import com.rizamo.goldStore.features.invoice.DTO.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceValidator invoiceValidator;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, InvoiceValidator invoiceValidator) {
        this.invoiceService = invoiceService;
        this.invoiceValidator = invoiceValidator;
    }

    @PostMapping("/create")
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO reqInvoiceInfo, Principal principal){
        invoiceValidator.validateInvoiceDTO(reqInvoiceInfo);
        InvoiceDTO resInvoiceInfo = invoiceService.createInvoice(reqInvoiceInfo,principal.getName());
        return new ResponseEntity<>(resInvoiceInfo, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<InvoiceDTO> updateInvoice(@RequestBody InvoiceDTO reqInvoice){
        invoiceValidator.validateInvoiceDTO(reqInvoice);
        InvoiceDTO resInvoiceDTO = invoiceService.updateInvoice(reqInvoice);
        return new ResponseEntity<>(resInvoiceDTO,HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<GetListInvoiceDTO> getInvoiceDTOList(@RequestParam(required = false) Long invoiceId,
                                                               @RequestParam(required = false) LocalDate date,
                                                               @RequestParam(required = false) String phoneNumber
                                                           ){
        GetListInvoiceDTO getListInvoiceDTO = invoiceService.getInvoiceList(invoiceId,date,phoneNumber);
        return new ResponseEntity<>(getListInvoiceDTO,HttpStatus.OK);
    }

}
