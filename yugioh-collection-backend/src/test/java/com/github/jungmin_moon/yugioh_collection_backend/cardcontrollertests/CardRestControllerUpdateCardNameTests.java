package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;

@WebMvcTest(CardController.class)
public class CardRestControllerUpdateCardNameTests {
	
	@Autowired
	MockMvcTester mockMvcTester;
	
	@Test
	@DisplayName("Should return a 2xx status because card name was changed.")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void shouldSucceedWhenTryingToUpdateName() {
		
	}
	
	@Test
	@DisplayName("Should print an error message that oldCardName can't be empty or null.")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void shouldPrintErrorMessageBecauseOldCardNameEmptyOrNull() {
		
	}
	
	@Test
	@DisplayName("Should print an error message that newCardName can't be empty or null.")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void shouldPrintErrorMessageBecauseNewCardNameEmptyOrNull() {
		
	}
	
	@Test
	@DisplayName("Should print an error message because both newCardName and oldCardName were empty or null.")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void shouldPrintErrorMessageBecauseAllInputEmptyOrNull() {
		
	}
}
