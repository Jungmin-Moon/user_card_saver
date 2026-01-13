package com.github.jungmin_moon.yugioh_collection_backend.cardcontrollertests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.CardController;

@WebMvcTest(controllers = CardController.class)
public class CardRestControllerTests {

	@Autowired
	MockMvcTester mockMvcTester;
	
	@Test
	void cardAdditionSuccessful() {
		String requestBody = """
				{
					
				}
				""";
	}
}
