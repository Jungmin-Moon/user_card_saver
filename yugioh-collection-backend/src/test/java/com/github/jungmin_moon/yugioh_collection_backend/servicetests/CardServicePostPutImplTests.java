package com.github.jungmin_moon.yugioh_collection_backend.servicetests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.github.jungmin_moon.yugioh_collection_backend.dto.NewCardRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardQuantityRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.CardRepository;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;


//Loaded the entire context because the logic in the service methods are simple
@SpringBootTest
public class CardServicePostPutImplTests {

	@Mock
	private CardRepository cardRepository;
	
	@Autowired
	CardService cardService;
	
	@Test
	@Transactional
	@DisplayName("Test card was added.")
	public void testCardAddedHappyFlow() {
		
		Card card = new Card();
		card.setCardName("Dark Magician");
		card.setCardType("Normal Monster");
		card.setQuantity(3);
		card.setUsername("TestUser2");
		
		NewCardRequest newCardRequest = new NewCardRequest(card.getCardName(), card.getCardType(), card.getQuantity());
		
		cardService.addCard(card.getUsername(), newCardRequest);
		
		assertThat(cardService.getCardInfo(card.getUsername(), card.getCardName()).getId() == 1);
		
	}
	
	@Test
	@Transactional
	@DisplayName("Test card quantity was changed.")
	public void testCardQuantityChangedHappyFlow() {
		
		Card card = new Card();
		card.setCardName("Dark Magician");
		card.setCardType("Normal Monster");
		card.setQuantity(3);
		card.setUsername("TestUser2");
		
		NewCardRequest newCardRequest = new NewCardRequest(card.getCardName(), card.getCardType(), card.getQuantity());
		
		cardService.addCard(card.getUsername(), newCardRequest);
		
		UpdateCardQuantityRequest updateCardQuantityRequest = new UpdateCardQuantityRequest(card.getCardName(), 5);
		
		cardService.updateCardCount(card.getUsername(), updateCardQuantityRequest);
		
		assertThat(cardService.getCardInfo(card.getUsername(), card.getCardName()).getQuantity() == 5);
	}
	
	@Test
	@Transactional
	@DisplayName("Test card name was changed.")
	public void testCardNameChangeHappyFlow() {
		
	}
}
