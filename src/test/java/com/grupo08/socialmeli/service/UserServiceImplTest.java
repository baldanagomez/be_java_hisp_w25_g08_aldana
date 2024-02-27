package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowersDto;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.entity.User;
import com.grupo08.socialmeli.exception.BadRequestException;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.repository.SellerRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private SellerRepositoryImpl sellerRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void follow() {
    }

    @Test
    @DisplayName("Camino feliz T-0003 asc")
    void getFollowers() {
        //Arrange
        Integer userId=1;
        String order="name_asc";

        List<User> followers = new ArrayList<>(List.of(new User(2,"Fabian"),new User(3,"Stiven")));

        Seller seller = new Seller(1, "Fabian Rodriguez", new ArrayList<>(),followers);
        Optional<Seller> optionalSeller = Optional.of(seller);

        when(sellerRepository.findById(userId)).thenReturn(optionalSeller);

        //Act
        FollowersDto result = userService.getFollowers(userId,order);

        //Assert
        assertEquals(userId, result.getUserId());
        assertEquals("Fabian Rodriguez", result.getUserName());
        assertEquals(seller.getFollowers().size(), result.getFollowers().size());
        assertEquals(seller.getFollowers().get(0).getId(),result.getFollowers().get(0).getUserId());
    }

    @Test
    @DisplayName("Camino feliz T-0003 desc")
    void getFollowersDesc() {
        //Arrange
        Integer userId=1;
        String order="name_desc";

        List<User> followers = new ArrayList<>(List.of(new User(3,"Stiven"), new User(2,"Fabian")));

        Seller seller = new Seller(1, "Fabian Rodriguez", new ArrayList<>(),followers);
        Optional<Seller> optionalSeller = Optional.of(seller);

        when(sellerRepository.findById(userId)).thenReturn(optionalSeller);

        //Act
        FollowersDto result = userService.getFollowers(userId,order);

        //Assert
        assertEquals(userId, result.getUserId());
        assertEquals("Fabian Rodriguez", result.getUserName());
        assertEquals(seller.getFollowers().size(), result.getFollowers().size());
        assertEquals(seller.getFollowers().get(0).getId(),result.getFollowers().get(0).getUserId());
    }

    @Test
    @DisplayName("Camino error usuario no existe T-0003")
    void getFollowersFail() {
        //Arrange
        Integer userId=1;
        String order="test";

        List<User> followers = new ArrayList<>(List.of(new User(3,"Stiven"), new User(2,"Fabian")));

        Seller seller = new Seller(1, "Fabian Rodriguez", new ArrayList<>(),followers);
        Optional<Seller> optionalSeller = Optional.of(seller);

        when(sellerRepository.findById(userId)).thenReturn(optionalSeller);


        //Act && Assert
        assertThrows(BadRequestException.class, ()->{userService.getFollowers(userId,order);});

    }
    @Test
    @DisplayName("Camino error orden invalido T-0003")
    void getFollowersFailOrder() {
        //Arrange
        Integer userId=1;
        String order ="name_desc";

        Optional<Seller> optionalSeller = Optional.empty();

        when(sellerRepository.findById(userId)).thenReturn(optionalSeller);

        //Act && Assert
        assertThrows(NotFoundException.class, ()->{userService.getFollowers(userId,order);});

    }

    @Test
    void unfollow() {
    }

    @Test
    void getFollowedSellers() {
    }

    @Test
    void testGetFollowedSellers() {
    }

    @Test
    void countSellerFollowers() {
    }

    @Test
    void postSortDate() {
    }

    @Test
    void postSortWeeks() {
    }
}