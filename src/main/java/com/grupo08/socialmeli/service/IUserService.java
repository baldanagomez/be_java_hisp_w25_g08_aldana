package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowDto;
import com.grupo08.socialmeli.dto.response.FollowedDTO;

public interface IUserService {

    public FollowDto follow(int idBuyer, int idSeller);

    FollowedDTO getFollowedSellers(int userId);
}
