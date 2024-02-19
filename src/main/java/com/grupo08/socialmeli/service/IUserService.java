package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowDto;

public interface IUserService {

    FollowDto follow(int idBuyer, int idSeller);

    void unfollow(int idBuyer, int idSeller);

}
