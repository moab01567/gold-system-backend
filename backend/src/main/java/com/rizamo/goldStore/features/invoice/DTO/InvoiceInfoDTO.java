package com.rizamo.goldStore.features.invoice.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceInfoDTO {
    private Long invoiceId;
    private String invoiceDate;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String invoiceSaleName;
}
