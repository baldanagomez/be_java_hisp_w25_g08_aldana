package com.grupo08.socialmeli.dto;

import com.grupo08.socialmeli.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    int userId;
    String date;
    Product product;
    int category;
    double price;
}
