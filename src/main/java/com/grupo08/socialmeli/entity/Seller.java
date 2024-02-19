package com.grupo08.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Seller extends User{
    private List<Post> posts;
    private List<User> followers;

    public Seller(int id, String name, List<Post> posts, List<User> followers) {
        super(id, name);
        this.posts = posts;
        this.followers = followers;
    }

    public Seller(List<Post> posts, List<User> followers) {
        this.posts = posts;
        this.followers = followers;
    }
}
