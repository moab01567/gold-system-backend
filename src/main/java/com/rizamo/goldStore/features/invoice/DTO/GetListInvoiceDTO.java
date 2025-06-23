package com.rizamo.goldStore.features.invoice.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder @Data
public class GetListInvoiceDTO {
    private List<InvoiceDTO> invoiceDTOList;
    private int totaleInvoiceDTO;
}
