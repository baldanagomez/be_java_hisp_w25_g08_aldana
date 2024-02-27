package com.grupo08.socialmeli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo08.socialmeli.entity.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto implements  Comparable<PostDto> {
    @JsonProperty("user_id")
    @NotBlank(message = "El id no puede estar vacio.")
    @Positive(message = "El id debe ser mayor a 0.")
    Integer userId;
    @NotBlank(message = "La fecha no puede estar vacía.")
    String date;
    Product product;
    Integer category;
    Double price;

    @Override
    public int compareTo(PostDto postDto){
return this.date.compareTo(postDto.getDate());
    }
}
