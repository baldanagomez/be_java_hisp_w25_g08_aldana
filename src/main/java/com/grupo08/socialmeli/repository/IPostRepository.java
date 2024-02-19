package com.grupo08.socialmeli.repository;

import java.util.List;

import com.grupo08.socialmeli.entity.Post;

public interface IPostRepository {
   void insertPost(Post post);
   List<Post> getAll();
}
