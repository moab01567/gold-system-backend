package com.rizamo.goldStore.features.invoice.DTO;

import com.rizamo.goldStore.features.invoice.invoiceDetails.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsDTO {
    private Long invoiceDetailsId;
    private TransactionType transactionType;
    private String description;
    private int wagePerGm;
    private int pricePerGm;
    private int goldKarat;
    private int weightGm;
    private int weightMg;
}
