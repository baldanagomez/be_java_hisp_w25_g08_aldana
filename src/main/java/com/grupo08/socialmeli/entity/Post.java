package com.grupo08.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int userId;
    private LocalDate date;
    private Product product;
    private int category;
    private double price;

}
