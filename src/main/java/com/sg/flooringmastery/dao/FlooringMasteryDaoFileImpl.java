/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Orders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author travi
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    
    private Map<String, Orders> myOrders = new HashMap<>();
    
    public static final String ORDERS_FILE = "FLOORINGMASTERY.txt";
    public static final String DELIMITER = "::"; 

    @Override
    public Orders getOrder(String orderNumber) 
            throws FlooringMasteryPersistenceExpection {
        //loadRoster();
        return myOrders.get(orderNumber);        
    }

    @Override
    public Orders addOrder(String orderNumber, Orders order) 
            throws FlooringMasteryPersistenceExpection {
        Orders newOrder = myOrders.put(orderNumber, order);
        //writeRoster();
        return newOrder;                
    }

    @Override
    public List<Orders> getAllOrders() 
            throws FlooringMasteryPersistenceExpection {
        return new ArrayList<Orders>(myOrders.values());        
    }

    @Override
    public Orders removeOrder(String orderNumber) 
            throws FlooringMasteryPersistenceExpection {
        Orders removedOrder = myOrders.remove(orderNumber);
        //writeRoster();
        return removedOrder;        
    }
    
    
    
    
    
    
    
    
    /*
    // -- LOAD - ROSTER --
    private void loadRoster() throws PlayerRosterDaoException {
        Scanner scanner;
        
        try {   
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                        new FileReader(PLAYER_FILE)));
        } catch (FileNotFoundException e){
            throw new PlayerRosterDaoException(
                    "-_- Could not load roster data into memory", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        String[] currentTokens;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Player currentPlayer = new Player(currentTokens[0]);
            currentPlayer.setFirstName(currentTokens[1]);
	    currentPlayer.setLastName(currentTokens[2]);
	    currentPlayer.setTeam(currentTokens[3]);
            
            players.put(currentPlayer.getPlayerId(), currentPlayer);           
        }
        scanner.close(); 
        
    } // -- loadRoster() --


    // -- WRITE - ROSTER --
    private void writeRoster() throws PlayerRosterDaoException {
	    
	    PrintWriter out;
	    
	    try {                
	        out = new PrintWriter(new FileWriter(PLAYER_FILE));
	    } catch (IOException e) {
	        throw new PlayerRosterDaoException(
	                "Could not save student data.", e);
	    }	    
	    
	    List<Player> playerList = this.getAllPlayers();
	    for (Player currentPlayer : playerList) {
	        // write the Student object to the file
	        out.println(currentPlayer.getPlayerId() + DELIMITER
	                + currentPlayer.getFirstName() + DELIMITER 
	                + currentPlayer.getLastName() + DELIMITER
	                + currentPlayer.getTeam());
	        // force PrintWriter to write line to the file
	        out.flush();
	    }
	    // Clean up
	    out.close();
            
    } // -- writeRoster() --    
    
    */
    
    
}
