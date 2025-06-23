package com.rizamo.goldStore.features.invoice.invoiceDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailsService {

    private final InvoiceDetailsRepository invoiceDetailsRepo;

    @Autowired
    public InvoiceDetailsService(InvoiceDetailsRepository invoiceDetailsRepo) {
        this.invoiceDetailsRepo = invoiceDetailsRepo;
    }

}
