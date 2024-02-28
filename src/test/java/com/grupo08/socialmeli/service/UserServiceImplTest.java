package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowDto;
import com.grupo08.socialmeli.dto.response.FollowedDTO;
import com.grupo08.socialmeli.entity.Buyer;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.entity.User;
import com.grupo08.socialmeli.exception.BadRequestException;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.repository.IBuyerRepository;
import com.grupo08.socialmeli.utils.TestData;
import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.number.OrderingComparison;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    IBuyerRepository buyerRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void follow() {
    }

    @Test
    void getFollowers() {
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
    void countSellerFollowers() {
    }

    @Test
    void postSortDate() {
    }

    @Test
    void postSortWeeks() {
    }
}