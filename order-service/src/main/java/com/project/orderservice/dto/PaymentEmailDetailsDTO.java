package com.project.orderservice.dto;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.project.orderservice.model.Bill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PaymentEmailDetailsDTO {
    private Bill bill;
    private User user;
    // private List<Void> bookItems;
    // private EmailDetailsDTO emailDetails;
    // private List<Discount> discountsService;
}
