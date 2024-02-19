package com.grupo08.socialmeli.controller;

import com.grupo08.socialmeli.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    IUserService sellerService;

    public ProductController(IUserService sellerService) {
        this.sellerService = sellerService;
    }
}
