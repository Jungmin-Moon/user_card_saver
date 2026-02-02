package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.client.RestTestClient;

import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;

@WebMvcTest(CardController.class)
@AutoConfigureRestTestClient
public class CardRestControllerGetTests {
	
	@Autowired
	private RestTestClient restTestClient;
	
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
		
		restTestClient.get().uri("/card/countAll")
							.exchange()
							.expectBody(String.class)
							.isEqualTo("testUser1 you currently own 0 cards.");
		
	}
}
