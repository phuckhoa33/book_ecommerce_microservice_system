package com.project.orderservice.dto;

import com.project.orderservice.model.Bill;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillResultDTO extends ResultDTO {
    private Bill bill;

    public BillResultDTO(Bill bill, String message) {
        this.bill = bill;
        this.setMessage(message);
    }
}
