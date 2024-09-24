import java.awt.Image;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;

public class Deck {

	private Card[] cardList;							// Declares data member to store all the 52 cards that form part of the deck

	public Deck() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	Deck()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method is called as a constructor when an object of type Deck is instantiated.
		//							It uses lists with predetermined values and images to instantiate and store all  
		//							the cards that will form part of the deck by using a for to setup a temporary card
		//							and storing it in the data member "cardList".
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-29		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		this.cardList = new Card[52];								// Instantiates the deck's data member "cardList" as an empty list with 52 spaces		
		int[] cardValues = {11,2,3,4,5,6,7,8,9,10,10,10,10};		// Instantiates a list of integers called "cardValues" as a list corresponding to 
																	// the values of A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q and K respectively
		
		ImageIcon[] cardImages = GetCardImages();					// Instantiates a list of ImageIcons called "cardImages" by storing in it 
																	// the list of all the icons of the cards that is gotten from GetCardImages()
		
		Card t_Card; 												// Declares data member to store the different cards that will be generated to
																	// fill the deck
		
		int counter;												// Declares counter to control the iterations of the for loop

		for(counter = 0; counter < 52; counter++) {					// Uses a for to fill the deck with 52 different Cards
			
			t_Card = new Card();									// Instantiates a new Card each loop
			
			t_Card.setValue(cardValues[counter%13]);				// Gives the card a value following the order of the list "cardValues" and
																	// goes back to the first integer of the list after getting to the last
																	// to then repeat the cycle
			
			t_Card.setCardImage(cardImages[counter]);				// Gives the card an ImageIcon from the list of 52 icons that was previously called
			
			cardList[counter] = t_Card;								// Adds the card to the data member "cardList" on the position
																	// equal to the value of the counter
			
		}

	}

	public static ImageIcon[] GetCardImages() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	ImageIcon[] GetCardImages()
		//
		// Method parameters	:	none
		//
		// Method return		:	ImageIcon[]
		//
		// Synopsis				:   This method is called to load all the images that represents the cards of the deck.
		//							It uses list to create predetermined paths and get the image in said path,
		//							storing them as ImageIcon inside a list that then it returns.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		
		String imagesPath;																			//declares variable to store direction of the images
		char[] cardTypes = {'C','D','H','S'};														//declares list of chars corresponding to the suit of the cards
		String[] cardNames = {"1A","2","3","4","5","6","7","8","9","10","10J","10Q","10K"};			//declares string list with all the possible values the cards can have
		
		String t_Path;																				//declares string that will change into the different paths
		ImageIcon t_CardImage;																		//declares variable to store each Image Icon that is created
		ImageIcon[] cardImages = new ImageIcon[52];													//declares an empty list of ImageIcons to store all the created 
																									// Image Icons
		
		int cardTypeCounter;																		//declares counter to change between the different suit of cards
		int counter;																				//declares counter to control iterations of the loop
		
		imagesPath = "Images/Cards/";										//Declares imagePath to the string corresponding to the folder
																			// that contains the images of the cards
		
		cardTypeCounter = -1; 												// Sets cardTypeCounter as -1 as it will turn to 0 on the first loop												
		
		for(counter=0; counter<52;counter++) {															//goes through 52 different images in the folder
			
			if(counter%13== 0)																			// every 13 cards, the type of card changes
				cardTypeCounter++;
			
			t_Path = imagesPath + (cardTypes[cardTypeCounter])+ '-'+ (cardNames[counter%13]+".png");	// Sets a path based on the previously created lists,
																										// circling through the different paths combinations that form
																										// the whole deck of cards
			
			t_CardImage =  new ImageIcon(t_Path);														// stores the new ImageIcon in t_Card and then the list of
			cardImages[counter] = t_CardImage;															// images so it doesn't get lost when looking for the next one
			
			
		}
		
		return cardImages;																				//returns the complete list of ImageIcons
	}

	public Card[] ShuffleDeck() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	Card[] ShuffleDeck()
		//
		// Method parameters	:	none
		//
		// Method return		:	Card[]
		//
		// Synopsis				:   This method is called at the beginning of each round to determine what
		//							random set of cards the dealer and the player will be using.
		//							It randomly selects an element of the deck and if the element has already been
		//							selected, it looks for a new one.
		//							At the end it returns the list of the cards that will be used in the round.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		Card[] shuffledDeck = new Card[10];										//Instantiates a list of Cards to store the resulting shuffled Deck
		
		int randomNumber;														//Instantiates an integer to store the different random values obtained
		int counter, cardsTakenCounter;											//Instantiates integers to control the iterations of 2 for loops
		int[] cardsTaken = new int[10];											//Instantiates a list of integers to know which cards have already been taken
		boolean done;															//Instantiates boolean to now if we can go to the next card
		
		Random random = new Random();											//Instantiates random to generate random numbers
		

		for(counter = 0; counter < 10; counter++) {														//looks for 10 different random cards
			
			do {																						
				randomNumber = random.nextInt(52);														//generates a random number between 0 and 52, not including
				done = true;																			//set the boolean as true to get out of the while
				
				for(cardsTakenCounter = 0; cardsTakenCounter < counter; cardsTakenCounter++) {			//but if the number that was just generated is the same
					if(randomNumber == cardsTaken[cardsTakenCounter]) {									// as another one generated before, sets the boolean back to false
						System.out.println("Card Already taken");										// to generate again another random number
						done = false;
					}
				}
			}while(!done);
			
			System.out.println(this.cardList[randomNumber].getValue());									
			
			shuffledDeck[counter] = this.cardList[randomNumber];					 // if the number hasn't been used before, it's used as a pointer to the deck
																					// to take the cards in that position and saving it in the new shuffled Deck
			
			cardsTaken[counter] = randomNumber;										// adds the random number used to the list of random numbers generated so it isn't
		}																			// used again
		
		return shuffledDeck;														// returns the shuffled list of cards

	}

	public Card[] getCardList() {							// getter for the data member "cardList"
		return cardList;									// returns the object's current cardList
	}

	public void setCardList(Card[] cardList) {					// setter for the data member "cardList"
		this.cardList = cardList;								// stores in the object's data member "cardList"
	}															// the list of Cards given.

}
