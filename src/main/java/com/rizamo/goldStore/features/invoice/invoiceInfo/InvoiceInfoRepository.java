package com.rizamo.goldStore.features.invoice.invoiceInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceInfoRepository extends JpaRepository<InvoiceInfo,Long> {

    List<InvoiceInfo> findInvoiceInfoByInvoiceIdOrInvoiceDateOrCustomerPhone(Long invoiceId, LocalDate invoiceDate, String customerPhone);




}
