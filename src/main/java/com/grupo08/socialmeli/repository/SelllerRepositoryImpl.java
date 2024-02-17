package com.grupo08.socialmeli.repository;

import com.grupo08.socialmeli.entity.Post;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SelllerRepositoryImpl implements ISellerRepository{

    private List<Seller> sellers = new ArrayList<Seller>(){{
        new Seller(1,"Brayan", new ArrayList<Post>());
        new Seller(2,"Juan", new ArrayList<Post>());
        new Seller(3,"Carlos", new ArrayList<Post>());
    }};

    public SelllerRepositoryImpl() {
        this.sellers = sellers;
    }

    @Override
    public List<Seller> findAll() {
        return sellers;
    }

    @Override
    public Optional<Seller> findById(int id) {
        return sellers.stream().filter(seller -> seller.getId()==id).findFirst();
    }
}
