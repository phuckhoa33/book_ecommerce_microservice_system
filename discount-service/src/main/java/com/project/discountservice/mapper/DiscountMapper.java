package com.project.discountservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.discountservice.model.Discount;

@Mapper
public interface DiscountMapper {
    List<Discount> getAllDiscounts();

    Discount getDiscount(@Param("title") String title);

    Discount getDiscountByDiscountid(@Param("discountid") String discountid);

    void createDiscount(Discount discount);

}
