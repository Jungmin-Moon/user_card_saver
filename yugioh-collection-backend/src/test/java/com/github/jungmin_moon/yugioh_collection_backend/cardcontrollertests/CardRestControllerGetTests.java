package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
		
		Card card1 = new Card();
		card1.setCardName("Dark Magician");
		card1.setCardType("Normal Monster");
		card1.setQuantity(2);
		card1.setUsername("testUser1");
		card1.setId(0);
		
		List<Card> cardList = new ArrayList<>();
		cardList.add(card1);
		
		when(cardService.returnCollectionCount("testUser1")).thenReturn(cardList.size());
		
		
		restTestClient.get().uri("/card/countAll")
							.exchange()
							.expectBody(String.class)
							.isEqualTo("testUser1 you currently own 1 cards.");
		
	}
}
