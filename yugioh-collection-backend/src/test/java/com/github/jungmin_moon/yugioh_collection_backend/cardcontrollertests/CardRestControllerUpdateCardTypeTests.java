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
public class CardRestControllerUpdateCardTypeTests {

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
	@DisplayName("")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenNoAuthorization_WhenAccessingEndpoint_ThenReturn4XX() throws Exception {
		
		String updateTypeRequest = """
				{
					"cardName": "Dark Magician",
					"newCardType": "Effect Monster"
				}
				""";
	}
	
	@Test
	@DisplayName("")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardTypeRequest_WhenAllValid_ThenReturn2XX() throws Exception {
		
	}
	
	
}
