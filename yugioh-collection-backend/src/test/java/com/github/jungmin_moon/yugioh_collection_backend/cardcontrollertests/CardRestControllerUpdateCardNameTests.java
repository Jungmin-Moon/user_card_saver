package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

@WebMvcTest(CardController.class)
public class CardRestControllerUpdateCardNameTests {
	
	@MockitoBean
	private CardService cardService;
	
	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void shouldSucceedWhenTryingToUpdateName() throws Exception {
		
		String updateNameRequest = """
				{
					"oldCardName": "Dark Magician",
					"newCardName": "Red-Eyes Black Dragon"
				}
				""";
		
		mvc.perform(MockMvcRequestBuilders
					.put("/card/update/card_name")
					.with(csrf())
					.contentType(MediaType.APPLICATION_JSON)
					.content(updateNameRequest)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
		
	} 
	
	@Test
	@DisplayName("Should print an error message that oldCardName can't be empty or null.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void shouldPrintErrorMessageBecauseOldCardNameEmptyOrNull() {
		
	}
	
	@Test
	@DisplayName("Should print an error message that newCardName can't be empty or null.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void shouldPrintErrorMessageBecauseNewCardNameEmptyOrNull() {
		
	}
	
	@Test
	@DisplayName("Should print an error message because both newCardName and oldCardName were empty or null.")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void shouldPrintErrorMessageBecauseAllInputEmptyOrNull() {
		
	}
}
