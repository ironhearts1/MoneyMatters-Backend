package com.moneymatters.MoneyMattersBackend.service;

import com.moneymatters.MoneyMattersBackend.model.Expense;
import com.moneymatters.MoneyMattersBackend.model.ExpenseHttpReturn;
import com.moneymatters.MoneyMattersBackend.model.User;
import com.moneymatters.MoneyMattersBackend.model.UserHttpReturn;
import com.moneymatters.MoneyMattersBackend.repository.ExpenseRepository;
import com.moneymatters.MoneyMattersBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    @Autowired
    public UserService(UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public UserHttpReturn getUser(String name){
        Optional<User> _u = userRepository.findByUsername(name);
        if (_u.isEmpty()){
            return new UserHttpReturn(new User(), ResponseEntity.ok(HttpStatus.NOT_FOUND));
        }
        return new UserHttpReturn(userRepository.findByUsername(name).get(), ResponseEntity.ok(HttpStatus.OK));
    }

    public UserHttpReturn addUser(String name){
        List<Expense> expenseList = new ArrayList<>();
        User u = new User(name, 0, expenseList);
        userRepository.save(u);
        ResponseEntity<HttpStatus> response = ResponseEntity.ok(HttpStatus.OK);
        UserHttpReturn httpReturn = new UserHttpReturn(u, response);
        return httpReturn;
    }
    public ResponseEntity<HttpStatus> updateIncome(String name, float amount){
        Optional<User> _u = userRepository.findByUsername(name);
        if (_u.isEmpty()){
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }
        else{
            User u = _u.get();
            u.setMonthlyIncome(amount);
            userRepository.save(u);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
    public Expense addExpense(User u, Expense expense){
        List<Expense> exList = u.getExpenses();
        expense.setCreatedDate(ZonedDateTime.now());
        expense.setUpdatedDate(ZonedDateTime.now());
        exList.add(expense);
        u.setExpenses(exList);

        userRepository.save(u);
        return u.getExpenses().stream().filter(e -> e.getNameOfExpense() == expense.getNameOfExpense()).collect(Collectors.toList()).get(0);
    }
    public ResponseEntity<HttpStatus> deleteUser(String name){
        Optional<User> _u = userRepository.findByUsername(name);
        if (_u.isEmpty()){
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }
        User u = _u.get();
        userRepository.delete(u);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> deleteExpense(String name, Long expenseId){
        Optional<User> _u = userRepository.findByUsername(name);
        if (_u.isEmpty()){
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }
        User u = _u.get();
        List<Expense> exList = u.getExpenses();
        List<Expense> newList = exList.stream().filter(e -> e.getId() != expenseId).collect(Collectors.toList());
        u.setExpenses(newList);
        userRepository.save(u);
        return ResponseEntity.ok(HttpStatus.OK);
    };

    public ExpenseHttpReturn updateExpense(String name, Expense expense){
        Optional<User> _u = userRepository.findByUsername(name);
        if (_u.isEmpty()){
            return new ExpenseHttpReturn(expense, ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE));
        }
        Optional<Expense> _updatedEx = expenseRepository.findById(expense.getId());
        if (_updatedEx.isPresent()){
            Expense updatedEx = _updatedEx.get();
            updatedEx.setNameOfExpense(expense.getNameOfExpense());
            updatedEx.setTypeOfExpense(expense.getTypeOfExpense());
            updatedEx.setExpenseAmount(expense.getExpenseAmount());
            updatedEx.setFrequencyOfExpenseMonthly(expense.getFrequencyOfExpenseMonthly());
            updatedEx.setUpdatedDate(ZonedDateTime.now());
            expenseRepository.save(updatedEx);
            return new ExpenseHttpReturn(updatedEx, ResponseEntity.ok(HttpStatus.OK));
        }
        else{
            return new ExpenseHttpReturn(expense, ResponseEntity.ok(HttpStatus.NOT_FOUND));
        }



//                List<Expense> exList = u.getExpenses();
//        Expense updatedEx = exList.stream().filter(e -> e.getId() == expense.getId()).collect(Collectors.toList()).get(0);
    }
}
