package guessthespy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


/**
 * @author glendon cheney
 */
public class GuessTheSpyUI extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private ButtonGroup btnGroup = new ButtonGroup();
    private final int MAX_PLAYERS = 6;
    private String [] playerWords = new String[MAX_PLAYERS];
    private JRadioButton[] radios = new JRadioButton[MAX_PLAYERS];
    private int numPlayers = 0, spyPlayer = 0, spyWins = 0, alliesWins = 0;
    private GuessTheSpy game; //the game
    
    /**
     * Creates new form SpyGameUI
     */
    public GuessTheSpyUI() {
        initComponents();
        initForm();
    }

    private void initComponents() {    	
    	//Initialize components for GUI
        playersPanel = new JPanel();
        lblMainTitle = new JLabel();
        lblChoosePlayer = new JLabel();
        btnChoice = new JButton();
        radButtonPanel = new JPanel();
        radBtnPlayer1 = new JRadioButton();
        radBtnPlayer2 = new JRadioButton();
        radBtnPlayer3 = new JRadioButton();
        radBtnPlayer4 = new JRadioButton();
        radBtnPlayer5 = new JRadioButton();
        radBtnPlayer6 = new JRadioButton();
        btnNewGame = new JButton();
        btnStartGame = new JButton();
        btnWords = new JButton();
        btnGameStats = new JButton();
        lblTitle = new JLabel();
        setPlayersPanel = new JPanel();
        lblNumPlayers = new JLabel();
        cmbPlayers = new JComboBox<String>();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblMainTitle.setFont(new Font("Lucida Grande", 0, 14)); 
        lblMainTitle.setText("Guess The Spy");

        lblChoosePlayer.setText("Choose a Player from the list below:");

        btnChoice.setText("Make Your Choice");
        btnChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChoiceActionPerformed(evt);
            }
        });

        radBtnPlayer1.setFont(new Font("Lucida Grande", 0, 14)); 
        radBtnPlayer1.setText("Player 1");
        radBtnPlayer1.setName("1");

        radBtnPlayer2.setFont(new Font("Lucida Grande", 0, 14)); 
        radBtnPlayer2.setText("Player 2");
        radBtnPlayer2.setName("2");

        radBtnPlayer3.setFont(new Font("Lucida Grande", 0, 14)); 
        radBtnPlayer3.setText("Player 3");
        radBtnPlayer3.setName("3");

        radBtnPlayer4.setFont(new Font("Lucida Grande", 0, 14));
        radBtnPlayer4.setText("Player 4");
        radBtnPlayer4.setName("4");

        radBtnPlayer5.setFont(new Font("Lucida Grande", 0, 14)); 
        radBtnPlayer5.setText("Player 5");
        radBtnPlayer5.setName("5");

        radBtnPlayer6.setFont(new Font("Lucida Grande", 0, 14)); 
        radBtnPlayer6.setText("Player 6");
        radBtnPlayer6.setName("6");

        org.jdesktop.layout.GroupLayout radButtonPanelLayout = new org.jdesktop.layout.GroupLayout(radButtonPanel);
        radButtonPanel.setLayout(radButtonPanelLayout);
        radButtonPanelLayout.setHorizontalGroup(
            radButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(radButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(radButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(radBtnPlayer3)
                    .add(radBtnPlayer6)
                    .add(radBtnPlayer2)
                    .add(radBtnPlayer4)
                    .add(radBtnPlayer5)
                    .add(radBtnPlayer1))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        radButtonPanelLayout.setVerticalGroup(
            radButtonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(radButtonPanelLayout.createSequentialGroup()
                .add(12, 12, 12)
                .add(radBtnPlayer1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(radBtnPlayer2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(radBtnPlayer3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(radBtnPlayer4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(radBtnPlayer5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(radBtnPlayer6)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnNewGame.setText("New Game");
        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });

        btnStartGame.setText("Start Game");
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });

        btnWords.setText("See Words");
        btnWords.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnWordsActionPerformed(evt);
            }
        });

        btnGameStats.setText("Get Stats");
        btnGameStats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGameStatsActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout playersPanelLayout 
        	= new org.jdesktop.layout.GroupLayout(playersPanel);
        playersPanel.setLayout(playersPanelLayout);
        playersPanelLayout.setHorizontalGroup(
            playersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(playersPanelLayout.createSequentialGroup()
                .add(btnWords, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 139, 
                		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(playersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(playersPanelLayout.createSequentialGroup()
                        .add(btnChoice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 139, 
                        		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(playersPanelLayout.createSequentialGroup()
                        .add(btnNewGame, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 139, 
                        		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnGameStats, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 125, 
                        		Short.MAX_VALUE)))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, playersPanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(playersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, playersPanelLayout.createSequentialGroup()
                        .add(btnStartGame, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 126, 
                        		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(133, 133, 133))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, playersPanelLayout.createSequentialGroup()
                        .add(lblMainTitle)
                        .add(146, 146, 146))))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, playersPanelLayout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(playersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, playersPanelLayout.createSequentialGroup()
                        .add(lblChoosePlayer)
                        .add(87, 87, 87))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, playersPanelLayout.createSequentialGroup()
                        .add(radButtonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 
                        		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 
                        		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(154, 154, 154))))
        );
        
        playersPanelLayout.setVerticalGroup(
            playersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(playersPanelLayout.createSequentialGroup()
                .add(btnStartGame)
                .add(12, 12, 12)
                .add(lblMainTitle)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblChoosePlayer)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(radButtonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 
                		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 
                		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnChoice)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(playersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnWords)
                    .add(btnNewGame)
                    .add(btnGameStats))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTitle.setFont(new Font("Lucida Grande", 0, 18)); 
        lblTitle.setText("Spy Games: The Game");

        lblNumPlayers.setText("How many Players are there? ");

        cmbPlayers.setModel(new DefaultComboBoxModel<String>(new String[] { "3", "4", "5", "6" }));
        cmbPlayers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmbPlayersActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout setPlayersPanelLayout 
        	= new org.jdesktop.layout.GroupLayout(setPlayersPanel);
        setPlayersPanel.setLayout(setPlayersPanelLayout);
        setPlayersPanelLayout.setHorizontalGroup(
            setPlayersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(setPlayersPanelLayout.createSequentialGroup()
                .add(15, 15, 15)
                .add(lblNumPlayers)
                .add(60, 60, 60)
                .add(cmbPlayers, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 
                		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 
                		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );       
        setPlayersPanelLayout.setVerticalGroup(
            setPlayersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(setPlayersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(setPlayersPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblNumPlayers)
                    .add(cmbPlayers, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 
                    		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 
                    		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(lblTitle)
                .add(104, 104, 104))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, playersPanel, 
                    		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 
                    		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 
                    		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, setPlayersPanel, 
                    		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 
                    		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 
                    		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(lblTitle)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 
                		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(setPlayersPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 
                		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 
                		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(playersPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 
                		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 
                		org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(78, 78, 78))
        );

        this.pack();
    }
    
    private void initForm(){
        playersPanel.setVisible(false);
        setPlayersPanel.setVisible(true);
        btnChoice.setEnabled(false);
        btnWords.setEnabled(false);
        cmbPlayers.setEnabled(true);
        
        for ( int i=0; i < radButtonPanel.getComponentCount(); i++ ) {
            JRadioButton radButton = (JRadioButton)radButtonPanel.getComponent(i);
            btnGroup.add(radButton);
            radButton.setVisible(false);
            radButton.setEnabled(false);
            radButton.setForeground(Color.BLACK);
            int index = Integer.parseInt(radButton.getName());
            radButton.setText("Player " + index);
            radios[index - 1] = radButton;
        }
        
        playerWords = new String[MAX_PLAYERS];
        numPlayers = 0;
        spyPlayer = 0;
        
        this.pack();
    }
    
    private void showWordsToPlayers() {
        for (int i=0; i < numPlayers; i++) { 
            if (radios[i].isEnabled()) {
                JOptionPane.showMessageDialog(this, "Let Player " + (i+1) 
                        +" come to the screen. Click OK when ready.", 
                        "Player " + (i+1) + " Ready", 
                        JOptionPane.INFORMATION_MESSAGE);
                
                JOptionPane.showMessageDialog(this, "Your word is: " 
                        + playerWords[i] + "\nPress OK to continue.", 
                        "Player " + (i+1) + " Word", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    //Code for a window closing event
    private void exitProgram(WindowEvent e) {
        JFrame frame = (JFrame)e.getSource();

        int result = JOptionPane.showConfirmDialog( frame,
                        "Are you sure you want to exit the game?",
                        "Exit Game?", JOptionPane.YES_NO_OPTION);

       if (result == JOptionPane.YES_OPTION){
           frame.setVisible(false);
           System.exit(0);
       }
       else {
           frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       }
    }
    
    private void cmbPlayersActionPerformed(ActionEvent evt) {
        // Make the main form visible and initialize radio buttons
        playersPanel.setVisible(true);
        btnStartGame.setEnabled(true);
        numPlayers = Integer.parseInt((String)cmbPlayers.getSelectedItem()); 
        
        for (JRadioButton radBtn : radios) {
            radBtn.setVisible(false);
        }
        
        for (int i=0; i<numPlayers; i++) {
            radios[i].setVisible(true);
        }
        
        this.pack(); //Resize the Form
    }

    private void btnNewGameActionPerformed(ActionEvent evt) {
    	int result = JOptionPane.showConfirmDialog( this,
                "Are you sure you want to start a new game?",
                "New Game?", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION){
			 initForm();
		}
		else {
			System.out.println("The game is still afoot!");
		}
    }

    private void btnStartGameActionPerformed(ActionEvent evt) {
        //Enable GUI controls
        setPlayersPanel.setVisible(false);
        btnChoice.setEnabled(true);
        btnWords.setEnabled(true);
        btnStartGame.setEnabled(false);
        cmbPlayers.setEnabled(false);
        this.pack();
        
        for (JRadioButton radBtn : radios){
            radBtn.setEnabled(true);
        }
        //Set up a new SpyGame object
        game = new GuessTheSpy(numPlayers);
        
        //assign values from game
        spyPlayer = game.getSpyNumber();
        String spy = game.getSpyWord();
        String allies = game.getAlliesWord();
        
        for (int i = 0; i < numPlayers; i++) {
            if (i==spyPlayer) {
                playerWords[i] = spy;
            }
            else {
                playerWords[i] = allies;
            }
        }
        //Calls the method to Display words to all players
        showWordsToPlayers();
        
        JOptionPane.showMessageDialog(this, 
                "Now you must each describe the word assigned to you and " + 
                "decide amongst yourselves who the spy is.\n" + 
                "Press the Choice button once you have made your decision.", 
                "Game Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnChoiceActionPerformed(ActionEvent evt) {
        //Get count for remaining radio buttons
        int count = 0;
        for (JRadioButton radButton : radios) {
            if (radButton.isEnabled() && radButton.isVisible()) {
                count++;
            }
        }
        //Check is guess was correct
        for ( int i=0; i < numPlayers; i++ ) {
            if (radios[i].isSelected()) {
                if (i==spyPlayer) {
                    JOptionPane.showMessageDialog(this, 
                            "Congratulations! You guessed the spy!", 
                            "Winner!", JOptionPane.INFORMATION_MESSAGE);
                    radios[i].setForeground(Color.BLUE);
                    radios[i].setText("Player " + (spyPlayer + 1) 
                                + " was the spy!");
                    alliesWins++;
                    gameOver();
                }
                else {
                    if (count==2) {
                        JOptionPane.showMessageDialog(this, 
                                "The spy escaped! You have failed!", 
                                "The spy won!", JOptionPane.WARNING_MESSAGE);
                        radios[spyPlayer].setForeground(Color.RED);
                        radios[spyPlayer].setText("Player " 
                                + (spyPlayer + 1) + " was the spy!");
                        spyWins++;
                        gameOver();
                    }
                    else {
                        JOptionPane.showMessageDialog(this, 
                                "Oops! You guessed wrong! Play another round,", 
                                "Wrong!", JOptionPane.ERROR_MESSAGE);
                        radios[i].setEnabled(false);
                        radios[i].setVisible(false);
                        this.pack();
                    }
                }
            }
        }
    }

    private void btnWordsActionPerformed(ActionEvent evt) {
        showWordsToPlayers();
    }

    private void btnGameStatsActionPerformed(ActionEvent evt) {
        //Calculate and display wins/losses
        int totalGames = GuessTheSpy.getGameCount();
        int quitGames = totalGames- (spyWins + alliesWins);
        JOptionPane.showMessageDialog(this, 
                "The spy has won " + spyWins + " games.\n" 
                + "The allies have won " + alliesWins + " games.\n"
                + "You have quit " + quitGames + " games.\n"
                + "You have played " + totalGames + " games.", 
                "Game Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void gameOver() {
        //Call once the current game has finished
        btnChoice.setEnabled(false);
        btnWords.setEnabled(false);
        game.increaseGameCount();
    }

    private void formWindowClosing(WindowEvent evt) {
        exitProgram(evt);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(GuessTheSpyUI.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) {
            Logger.getLogger(GuessTheSpyUI.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) {
            Logger.getLogger(GuessTheSpyUI.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GuessTheSpyUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
         * Create and display the game UI
         */
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GuessTheSpyUI().setVisible(true);
            }
            
        });
    }
    
    // Compennt Variables declaration 
    private JButton btnChoice;
    private JButton btnGameStats;
    private JButton btnNewGame;
    private JButton btnStartGame;
    private JButton btnWords;
    private JComboBox<String> cmbPlayers;
    private JLabel lblMainTitle;
    private JLabel lblChoosePlayer;
    private JLabel lblNumPlayers;
    private JLabel lblTitle;
    private JRadioButton radBtnPlayer1;
    private JRadioButton radBtnPlayer2;
    private JRadioButton radBtnPlayer3;
    private JRadioButton radBtnPlayer4;
    private JRadioButton radBtnPlayer5;
    private JRadioButton radBtnPlayer6;
    private JPanel playersPanel;
    private JPanel radButtonPanel;
    private JPanel setPlayersPanel;
    // End of variables declaration
}
