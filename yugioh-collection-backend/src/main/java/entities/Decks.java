package entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Decks")
public class Decks {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String deckName;
	
	//Will have the ids of each card that belong to specific user as a comma separated list of strings
	private String mainDeck;
	private String sideDeck;
	
	private String username;
	
	@Transient
	private List<Card> mainDeckList;
	
	@Transient
	private List<Card> sideDeckList;

	public Decks(long id, @NotNull String deckName, String mainDeck, String sideDeck, String username,
			List<Card> mainDeckList, List<Card> sideDeckList) {
		super();
		this.id = id;
		this.deckName = deckName;
		this.mainDeck = mainDeck;
		this.sideDeck = sideDeck;
		this.username = username;
		this.mainDeckList = mainDeckList;
		this.sideDeckList = sideDeckList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeckName() {
		return deckName;
	}

	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	public String getMainDeck() {
		return mainDeck;
	}

	public void setMainDeck(String mainDeck) {
		this.mainDeck = mainDeck;
	}

	public String getSideDeck() {
		return sideDeck;
	}

	public void setSideDeck(String sideDeck) {
		this.sideDeck = sideDeck;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Card> getMainDeckList() {
		return mainDeckList;
	}

	public void setMainDeckList(List<Card> mainDeckList) {
		this.mainDeckList = mainDeckList;
	}

	public List<Card> getSideDeckList() {
		return sideDeckList;
	}

	public void setSideDeckList(List<Card> sideDeckList) {
		this.sideDeckList = sideDeckList;
	}
	
	
}
