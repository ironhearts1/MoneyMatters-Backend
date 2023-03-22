package com.moneymatters.MoneyMattersBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseHttpReturn {
    @Getter
    @Setter
    Expense expense;
    @Getter
    @Setter
    ResponseEntity<HttpStatus> httpStatus;
}
