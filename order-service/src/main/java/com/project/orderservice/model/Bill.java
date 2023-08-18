package com.project.orderservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    private long id;
    private int price;
    private long userid;
    private String payment;
    private Date created_at;
    private Date updated_at;
}
