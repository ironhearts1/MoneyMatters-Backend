package com.moneymatters.MoneyMattersBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Getter
    @Setter
    @Column(name = "user_id")
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private float monthlyIncome;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private List<Expense> expenses;

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
    public User(String username, float monthlyIncome, List<Expense> expenses) {
        this.username = username;
        this.monthlyIncome = monthlyIncome;
        this.expenses = expenses;
    }
}
