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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

@WebMvcTest(CardController.class)
public class CardRestControllerUpdateCardNameTests {
	
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
	void shouldSucceedWhenTryingToUpdateName() throws Exception {
		
		when(cardService.isCardInDatabase("testUser3", "Dark Magician")).thenReturn(true);
		
		String updateNameRequest = """
				{
					"oldCardName": "Dark Magician",
					"newCardName": "Red-Eyes Black Dragon"
				} 
				""";
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders
					.put("/card/update/card_name")
					.with(csrf())
					.contentType(MediaType.APPLICATION_JSON)
					.content(updateNameRequest)
					.accept(MediaType.APPLICATION_JSON));
		
		result
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk());
		
	} 
	
	@Test
	@DisplayName("Should print an error message that oldCardName can't be empty or null.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void shouldPrintErrorMessageBecauseOldCardNameEmptyOrNull() throws Exception {
		
		String updateNameRequest = """
				{
					"oldCardName": "",
					"newCardName": "Stardust Dragon"
				}
				""";
		
		String error = mvc.perform(MockMvcRequestBuilders
					.put("/card/update/card_name")
					.with(csrf())
					.contentType(MediaType.APPLICATION_JSON)
					.content(updateNameRequest)
					.accept(MediaType.APPLICATION_JSON))
						.andDo(MockMvcResultHandlers.print())
						.andExpect(status().is4xxClientError())
						.andReturn().getResolvedException().getMessage();
		
		assertThat(error).contains("null or empty");
		
	}
	
	@Test
	@DisplayName("Should print an error message that newCardName can't be empty or null.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void shouldPrintErrorMessageBecauseNewCardNameEmptyOrNull() throws Exception {
		
		String updateNameRequest = """
				{
					"oldCardName": "Dark Magician",
					"newCardName": null
				}
				""";
		
		String error = mvc.perform(MockMvcRequestBuilders
									.put("/card/update/card_name")
									.with(csrf())
									.contentType(MediaType.APPLICATION_JSON)
									.content(updateNameRequest)
									.accept(MediaType.APPLICATION_JSON))
										.andDo(MockMvcResultHandlers.print())
										.andExpect(status().is4xxClientError())
										.andReturn().getResolvedException().getMessage();
		
		assertThat(error).contains("null or empty");
		
	}
	
	@Test
	@DisplayName("Should print an error message because both newCardName and oldCardName were empty or null.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void shouldPrintErrorMessageBecauseAllInputEmptyOrNull() throws Exception{
		
		String updateNameRequest = """
				{
					"oldCardName": "",
					"newCardName": null
				}
				""";
		
		String error = mvc.perform(MockMvcRequestBuilders
									.put("/card/update/card_name")
									.with(csrf())
									.contentType(MediaType.APPLICATION_JSON)
									.content(updateNameRequest)
									.accept(MediaType.APPLICATION_JSON))
										.andDo(MockMvcResultHandlers.print())
										.andExpect(status().is4xxClientError())
										.andReturn().getResolvedException().getMessage();
		assertThat(error).contains("null or empty");
		
	}
}
