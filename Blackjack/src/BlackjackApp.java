import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class BlackjackApp extends JDialog {
	
	
	private final JPanel contentPanel = new JPanel();


	private Blackjack blackjack;						// Declares data member to store the information of the game
	
	private ImageIcon[] cardImages;						// Declares a list of ImageIcons to show the cards that are 
														// going to be used each round
	
	private ImageIcon backCardImage;					// Declares data member to store the ImageIcon that will be shown
														// as the back of the card
	
	private int cardsRevealed;							//Declares data member to first store the amount of cards the player
														// has revealed and then does the same for the dealer
	
	private JLabel lblDealerWallet;						//Visual components that are needed as global to perform different methods
	private JLabel lblPlayerWallet;
	private JLabel lblBetMoney;
	private JPanel dealerCardPanel;
	private JPanel playerCardPanel;
	private JPanel questionPanel;
	private JPanel betQuestionPanel;
	private JPanel dealerMessagePanel;
	private JPanel endRoundMessagePanel;
	private JButton btnContinue;
	private JButton btnNewRound;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BlackjackApp dialog = new BlackjackApp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BlackjackApp() {

		setResizable(false);
		setTitle("BlackJack");
		setBounds(100, 100, 963, 657);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel playerPanel = new JPanel();
		playerPanel.setBackground(new Color(192, 192, 192));
		playerPanel.setBounds(0, 393, 949, 227);
		contentPanel.add(playerPanel);
		playerPanel.setLayout(null);

		playerCardPanel = new JPanel();
		playerCardPanel.setBackground(new Color(0, 102, 51));
		playerCardPanel.setBounds(10, 25, 741, 202);
		playerPanel.add(playerCardPanel);
		playerCardPanel.setLayout(null);

		JLabel lblPlayerCard1 = new JLabel("");
		lblPlayerCard1.setIcon(null);
		lblPlayerCard1.setForeground(new Color(102, 153, 102));
		lblPlayerCard1.setBackground(new Color(51, 51, 0));
		lblPlayerCard1.setBounds(27, 10, 131, 182);
		playerCardPanel.add(lblPlayerCard1);

		JLabel lblPlayerCard2 = new JLabel("");
		lblPlayerCard2.setIcon(null);
		lblPlayerCard2.setForeground(new Color(102, 153, 102));
		lblPlayerCard2.setBackground(new Color(51, 51, 0));
		lblPlayerCard2.setBounds(168, 10, 131, 182);
		playerCardPanel.add(lblPlayerCard2);

		JLabel lblPlayerCard3 = new JLabel("");
		lblPlayerCard3.setIcon(null);
		lblPlayerCard3.setForeground(new Color(102, 153, 102));
		lblPlayerCard3.setBackground(new Color(51, 51, 0));
		lblPlayerCard3.setBounds(309, 10, 131, 182);
		playerCardPanel.add(lblPlayerCard3);

		JLabel lblPlayerCard4 = new JLabel("");
		lblPlayerCard4.setIcon(null);
		lblPlayerCard4.setForeground(new Color(102, 153, 102));
		lblPlayerCard4.setBackground(new Color(51, 51, 0));
		lblPlayerCard4.setBounds(450, 10, 131, 182);
		playerCardPanel.add(lblPlayerCard4);

		JLabel lblPlayerCard5 = new JLabel("");
		lblPlayerCard5.setIcon(null);
		lblPlayerCard5.setForeground(new Color(102, 153, 102));
		lblPlayerCard5.setBackground(new Color(51, 51, 0));
		lblPlayerCard5.setBounds(591, 10, 131, 182);
		playerCardPanel.add(lblPlayerCard5);

		JPanel playerWalletPanel = new JPanel();
		playerWalletPanel.setBackground(new Color(102, 51, 0));
		playerWalletPanel.setBounds(754, 25, 185, 202);
		playerPanel.add(playerWalletPanel);
		playerWalletPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Player");
		lblNewLabel.setForeground(new Color(153, 255, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 185, 47);
		playerWalletPanel.add(lblNewLabel);

		JLabel lblWallet = new JLabel("Wallet:");
		lblWallet.setForeground(new Color(255, 255, 153));
		lblWallet.setHorizontalAlignment(SwingConstants.CENTER);
		lblWallet.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWallet.setBounds(0, 57, 185, 34);
		playerWalletPanel.add(lblWallet);

		lblPlayerWallet = new JLabel("####");
		lblPlayerWallet.setForeground(new Color(255, 255, 153));
		lblPlayerWallet.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerWallet.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlayerWallet.setBounds(0, 92, 185, 81);
		playerWalletPanel.add(lblPlayerWallet);

		JButton btnHit = new JButton("Hit");
		btnHit.setEnabled(false);
		btnHit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHit.setBounds(178, 0, 105, 25);
		playerPanel.add(btnHit);

		JButton btnStand = new JButton("Stand");
		btnStand.addActionListener(new ActionListener() {					//If the Stand button is pressed
			public void actionPerformed(ActionEvent e) {
				blackjack.CheckForDoubleAces();								// As the change of a value of an ace from 11 to 1 happens during hit
																			// Calls method Check for Double Aces to make sure
																			// that the player's cardSum is not 22 by having 2 aces as their
																			// first cards and then deciding to Stand
				
				cardsRevealed = 2;											//Data member cardsRevealed is set to 2, now representing the dealer's 
																			// number of revealed cards
				btnHit.setEnabled(false);									//Lets the user interact with the Hit button
				btnStand.setEnabled(false);									//Lets the user interact with the Stand button
				
				
				questionPanel.setVisible(false);							// Hides the panel asking the player weather to hit or stand
				
				((JLabel)dealerMessagePanel.getComponent(0)).setText("Dealer Turn");	//Setups the new panel that will guide the player through the 
				dealerMessagePanel.setVisible(true);									// dealer's turn and makes the panel visible
			}
		});
		btnStand.setEnabled(false);
		btnStand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStand.setBounds(487, 0, 105, 25);
		playerPanel.add(btnStand);
		
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {										//If the Hit button is pressed
				
				Component[] playerCardList;														//Declares a list of Components to store the labels representing
																								// the player's cards
				
				int playerCardsAddition;														//Declares int to store the addition of the values of the revealed
																								// cards of the player
				
				playerCardList = playerCardPanel.getComponents();								// Gets all the components that are inside the playerCardPanel  
																								// in the structure of the design
				
				((JLabel)playerCardList[cardsRevealed]).setIcon(cardImages[cardsRevealed]);		// Changes the label corresponding to the new card revealed
																								// so that its icon displays the imageIcon of the card

				playerCardsAddition = blackjack.AddPlayerCards(cardsRevealed);					// Calls the method Add Player Cards of blackjack to know
																								// what the value of the cardSum is after adding the value
																								// of the newly revealed card
			
				cardsRevealed++;																// Increases the amount of cardsRevealed by 1
				
				if(playerCardsAddition>21) {													// If the addition of the cards is over 21
					JOptionPane.showMessageDialog(null, "You went over 21!");					// Tells the player what happened
					btnHit.setEnabled(false);													// Doesn't let the player interact with the buttons
					btnStand.setEnabled(false);													// Hit and Stand anymore
					questionPanel.setVisible(false);											// Hides the panel asking the player weather to hit or stand
					
					ShowResults(2);																// And calls Show Results to execute the actions 
																								// corresponding to the player loosing
				
				}else if(playerCardsAddition<=21 && cardsRevealed==5){							// Else if the player hasn't exceeded 21 and there are
																								// now 5 cards revealed
					JOptionPane.showMessageDialog(null, "You got a 5 Card Monty!");				// Tells the user they got a 5 Card Monty
					btnHit.setEnabled(false);													// Doesn't let the player interact with the buttons
					btnStand.setEnabled(false);													// Hit and Stand anymore
					questionPanel.setVisible(false);											// Hides the panel asking the player weather to hit or stand
					
					ShowResults(1);																// And calls Show Results do execute the actions 
																								// corresponding to the player winning
				}
																								// Else, there are no changes and the player's turn keeps going
			}
		});


		JPanel dealerPanel = new JPanel();
		dealerPanel.setBackground(Color.LIGHT_GRAY);
		dealerPanel.setBounds(0, 0, 949, 202);
		contentPanel.add(dealerPanel);
		dealerPanel.setLayout(null);

		dealerCardPanel = new JPanel();
		dealerCardPanel.setBackground(new Color(0, 102, 51));
		dealerCardPanel.setLayout(null);
		dealerCardPanel.setBounds(10, 0, 741, 202);
		dealerPanel.add(dealerCardPanel);

		JLabel lblDealerCard1 = new JLabel("");
		lblDealerCard1.setIcon(null);
		lblDealerCard1.setForeground(new Color(102, 153, 102));
		lblDealerCard1.setBackground(new Color(51, 51, 0));
		lblDealerCard1.setBounds(27, 10, 131, 182);
		dealerCardPanel.add(lblDealerCard1);

		JLabel lblDealerCard2 = new JLabel("");
		lblDealerCard2.setIcon(null);
		lblDealerCard2.setForeground(new Color(102, 153, 102));
		lblDealerCard2.setBackground(new Color(51, 51, 0));
		lblDealerCard2.setBounds(168, 10, 131, 182);
		dealerCardPanel.add(lblDealerCard2);

		JLabel lblDealerCard3 = new JLabel("");
		lblDealerCard3.setIcon(null);
		lblDealerCard3.setForeground(new Color(102, 153, 102));
		lblDealerCard3.setBackground(new Color(51, 51, 0));
		lblDealerCard3.setBounds(309, 10, 131, 182);
		dealerCardPanel.add(lblDealerCard3);

		JLabel lblDealerCard4 = new JLabel("");
		lblDealerCard4.setIcon(null);
		lblDealerCard4.setForeground(new Color(102, 153, 102));
		lblDealerCard4.setBackground(new Color(51, 51, 0));
		lblDealerCard4.setBounds(450, 10, 131, 182);
		dealerCardPanel.add(lblDealerCard4);

		JLabel lblDealerCard5 = new JLabel("");
		lblDealerCard5.setIcon(null);
		lblDealerCard5.setForeground(new Color(102, 153, 102));
		lblDealerCard5.setBackground(new Color(51, 51, 0));
		lblDealerCard5.setBounds(591, 10, 131, 182);
		dealerCardPanel.add(lblDealerCard5);

		JPanel dealerWalletPanel = new JPanel();
		dealerWalletPanel.setBackground(new Color(102, 51, 0));
		dealerWalletPanel.setLayout(null);
		dealerWalletPanel.setBounds(754, 0, 185, 202);
		dealerPanel.add(dealerWalletPanel);

		JLabel lblDealer = new JLabel("Dealer");
		lblDealer.setForeground(new Color(255, 153, 153));
		lblDealer.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDealer.setBounds(0, 155, 185, 47);
		dealerWalletPanel.add(lblDealer);

		JLabel lblWallet2 = new JLabel("Wallet:");
		lblWallet2.setForeground(new Color(255, 255, 153));
		lblWallet2.setHorizontalAlignment(SwingConstants.CENTER);
		lblWallet2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWallet2.setBounds(0, 29, 185, 34);
		dealerWalletPanel.add(lblWallet2);

		lblDealerWallet = new JLabel("####");
		lblDealerWallet.setForeground(new Color(255, 255, 153));
		lblDealerWallet.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealerWallet.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDealerWallet.setBounds(0, 64, 185, 81);
		dealerWalletPanel.add(lblDealerWallet);

		JPanel messagePanel = new JPanel();
		messagePanel.setBackground(new Color(0, 102, 102));
		messagePanel.setBounds(10, 212, 929, 171);
		contentPanel.add(messagePanel);
		messagePanel.setLayout(null);

		questionPanel = new JPanel();
		questionPanel.setBackground(new Color(51, 204, 153));
		questionPanel.setBounds(220, 10, 377, 151);
		messagePanel.add(questionPanel);
		questionPanel.setLayout(null);

		JLabel lblMessage = new JLabel("Do you want to Hit or Stand?");
		lblMessage.setBounds(0, 45, 377, 64);
		questionPanel.add(lblMessage);
		lblMessage.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		lblMessage.setBackground(new Color(0, 204, 102));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel betAmountPanel = new JPanel();
		betAmountPanel.setBackground(new Color(51, 204, 153));
		betAmountPanel.setBounds(745, 0, 184, 171);
		messagePanel.add(betAmountPanel);
		betAmountPanel.setLayout(null);

		JLabel lblBet = new JLabel("Bet:");
		lblBet.setHorizontalAlignment(SwingConstants.CENTER);
		lblBet.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBet.setBounds(0, 10, 185, 34);
		betAmountPanel.add(lblBet);

		lblBetMoney = new JLabel("0");
		lblBetMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblBetMoney.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBetMoney.setBounds(0, 54, 185, 81);
		betAmountPanel.add(lblBetMoney);

		betQuestionPanel = new JPanel();
		betQuestionPanel.setLayout(null);
		betQuestionPanel.setBackground(new Color(51, 204, 153));
		betQuestionPanel.setBounds(220, 10, 377, 151);
		messagePanel.add(betQuestionPanel);

		JLabel lblMessage_1 = new JLabel("How much do you want to Bet?");
		lblMessage_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		lblMessage_1.setBackground(new Color(0, 204, 102));
		lblMessage_1.setBounds(0, 0, 377, 53);
		betQuestionPanel.add(lblMessage_1);

		JPanel betButtonsPanel = new JPanel();
		betButtonsPanel.setBounds(70, 55, 224, 47);
		betQuestionPanel.add(betButtonsPanel);
		betButtonsPanel.setLayout(null);

		JLabel lblCurrentBet = new JLabel("0");
		lblCurrentBet.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurrentBet.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentBet.setBounds(50, 0, 125, 47);
		betButtonsPanel.add(lblCurrentBet);

		JButton btnLowerBetButton = new JButton("-");
		btnLowerBetButton.addActionListener(new ActionListener() {										//If the button to lower the bet is pressed
			public void actionPerformed(ActionEvent e) {
				int playerWallet;																		//Declares integer to make operations on the player's wallet
				int dealerWallet;																		//Declares integer to make operations on the dealer's wallet
				int currentBet;																			//Declares integer to store the proposed bet amount
				int totalBetMoney;																		//Declares integer to store the money bet on the round
				
				playerWallet = Integer.parseInt(lblPlayerWallet.getText());								//Stores the text of the labels as integers in the 
				dealerWallet = Integer.parseInt(lblDealerWallet.getText());								// corresponding variables
				currentBet = Integer.parseInt(lblCurrentBet.getText());
				totalBetMoney = Integer.parseInt(lblBetMoney.getText());

				if(currentBet == 0)																								//If the current bet is 0
					JOptionPane.showMessageDialog(lblCurrentBet, "ERROR. You can't bet a negative amount of money");			//doesn't let the player lower it more
				
				else {																											//Else,
					playerWallet+=20;																							// Increases the wallet of the player and the dealer
					dealerWallet+=20;																							// by 20
					currentBet-=20;																								// Reduces the current proposed bet by 20
					totalBetMoney-=40;																							// And increases the pot by 40

					lblPlayerWallet.setText(String.valueOf(playerWallet));														// And shows the new values in the 
					lblDealerWallet.setText(String.valueOf(dealerWallet));														//corresponding labels
					lblCurrentBet.setText(String.valueOf(currentBet));
					lblBetMoney.setText(String.valueOf(totalBetMoney));
				}
			}
		});
		btnLowerBetButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLowerBetButton.setBounds(0, 0, 50, 50);
		betButtonsPanel.add(btnLowerBetButton);

		JButton btnRaiseBetButton = new JButton("+");
		btnRaiseBetButton.addActionListener(new ActionListener() {									//If the button to raise the bet is pressed
			public void actionPerformed(ActionEvent e) {
				int playerWallet;																	//Declares integer to make operations on the player's wallet
				int dealerWallet;																	//Declares integer to make operations on the dealer's wallet
				int currentBet;																		//Declares integer to store the proposed bet amount
				int totalBetMoney;																	//Declares integer to store the money bet on the round
				
				playerWallet = Integer.parseInt(lblPlayerWallet.getText());							//Stores the text of the labels as integers in the 
				dealerWallet = Integer.parseInt(lblDealerWallet.getText());							// corresponding variables
				currentBet = Integer.parseInt(lblCurrentBet.getText());
				totalBetMoney = Integer.parseInt(lblBetMoney.getText()); 

				if(playerWallet - 20 < 0)																		// If either the player's wallet or the dealer's wallet 
					JOptionPane.showMessageDialog(lblCurrentBet, "ERROR. Player Wallet insufficient");			// cannot go any further down,
				else if (dealerWallet - 20 < 0)																	// tells the player the money is insufficient and
					JOptionPane.showMessageDialog(lblCurrentBet, "ERROR. Dealer Wallet insufficient");			// doesn't let the user raise the bet anymore,
				
				else {																							// Else, 
					playerWallet-=20;																			// Reduces the wallet of the player and the dealer
					dealerWallet-=20;																			// by 20
					currentBet+=20;																				// Increases the current proposed bet by 20
					totalBetMoney+=40;																			// And decreases the pot by 40

					lblPlayerWallet.setText(String.valueOf(playerWallet));										// And shows the new values in the
					lblDealerWallet.setText(String.valueOf(dealerWallet));										// corresponding labels
					lblCurrentBet.setText(String.valueOf(currentBet));
					lblBetMoney.setText(String.valueOf(totalBetMoney));
				}
			}
		});
		btnRaiseBetButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRaiseBetButton.setBounds(174, 0, 50, 50);
		betButtonsPanel.add(btnRaiseBetButton);

		JButton btnConfirmBetButton = new JButton("Confirm");
		btnConfirmBetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {									// If the  button to confirm the bet is pressed
				
				int betMoney;																//Declares integer to store the money bet on the round
				
				Component[] playerCardList;													//Declares list of Components to store the labels representing
				Component[] dealerCardList;													// the dealer's cards and player's cards
				
				betMoney = Integer.parseInt(lblBetMoney.getText());
				
				if(betMoney == 0)																// If the current proposed bet is 0,
					JOptionPane.showMessageDialog(null, "Bet must be higher than 0");			// informs the player that the action isn't possible 
																								// and doesn't let the bet go through
				
				else {																			// Else, 
					blackjack.PlaceBet(betMoney);												// Calls the method Place Bet from blackjack to change the 
																								// player's and the dealer's wallet data member accordingly
					
					betQuestionPanel.setVisible(false);											// Hides the panel that determines the bet amount
					questionPanel.setVisible(true);												// Makes visible the panel that asks the player weather to Hit or Stand
					
					
					playerCardList = playerCardPanel.getComponents();							// Gets all the components that are inside the playerCardPanel  
					dealerCardList = dealerCardPanel.getComponents();							// and the DealerCardPanel in the structure of the design
					
					((JLabel)playerCardList[0]).setIcon(cardImages[0]);							// Changes the icons of the first 2 labels in each panel
					((JLabel)playerCardList[1]).setIcon(cardImages[1]);							// to visually represent the corresponding cards, thus
					((JLabel)dealerCardList[0]).setIcon(cardImages[5]);							// "revealing" them
					((JLabel)dealerCardList[1]).setIcon(cardImages[6]);
					
					cardsRevealed = 2;															// Data member cardsRevealed is set to 2, representing the amount
																								// of revealed cards the player has
					
					btnHit.setEnabled(true);													// Lets the user interact with the Hit and Stand buttons
					btnStand.setEnabled(true);
					
					lblCurrentBet.setText("0");													// Sets the text of the label representing the proposed bet
																								// to "0", so it appears to have reset when showing the 
																								// Bet Question Panel again
				}
			}
		});
		btnConfirmBetButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmBetButton.setBounds(130, 120, 105, 25);
		betQuestionPanel.add(btnConfirmBetButton);

		dealerMessagePanel = new JPanel();
		dealerMessagePanel.setLayout(null);
		dealerMessagePanel.setBackground(new Color(51, 204, 153));
		dealerMessagePanel.setBounds(220, 10, 377, 151);
		messagePanel.add(dealerMessagePanel);
		dealerMessagePanel.setVisible(false);

		JLabel lblDealerMessage = new JLabel("Dealer chose to Hit and got a 5 Card Monty!”");
		lblDealerMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealerMessage.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		lblDealerMessage.setBackground(new Color(0, 204, 102));
		lblDealerMessage.setBounds(0, 30, 377, 64);
		dealerMessagePanel.add(lblDealerMessage);

		btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				DealerTurn();
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnContinue.setBounds(134, 105, 105, 25);
		dealerMessagePanel.add(btnContinue);
		
		endRoundMessagePanel = new JPanel();
		endRoundMessagePanel.setLayout(null);
		endRoundMessagePanel.setBackground(new Color(51, 204, 153));
		endRoundMessagePanel.setBounds(220, 10, 377, 151);
		messagePanel.add(endRoundMessagePanel);
		endRoundMessagePanel.setVisible(false);
		
		JLabel lblEndMessage = new JLabel("");
		lblEndMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndMessage.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		lblEndMessage.setBackground(new Color(0, 204, 102));
		lblEndMessage.setBounds(0, 30, 377, 64);
		endRoundMessagePanel.add(lblEndMessage);
		
		btnNewRound = new JButton("Next Round");
		btnNewRound.addActionListener(new ActionListener() {			//If the "Next Round" Button is pressed
			public void actionPerformed(ActionEvent e) {
				endRoundMessagePanel.setVisible(false);					//Hides panel showing the winner
				NewRound();												//Starts a new round
			}
		});
		btnNewRound.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewRound.setBounds(120, 105, 132, 25);
		endRoundMessagePanel.add(btnNewRound);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {				//If the "New Game" Button is pressed
			public void actionPerformed(ActionEvent e) {
				btnNewRound.setVisible(true);							//Makes the button for new round visible again
																		//for the next time the panel opens
				
				endRoundMessagePanel.setVisible(false);					//Hides panel showing the winner
				NewGame();												//Start a new game
			}
		});
		btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewGame.setBounds(120, 105, 132, 25);
		endRoundMessagePanel.add(btnNewGame);
		
		
		
		{	
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// Method				:	BlackjackApp()
			//
			// Method parameters	:	none
			//
			// Method return		:	void
			//
			// Synopsis				:   This section is used as the more traditional constructor of the class BlackjackApp
			//							It defines the data members of BlackjackApp as 0, or an empty list or object
			//							so they can later be obtained or modified, the only exception being
			//							backCardImage which is set as the corresponding ImageIcon.
			//							Then, it starts a New Game.
			//
			// Modifications		:
			//							Date			Developer				Notes
			//							----			---------				-----
			//							2023-03-30		A. Mojica				Initial setup
			//
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			
			
			
			this.blackjack = new Blackjack();											// Instantiates the BlackjackApp's data member "blackjack"
																						// as a new object of type Blackjack
			
			this.cardImages = new ImageIcon[10];										// Instantiates the BlackjackApp's data member "cardImages" as an empty list with 10 spaces		
			
			this.backCardImage = new ImageIcon("Images/BackImage.png");					// Defines the BlackjackApp's data member "backCardImage" as an Image Icon resulting
																						// from a given png image that is saved in the images file of the project
			
			this.cardsRevealed = 0;														// Instantiates the BlackjackApp's data member "cardsRevealed" as the integer 0
			
			JOptionPane.showMessageDialog(null, "Welcome to Blackjack!");				// Welcomes the player into the game
			NewGame();																	// Calls the method NewGame to setup the UI for a new game
		}
	}

	public void NewGame() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void NewGame()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method makes the visual setup necessary to start a New Game
		//							and calls the method of the same name in the object blackjack to do back-end setup.
		//							Then it starts a New Round.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		blackjack.NewGame();													// Calls the method NewGame of the object blackjack to 
																				// change the wallets of the dealer and the player
		
		lblPlayerWallet.setText("1000");									//Changes the text of the labels representing the wallet to "1000"
		lblDealerWallet.setText("1000");									//so they are in line with the actual wallet data member of the dealer
																			// and the player
		
		NewRound();															// Calls method NewRound to do the setup for a new round
	}
	
	public void NewRound() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void NewRound()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method makes the visual setup necessary to start a New Round by turning all
		//							the cards "face down" and calling the method of the same name in the object blackjack
		//							to do the corresponding back-end setup.
		//							Then it turn the betQuestionPnael visible so the player can bet.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		Component[] playerCardList;																//Declares a list of Components to store the labels representing
																								// the player's cards
		
		Component[] dealerCardList;																//Declares a list of Components to store the labels representing
																								// the dealer's cards
		
		int counter;																			//Declares counter to control iterations of the loop
		
		playerCardList = playerCardPanel.getComponents();										// Gets all the components that are inside the playerCardPanel
		dealerCardList = dealerCardPanel.getComponents();										// and the DealerCardPanel in the structure of the design
		
		for(counter = 0; counter<5; counter ++) {												// Uses a for to set all the labels representing
			((JLabel)playerCardList[counter]).setIcon(backCardImage);							// the cards of the dealer and the player with an ImageIcon
			((JLabel)dealerCardList[counter]).setIcon(backCardImage);							// that shows the card "face down"
		}
		
		this.cardImages = blackjack.NewRound();													// Calls the method NewRound of the object blackjack to shuffle the deck 
																								// and returns the Image Icons of the cards that will be used 
																								// in the round, which are stored in the data member cardImages
		
		questionPanel.setVisible(false);														// Hides the panel asking the player weather to hit or stand
		betQuestionPanel.setVisible(true);														// Makes visible the panel that lets the player decide the bet amount
		
	
	}
	
	public void DealerTurn() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void DealerTurn()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method calls for the blackjack method Dealer Action and shows information
		//							to the user based on the result obtained from said method.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		Component[] dealerCardList;																//Declares a list of Components to store the labels representing
																								// the dealer's cards
		
		int result;																				//Declares an integer to store the result of the dealer's action
																								// and give feedback accordingly
		
		dealerCardList = dealerCardPanel.getComponents();										// Gets all the components that are inside the dealerCardPanel  
																								// in the structure of the design
		
		result= blackjack.DealerAction(cardsRevealed);											// Calls the method DealerAction of the object blackjack where the 
																								// dealer decides its next action and stores the integer representing the 
																								// action that took place in the variable result
		
		if(result == 1) {																		//If the result is 1, it mean the dealer chose to stand
			((JLabel)dealerMessagePanel.getComponent(0)).setText("Dealer chose to Stand");		//Inform the user about the action
			dealerMessagePanel.setVisible(false);												// Hides the dealerMessage Panel as no more dealer actions will take place
			ShowResults(0);																		// And calls the method Show Results to execute the actions corresponding to
																								// a winner not being determined yet
		
		}else if(result == 2) {																	//Else, if the result is 2, it mean the dealer chose to hit
			((JLabel)dealerCardList[cardsRevealed]).setIcon(cardImages[cardsRevealed+5]);		//Changes the corresponding label to reveal the new ImageIcon
			cardsRevealed++;																	// increases the amount of cardsRevealed by 1
			((JLabel)dealerMessagePanel.getComponent(0)).setText("Dealer chose to Hit");		// And informs the user about the action
	
		}else if(result == 3) {																	//Else, if the result is 2, it mean the dealer went over 21
			((JLabel)dealerCardList[cardsRevealed]).setIcon(cardImages[cardsRevealed+5]);		//Changes the corresponding label to reveal the new ImageIcon
			cardsRevealed++;																	// increases the amount of cardsRevealed by 1
			JOptionPane.showMessageDialog(null,"Dealer chose to Hit and went over 21!");		// And informs the user about the action and the result
			dealerMessagePanel.setVisible(false);												// Hides the dealerMessage Panel as no more dealer actions will take place
			ShowResults(1);																		// And calls the method Show Results to execute the actions corresponding to
																								// the dealer loosing
			
		}else if(result==4) {																	//Else, if the result is 4, it mean the dealer got a 5 Card Monty
			((JLabel)dealerCardList[cardsRevealed]).setIcon(cardImages[cardsRevealed+5]);		//Changes the corresponding label to reveal the new ImageIcon
			cardsRevealed++;																	// increases the amount of cardsRevealed by 1
			JOptionPane.showMessageDialog(null,"Dealer chose to Hit and got a 5 Card Monty!" );	// And informs the user about the action and the result
			dealerMessagePanel.setVisible(false);												// Hides the dealerMessage Panel as no more dealer actions will take place
			
			ShowResults(2);																		// And calls the method Show Results to execute the actions corresponding to
																								// the dealer winning
		}
		
		
	}
	
	public void ShowResults(int result) { 
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void ShowResults(int result)
		//
		// Method parameters	:	result		- The method uses it to decide what feedback should be shown to
		//											the user
		//
		// Method return		:	void
		//
		// Synopsis				:   This method receives an integer called result and uses it 
		//							to show who is the winner of the round and show the winning party
		//							receiving the money bet on the round.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		
		int playerWallet;															//Declares integer to make operations on the player's wallet
		int dealerWallet;															//Declares integer to make operations on the dealer's wallet
		int betMoney;																//Declares integer to store the money bet on the round
		
		endRoundMessagePanel.setVisible(true);										//Makes the panel that shows the results visible
		
		playerWallet = Integer.parseInt(lblPlayerWallet.getText());
		dealerWallet = Integer.parseInt(lblDealerWallet.getText());
		betMoney = Integer.parseInt(lblBetMoney.getText());
		
		if(result == 0)																//if no winner has been determined yet,
			result=blackjack.CheckResults();										//calls CheckResults to determine who has the bigger cardSum
																					// and should be the winner
		
		
		if (result==1) {																		//If the player is the winner
			((JLabel)endRoundMessagePanel.getComponent(0)).setText("You won the Round!");		//Informs the player
			blackjack.GiveMoney(betMoney, result);												// Calls GiveMoney to give the player the pot money
			playerWallet+= betMoney;															// Adds the betMoney to the player's wallet
			lblPlayerWallet.setText(String.valueOf(playerWallet));								// Changes the label reflecting the player's wallet to reflect the new amount
			
		}else if (result==2) {																		//If the dealer is the winner
			((JLabel)endRoundMessagePanel.getComponent(0)).setText("The Dealer won the Round!");	//Informs the player
			blackjack.GiveMoney(betMoney, result);													// Calls GiveMoney to give the dealer the pot money
			dealerWallet+= betMoney;																// Adds the betMoney to the dealer's wallet
			lblDealerWallet.setText(String.valueOf(dealerWallet));									// Changes the label reflecting the dealer's wallet to reflect the new amount
			
		}else {																						//Else, it's a tie
			((JLabel)endRoundMessagePanel.getComponent(0)).setText("It's a Tie!");					//Informs the player
			
			betMoney/= 2;																			// divides bet money by 2
			
			blackjack.GiveMoney(betMoney, result);													// Calls GiveMoney to split the money between the player and the dealer
			
			playerWallet+= betMoney;																// and gives both the dealer and the player a half of the pot money
			dealerWallet+= betMoney;

			lblPlayerWallet.setText(String.valueOf(playerWallet));									// Changes the label reflecting the dealer's and the player's
			lblDealerWallet.setText(String.valueOf(dealerWallet));									// wallet to reflect the new amount

		}
		lblBetMoney.setText("0");																	//Shows the pot as having 0 money
		CheckForWinner();																			//Calls CheckForWinner to see if either the dealer or the player
																									// has no money left on the wallet
	}
	
	public void CheckForWinner() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void CheckForWinner()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method calls for the blackjack method CheckMoney to see if either the dealer
		//							or the player has 0 on their wallet, which would mean losing the game.
		//							If so, shows the corresponding message to the user and then hides the button
		//							that takes the player to a new round, so a new game starts instead.
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2023-03-30		A. Mojica				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
				
		int winner;														//Declares an integer to determine if there is a winner
		
		winner = blackjack.CheckMoney();								//Calls the method CheckMoney to see if one of the wallets
																		// is at 0
		
		if(winner == 1) {																				//If the dealer's wallet is at 0, the player wins
			JOptionPane.showMessageDialog(null, "The Dealer lost and has no Money Left. You Won!");		//Informs the player
			((JLabel)endRoundMessagePanel.getComponent(0)).setText("Do you want to play again?");		//Changes the visible text so it asks for a new game instead
			btnNewRound.setVisible(false);																//And hides the button that takes the player to a new round
		
		}else if(winner == 2) {																			//Else, if the player's wallet is at 0, the dealer wins
			JOptionPane.showMessageDialog(null, "You lost and have no Money Left. The Dealer Won!");	//Informs the player
			((JLabel)endRoundMessagePanel.getComponent(0)).setText("Do you want to play again?");		//Changes the visible text so it asks for a new game instead
			btnNewRound.setVisible(false);																//And hides the button that takes the player to a new round
		}
		
																										//Else, nothing happens and the player proceeds to the next 
																										//round as usual
	}
}
