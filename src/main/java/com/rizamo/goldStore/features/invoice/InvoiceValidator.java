package com.rizamo.goldStore.features.invoice;

import com.rizamo.goldStore.features.invoice.DTO.InvoiceDTO;
import com.rizamo.goldStore.features.invoice.DTO.InvoiceDetailsDTO;
import com.rizamo.goldStore.features.invoice.DTO.InvoiceInfoDTO;
import com.rizamo.goldStore.features.invoice.invoiceDetails.TransactionType;
import com.rizamo.goldStore.features.user.userException.BadRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class InvoiceValidator {

    public void validateInvoiceDTO(InvoiceDTO invoiceDTO){
        validateInvoiceInfoDTO(invoiceDTO.getInvoiceInfo());
        validateInvoiceDetailsDTO(invoiceDTO.getInvoiceDetails());
    }

    private void validateInvoiceInfoDTO(InvoiceInfoDTO invoiceInfoDTO){

        if (invoiceInfoDTO.getCustomerName().isEmpty()) {
            throw new BadRequest("Missing Customer Name / الاسم مفقود");
        }

        if (invoiceInfoDTO.getCustomerAddress().isEmpty()) {
            throw new BadRequest("Missing Customer Address / العنوان مفقود");
        }

        if (invoiceInfoDTO.getCustomerPhone().isEmpty()) {
            throw new BadRequest("Missing Phone Number / رقم الهاتف مفقود");
        }

        try {
            LocalDate.parse(invoiceInfoDTO.getInvoiceDate());
        } catch (Exception e) {
            throw new BadRequest("Invalid Invoice Date Format / تنسيق تاريخ الفاتورة غير صالح");
        }

    }

    private void validateInvoiceDetailsDTO(List<InvoiceDetailsDTO> invoiceDetails) {

        for (InvoiceDetailsDTO invoiceDetail : invoiceDetails) {
            try {
                TransactionType.valueOf(invoiceDetail.getTransactionType().toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new BadRequest("Invalid Transaction Type / نوع المعاملة غير صالح");
            }

            if (invoiceDetail.getDescription().isEmpty()) {
                throw new BadRequest("Missing Description / الوصف مفقود");
            }
        }

    }

}
