package entities;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min = 5, max = 20)
	private String username;
	
	@NotNull
	@Size(min = 8, max = 20)
	private String password;
	
	@Transient
	private List<Card> cardCollection;

	public User(long id, @NotNull @Size(min = 5, max = 20) String username,
			@NotNull @Size(min = 8, max = 20) String password, List<Card> cardCollection) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.cardCollection = cardCollection;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Card> getCardCollection() {
		return cardCollection;
	}

	public void setCardCollection(List<Card> cardCollection) {
		this.cardCollection = cardCollection;
	}
	
	
	
}
