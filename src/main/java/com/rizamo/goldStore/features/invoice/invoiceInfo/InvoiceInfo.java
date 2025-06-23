package com.rizamo.goldStore.features.invoice.invoiceInfo;

import com.rizamo.goldStore.features.invoice.invoiceDetails.InvoiceDetails;
import com.rizamo.goldStore.features.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
@Entity @Builder
@NoArgsConstructor @AllArgsConstructor
public class InvoiceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    @Column(nullable = false)
    private LocalDate invoiceDate;
    @Column(nullable = false)
    private LocalDateTime invoiceGeneratedDateTime;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String customerAddress;
    @Column(nullable = false)
    private String customerPhone;

    @ManyToOne
    @JoinColumn
    private User invoiceSaleName;

    @OneToMany(mappedBy = "invoiceInfo",  cascade = CascadeType.ALL,orphanRemoval = true)
    private List<InvoiceDetails> invoiceDetails;


}
