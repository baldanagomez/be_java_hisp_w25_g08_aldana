package com.grupo08.socialmeli.service;

import com.grupo08.socialmeli.dto.response.FollowDto;
import com.grupo08.socialmeli.entity.Buyer;
import com.grupo08.socialmeli.entity.Seller;
import com.grupo08.socialmeli.entity.User;
import com.grupo08.socialmeli.exception.NotFoundException;
import com.grupo08.socialmeli.repository.BuyerRepositoryImpl;
import com.grupo08.socialmeli.repository.SellerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Mock
    SellerRepositoryImpl sellerRepository;

    @Mock
    BuyerRepositoryImpl buyerRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp(){
    }

    @Test
    @DisplayName("T0001_Ok")
    public void testFollowSuccess() {
        int existingBuyerId =  1; // ID de comprador existente
        int existingSellerId =  2; // ID de vendedor existente

        // Configura el mock para que devuelva objetos válidos
        Buyer mockBuyer = new Buyer(existingBuyerId, "Fabian", new ArrayList<>()); // Asume que Buyer y Seller son clases que existen
        Seller mockSeller = new Seller(existingSellerId, "Juan", new ArrayList<>(), new ArrayList<>());

        when(buyerRepository.findById(existingBuyerId)).thenReturn(Optional.of(mockBuyer));
        when(sellerRepository.findById(existingSellerId)).thenReturn(Optional.of(mockSeller));

        // Ejecuta el método a probar
        FollowDto result = userService.follow(1, 2);

        // Verifica que el resultado es el esperado
        assertNotNull(result);
        assertEquals(existingSellerId, result.getUserId());
        assertEquals(mockSeller.getName(), result.getUserName());

        // Verifica que el método findById fue llamado con los ID correctos
        verify(buyerRepository).findById(existingBuyerId);
        verify(sellerRepository).findById(existingSellerId);

        // Verifica que se añadió el vendedor a seguir al comprador y viceversa
        assertTrue(mockBuyer.getFollowing().contains(mockSeller));
        assertTrue(mockSeller.getFollowers().contains(mockBuyer));
    }

    @Test
    @DisplayName("T0001_buyer_not_found")
    void followBuyerNotFound() {

        int nonExistentBuyerId = 123;
        int existingSellerId = 3;

        // Configura el mock para que devuelva un Optional vacío
        when(buyerRepository.findById(nonExistentBuyerId)).thenReturn(Optional.empty());
        when(sellerRepository.findById(existingSellerId)).thenReturn(Optional.of(new Seller())); // Asume que Seller es una clase que existe

        // Ejecuta el método a probar
        assertThrows(NotFoundException.class, () ->
            userService.follow(nonExistentBuyerId, existingSellerId));

        // Verifica que el método findById fue llamado con el ID correcto
        verify(buyerRepository).findById(nonExistentBuyerId);
        verify(sellerRepository).findById(existingSellerId);
    }

    @Test
    @DisplayName("T0001_seller_not_found")
    void followSellerNotFound() {

        int existentBuyerId = 1;
        int nonExistentSellerId = 124;

        when(buyerRepository.findById(existentBuyerId)).thenReturn(Optional.of(new Buyer()));
        when(sellerRepository.findById(nonExistentSellerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                userService.follow(existentBuyerId, nonExistentSellerId));

        verify(buyerRepository).findById(existentBuyerId);
        verify(sellerRepository).findById(nonExistentSellerId);
    }

    @Test
    void getFollowers() {
    }

    @Test
    @DisplayName("T0002_OK")
    void unfollowOk() {
         int existingBuyerId = 1;
         int existingSellerId = 2;

         Buyer mockBuyer = new Buyer(existingBuyerId, "Juan", new ArrayList<>(
                 List.of(new Seller(existingSellerId, "Andrés", new ArrayList<>(), new ArrayList<>()))
         ));
         Seller mockSeller = new Seller(existingSellerId, "Andrés", new ArrayList<>(), new ArrayList<>(
                 List.of(new Buyer(existingBuyerId, "Juan", new ArrayList<>()))
         ));

         when(buyerRepository.findById(existingBuyerId)).thenReturn(Optional.of(mockBuyer));
         when(sellerRepository.findById(existingSellerId)).thenReturn(Optional.of(mockSeller));

         userService.unfollow(existingBuyerId, existingSellerId);

         assertFalse(mockBuyer.getFollowing().contains(mockSeller));
         assertFalse(mockSeller.getFollowers().contains(mockBuyer));
    }

    @Test
    @DisplayName("T0002_buyer_not_found")
    void unfollow_buyer_notfound() {
        int nonExistingBuyerId = 12;
        int existingSellerId = 2;

        when(buyerRepository.findById(nonExistingBuyerId)).thenReturn(Optional.empty());
        when(sellerRepository.findById(existingSellerId)).thenReturn(Optional.of(new Seller()));

        assertThrows(NotFoundException.class, () -> userService.unfollow(nonExistingBuyerId, existingSellerId));

        verify(buyerRepository).findById(nonExistingBuyerId);
        verify(sellerRepository).findById(existingSellerId);
    }

    @Test
    @DisplayName("T0002_seller_not_found")
    void unfollow_seller_notfound(){
        int existingBuyerId = 12;
        int nonExistingSellerId = 2;

        when(buyerRepository.findById(existingBuyerId)).thenReturn(Optional.of(new Buyer()));
        when(sellerRepository.findById(nonExistingSellerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.unfollow(existingBuyerId, nonExistingSellerId));

        verify(buyerRepository).findById(existingBuyerId);
        verify(sellerRepository).findById(nonExistingSellerId);
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