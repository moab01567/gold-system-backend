package com.rizamo.goldStore.features.user;

import com.rizamo.goldStore.features.invoice.invoiceInfo.InvoiceInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String invoiceName;
    @Column(nullable = false)
    private Authority authority;
    @Column(nullable = false)
    private boolean disabled;

    @OneToMany(mappedBy = "invoiceSaleName", cascade = CascadeType.ALL)
    private List<InvoiceInfo> invoiceInfos;

}
