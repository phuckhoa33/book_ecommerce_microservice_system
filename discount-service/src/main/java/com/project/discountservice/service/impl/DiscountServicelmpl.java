package com.project.discountservice.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.discountservice.mapper.DiscountMapper;
import com.project.discountservice.model.Discount;
import com.project.discountservice.service.DiscountService;

@Service
public class DiscountServicelmpl implements DiscountService {

    @Autowired
    DiscountMapper discountMapper;

    @Override
    public List<Discount> getAllDiscounts() {
        return discountMapper.getAllDiscounts();
    }

    @Override
    public Discount getDiscount(String discountid) {
        return discountMapper.getDiscount(discountid);
    }

    @Override
    public void createDiscount(Discount discount) {
        discountMapper.createDiscount(discount);
    }

    @Override
    public String createNewDiscount(Discount discount) {
        Discount oldDiscount = discountMapper.getDiscount(discount.getTitle());
        if (oldDiscount != null) {
            return "This discount label is exist";
        }
        Long discountid = createRandomId(10);

        discount.setId(discountid);
        discountMapper.createDiscount(discount);
        return "Add discount is successfully";
    }

    public long createRandomId(int desiredLength) {
        if (desiredLength <= 0) {
            throw new IllegalArgumentException("Desired length must be a positive integer.");
        }

        Random random = new Random();
        long min = (long) Math.pow(10, desiredLength - 1);
        long max = (long) Math.pow(10, desiredLength) - 1;

        return min + random.nextLong() % (max - min + 1);
    }

}
