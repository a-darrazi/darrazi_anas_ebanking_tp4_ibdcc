package org.darrazi.ebank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.darrazi.ebank.enums.OperationType;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Operations")
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPERATION_ID", unique = true, updatable = false, nullable = false)
    private Long id;
    @Column(name = "OPERATION_DATE")
    @Temporal(TemporalType.TIME)
    private Date operationDate;
    @Column(name = "AMOUNT")
    private double amount;
    @Column(name = "TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    @JoinColumn(name = "BANK_ACCOUNT_ID", referencedColumnName = "BANK_ACCOUNT_ID")
    private BankAccount bankAccount;
    @Column(name = "DESCRIPTION", length = 100)
    private String description;
}

