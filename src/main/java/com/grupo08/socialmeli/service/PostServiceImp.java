package com.grupo08.socialmeli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo08.socialmeli.dto.ExceptionDto;
import com.grupo08.socialmeli.dto.PostDto;
import com.grupo08.socialmeli.entity.Post;
import com.grupo08.socialmeli.repository.PostRepositoryImp;
import com.grupo08.socialmeli.utils.PostMapper;

@Service
public class PostServiceImp implements IPostService {

    @Autowired
    PostRepositoryImp postRepository;

    @Override
    public List<PostDto> getAll() {
        List<PostDto> listPostDtos = PostMapper.ListToDto(postRepository.getAll());
        return listPostDtos;
    }

    @Override
    public ExceptionDto insertPost(PostDto postDto){
        Post post = PostMapper.fromDto(postDto);
        postRepository.insertPost(post);

        return new ExceptionDto("Todo Ok");
    }
}
