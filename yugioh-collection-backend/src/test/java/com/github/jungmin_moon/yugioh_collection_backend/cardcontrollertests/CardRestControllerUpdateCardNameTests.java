package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewCardRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

@WebMvcTest(CardController.class)
@AutoConfigureRestTestClient
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CardRestControllerUpdateCardNameTests {
	
	@MockitoBean
	private CardService cardService;
	
	@Autowired
	MockMvcTester mockMvcTester;
		
	
	@Test
	@DisplayName("")
	@WithMockUser(username = "testUser3", password = "password1", roles = {"USER"})
	void shouldSucceedWhenTryingToUpdateName() throws UnsupportedEncodingException {
		
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
