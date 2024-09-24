import java.awt.Image;

import javax.swing.ImageIcon;

public class Card {
	private int value;										// Declares data member to store the value of the card 
	private ImageIcon cardImage;							// Declares data member to stored the image of the card
	
	public Card() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	Card()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method is called as a constructor when an object of type Card is instantiated.
		//							It defines the data members of the object as 0 and null respectively so they can later
		//							be obtained or modified.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-29		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		this.value=0;										// Instantiates the card's data member "value" as the integer 0
		this.cardImage = null;								// Instantiates the card's data member "cardImage" as a null image
	}
	
	public int getValue() {									// getter for the data member "value"
		return this.value;									// returns the object's current value
	}
	
	public void setValue(int value) {						// setter for the data member "value"
		this.value = value;									// stores in the object's data member "value" the number given
	}
	
	public ImageIcon getCardImage() {						// getter for the data member "cardImage"
		return this.cardImage;								// returns the object's current cardImage;
	}
	
	public void setCardImage(ImageIcon cardImage) {			// setter for the data member "cardImage"
		this.cardImage = cardImage;							// stores in the object's data member "cardImage the number given
	}
	
}
