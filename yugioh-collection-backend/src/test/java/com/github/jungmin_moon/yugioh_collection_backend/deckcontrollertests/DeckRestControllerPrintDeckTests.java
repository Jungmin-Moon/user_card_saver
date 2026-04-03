package com.github.jungmin_moon.yugioh_collection_backend.deckcontrollertests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.github.jungmin_moon.yugioh_collection_backend.entities.DeckContents;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Decks;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.DeckRepository;
import com.github.jungmin_moon.yugioh_collection_backend.restcontroller.DeckController;
import com.github.jungmin_moon.yugioh_collection_backend.services.CardService;
import com.github.jungmin_moon.yugioh_collection_backend.services.DeckService;

@WebMvcTest(DeckController.class)
@AutoConfigureRestTestClient
public class DeckRestControllerPrintDeckTests {

	@Autowired
	private RestTestClient restTestClient;
	
	@MockitoBean
	private DeckService deckService;
	
	List<DeckContents> deckContentTest = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		
		/*
		 * TODO
		 * make three cards each for side deck and main deck
		 */
		
		DeckContents cardInMainDeck1 = new DeckContents();
		cardInMainDeck1.setDeckName("Dark Magician Deck");
		cardInMainDeck1.setCardLocation("Main deck");
		cardInMainDeck1.setCardName("Dark Magician");
		cardInMainDeck1.setQuantity(2);
		
		DeckContents cardInMainDeck2 = new DeckContents();
		cardInMainDeck2.setDeckName("Dark Magician Deck");
		cardInMainDeck2.setCardLocation("Main Deck");
		cardInMainDeck2.setCardName("Eye of Timaeus");
		cardInMainDeck2.setQuantity(3);
		
		DeckContents cardInMainDeck3 = new DeckContents();
		
	}
}
