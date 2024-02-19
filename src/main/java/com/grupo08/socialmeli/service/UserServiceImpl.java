package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowDto;
import com.grupo08.socialmeli.dto.response.FollowersDto;
import com.grupo08.socialmeli.dto.response.FollowedDTO;
import com.grupo08.socialmeli.dto.response.PostDto;
import com.grupo08.socialmeli.entity.Buyer;
import com.grupo08.socialmeli.entity.Post;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.entity.User;
import com.grupo08.socialmeli.exception.BadRequestException;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.repository.IBuyerRepository;
import com.grupo08.socialmeli.repository.ISellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public FollowersDto getFollowers(int idSeller) {
        Optional<Seller> seller = sellerRepository.findById(idSeller);

        if(seller.isEmpty()) throw new NotFoundException("No hay vendedor con ese ID.");

        List<User> followers = seller.get().getFollowers();

        FollowersDto followersDto = new FollowersDto(
                seller.get().getId(),
                seller.get().getName(),
                followers.stream().map(this::convertFollowerDto).collect(Collectors.toList())
        );
        return followersDto;
    }

    private FollowDto convertFollowerDto(User user){
        return new FollowDto(user.getId(), user.getName());
    }

    @Override
    public void unfollow(int idBuyer, int idSeller) {

        Optional<Buyer> buyer = buyerRepository.findById(idBuyer);

        Optional<Seller> seller = sellerRepository.findById(idSeller);

        if(buyer.isEmpty()) throw new NotFoundException("No se encuentra comprador con ese ID.");

        if(seller.isEmpty()) throw new NotFoundException("No hay vendedor con ese ID.");

        buyer.get().unFollowSeller(seller.get());

    }

    @Override
    public FollowedDTO getFollowedSellers(int userId) {
        Optional<Buyer> user = this.buyerRepository.findById(userId);
        if(user.isEmpty()){
            throw new NotFoundException("El usuario con el id:"+userId+" no se encontró");
        }

        if(!(user.get() instanceof Buyer)){
            throw new BadRequestException("El usuario con el id:"+userId+" no es un comprador");
        }
        Buyer buyer = user.get();

        FollowedDTO buyerResponseDTO = new FollowedDTO();
        buyerResponseDTO.setUser_id(buyer.getId());
        buyerResponseDTO.setUser_name(buyer.getName());

        List<Seller> followedSellers = buyer.getFollowing();
        List<FollowDto> followedSellersDTO = new ArrayList<>();

        for(Seller seller: followedSellers){
            followedSellersDTO.add(new FollowDto(seller.getId(),seller.getName()));
        }

        buyerResponseDTO.setFollowed(followedSellersDTO);

        return buyerResponseDTO;
    }

    @Override
    public List<PostDto> postSortWeeks(Long idUser) {
        FollowedDTO vendedoresSeguidos= getFollowedSellers((int) idUser.longValue());
        List<Integer> listaDeIdsDeVendedores=vendedoresSeguidos.getFollowed().stream().map(FollowDto::getUser_id).toList();
        List<Post> listaDePost= new ArrayList<>();
        for(Integer id:listaDeIdsDeVendedores  ){
            listaDePost.addAll(sellerRepository.findById(id).get().getPosts());
        }
        LocalDate now= LocalDate.now();
        LocalDate afterweeks=LocalDate.now().minusWeeks(2);
        listaDePost.stream().filter(x->x.getDate().isBefore(afterweeks)&&x.getDate().isAfter(now)).toList()
                .stream().map();
        return null;
    }
}
