package com.project.orderservice.service.impl;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.project.orderservice.dto.BillInputDataDTO;
import com.project.orderservice.dto.BookGetResultDTO;
import com.project.orderservice.dto.DiscountGetResultDTO;
import com.project.orderservice.dto.PaymentEmailDetailsDTO;
import com.project.orderservice.mapper.BillDiscountMapper;
import com.project.orderservice.mapper.BillItemMapper;
import com.project.orderservice.mapper.BillMapper;
import com.project.orderservice.model.Bill;
import com.project.orderservice.model.BillDiscount;
import com.project.orderservice.model.BillItem;
import com.project.orderservice.service.BillService;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;

    @Autowired
    BillItemMapper billItemMapper;

    @Autowired
    BillDiscountMapper billDiscountMapper;

    @Autowired
    WebClient bookClient;

    @Autowired
    WebClient discountClient;

    @Override
    public List<Bill> getAllBills(String userid) {
        return billMapper.getAllBills(userid);
    }

    @Override
    public Bill getBill(String billid, String userid) {
        return billMapper.getBill(billid, userid);
    }

    @Override
    public void paidBill(Bill bill) {
        billMapper.paidBill(bill);
    }

    @Override
    public String createNewBill(BillInputDataDTO request) {
        Bill newBill = request.getBill();
        List<String> discountList = request.getDiscountList();
        List<BillItem> billItems = request.getBillItems();
        final String[] message = { "Order bill is successfully" };

        Long billid = createRandomId(10);

        // Create Thread
        // Create bill
        CompletableFuture<Void> createNewBillFuture = CompletableFuture.runAsync(() -> {
            newBill.setId(billid);
            paidBill(newBill);

        });
        // create billItems
        CompletableFuture<Void> createNewBillItemFuture = CompletableFuture.runAsync(() -> {
            for (BillItem billItem : billItems) {
                BookGetResultDTO book = bookClient.get().uri("/api/book" + billItem.getBookid()).retrieve()
                        .bodyToMono(BookGetResultDTO.class).block();
                if (book.getData() == null) {
                    message[0] = "Book is not exist";
                    createNewBillFuture.cancel(true);
                }
                billItem.setId(createRandomId(10));
                billItem.setBillid(billid);
                billItemMapper.createBillItem(billItem);
            }

        });

        // create billDiscount

        CompletableFuture<Void> createNewBillDiscountFuture = CompletableFuture.runAsync(() -> {
            for (String discount : discountList) {
                DiscountGetResultDTO discountItem = discountClient.get()
                        .uri("/api/discount/" + discount).retrieve()
                        .bodyToMono(DiscountGetResultDTO.class).block();
                if (discountItem == null) {
                    message[0] = "Discount label is invalid";
                    createNewBillFuture.cancel(true);
                    createNewBillItemFuture.cancel(true);
                    return;
                }
                BillDiscount newBillDiscount = new BillDiscount();
                newBillDiscount.setBillid(billid);
                newBillDiscount.setDiscountid(discount);
                newBillDiscount.setId(createRandomId(10));
                billDiscountMapper.createBillDiscount(newBillDiscount);

            }

        });

        // Send Email
        CompletableFuture<Void> sendEmail = CompletableFuture.runAsync(() -> {
            try {

                PaymentEmailDetailsDTO paymentEmail = new PaymentEmailDetailsDTO();
                // EmailDetailsDTO emailDetails = new EmailDetailsDTO();
                // emailDetails.setRecipient("mphuc8671@gmail.com");
                // emailDetails.setSubject(message[0]);
                // emailDetails.setMsgBody(message[0]);
                // paymentEmail.setEmailDetails(emailDetails);
                // paymentEmail.setBill(newBill);
                // emailService.sendSimpleMail(paymentEmail, "payment");
            } catch (Exception e) {
                // TODO: handle exception
                message[0] = "Failed to send email";
                createNewBillFuture.cancel(true);
                createNewBillDiscountFuture.cancel(true);
                createNewBillItemFuture.cancel(true);
            }
        });
        // Start Thread
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(
                createNewBillFuture,
                createNewBillItemFuture,
                createNewBillDiscountFuture, sendEmail);

        // Wait all thread is ended

        try {
            combinedFuture.get(); // Wait for all tasks to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Handle exceptions here
        }

        return message[0];
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
