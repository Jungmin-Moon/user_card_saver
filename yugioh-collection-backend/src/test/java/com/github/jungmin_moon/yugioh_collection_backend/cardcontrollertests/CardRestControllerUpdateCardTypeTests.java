package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

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
	void givenNoAuthorization_WhenAccessingEndpoint_ThenReturn4XX() throws Exception {
		
		String updateTypeRequest = """
				{
					"cardName": "Dark Magician",
					"newCardType": "Effect Monster"
				}
				""";
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders
											.put("/card/update/card_type")
											.with(csrf())
											.contentType(MediaType.APPLICATION_JSON)
											.content(updateTypeRequest)
											.accept(MediaType.APPLICATION_JSON));
		
		result.andDo(MockMvcResultHandlers.print())
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	@DisplayName("")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardTypeRequest_WhenAllValid_ThenReturnSuccessStringMessage() throws Exception {
		
		when(cardService.isCardInDatabase("testUser3", "Dark Magician")).thenReturn(true);
		
		String updateTypeRequest = """
				{
					"cardName": "Dark Magician",
					"newCardType": "Effect Monster"
				}
				""";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders
										.put("/card/update/card_type")
										.with(csrf())
										.contentType(MediaType.APPLICATION_JSON)
										.content(updateTypeRequest)
										.accept(MediaType.APPLICATION_JSON))
										.andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("card type has been updated to:");
		
	}
	
	@Test
	@DisplayName("")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardTypeRequest_WhenBadCardNameValue_ThenReturnFailureStringMessage() throws Exception {
		
		String updateTypeRequest = """
				{
					"cardName": "",
					"newCardType": "Effect Monster"
				}
				""";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders
										.put("/card/update/card_type")
										.with(csrf())
										.contentType(MediaType.APPLICATION_JSON)
										.content(updateTypeRequest)
										.accept(MediaType.APPLICATION_JSON))
										.andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("Card name must not be null or empty");
		
	}
	
	@Test
	@DisplayName("")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardTypeRequest_WhenBadCardTypeValue_ThenReturnFailureStringMessage() throws Exception {
		
		String updateTypeRequest = """
				{
					"cardName": "Dark Magician",
					"newCardType": null
				}
				""";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders
										.put("/card/update/card_type")
										.with(csrf())
										.contentType(MediaType.APPLICATION_JSON)
										.content(updateTypeRequest)
										.accept(MediaType.APPLICATION_JSON))
										.andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("New card type must not be null or empty");
		
	}
	
	@Test
	@DisplayName("")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void givenUpdateCardTypeRequest_WhenBothValuesBad_ThenReturnMultipleFailureStringMessages() throws Exception {
		
		String updateTypeRequest = """
				{
					"cardName": null,
					"newCardType": ""
				}
				""";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders
										.put("/card/update/card_type")
										.with(csrf())
										.contentType(MediaType.APPLICATION_JSON)
										.content(updateTypeRequest)
										.accept(MediaType.APPLICATION_JSON))
										.andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("Card name must not be null or empty", "New card type must not be null or empty");
		
	}
}
