package com.project.orderservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.orderservice.model.BillItem;

@Mapper
public interface BillItemMapper {
    List<BillItem> getBillItems(@Param("billitemid") String billitemid);

    void createBillItem(BillItem billItem);
}
