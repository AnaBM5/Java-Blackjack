import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Blackjack {

	private Player player;								// Declares data member to store the information of the player
	private Player dealer;								// Declares data member to store the information of the dealer
	private Deck deck;									// Declares data member to store the deck with the 52 cards used for Blackjack
	
	public Blackjack(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	Blackjack()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method is called as a constructor when an object of type Blackjack is instantiated.
		//							It calls the constructor of the different data members to instantiate them as empty
		//							objects so they can later be obtained or modified.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-29		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		this.player = new Player();										// Instantiates the Blackjack's data member "player" as a new object of type Player
		this.dealer = new Player();										// Instantiates the Blackjack's data member "dealer" as a new object of type Player
		this.deck = new Deck();											// Instantiates the Blackjack's data member "deck" as a new object of type Deck
	}
	
	public void NewGame() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void NewGame()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method is called to setup the dealer's and the player's wallet for a new game.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-29		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		player.setWallet(1000);											// Sets the wallet of the data member player to 1000
		dealer.setWallet(1000);											// Sets the wallet of the data member dealer to 1000
	}
	
	public ImageIcon[] NewRound() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	ImageIcon[] NewRound()
		//
		// Method parameters	:	none
		//
		// Method return		:	ImageIcon[]
		//
		// Synopsis				:   This method is called when a new round starts. 
		//							It shuffles the deck and give both the dealer and the player the cards that they
		//							are going to have during that round and returns a list of ImageIcons that BlackjackApp 
		//							is going to use to show the cards.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-29		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		Card[] shuffledDeck;									//Declares a list of Cards to store the shuffled deck
		ImageIcon[] cardImages;									// Declares a list of ImageIcons to store the images of the cards
																// that are in the shuffled deck
		
		Card[] playerHand;										//Declares a list to store the hand the player will have that round
		Card[] dealerHand;										//Declares a list to store the hand the dealer will have that round
		
		int counter;											//Declares counter to control iterations of the loop							
		
		deck = new Deck();										//Establishes a new deck for the new round
		
		shuffledDeck = deck.ShuffleDeck();												//Calls the method "ShuffleDeck" from deck and stores the returning
																						// value in the variable shuffledDeck
		
		cardImages = new ImageIcon[10];													//Instantiates cardImages as an empty list with 10 spaces
		playerHand = new Card[5];														//Instantiates playerHand as an empty list with 5 spaces
		dealerHand = new Card[5];														//Instantiates dealerHand as an empty list with 5 spaces
			
		for (counter = 0; counter<10; counter++) {										//Goes through the list of shuffled cards
			cardImages[counter]= shuffledDeck[counter].getCardImage();					//stores the cards' images in the list "cardImages"
			
			if(counter < 5) {															//If it is one of the first five cards,
				playerHand[counter] = shuffledDeck[counter];							// it stores the card in the "playerHand" list
			
			}else {																		//Else, 
				dealerHand[counter-5] = shuffledDeck[counter];							//stores the card in the "dealerHand" list
			}
		}
		
		player.setHand(playerHand);														//Stores the local list "playerHand" as the player's hand
		dealer.setHand(dealerHand);														//Stores the local list "dealerHand" as the dealer's hand
		
		return cardImages;																//Returns the list of the card images that will be used for the new round
	}
	
	public void PlaceBet(int betMoney) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void PlaceBet(int betMoney)
		//
		// Method parameters	:	betMoney - Used to modify the wallet of the dealer and the player
		//
		// Method return		:	void
		//
		// Synopsis				:   This method is called after the player selects the button to confirm the bet amount.
		//							It takes the total amount of money in the pot and subtracts half of it from each wallet.
		//							Then it sets the player's and the dealer's cardSum as the addition of their respective
		//							first two cards, as these are now revealed.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		betMoney/= 2; 																	// As the 'betMoney' variable comes from the pot, it divides it into 2
																						// so that the correct amount of money is subtracted from the wallets
		
		Card[] playerHand;																//Declares a list to use the value of the cards in the player's hand					
		Card[] dealerHand;																//Declares a list to use the value of the cards in the dealer's hand	
		playerHand = player.getHand();													
		dealerHand = dealer.getHand();
		
		player.setWallet(player.getWallet()-betMoney);									//Subtracts the amount of money bet from the player's wallet
		dealer.setWallet(dealer.getWallet()-betMoney);									//Subtracts the amount of money bet from the dealer's wallet
		
		player.setCardSum(playerHand[0].getValue()+playerHand[1].getValue());			//Declares the cardSum of the dealer and the player at 
		dealer.setCardSum(dealerHand[0].getValue()+dealerHand[1].getValue());			// the start of the round, where 2 cards are revealed
		
	}
	
	public int AddPlayerCards(int cardsRevealed) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	int AddPlayerCards(int cardsRevealed)
		//
		// Method parameters	:	cardsRevealed - Used to determine what value will be added to the player's cardSum
		//
		// Method return		:	int
		//
		// Synopsis				:   This method is called after the player decides to hit and turn another card.
		//							It adds the value of the new card to the current cardSum of the player and checks
		//							if the result is higher than 21, if it is, it looks for aces to possibly change the 
		//							value of the card and the total sum. 
		//							If no ace is found, the sum remain the same.
		//							At the end, it returns the sum.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		int counter;																			//Declares counter to control iterations of the loop	
		
		Card[] playerHand;																		//Declares a list of Cards to go through the player's hand
																								// and the value of their cards
		
		int playerCardSum;																		//Declares an integer to store the new resulting addition of the 
																								// player's revealed cards
		
		playerHand = this.player.getHand();
		playerCardSum = this.player.getCardSum() + playerHand[cardsRevealed].getValue();				// Adds the value of the new card to the to the previous sum 
																										// of values the player had and stores it in playerCardSum

								
		
		if(playerCardSum> 21) {																				// If the new sum of cards is over 21, it goes
			for(counter = 0; counter<=cardsRevealed; counter++) {											// through the list of revealed cards to see if there is an ace.
				

				if(playerHand[counter].getValue() == 11){													//If it finds it,
					this.player.getHand()[counter].setValue(1);												// it changes the ace's value from 11 to 1 and
					playerCardSum-= 10;																		// reduces 10 to the player cardSum to reflect the new value of the ace.
					
					if(playerCardSum<=21) {																	// If the sum of the player's revealed cards goes under 21 after changing the ace
						this.player.setCardSum(playerCardSum);												// changes the 'cardSum' data member of the player
						return playerCardSum;																// and returns the new sum
					}
				}
			}
		}
		
		this.player.setCardSum(playerCardSum);  															// Else, sets the player's 'cardSum' data member to have the same value as   
		return playerCardSum;																				// the local variable and returns the new sum
	}
	
	public void CheckForDoubleAces() {					 	
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void CheckForDoubleAces()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method is called after the player decides to Stand.
		//							It corrects the values of aces in case the player stands after getting 2 aces by
		//							turning one of the aces value to 1 instead of 11 and the cardSum to 12 instead of 22.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		int playerCardSum;													//Declares an int to modify the player's cardSum
		playerCardSum = this.player.getCardSum();			
		
		if(playerCardSum == 22) {							// If the card sum of the player is 22 (has 2 aces)
			this.player.getHand()[0].setValue(1);			// changes the value of the first ace into 1
			this.player.setCardSum(12);						// and the sum of the cards of the player to 12.
		}
	}
	
	public int DealerAction(int cardsRevealed) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	int DealerAction(int cardsRevealed)
		//
		// Method parameters	:	cardsRevealed   -  
		//
		// Method return		:	int
		//
		// Synopsis				:   This method is called every time the dealer has to decide a new move.
		//							First it check if the first two cards of the dealer are aces to set the cardSum to 12.
		//							
		//							After that, it starts comparing the dealer's cardSum to see if it is greater than the 
		//							player's cardSum, in which case the dealer will stand.
		//	
		//							Otherwise, the dealer hits and the value of the new card is added to the dealer's cardSum,
		//							if the new sum is over 21 it looks for aces to possibly change the value of the card,
		//							if no ace is to be found the sum stays the same and the dealer loses.
		//
		//							If the sum doesn't exceed 21 the dealer can keep playing and make another decision.
		//							If the dealer reveals 5 cards and the cardSum doesn't exceed 21, it gets a 5 Card Monty
		//							and wins.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		Card[] dealerHand;												//Declares a list of Cards to go through the dealer's hand
																		// and the value of their cards
	
		int dealerCardSum;												//Declares an integer to store the new resulting addition of the 
																		// dealer's revealed cards
			
		int counter;													//Declares counter to control iterations of the loop	
		
		
		dealerHand = this.dealer.getHand();
		dealerCardSum = this.dealer.getCardSum();
		
		
		
		if(dealerCardSum == 22 && cardsRevealed == 2) {			//If the first two cards revealed for the dealer are aces, 
			dealer.getHand()[0].setValue(1);					// it changes the value of the first ace to 1 and the local dealerCarSum
			dealerCardSum = 12;									// to 12 so it can make the decision of weather to hit or stand
		}
		
		
		if(dealerCardSum == 21 || dealerCardSum>player.getCardSum()) {		//If the sum of the value of the cards of the dealer is equal to
			return 1;														//21 or greater than the sum of the player's revealed cards,
																			// the dealer decides to stand, represented as 1
		
		}else {																// Else, the dealer decides to hit
			dealerCardSum += dealerHand[cardsRevealed].getValue();			// The value of the next revealed card is added to the local variable cardSum
			dealer.setCardSum(dealerCardSum);								// and the dealers own cardSum data member;
			
			cardsRevealed++;												// The number of cards Revealed increases by 1
			
			if(dealerCardSum > 21) {										//If, after revealing a new card, the sum goes over 21
				for(counter = 0; counter < cardsRevealed; counter++) {		// it goes through the list of revealed cards to see if there is an ace.
					
					if(dealerHand[counter].getValue() == 11) {				// If it finds it, it changes the ace's value from 11 to 1
						dealer.getHand()[counter].setValue(1);				// and reduces 10 to the player cardSum to reflect the new value of the ace.
						dealer.setCardSum(dealerCardSum-10);
						
						if(cardsRevealed == 5)								// Being that the value would not exceed 21, it check if the dealer has 5 cards revealed
							return 4;										// if so, returns that the dealer decided to hit and got a 5 card monty, represented by 4.
						else
							return 2;										// If not, it just returns that the dealer decided to hit, represented by 2
					}
				}
				return 3;													// If the dealer went over 21 and there are no aces, it returns that the dealer
																			// decided to hit and went over 21, represented as 3
		
			}else if (cardsRevealed == 5)									// If the sum of the cards of the dealer hasn't exceeded 21 and there are 5 cards revealed,
				return  4;													// returns that the dealer decided to hit and got a 5 card monty, represented by 4.
			else
				return 2;													// If the sum of cards didn't exceed 21
		}																	// it just returns that the dealer decided to hit, represented by 2
	}
	
	public int CheckResults() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	int CheckResults()
		//
		// Method parameters	:	none
		//
		// Method return		:	int
		//
		// Synopsis				:   This method is called if, at the end of the round, no winner has been determined yet.
		//							It checks who between the dealer and the player has the highest cardSum, and returns
		//							an int corresponding to who the winner is.
		//							If both parties have the same cardSum, a tie it's declared
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
			
		int playerCardSum;													//Declares an integer to store the value of the 
																			// player's revealed cards
		
		int dealerCardSum;													//Declares an integer to store the value of the 
																			// player's revealed cards
		playerCardSum = this.player.getCardSum();
		dealerCardSum = this.dealer.getCardSum();
		
		
		if(playerCardSum > dealerCardSum)									// If the player's card sum is higher than the dealer,
			return 1;														//  the player wins, represented by 1
		
		else if(playerCardSum < dealerCardSum)								// else if the player's card sum is lower than the dealer's card sum,
			return 2;														// the dealer wins, represented by 2
		
		else																// else, there is a tie, which is represented by							
			return 3;														// returning 3
					
	}
	
	public void GiveMoney(int betMoney, int result) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void GiveMoney(int betMoney, int result)
		//
		// Method parameters	:	betMoney - 		use to determine how much money to add to the wallets
		//							
		//							result -		use to determine who to give the money to
		//
		// Method return		:	void
		//
		// Synopsis				:   This method is called if at the end of the round and gives the money in the pot 
		//							to the winner, this being determined by the variable "result" that it receives.
		//							In case of a tie, splits the pot in half and gives half of it to the player
		//							and the other half to the dealer.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		if(result==1) 																//If the result is 1, it means the player won and they receive
			this.player.setWallet(this.player.getWallet()+betMoney);				// the money that was on the pot
		
		else if (result == 2) 														//If the result is 2, it means the dealer won and it receives
			this.dealer.setWallet(this.dealer.getWallet()+betMoney);				// the money that was on the pot
		
		else {																		// Else, there was a tie and both the player and the dealer receive
			this.player.setWallet(this.player.getWallet()+betMoney);				// the same amount of money back
			this.dealer.setWallet(this.dealer.getWallet()+betMoney);

		}
	}
	
	public int CheckMoney() {				
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	int CheckMoney()
		//
		// Method parameters	:	none
		//
		// Method return		:	int
		//
		// Synopsis				:   This method is called at the end of the round to check if either the player or
		//							the dealer have no money left on their wallet and returns the winner  of the game 
		//							as an integer.
		//							If one of the parties has its wallet at 0, the other will be declared the winner.
		//							But if neither of them are at 0, the game will keep going as usual.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		if(this.dealer.getWallet()<=0)												// If the dealer's wallet is at 0, it returns that the dealer lost 
			return 1;																// the game, represented as 1
		
		else if(this.player.getWallet()<=0)											// If the player's wallet is at 0, it returns the the player lost
			return 2;																// the game, represented a 2
		
		return 0;																	// Else, the game keeps going, represented by 0
	}
}
