package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	Card card1 = new Card();
	Card card2 = new Card();
	Card card3 = new Card();
	Card card4 = new Card();
	Card card5 = new Card();
	
	@BeforeEach
	void setup() {
		card1.setCardName("Dark Magician");
		card1.setCardType("Normal Monster");
		card1.setQuantity(2);
		card1.setUsername("testUser1");
		card1.setId(0);
		
		card2.setCardName("Blue-Eyes White Dragon");
		card2.setCardType("Normal Monster");
		card2.setQuantity(2);
		card2.setUsername("testUser1");
		card2.setId(1);
		
		card3.setCardName("Red-Eyes Black Dragon");
		card3.setCardType("Normal Monster");
		card3.setQuantity(5);
		card3.setUsername("testUser1");
		card3.setId(2);
		
		card4.setCardName("Stardust Dragon");
		card4.setCardType("Synchro Monster");
		card4.setQuantity(3);
		card4.setUsername("testUser1");
		card4.setId(3);
		
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
	@DisplayName("Endpoint, /card, unauthorized test")
	void ifAccessEndpoint_WhenNoAuthorization_ThenReturn4xx() {
		
		restTestClient.get().uri("/card")
							.exchange()
							.expectStatus()
							.isEqualTo(HttpStatus.UNAUTHORIZED);
		
	}
	
	@Test
	@DisplayName("Endpoint, /card, authorized test")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void ifAccessEndpoint_WhenAuthorized_ThenReturn2xx() {
		
		restTestClient.get().uri("/card")
							.exchange()
							.expectStatus()
							.isEqualTo(HttpStatus.OK);
		
	}
	
	@Test
	@DisplayName("countAll endpoint test")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void whenAccessEndpoint_WhenAuthorized_ThenReturnHowManyCardsOwned() {
		
		when(cardService.returnCollectionCount("testUser1")).thenReturn(cardList.size());
		
		
		restTestClient.get().uri("/card/countAll")
							.exchange()
							.expectBody(String.class)
							.isEqualTo("testUser1 you currently own 5 cards.");
		
	}
	
	@Test
	@DisplayName("Endpoint, /card, test that returns all cards user has.")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void whenAccessEndpoint_WhenAuthorized_ThenReturnCardsOwned() {
		
		when(cardService.getAll("testUser1")).thenReturn(cardList);
		
		String result = restTestClient.get().uri("/card")
							.exchange()
							.expectBody(String.class)
							.toString();
		
		assertThat(result.contains("Card Name: Dark Magician"));
		
	}
	
	@Test
	@DisplayName("Endpoint, /card, test that returns string stating user owns no cards.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void whenAccessEndpoint_WhenWithNoCards_ThenReturnNoCardsOwned() {
		
		String result = restTestClient.get()
							.uri("/card")
							.exchange()
							.expectBody(String.class)
							.toString();
		
		assertThat(result.contains("You own no cards in your collection."));
		
	}
	
	@Test
	@DisplayName("Endpoint, /card/{cardName}, test that returns a card with the exact word in their name.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void whenAccessEndPoint_WhenWithCards_ThenReturnCardsWithExactlyGivenString() {
		
		when(cardService.isCardInDatabase("testUser3", "Dark Magician")).thenReturn(true);
		when(cardService.getCardInfo("testUser3", "Dark Magician")).thenReturn(card1);
		
		String result = restTestClient.get()
							.uri("/card/{cardName}", "Dark Magician")
							.exchange()
							.expectBody(String.class)
							.toString();
		
		assertThat(result.contains("Card Name: Dark Magician"));
	
	}
	
	@Test
	@DisplayName("Endpoint, /card/{cardName}, test that returns a string stating user owns no cards with that word in their name.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void whenAccessEndPoint_WhenWithCards_ThenReturnNoCardInCollectionStringMessage() {
		
		when(cardService.isCardInDatabase("testUser3", "Dark Magician")).thenReturn(false);
		
		String result = restTestClient.get()
						.uri("/card/{cardName}", "Dark Magician")
						.exchange()
						.expectBody(String.class)
						.toString();
		
		assertThat(result.contains("That card does not exist in your collection,"));
	}
	
	//quantity
	@Test
	@DisplayName("Endpoint, /card/quantity/{quantity}, test that returns a string with the cards in user collection with that quantity.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void whenAccessEndPoint_WhendWithCards_ThenReturnCardsWithGivenQuantityStringMessage() {
		
		when(cardService.getCardsByQuantity("testUser3", 2)).thenReturn(cardList.stream().filter(c -> c.getQuantity() == 2).collect(Collectors.toList()));
		
		String result = restTestClient.get()
								.uri("/card/quantity/{quantity}", 2)
								.exchange()
								.expectBody(String.class)
								.toString();
		
		assertThat(result.contains("Card Name: Blue-Eyes"));
	}
	
	
	@Test
	@DisplayName("Endpoint, /card/quantity/{quantity}, test that returns a string stating the user has no cards in their collection with that quantity.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void whenAccessEndPoint_WhenWithCards_ThenReturnNoCardsWithGivenQuantityStringMessage() {
		
		when(cardService.getCardsByQuantity("testUser3", 4)).thenReturn(cardList.stream().filter(c -> c.getQuantity() == 4).collect(Collectors.toList()));
		
		String result = restTestClient.get()
							.uri("/card/quantity/{quantity}", 4)
							.exchange()
							.expectBody(String.class)
							.toString();
		
		assertThat(result.contains("You have no cards in your collection that are exactly"));
	}
	
	@Test
	@DisplayName("Endpoint, /card/word/{wordToSearch}, test that returns a string with the cards that contain the given substring in their name.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void whenAccessEndPoint_WhenWithCards_ThenReturnCardInfoWithGivenSubtringMessage() {
		
		when(cardService.getCardsWithWordInName("testUser3", "dragon")).thenReturn(cardList.stream().filter(c -> c.getCardName().matches("^[dD]ragon")).collect(Collectors.toList()));
		
		String result = restTestClient.get()
							.uri("/card/word/{wordToSearch}", "dragon")
							.exchange()
							.expectBody(String.class)
							.toString();
		
		assertThat(result.contains("Card Name: Blue-Eyes White Dragon"));
	}
	
	@Test
	@DisplayName("Endpoint, /card/word/{wordToSearch}, test that returns a string stating user has no cards with substring in their name.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void whenAccessEndPoint_WithCards_ThenReturnNoCardWithSubStringInfoStringMessage() {
		
		when(cardService.getCardsWithWordInName("testUser3", "Guardian")).thenReturn(cardList.stream().filter(c -> c.getCardName().matches("^[gG]uardian")).collect(Collectors.toList()));
		
		String result = restTestClient.get()
							.uri("/card/word/{wordToSearch}", "Guardian")
							.exchange()
							.expectBody(String.class)
							.toString();
		
		assertThat(result.contains("No cards in your collection have the word:"));
		
	}
}
