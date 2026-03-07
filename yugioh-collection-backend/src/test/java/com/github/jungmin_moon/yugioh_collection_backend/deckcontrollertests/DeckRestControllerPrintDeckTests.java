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
	
	@MockitoBean
	private DeckRepository deckRepository;
	
	@MockitoBean
	private CardService cardService;
	
	List<DeckContents> deckContentTest = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		
		
		
	}
}
