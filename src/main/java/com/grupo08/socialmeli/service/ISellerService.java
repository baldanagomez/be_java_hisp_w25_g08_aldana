package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowDto;

public interface ISellerService {

    public FollowDto follow(int idBuyer, int idSeller);

}
