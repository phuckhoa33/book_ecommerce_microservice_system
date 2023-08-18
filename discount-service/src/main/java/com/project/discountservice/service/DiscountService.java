package com.project.discountservice.service;

import java.util.List;

import com.project.discountservice.model.Discount;

public interface DiscountService {
    List<Discount> getAllDiscounts();

    Discount getDiscount(String discountid);

    void createDiscount(Discount discount);

    String createNewDiscount(Discount discount);
}
