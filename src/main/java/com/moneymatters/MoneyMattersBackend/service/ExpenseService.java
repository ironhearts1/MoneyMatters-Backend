package com.moneymatters.MoneyMattersBackend.service;

import com.moneymatters.MoneyMattersBackend.model.Expense;
import com.moneymatters.MoneyMattersBackend.model.User;
import com.moneymatters.MoneyMattersBackend.repository.ExpenseRepository;
import com.moneymatters.MoneyMattersBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }
}
