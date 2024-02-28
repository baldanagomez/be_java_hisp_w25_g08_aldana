package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.entity.Buyer;
import com.grupo08.socialmeli.entity.Post;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.repository.PostRepositoryImp;
import com.grupo08.socialmeli.repository.SellerRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grupo08.socialmeli.dto.PostDto;
import com.grupo08.socialmeli.dto.response.FollowingPostDto;
import com.grupo08.socialmeli.entity.Product;
import com.grupo08.socialmeli.repository.BuyerRepositoryImpl;
import com.grupo08.socialmeli.repository.IBuyerRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Mock
    BuyerRepositoryImpl buyerRepository;

    @Mock
    SellerRepositoryImpl sellerRepository;

    @Mock
    PostRepositoryImp postRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void follow() {
    }

    @Test
    @DisplayName("Get users sorted by name asc or desc")
    void getFollowersbyId() {
        // Arrange
        FollowingPostDto expectedfollowingPost = new FollowingPostDto(
            1,
            List.of(
                new PostDto(
                    1,
                    LocalDate.of(2024, 02, 22),
                    new Product(
                        1,
                        "Silla Gamer",
                        "Gamer",
                        "Racer",
                        "Blue & Green",
                        "Cheap edition"
                    ),
                    1,
                    350000.0
                ),
                new PostDto(
                        1,
                        LocalDate.of(2024, 02, 22),
                        new Product(
                                12,
                                "Silla Gamer",
                                "Gamer",
                                "Racer",
                                "Blue & Green",
                                "Cheap edition"
                        ),
                        1,
                        350000.0
                )
            )
        );


        Post post1 = new Post(
                1,
                LocalDate.of(2024, 02, 22),
                new Product(
                        1,
                        "Silla Gamer",
                        "Gamer",
                        "Racer",
                        "Blue & Green",
                        "Cheap edition"
                ),
                1,
                350000.0
        );

        Post post2 = new Post(
                1,
                LocalDate.of(2024, 02, 22),
                new Product(
                        12,
                        "Silla Gamer",
                        "Gamer",
                        "Racer",
                        "Blue & Green",
                        "Cheap edition"
                ),
                1,
                350000.0
        );

        Seller seller = new Seller(
                1, "Andres Seller Mock", List.of(post1, post2), List.of(
                        new Buyer(
                                1, "Andres Buyer Mock", List.of()
                        )
                )
        );

        Buyer buyer1 = new Buyer(
                1, "Andres Buyer Mock", List.of(seller)
        );

        when(postRepository.getByIdUser(1L)).thenReturn(List.of(post1, post2));
        when(buyerRepository.findById(1)).thenReturn(Optional.of(buyer1));

        FollowingPostDto followingPostDto = userService.postSortDate(1, "date_desc");
        assertEquals(expectedfollowingPost, followingPostDto);
    }

    @Test
    void getFollowers() {
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