package com.grupo08.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Buyer extends User{
    private List<Seller> following;

    public Buyer(int id, String name, List<Seller> following) {
        super(id, name);
        this.following = following;
    }

    public void addFollowingSeller(Seller seller){
        following.add(seller);
    }

    public void removeFollowingSeller(int id){
        following.removeIf(v -> v.getId() == id);
    }

}
