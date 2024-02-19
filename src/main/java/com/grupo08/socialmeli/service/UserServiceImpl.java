package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowDto;
import com.grupo08.socialmeli.entity.Buyer;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.exception.BadRequestException;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.repository.IBuyerRepository;
import com.grupo08.socialmeli.repository.ISellerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {

    IBuyerRepository buyerRepository;
    ISellerRepository sellerRepository;

    public UserServiceImpl(IBuyerRepository buyerRepository, ISellerRepository sellerRepository) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public FollowDto follow(int idBuyer, int idSeller) {

        Optional<Buyer> buyer = buyerRepository.findById(idBuyer);

        Optional<Seller> seller = sellerRepository.findById(idSeller);

        if(buyer.isEmpty()) throw new NotFoundException("No se encuentra comprador con ese ID.");

        if(seller.isEmpty()) throw new NotFoundException("No hay vendedor con ese ID.");

        Optional<Seller> sellerToRemove = buyer.get().getFollowing().stream()
                .filter(s -> s.getId() == idSeller)
                .findFirst();

        if (sellerToRemove.isPresent()) {
            throw new BadRequestException("No puedes seguir un vendedor que ya sigues.");
        }
        
        buyer.get().addFollowingSeller(seller.get());

        return new FollowDto(idSeller, seller.get().getName());
    }
}
