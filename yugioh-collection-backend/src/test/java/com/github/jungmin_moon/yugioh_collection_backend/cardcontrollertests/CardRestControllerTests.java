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
	@DisplayName("Should return {cardName:Card name must not be null or empty.} due to the NewCardRequest object having bad cardName values")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void shouldReturnCardNameMessageDueToInvalidAddNewCardRequest() {
		
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
		
		String expectedMessage = "{\"cardName\":\"Card name must not be null or empty.\"}";
		
		assertThat(testResult).bodyText().isEqualTo(expectedMessage);
		
	}
	
	@Test
	@DisplayName("Should return {cardType:Card type must not be null or empty.} due to NewCardRequest object having bad cardType values")
	@WithMockUser(username = "testUser1", password = "password1", roles = "{USER}")
	void shouldReturnCardTypeMessageDueToInvalidAddNewCardRequest() {
		
		String requestBody = """
				{
					"cardName": "Dark Magician",
					"cardType": null,
					"quantity": 4
				}
				""";
		
		MvcTestResult testResult = mockMvcTester.post()
												.with(csrf())
												.uri("/card/add")
												.contentType(MediaType.APPLICATION_JSON)
												.content(requestBody)
												.exchange();
		
		String expectedMessage = "{\"cardType\":\"Card type must not be null or empty.\"}";
		
		assertThat(testResult).bodyText().isEqualTo(expectedMessage);
		
	}
	
	@Test
	@DisplayName("Should return {quantity:Quantity must be 1 or greater.} due to NewCardRequest object having bad quantity:quantity values")
	@WithMockUser(username = "testUser1", password = "password1", roles = "{USER}")
	void shouldReturnCardQuantityMessageDueToInvalidAddNewCardRequest() {
		
		String requestBody = """
				{
					"cardName": "Dark Magician",
					"cardType": "Normal Monster",
					"quantity": 0
				}
				""";
		
		MvcTestResult testResult = mockMvcTester.post()
				.with(csrf())
				.uri("/card/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.exchange();
		
		String expectedMessage = "{\"quantity\":\"Quantity must be 1 or greater.\"}";
		
		assertThat(testResult).bodyText().isEqualTo(expectedMessage);
	}
	
	@Test
	@DisplayName("Should return both a cardName error message and cardType error message.")
	@WithMockUser(username = "testUser1", password = "password1", roles = "{USER}")
	void shouldReturnCardNameAndCardTypeErrorMessageDueToInvalidNewCardRequest() {
		
		String requestBody = """
				{
					"cardName": "",
					"cardType": null,
					"quantity": 5
				}
				""";
		
		MvcTestResult testResult = mockMvcTester.post()
				.with(csrf())
				.uri("/card/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.exchange();
		
		String expectedMessage = "{\"cardName\":\"Card name must not be null or empty.\""
								+ ",\"cardType\":\"Card type must not be null or empty.\"}";
		
		assertThat(testResult).bodyText().isEqualTo(expectedMessage);
	}
	
	@Test
	@DisplayName("Should return both a cardName error message and quantity error message.")
	@WithMockUser(username = "testUser1", password = "password1", roles = "{USER}")
	void shouldReturnCardNameAndQuantityErrorMessageDueToInvalidNewCardRequest() {
		
		String requestBody = """
				{
					"cardName": null,
					"cardType": "Normal Monster",
					"quantity": 0
				}
				""";
		MvcTestResult testResult = mockMvcTester.post()
				.with(csrf())
				.uri("/card/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.exchange();
		
		//Might change how it is stored to be in order because Postman doesn't show in this order
		String expectedMessage = "{\"quantity\":\"Quantity must be 1 or greater.\""
								+ ",\"cardName\":\"Card name must not be null or empty.\"}";
		
		assertThat(testResult).bodyText().isEqualTo(expectedMessage);
		
	}
	
	@Test
	@DisplayName("Should return both a cardType error message and quantity error message.")
	@WithMockUser(username = "testUser1", password = "password1", roles = "{USER}")
	void shouldReturnCardTypeAndQuantityErrorMessageDueToInvalidNewCardRequest() {
		
	}
	
	@Test
	@DisplayName("Should return all three error messages for cardName, cardType and quantity")
	@WithMockUser(username = "testUser1", password = "password1", roles = "{USER}")
	void shouldReturnErrorMessageForAllThreePropertiesDueToInvalidNewCardRequest() {
		
	}
}
