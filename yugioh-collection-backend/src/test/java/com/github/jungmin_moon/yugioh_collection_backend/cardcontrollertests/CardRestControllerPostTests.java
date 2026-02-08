package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;

@WebMvcTest(CardController.class)
public class CardRestControllerPostTests {

	@MockitoBean
	private CardService cardService;
	
	@Autowired
	MockMvcTester mockMvcTester;
	
	@Test
	@DisplayName("Should return a 2xx status from a successful card add.")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void givenNewCardRequest_WhenNoBadValues_ThenReturn2xx() {
		
		String requestBody = """
				{
					"cardName": "Dark Magician",
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
		
		assertThat(testResult).hasStatus2xxSuccessful();
		
	}
	
	@Test
	@DisplayName("Should return {cardName:Card name must not be null or empty.} due to the NewCardRequest object having bad cardName values")
	@WithMockUser(username = "testUser1", password = "password1", roles = {"USER"})
	void givenNewCardRequest_WhenBadCardName_ThenReturnErrorMessage() {
		
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
	void givenNewCardRequest_WhenBadCardType_ThenReturnErrorMessage() {
		
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
	void givenNewCardRequest_WhenBadQuantity_ThenReturnErrorMessage() {
		
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
	void givenNewCardRequest_WhenBadCardNameAndCardType_ThenReturnMultipleErrorMessages() {
		
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
	void givenNewCardRequest_WhenBadCardNameAndQuantity_ThenReturnMultipleErrorMessages() {
		
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
		
		String expectedMessage = "{\"quantity\":\"Quantity must be 1 or greater.\""
								+ ",\"cardName\":\"Card name must not be null or empty.\"}";
		
		assertThat(testResult).bodyText().isEqualTo(expectedMessage);
		
	}
	
	@Test
	@DisplayName("Should return both a cardType error message and quantity error message.")
	@WithMockUser(username = "testUser1", password = "password1", roles = "{USER}")
	void givenNewCardRequest_WhenBadCardTypeAndQuantity_ThenReturnMultipleErrorMessage() {
		
		String requestBody = """
				{
					"cardName": "Dark Magician",
					"cardType": "",
					"quantity": 0
				}
				""";
		
		MvcTestResult testResult = mockMvcTester.post()
												.with(csrf())
												.uri("/card/add")
												.contentType(MediaType.APPLICATION_JSON)
												.content(requestBody)
												.exchange();
		
		String expectedMessage = "{\"quantity\":\"Quantity must be 1 or greater.\""
								+ ",\"cardType\":\"Card type must not be null or empty.\"}";
		
		assertThat(testResult).bodyText().isEqualTo(expectedMessage);
		
	}
	
	@Test
	@DisplayName("Should return all three error messages for cardName, cardType and quantity")
	@WithMockUser(username = "testUser1", password = "password1", roles = "{USER}")
	void givenNewCardRequest_WhenBadCardNameAndCardTypeAndQuantity_ThenReturnMultipleErrorMessages() {
		
		String requestBody = """
				{
					"cardName": null,
					"cardType": "",
					"quantity": 0
				}
				""";
		
		MvcTestResult testResult = mockMvcTester.post()
												.with(csrf())
												.uri("/card/add")
												.contentType(MediaType.APPLICATION_JSON)
												.content(requestBody)
												.exchange();
		
		String expectedMessage = "{\"quantity\":\"Quantity must be 1 or greater.\""
									+ ",\"cardName\":\"Card name must not be null or empty.\""
									+ ",\"cardType\":\"Card type must not be null or empty.\"}";

		assertThat(testResult).bodyText().isEqualTo(expectedMessage);
		
	}
	
}
