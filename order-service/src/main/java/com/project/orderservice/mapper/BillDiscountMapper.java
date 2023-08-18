package com.project.orderservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.orderservice.model.BillDiscount;

@Mapper
public interface BillDiscountMapper {
    BillDiscount getBillDiscount(@Param("billdiscountid") String billdiscountid);

    void createBillDiscount(BillDiscount billDiscount);
}
