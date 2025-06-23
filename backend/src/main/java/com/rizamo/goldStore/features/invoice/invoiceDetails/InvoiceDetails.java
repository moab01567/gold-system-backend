package com.rizamo.goldStore.features.invoice.invoiceDetails;

import com.rizamo.goldStore.features.invoice.invoiceInfo.InvoiceInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder @Data
public class InvoiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceDetailsId;
    @Column(nullable = false)
    private TransactionType transactionType;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int wagePerGm;
    @Column(nullable = false)
    private int pricePerGm;
    @Column(nullable = false)
    private int goldKarat;
    @Column(nullable = false)
    private int weightGm;
    @Column(nullable = false)
    private int weightMg;

    @ManyToOne
    @JoinColumn
    private InvoiceInfo invoiceInfo;
}
