package spygames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * SpyGame Class
 * This class represents a new instantiation 
 * of the Spy Game UI 
 * @author glendon cheney
 */
public class SpyGames {
	
    private int spyPlayerNumber;
    private String spyWord, alliesWord;
    private static List<Entry<String, String>> wordEntryList;
    private static int gameCount = 0;
    
    public SpyGames(int numPlayers) {
        setSpy(numPlayers);
        assignWords();    
    }
    
    private void setSpy(int n) {
        Random rand = new Random();
        spyPlayerNumber = rand.nextInt(n);
    }
    
    private void initWordList() {
    	Map<String, String> wordMap = new HashMap<String, String>();
    	wordMap.put("Newspaper" , "Magazine");
    	wordMap.put("Book" , "Article");
    	wordMap.put("DVD" , "VHS");
    	wordMap.put("CD" , "MP3");
    	wordMap.put("Coke" , "Pepsi");
    	wordMap.put("Horse" , "Pony");
    	wordMap.put("Pokemon" , "Digimon");
    	wordMap.put("Cello" , "Violin");
    	wordMap.put("Piano" , "Keyboard");
    	wordMap.put("Cigar" , "Cigarette");
    	
    	wordEntryList = new ArrayList<Entry<String, String>>(wordMap.entrySet());      
    }
    
    private void assignWords() {
    	//if first run or every word has already been used
    	if (getGameCount() == 0 || wordEntryList.size() == 0) {
    		initWordList();
    	}
    	
        //create a radnom number to get at a random word group
        Random rand = new Random();
        int randomIndex = rand.nextInt(wordEntryList.size());
           
        //Get the word from that index in list, and remove it 
        //from list to avoid duplicate rounds
        Entry<String, String> wordEntry = wordEntryList.get(randomIndex);
        wordEntryList.remove(randomIndex);
        
        setSpyWord(wordEntry.getValue());
        setAlliesWord(wordEntry.getKey());       
    }
    
    /*
     * 
     * GETTERS and SETTERS
     * 
     */
    
    public int getSpyNumber() {
        return spyPlayerNumber;
    }
    
    private void setSpyWord(String word) {
    	spyWord = word;
    }
    
    public String getSpyWord(){
        return spyWord;
    }
    
    private void setAlliesWord(String word) {
    	alliesWord = word;
    }
    
    public String getAlliesWord() {
        return alliesWord;
    }
    
    public static int getGameCount() { 
        return gameCount;
    }
    
    public void increaseGameCount() {
    	gameCount++;
    }
    
}
