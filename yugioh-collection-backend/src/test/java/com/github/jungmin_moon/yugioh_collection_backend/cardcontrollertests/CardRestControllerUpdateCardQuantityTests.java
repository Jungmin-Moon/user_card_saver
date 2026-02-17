package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

@WebMvcTest(CardController.class)
public class CardRestControllerUpdateCardQuantityTests {

	@MockitoBean
	private CardService cardService;
	
	@Autowired
	private MockMvc mvc;
	
	Card card = new Card();
	
	@BeforeEach
	void setup()
	{
		card.setCardName("Dark Magician");
		card.setCardType("Normal Monster");
		card.setQuantity(3);
		card.setUsername("testUser3");
		card.setId(233);
	}
	
	@Test
	@DisplayName("Test to make sure that users without authorization can't access the endpoint.")
	void givenNoAuthorization_WhenAccessingEndpoint_Return4XX() {
		
	}
	
	@Test
	@DisplayName("Test to make sure does not exist message appears when user is authorized and they don't own the card.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardQuantityRequest_WhenCardNotExist_ThenReturnNotInCollectionMessage() {
		
	}
	
	@Test
	@DisplayName("Test to make sure a successful message appears when user is authorized and they do own the card.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardQuantityRequest_WhenCardExists_ThenReturn2XXSuccessAndStringMessage() {
		
	}
	
	@Test
	@DisplayName("Test to make sure a message that states the card name value was bad is returned.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardQuantityRequest_WhenBadCardName_ThenReturn4XXAndStringMessage() {
		
	}
	
	@Test
	@DisplayName("Test to make sure a message that states the quantity value was bad is returned.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardQuantityRequest_WhenBadQuantity_ThenReturn4XXAndStringMessage() {
		
	}
	
	@Test
	@DisplayName("Test to make sure a message that states that both values were bad is returned.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardQuantityRequest_WhenBadCardNameAndQuantity_ThenReturn4XXAndStringMessage() {
		
	}
}
