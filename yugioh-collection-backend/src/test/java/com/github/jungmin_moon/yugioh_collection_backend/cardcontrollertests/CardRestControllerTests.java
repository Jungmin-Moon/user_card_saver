package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

@WebMvcTest(CardController.class)
@AutoConfigureRestTestClient
public class CardRestControllerTests {

	@Autowired
	private RestTestClient restTestClient;
	
	@MockitoBean
	private CardService cardService;
	
	@Autowired
	MockMvcTester mockMvcTester;
	
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
	
	@Test
	@DisplayName("Should return MethodArgumentNotValidException due to the NewCardRequest object having bad values")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void shouldReturnExceptionDueToInvalidAddNewCardRequest() {
		
		String requestBody = """
				{
					"cardName": null,
					"cardType": "Normal Monster",
					"quantity": 3
				}
				""";
		
		MvcTestResult testResult = mockMvcTester.post()
										.with(csrf())
										.uri("/card/add")
										.contentType(MediaType.APPLICATION_JSON)
										.content(requestBody)
										.exchange();
		
		//restTestClient.post().uri("/card/add").contentType(MediaType.APPLICATION_JSON)
						//.body(requestBody).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
		
		String expectedMessage = "{\"cardName\":\"Card name must not be null or empty.\"}";
		
		assertThat(testResult).bodyText().isEqualTo(expectedMessage);
		
		
	}
}
