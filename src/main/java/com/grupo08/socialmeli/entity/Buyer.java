package com.grupo08.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Buyer extends User{
    private List<Seller> following;

    public Buyer(int id, String name, List<Seller> following) {
        super(id, name);
        this.following = following;
    }
}
