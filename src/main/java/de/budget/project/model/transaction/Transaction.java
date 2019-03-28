package de.budget.project.model.transaction;

import de.budget.project.model.CustomMoney;
import de.budget.project.model.category.Category;
import de.budget.project.model.wallet.Wallet;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CUSTOM_DATE", nullable = false)
    private Date customDate;

//    @Columns(columns = {@Column(name = "CURRENCY"), @Column(name = "AMOUNT")})
//    @Type(type = "de.budget.project.model.CustomMoney")
    @Embedded
    private CustomMoney amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WALLET_ID", referencedColumnName = "ID")
    private Wallet wallet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private Category category;

    @Column(name = "DESCRIPTION")
    private String description;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public CustomMoney getAmount() {
        return amount;
    }

    public void setAmount(CustomMoney amount) {
        this.amount = amount;
    }
}