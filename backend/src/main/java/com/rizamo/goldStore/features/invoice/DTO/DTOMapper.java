package com.rizamo.goldStore.features.invoice.DTO;

import com.rizamo.goldStore.features.invoice.invoiceDetails.InvoiceDetails;
import com.rizamo.goldStore.features.invoice.invoiceInfo.InvoiceInfo;
import com.rizamo.goldStore.features.user.User;

import java.time.LocalDate;
import java.util.List;

public  class DTOMapper {

    public static InvoiceDTO mapperInvoiceInfoToInvoiceDTO(InvoiceInfo invoiceInfo){
        return InvoiceDTO.builder()
                .invoiceInfo(InvoiceInfoDTO.builder()
                        .invoiceId(invoiceInfo.getInvoiceId())
                        .customerAddress(invoiceInfo.getCustomerAddress())
                        .customerName(invoiceInfo.getCustomerName())
                        .customerPhone(invoiceInfo.getCustomerPhone())
                        .invoiceDate(invoiceInfo.getInvoiceDate().toString())
                        .invoiceSaleName(invoiceInfo.getInvoiceSaleName().getInvoiceName())
                        .build())
                .invoiceDetails(invoiceInfo.getInvoiceDetails().stream()
                        .map(invoiceDetail -> InvoiceDetailsDTO.builder()
                                .invoiceDetailsId(invoiceDetail.getInvoiceDetailsId())
                                .wagePerGm(invoiceDetail.getWagePerGm())
                                .weightGm(invoiceDetail.getWeightGm())
                                .weightMg(invoiceDetail.getWeightMg())
                                .transactionType(invoiceDetail.getTransactionType())
                                .pricePerGm(invoiceDetail.getPricePerGm())
                                .description(invoiceDetail.getDescription())
                                .goldKarat(invoiceDetail.getGoldKarat())
                                .build())
                        .toList())
                .build();
    }

    public static InvoiceInfo mapperInvoiceDtoToInvoiceInfo(InvoiceDTO reqInvoiceInfo, User user){
        List<InvoiceDetailsDTO> invoiceDetailsDTOS = reqInvoiceInfo.getInvoiceDetails();
        InvoiceInfoDTO invoiceInfoDTO = reqInvoiceInfo.getInvoiceInfo();
        return  InvoiceInfo.builder()
                .invoiceSaleName(user)
                .invoiceDate(LocalDate.parse(invoiceInfoDTO.getInvoiceDate()))
                .customerName(invoiceInfoDTO.getCustomerName())
                .customerPhone(invoiceInfoDTO.getCustomerPhone())
                .customerAddress(invoiceInfoDTO.getCustomerAddress())
                .invoiceDetails(invoiceDetailsDTOS.stream()
                        .map((invoiceDetailsDTO -> InvoiceDetails.builder()
                                .description(invoiceDetailsDTO.getDescription())
                                .pricePerGm(invoiceDetailsDTO.getPricePerGm())
                                .wagePerGm(invoiceDetailsDTO.getWagePerGm())
                                .weightGm(invoiceDetailsDTO.getWeightGm())
                                .weightMg(invoiceDetailsDTO.getWeightMg())
                                .goldKarat(invoiceDetailsDTO.getGoldKarat())
                                .transactionType(invoiceDetailsDTO.getTransactionType())
                                .build()))
                        .toList())
                .build();
    }

    public static GetListInvoiceDTO mapperInvoiceInfoListToGetInvoiceDTO(List<InvoiceInfo> invoiceInfos){
       return GetListInvoiceDTO.builder()
                .invoiceDTOList(
                    invoiceInfos.stream().map(
                                    DTOMapper::mapperInvoiceInfoToInvoiceDTO)
                            .toList())
                    .totaleInvoiceDTO(invoiceInfos.size())
                .build();
    }
}
