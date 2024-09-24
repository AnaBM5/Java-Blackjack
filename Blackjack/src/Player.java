import java.awt.Image;

public class Player {
	
	private Card[] hand;								// Declares data member to store the cards the player
														// will have during the round
	private int wallet;									// Declares data member to store the player's wallet 
	private int cardSum;								// Declares data member to store the sum of the cards
														// that have been revealed by the player
	
	public Player() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	Player()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method is called as a constructor when an object of type Player is instantiated.
		//							It defines the data members of type integer as 0 and the data member "hand" as an 
		//							empty list with 5 space so they can later be obtained or modified.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-29		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		this.wallet=0;													// Instantiates the player's data member "wallet" as the integer 0
		this.hand = new Card[5];										// Instantiates the player's data member "hand" as an empty list with 5 spaces
		this.cardSum = 0;												// Instantiates the player's data member "cardImage" as the integer 0
	}
	
	public Card[] getHand() {											// getter for the data member "hand"
		return this.hand;												// returns the object's current hand
	}
	
	public void setHand(Card[] hand) {									// setter for the data member "hand"
		this.hand = hand;												// stores in the object's data member "hand"
	}																	// the list of Cards given.
	
	public int getWallet() {											// getter for the data member "wallet"
		return this.wallet;												// returns the object's current wallet
	}
	
	public void setWallet(int wallet) {									// setter for the data member "wallet"
		this.wallet = wallet;											// stores in the object's data member "wallet" the number given
	}
	
	public int getCardSum() {											// getter for the data member "cardSum"
		return this.cardSum;											// returns the object's current cardSum
	}
	
	public void setCardSum(int cardSum) {								// setter for the data member "cardSum"
		this.cardSum = cardSum;											// stores in the object's data member "cardSum" the number given
	}
	
}
