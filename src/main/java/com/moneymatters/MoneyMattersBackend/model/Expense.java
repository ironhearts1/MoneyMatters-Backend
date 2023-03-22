package com.moneymatters.MoneyMattersBackend.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Optional;

@Entity
@Table (name = "expenses")
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @Getter
    @Setter
    @SequenceGenerator(
            name = "expense_sequence",
            sequenceName = "expense_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "expense_sequence"
    )
    private Long id;
    @Getter
    @Setter
    private String nameOfExpense;

    @Getter
    @Setter
    private String typeOfExpense;

    @Getter
    @Setter
    private float expenseAmount;

    @Getter
    @Setter
    private byte frequencyOfExpenseMonthly;

    @Getter
    @Setter
    private ZonedDateTime createdDate;

    @Getter
    @Setter
    private ZonedDateTime updatedDate;

    public Expense(String nameOfExpense, String typeOfExpense, float expenseAmount, byte frequencyOfExpenseMonthly) {
        this.nameOfExpense = nameOfExpense;
        this.typeOfExpense = typeOfExpense;
        this.expenseAmount = expenseAmount;
        this.frequencyOfExpenseMonthly = frequencyOfExpenseMonthly;
        this.createdDate = ZonedDateTime.now();
        this.updatedDate = ZonedDateTime.now();
    }

}
