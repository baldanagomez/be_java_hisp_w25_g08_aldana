package com.grupo08.socialmeli.service;
import com.grupo08.socialmeli.dto.response.FollowersDto;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.entity.User;
import com.grupo08.socialmeli.exception.BadRequestException;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.dto.response.FollowersCountDto;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.repository.ISellerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.grupo08.socialmeli.repository.BuyerRepositoryImpl;
import com.grupo08.socialmeli.repository.IBuyerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.grupo08.socialmeli.dto.response.FollowedDTO;
import com.grupo08.socialmeli.entity.Buyer;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.entity.User;
import com.grupo08.socialmeli.exception.BadRequestException;
import com.grupo08.socialmeli.utils.TestData;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    IBuyerRepository buyerRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    ISellerRepository sellerRepository;


    @Test
    void follow() {
    }

    @Test

    @DisplayName("Get users sorted by name asc or desc")
    void getFollowersbyId() {


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

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("FEATURE: vendedores seguidos por un comprador")
    class FollowedSellersByBuyer{

        @Test
        @Order(1)
        @DisplayName("No existe usuario/comprador: NotFoundException")
        void getFollowedSellersNotFoundUser() {
            //ARRANGE
            int id = 1000;
            String expectedMessage = "El usuario con el id:"+id+" no se encontró";
            when(buyerRepository.findById(id)).thenReturn(Optional.empty());

            //ACT & ASSERT
            Exception exception =
                    assertThrows(NotFoundException.class,
                            ()-> userService.getFollowedSellers(id,null));

            assertEquals(expectedMessage,exception.getMessage());
        }

        //pending BadRequestException

        @Test
        @Order(2)
        @DisplayName("Existe comprador: ok (seguidos sin ordenar)")
        void getFollowedSellersOk() {
            //ARRANGE
            int id = 1000;
            String name = "TestMan";
            Optional<Buyer> user = Optional.of(
                    TestData.getBuyerWithFollowedSellers(id, name));
            when(buyerRepository.findById(id)).thenReturn(user);

            //ACT
            FollowedDTO followedDTO = userService.getFollowedSellers(id,null);

            //ASSERT
            assertNotNull(followedDTO);
            assertNotNull(followedDTO.getFollowed());
            assertEquals(name,followedDTO.getUserName());
            assertEquals(id,followedDTO.getUserId());
        }

        @Test
        @Order(3)
        @DisplayName("Existe comprador: ok (seguidos ordenados asc)")
        void getFollowedSellersSortedByAsc(){
            //ARRANGE
            int id = 1000;
            String name = "TestMan";
            String order = "name_asc";
            Optional<Buyer> user = Optional.of(
                    TestData.getBuyerWithFollowedSellers(id, name));
            when(buyerRepository.findById(id)).thenReturn(user);

            //ACT
            FollowedDTO followedDTO = userService.getFollowedSellers(id,order);

            //ASSERT
            assertNotNull(followedDTO);
            assertNotNull(followedDTO.getFollowed());
            assertTrue(TestData.isFollowedSellersSorted(
                    followedDTO.getFollowed(),order));

        }

        @Test
        @Order(4)
        @DisplayName("Existe comprador: ok (seguidos ordenados desc)")
        void getFollowedSellersSortedByDesc(){
            //ARRANGE
            int id = 1000;
            String name = "TestMan";
            String order = "name_desc";
            Optional<Buyer> user = Optional.of(
                    TestData.getBuyerWithFollowedSellers(id, name));
            when(buyerRepository.findById(id)).thenReturn(user);

            //ACT
            FollowedDTO followedDTO = userService.getFollowedSellers(id,order);

            //ASSERT
            assertNotNull(followedDTO);
            assertNotNull(followedDTO.getFollowed());
            assertTrue(TestData.isFollowedSellersSorted(
                    followedDTO.getFollowed(),order));

        }

        @Test
        @Order(5)
        @DisplayName("Valor de parametro incorrecto: BadRequestException")
        void getFollowedSellersBadRequestParam() {
            //ARRANGE
            String expectedMessage = "El valor del parámetro order no es correcto";
            int id = 1000;
            String name = "TestMan";
            String order = "name_WRONG";
            Optional<Buyer> user = Optional.of(
                    TestData.getBuyerWithFollowedSellers(id, name));
            when(buyerRepository.findById(id)).thenReturn(user);

            //ACT & ASSERT
            Exception exception =
                    assertThrows(BadRequestException.class,
                            ()-> userService.getFollowedSellers(id,order));

            assertEquals(expectedMessage,exception.getMessage());
        }

    }


    @Test
    void testGetFollowedSellers() {
    }

    @Test
    @DisplayName("T0007| followersCount ok case")
    void countSellerFollowersOkTest() {
        //ARRANGE
        int sellerId = 1;
        when(sellerRepository.findById(sellerId)).thenReturn(Optional.of(new Seller(1, "Brayan", new ArrayList<>(), List.of(new User(1,"follower")))));
        //ACT
        FollowersCountDto countDto = userService.countSellerFollowers(sellerId);
        //ASSERT
        assertEquals(1L,countDto.getFollowersCount());
    }

    @Test
    @DisplayName("T0007| followersCount throwError case")
    void countSellerFollowersThrowErrorTest() {
        //ARRANGE
        when(sellerRepository.findById(anyInt())).thenReturn(Optional.empty());
        //ACT & ASSERT
        assertThrows(NotFoundException.class,() -> userService.countSellerFollowers(anyInt()));
    }

    @Test
    void postSortDate() {
    }

    @Test
    void postSortWeeks() {
    }
}