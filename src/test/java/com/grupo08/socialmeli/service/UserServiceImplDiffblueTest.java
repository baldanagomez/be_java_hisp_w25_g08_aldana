package com.grupo08.socialmeli.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.grupo08.socialmeli.dto.response.FollowingPostDto;
import com.grupo08.socialmeli.entity.Buyer;
import com.grupo08.socialmeli.entity.Post;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.exception.BadRequestException;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.repository.IBuyerRepository;
import com.grupo08.socialmeli.repository.IPostRepository;
import com.grupo08.socialmeli.repository.ISellerRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserServiceImplDiffblueTest {
    @MockBean
    private IBuyerRepository iBuyerRepository;

    @MockBean
    private IPostRepository iPostRepository;

    @MockBean
    private ISellerRepository iSellerRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#postSortDate(Integer, String)}
     */
    @Test
    void testPostSortDate() {
        // Arrange, Act and Assert
        assertThrows(BadRequestException.class, () -> userServiceImpl.postSortDate(1, "Order"));
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortDate(Integer, String)}
     */
    @Test
    void testPostSortDate2() {
        // Arrange
        ArrayList<Seller> following = new ArrayList<>();
        Optional<Buyer> ofResult = Optional.of(new Buyer(1, "date_desc", following));
        when(iBuyerRepository.findById(anyInt())).thenReturn(ofResult);

        // Act
        FollowingPostDto actualPostSortDateResult = userServiceImpl.postSortDate(1, "date_desc");

        // Assert
        verify(iBuyerRepository).findById(eq(1));
        assertEquals(1, actualPostSortDateResult.getUserId().intValue());
        assertEquals(following, actualPostSortDateResult.getPost());
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortDate(Integer, String)}
     */
    @Test
    void testPostSortDate3() {
        // Arrange
        Buyer buyer = mock(Buyer.class);
        when(buyer.getId()).thenReturn(1);
        when(buyer.getName()).thenReturn("Name");
        ArrayList<Seller> sellerList = new ArrayList<>();
        when(buyer.getFollowing()).thenReturn(sellerList);
        Optional<Buyer> ofResult = Optional.of(buyer);
        when(iBuyerRepository.findById(anyInt())).thenReturn(ofResult);

        // Act
        FollowingPostDto actualPostSortDateResult = userServiceImpl.postSortDate(1, "date_desc");

        // Assert
        verify(buyer).getFollowing();
        verify(buyer).getId();
        verify(buyer).getName();
        verify(iBuyerRepository).findById(eq(1));
        assertEquals(1, actualPostSortDateResult.getUserId().intValue());
        assertEquals(sellerList, actualPostSortDateResult.getPost());
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortDate(Integer, String)}
     */
    @Test
    void testPostSortDate4() {
        // Arrange
        Optional<Buyer> emptyResult = Optional.empty();
        when(iBuyerRepository.findById(anyInt())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NotFoundException.class, () -> userServiceImpl.postSortDate(1, "date_desc"));
        verify(iBuyerRepository).findById(eq(1));
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortDate(Integer, String)}
     */
    @Test
    void testPostSortDate5() {
        // Arrange
        Buyer buyer = mock(Buyer.class);
        when(buyer.getId()).thenReturn(1);
        when(buyer.getName()).thenReturn("Name");
        ArrayList<Seller> sellerList = new ArrayList<>();
        when(buyer.getFollowing()).thenReturn(sellerList);
        Optional<Buyer> ofResult = Optional.of(buyer);
        when(iBuyerRepository.findById(anyInt())).thenReturn(ofResult);

        // Act
        FollowingPostDto actualPostSortDateResult = userServiceImpl.postSortDate(1, "date_asc");

        // Assert
        verify(buyer).getFollowing();
        verify(buyer).getId();
        verify(buyer).getName();
        verify(iBuyerRepository).findById(eq(1));
        assertEquals(1, actualPostSortDateResult.getUserId().intValue());
        assertEquals(sellerList, actualPostSortDateResult.getPost());
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortDate(Integer, String)}
     */
    @Test
    void testPostSortDate6() {
        // Arrange
        ArrayList<Seller> sellerList = new ArrayList<>();
        ArrayList<Post> posts = new ArrayList<>();
        sellerList.add(new Seller(1, "date_desc", posts, new ArrayList<>()));
        Buyer buyer = mock(Buyer.class);
        when(buyer.getId()).thenReturn(1);
        when(buyer.getName()).thenReturn("Name");
        when(buyer.getFollowing()).thenReturn(sellerList);
        Optional<Buyer> ofResult = Optional.of(buyer);
        when(iBuyerRepository.findById(anyInt())).thenReturn(ofResult);
        when(iPostRepository.getByIdUser(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        // Act
        FollowingPostDto actualPostSortDateResult = userServiceImpl.postSortDate(1, "date_desc");

        // Assert
        verify(buyer).getFollowing();
        verify(buyer).getId();
        verify(buyer).getName();
        verify(iBuyerRepository).findById(eq(1));
        verify(iPostRepository, atLeast(1)).getByIdUser(Mockito.<Long>any());
        assertEquals(1, actualPostSortDateResult.getUserId().intValue());
        assertEquals(posts, actualPostSortDateResult.getPost());
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortDate(Integer, String)}
     */
    @Test
    void testPostSortDate7() {
        // Arrange
        Seller seller = mock(Seller.class);
        when(seller.getId()).thenReturn(1);
        when(seller.getName()).thenReturn("Name");

        ArrayList<Seller> sellerList = new ArrayList<>();
        sellerList.add(seller);
        Buyer buyer = mock(Buyer.class);
        when(buyer.getId()).thenReturn(1);
        when(buyer.getName()).thenReturn("Name");
        when(buyer.getFollowing()).thenReturn(sellerList);
        Optional<Buyer> ofResult = Optional.of(buyer);
        when(iBuyerRepository.findById(anyInt())).thenReturn(ofResult);
        ArrayList<Post> postList = new ArrayList<>();
        when(iPostRepository.getByIdUser(Mockito.<Long>any())).thenReturn(postList);

        // Act
        FollowingPostDto actualPostSortDateResult = userServiceImpl.postSortDate(1, "date_desc");

        // Assert
        verify(buyer).getFollowing();
        verify(buyer).getId();
        verify(seller).getId();
        verify(buyer).getName();
        verify(seller).getName();
        verify(iBuyerRepository).findById(eq(1));
        verify(iPostRepository, atLeast(1)).getByIdUser(Mockito.<Long>any());
        assertEquals(1, actualPostSortDateResult.getUserId().intValue());
        assertEquals(postList, actualPostSortDateResult.getPost());
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortWeeks(Integer)}
     */
    @Test
    void testPostSortWeeks() {
        // Arrange
        ArrayList<Seller> following = new ArrayList<>();
        Optional<Buyer> ofResult = Optional.of(new Buyer(1, "Name", following));
        when(iBuyerRepository.findById(anyInt())).thenReturn(ofResult);

        // Act
        FollowingPostDto actualPostSortWeeksResult = userServiceImpl.postSortWeeks(1);

        // Assert
        verify(iBuyerRepository).findById(eq(1));
        assertEquals(1, actualPostSortWeeksResult.getUserId().intValue());
        assertEquals(following, actualPostSortWeeksResult.getPost());
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortWeeks(Integer)}
     */
    @Test
    void testPostSortWeeks2() {
        // Arrange
        Buyer buyer = mock(Buyer.class);
        when(buyer.getId()).thenReturn(1);
        when(buyer.getName()).thenReturn("Name");
        ArrayList<Seller> sellerList = new ArrayList<>();
        when(buyer.getFollowing()).thenReturn(sellerList);
        Optional<Buyer> ofResult = Optional.of(buyer);
        when(iBuyerRepository.findById(anyInt())).thenReturn(ofResult);

        // Act
        FollowingPostDto actualPostSortWeeksResult = userServiceImpl.postSortWeeks(1);

        // Assert
        verify(buyer).getFollowing();
        verify(buyer).getId();
        verify(buyer).getName();
        verify(iBuyerRepository).findById(eq(1));
        assertEquals(1, actualPostSortWeeksResult.getUserId().intValue());
        assertEquals(sellerList, actualPostSortWeeksResult.getPost());
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortWeeks(Integer)}
     */
    @Test
    void testPostSortWeeks3() {
        // Arrange
        Optional<Buyer> emptyResult = Optional.empty();
        when(iBuyerRepository.findById(anyInt())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NotFoundException.class, () -> userServiceImpl.postSortWeeks(1));
        verify(iBuyerRepository).findById(eq(1));
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortWeeks(Integer)}
     */
    @Test
    void testPostSortWeeks4() {
        // Arrange
        ArrayList<Seller> sellerList = new ArrayList<>();
        ArrayList<Post> posts = new ArrayList<>();
        sellerList.add(new Seller(1, "Name", posts, new ArrayList<>()));
        Buyer buyer = mock(Buyer.class);
        when(buyer.getId()).thenReturn(1);
        when(buyer.getName()).thenReturn("Name");
        when(buyer.getFollowing()).thenReturn(sellerList);
        Optional<Buyer> ofResult = Optional.of(buyer);
        when(iBuyerRepository.findById(anyInt())).thenReturn(ofResult);
        when(iPostRepository.getByIdUser(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        // Act
        FollowingPostDto actualPostSortWeeksResult = userServiceImpl.postSortWeeks(1);

        // Assert
        verify(buyer).getFollowing();
        verify(buyer).getId();
        verify(buyer).getName();
        verify(iBuyerRepository).findById(eq(1));
        verify(iPostRepository, atLeast(1)).getByIdUser(Mockito.<Long>any());
        assertEquals(1, actualPostSortWeeksResult.getUserId().intValue());
        assertEquals(posts, actualPostSortWeeksResult.getPost());
    }

    /**
     * Method under test: {@link UserServiceImpl#postSortWeeks(Integer)}
     */
    @Test
    void testPostSortWeeks5() {
        // Arrange
        Seller seller = mock(Seller.class);
        when(seller.getId()).thenReturn(1);
        when(seller.getName()).thenReturn("Name");

        ArrayList<Seller> sellerList = new ArrayList<>();
        sellerList.add(seller);
        Buyer buyer = mock(Buyer.class);
        when(buyer.getId()).thenReturn(1);
        when(buyer.getName()).thenReturn("Name");
        when(buyer.getFollowing()).thenReturn(sellerList);
        Optional<Buyer> ofResult = Optional.of(buyer);
        when(iBuyerRepository.findById(anyInt())).thenReturn(ofResult);
        ArrayList<Post> postList = new ArrayList<>();
        when(iPostRepository.getByIdUser(Mockito.<Long>any())).thenReturn(postList);

        // Act
        FollowingPostDto actualPostSortWeeksResult = userServiceImpl.postSortWeeks(1);

        // Assert
        verify(buyer).getFollowing();
        verify(buyer).getId();
        verify(seller).getId();
        verify(buyer).getName();
        verify(seller).getName();
        verify(iBuyerRepository).findById(eq(1));
        verify(iPostRepository, atLeast(1)).getByIdUser(Mockito.<Long>any());
        assertEquals(1, actualPostSortWeeksResult.getUserId().intValue());
        assertEquals(postList, actualPostSortWeeksResult.getPost());
    }
}
