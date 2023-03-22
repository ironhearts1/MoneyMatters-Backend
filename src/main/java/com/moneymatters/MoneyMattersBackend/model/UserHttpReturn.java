package com.moneymatters.MoneyMattersBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
public class UserHttpReturn {

    @Getter
    @Setter
    User user;

    @Getter
    @Setter
    ResponseEntity<HttpStatus> httpStatus;
}
