package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

@WebMvcTest(CardController.class)
@AutoConfigureRestTestClient
public class CardRestControllerGetTests {
	
	@Autowired
	private RestTestClient restTestClient;
	
	@MockitoBean
	CardService cardService;
	
	List<Card> cardList = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		Card card1 = new Card();
		card1.setCardName("Dark Magician");
		card1.setCardType("Normal Monster");
		card1.setQuantity(2);
		card1.setUsername("testUser1");
		card1.setId(0);
		
		
		Card card2 = new Card();
		card2.setCardName("Blue-Eyes White Dragon");
		card2.setCardType("Normal Monster");
		card2.setQuantity(2);
		card2.setUsername("testUser1");
		card2.setId(1);
		
		Card card3 = new Card();
		card3.setCardName("Red-Eyes Black Dragon");
		card3.setCardType("Normal Monster");
		card3.setQuantity(5);
		card3.setUsername("testUser1");
		card3.setId(2);
		
		Card card4 = new Card();
		card4.setCardName("Stardust Dragon");
		card4.setCardType("Synchro Monster");
		card4.setQuantity(3);
		card4.setUsername("testUser1");
		card4.setId(3);
		
		Card card5 = new Card();
		card5.setCardName("Number 39: Utopia");
		card5.setCardType("XYZ Monster");
		card5.setQuantity(1);
		card5.setUsername("testUser1");
		card5.setId(4);
		
		cardList.add(card1);
		cardList.add(card2);
		cardList.add(card3);
		cardList.add(card4);
		cardList.add(card5);
	}
	
	@Test
	@DisplayName("Should return a 401 status because they are unauthorized")
	void getAllShouldReturn401() {
		
		restTestClient.get().uri("/card")
							.exchange()
							.expectStatus()
							.isEqualTo(HttpStatus.UNAUTHORIZED);
		
	}
	
	@Test
	@DisplayName("Should return a 200 status because user is authorized")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void getAllWhenUserAuthorized() {
		
		restTestClient.get().uri("/card")
							.exchange()
							.expectStatus()
							.isEqualTo(HttpStatus.OK);
		
	}
	
	@Test
	@DisplayName("Should return a String with the correct number of cards for user")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void shouldReturnCorrectCardCountString() {
		
		when(cardService.returnCollectionCount("testUser1")).thenReturn(cardList.size());
		
		
		restTestClient.get().uri("/card/countAll")
							.exchange()
							.expectBody(String.class)
							.isEqualTo("testUser1 you currently own 5 cards.");
		
	}
}
