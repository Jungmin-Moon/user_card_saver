package com.github.jungmin_moon.yugioh_collection_backend.deckcontrollertests;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.github.jungmin_moon.yugioh_collection_backend.entities.Decks;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.DeckRepository;
import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.DeckController;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;
import com.github.jungmin_moon.yugioh_collection_backend.services.DeckService;

@WebMvcTest(DeckController.class)
@AutoConfigureRestTestClient
public class DeckRestControllerGetTests {
	
	@Autowired
	private RestTestClient restTestClient;
	
	@MockitoBean
	private DeckService deckService;
	
	@MockitoBean
	private DeckRepository deckRepository;
	
	@MockitoBean
	private CardService cardService;
	
	List<Decks> decks = new ArrayList<>();
	List<Decks> emptyList = new ArrayList<>();
	
	Decks deck1 = new Decks();
	Decks deck2 = new Decks();
	Decks deck3 = new Decks();
	
	@BeforeEach() 
	void setup() {
		
		deck1.setDeckName("Dark Magician Deck");
		deck1.setUsername("testUser1");
		
		deck2.setDeckName("Blue-Eyes 2026");
		deck2.setUsername("testUser1");
		
		deck3.setDeckName("Yummy Mitsurugi");
		deck3.setUsername("testUser1");
		
		decks.add(deck1);
		decks.add(deck2);
		decks.add(deck3);
	}

	@Test
	@DisplayName("Endpoint test to make sure if user is not authorized they get a HTTP UNAUTHORIZED status.")
	void givenAccessingEndpoint_WhenNotAuthorized_ThenReturn4xx() {
		
		restTestClient.get().uri("/deck")
					.exchange()
					.expectStatus()
					.isEqualTo(HttpStatus.UNAUTHORIZED);
	}
	
	@Test
	@DisplayName("Endpoint test to make sure if user is authorized they get a HTTP OK status.")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void givenAccessingEndpoint_WhenAuthorized_ThenReturn2xx() {
		
		restTestClient.get()
						.uri("/deck")
						.exchange()
						.expectStatus()
						.isEqualTo(HttpStatus.OK);
	}
	
	@Test
	@DisplayName("When calling the endpoint /deck if user has decks created the name of each deck will be returned.")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void givenCallingGetAll_WhenHaveDecks_ThenReturnListOfDeckNames() {
		
		when(deckService.getAll("testUser1")).thenReturn("decks");
		
		String getAllResultWithDecks = restTestClient.get()
													.uri("/deck")
													.exchange()
													.expectBody(String.class)
													.toString();
		
		assertThat(getAllResultWithDecks.contains("Dark Magician Deck"));
		
	}
	
	
	@Test
	@DisplayName("When calling the endpoint /deck if user has no decks created then a string message stating so is returned.")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void givenCallingGetAll_WhenHaveNoDecks_ThenReturnUserHasNoDecksMessage() {
		
		when(deckService.getAll("testUser1")).thenReturn("You do not have any decks created.");
		
		String getAllResultWithNoDecks = restTestClient.get()
														.uri("/deck")
														.exchange()
														.expectBody(String.class)
														.toString();
		
		assertThat(getAllResultWithNoDecks.contains("You do not have any decks created."));
		
	}
	
	
}
