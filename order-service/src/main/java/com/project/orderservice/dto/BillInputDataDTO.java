package com.project.orderservice.dto;

import java.util.List;

import com.project.orderservice.model.Bill;
import com.project.orderservice.model.BillItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillInputDataDTO {
    private Bill bill;
    private List<String> discountList;
    private List<BillItem> billItems;
}
