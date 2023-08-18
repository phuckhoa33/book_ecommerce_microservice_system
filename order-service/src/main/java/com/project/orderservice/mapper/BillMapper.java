package com.project.orderservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.orderservice.model.Bill;

@Mapper
public interface BillMapper {
    List<Bill> getAllBills(@Param("userid") String userid);

    Bill getBill(@Param("billid") String billid, @Param("userid") String userid);

    void paidBill(Bill bill);

}
