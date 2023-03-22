package com.moneymatters.MoneyMattersBackend.controller;

import com.moneymatters.MoneyMattersBackend.model.Expense;
import com.moneymatters.MoneyMattersBackend.model.ExpenseHttpReturn;
import com.moneymatters.MoneyMattersBackend.model.User;
import com.moneymatters.MoneyMattersBackend.model.UserHttpReturn;
import com.moneymatters.MoneyMattersBackend.repository.UserRepository;
import com.moneymatters.MoneyMattersBackend.service.ExpenseService;
import com.moneymatters.MoneyMattersBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;
    private final UserRepository userRepository;


    @Autowired
    public ExpenseController(UserService userService, ExpenseService expenseService, UserRepository userRepository) {
        this.userService = userService;
        this.expenseService = expenseService;
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{name}")
    public UserHttpReturn getUser(@PathVariable String name){
        return userService.getUser(name);
    }
    @PostMapping("/{name}")
    public UserHttpReturn addUser(@PathVariable String name){
        return userService.addUser(name);
    }

    @PutMapping("/{name}/updateincome/{amount}")
    public ResponseEntity<HttpStatus> updatedIncome(@PathVariable String name, @PathVariable float amount){
        return userService.updateIncome(name, amount);
    }

    @PostMapping("/{name}/addex")
    public ExpenseHttpReturn addExpense(@PathVariable String name, @RequestBody Expense expense){
        Optional<User> _u = userRepository.findByUsername(name);
        if (_u.isEmpty()){
            return new ExpenseHttpReturn(expense, ResponseEntity.ok(HttpStatus.NOT_FOUND));
        }
        User u = _u.get();
        Expense newEx = userService.addExpense(u, expense);
        ResponseEntity<HttpStatus> status = ResponseEntity.ok(HttpStatus.OK);
        ExpenseHttpReturn httpReturn = new ExpenseHttpReturn(newEx, status);
        return httpReturn;
    }
    @DeleteMapping("/{name}/deleteex/{expenseId}")
    public ResponseEntity<HttpStatus> deleteExpense(@PathVariable String name, @PathVariable Long expenseId){
        return  userService.deleteExpense(name, expenseId);
    }
    @DeleteMapping("/{name}/deleteuser")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String name){
        return userService.deleteUser(name);
    }
    @PutMapping("/{name}/updateex")
    public ExpenseHttpReturn updateExpense(@PathVariable String name, @RequestBody Expense expense){
        return userService.updateExpense(name, expense);

    }
}
