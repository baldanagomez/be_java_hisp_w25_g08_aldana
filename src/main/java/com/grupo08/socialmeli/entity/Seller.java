package com.grupo08.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends User{
    private List<Post> posts;

    public Seller(int id, String name, List<Post> posts) {
        super(id, name);
        this.posts = posts;
    }
}
