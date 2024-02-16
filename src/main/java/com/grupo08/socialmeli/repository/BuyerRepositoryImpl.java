package com.grupo08.socialmeli.repository;

import com.grupo08.socialmeli.entity.Buyer;
import com.grupo08.socialmeli.entity.Seller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BuyerRepositoryImpl implements IBuyerRepository{
    private List<Buyer> listBuyers= new ArrayList<Buyer>(){{
       new Buyer(1, "Fabian", new ArrayList<>());
       new Buyer(1, "Miguel", new ArrayList<>());
       new Buyer(1, "Andres", new ArrayList<>());
    }};

    public BuyerRepositoryImpl() {
        this.listBuyers = listBuyers;
    }

    @Override
    public List<Buyer> findAll() {
        return listBuyers;
    }

    @Override
    public Optional<Buyer> findById(int id) {
            return listBuyers.stream().filter(buyer -> buyer.getId()==id).findFirst();
    }
}
