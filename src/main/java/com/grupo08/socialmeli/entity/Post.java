package com.grupo08.socialmeli.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    @NotBlank(message = "El id no puede estar vacio.")
    @Positive(message = "El id debe ser mayor a 0.")
    Integer userId;
    @NotBlank(message = "La fecha no puede estar vacía.")
    LocalDate date;
    @NotBlank(message = "El producto no puede estar vacio.")
    Product product;
    @NotBlank(message = "La categoria no puede estar vacío.")
    Integer category;
    @NotBlank(message = "El precio no puede estar vacía.")
    @Max(value = 10000000, message = "El precio máximo por producto es de 10.000.000")
    Double price;

    public Post(int userId, String date, Product product, int category, double price) {
        this.userId = userId;
        LocalDate datetime = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.date = datetime;
        this.product = product;
        this.category = category;
        this.price = price;
    }


}
