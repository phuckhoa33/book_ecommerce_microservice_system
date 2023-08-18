package com.project.bookservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Integer price;
    private String author;
    private int quantity;
    private String madeby;
    private String publisher;
    private String supplier;
    private Date created_at;
    private Date updated_at;
}
