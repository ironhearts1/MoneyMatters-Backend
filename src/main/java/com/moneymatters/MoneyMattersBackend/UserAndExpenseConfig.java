package com.moneymatters.MoneyMattersBackend;

import com.moneymatters.MoneyMattersBackend.model.Expense;
import com.moneymatters.MoneyMattersBackend.model.User;
import com.moneymatters.MoneyMattersBackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserAndExpenseConfig {
//    Expense ex1 = new Expense("Rent", "Housing", 1000f, (byte) 1);
//    Expense ex2 = new Expense("Groceries", "Living expenses", 450f, (byte) 1);
//    Expense ex3 = new Expense("Rent", "Housing", 900f, (byte) 1);
//    Expense ex4 = new Expense("Groceries", "Living expenses", 550f, (byte) 1);
//    List<Expense> joshList = new ArrayList<Expense>(List.of(ex1, ex2));
//    List<Expense> lilyList = new ArrayList<Expense>(List.of(ex3, ex4));
//
//
//
//
//
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository){
//        return args -> {
//            User josh = new User(
//                    "Josh",
//                    2000,
//                    joshList
//
//            );
//            User lily = new User(
//                    "Lily",
//                    1500,
//                    lilyList
//            );
//            userRepository.saveAll(List.of(josh, lily));
//        };
//    }
}
