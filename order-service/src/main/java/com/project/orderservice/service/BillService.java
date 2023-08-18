package com.project.orderservice.service;

import java.util.List;

import com.project.orderservice.dto.BillInputDataDTO;
import com.project.orderservice.model.Bill;

public interface BillService {
    List<Bill> getAllBills(String userid);

    Bill getBill(String billid, String userid);

    void paidBill(Bill bill);

    String createNewBill(BillInputDataDTO request);
}
