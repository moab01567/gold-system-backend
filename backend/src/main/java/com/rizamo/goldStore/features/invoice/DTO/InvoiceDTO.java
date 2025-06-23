package com.rizamo.goldStore.features.invoice.DTO;


import lombok.*;

import java.util.List;
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class InvoiceDTO {

    private InvoiceInfoDTO invoiceInfo;
    private List<InvoiceDetailsDTO> invoiceDetails;


}
