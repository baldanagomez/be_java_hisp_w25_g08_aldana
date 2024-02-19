package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowDto;
import com.grupo08.socialmeli.dto.response.FollowedDTO;
import com.grupo08.socialmeli.dto.response.FollowersCountDto;
import com.grupo08.socialmeli.dto.response.FollowersDto;

import java.util.Optional;

public interface IUserService {
    FollowDto follow(int idBuyer, int idSeller);

    void unfollow(int idBuyer, int idSeller);

    FollowedDTO getFollowedSellers(int userId, String order);

    public FollowersDto getFollowers( int idSeller, String order);


    FollowedDTO getFollowedSellers(int userId);

    FollowersCountDto countSellerFollowers(int userId);

}
