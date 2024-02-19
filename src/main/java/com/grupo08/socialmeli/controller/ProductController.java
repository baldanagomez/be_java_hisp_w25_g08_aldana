package com.grupo08.socialmeli.controller;

import com.grupo08.socialmeli.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    IUserService sellerService;

    public ProductController(IUserService sellerService) {
        this.sellerService = sellerService;
    }
    @GetMapping("products/followed/{userId}/list")
    public ResponseEntity<?> followProcducts(@PathVariable Long idUser){
        return new ResponseEntity<>(null);
    }
}
