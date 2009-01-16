package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Vector;

import types.Player;
import types.Team;
import info.TeamList;

public class WebRosters {
	
	private Team[] _allTeams = new Team[32];
	
	private final String _ignHome = "http://sports.ign.com/madden09/";

	public WebRosters(){
		createTeams();
		
		getWebRosters();
		
		//System.out.println();
		//System.out.println("Attr = " + _allTeams[0].getPlayers()[0].getAttributes()[0]);
		
	}
	
	public void getWebRosters(){
		try{
			for(int t=0;t<_allTeams.length;t++){
			    Team team = _allTeams[t];
				URL url = new URL(_ignHome+team.getTeamName().toLowerCase()+".html");
		        URLConnection urlConn = url.openConnection();
		        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		        String inputLine;
		        boolean readIn = false;
		        Vector<Player> players = new Vector<Player>();
		        while((inputLine = br.readLine()) != null){
		        	if(inputLine.equals("</table>")){
		        		readIn = false;
		        	}
		            if (readIn){
		            	Player tempPlayer;
		            	inputLine = inputLine.replaceAll("<tr><td>", "");
		            	inputLine = inputLine.replaceAll("</td><td>", ",");
		            	inputLine = inputLine.replaceAll("</td></tr>", ",");	            	
		            	Scanner input = new Scanner(inputLine);
		            	input.useDelimiter(",");
		            	String firstName = input.next();
		            	String lastName = input.next();
		            	String position = input.next();
		            	String number = input.next();
		            	String[] attributes = new String[48];
		            	for(int c=0;c<attributes.length;c++){
		            		attributes[c] = input.next();
		            	}
		            	players.add(new Player(team,firstName,lastName,position,number,attributes));
		            }
		            if(inputLine.equals("<table border=\"1\">")){
		            	readIn = true;
		            	inputLine = br.readLine();
		            }
		        }
		        
		        team.setPlayers(players.toArray(new Player[0]));
		        _allTeams[t] = team;
		        
		        br.close();
			}
		}catch(Exception e){
			System.out.println("util.WebRosters.getWebRosters() Exception - "+e.getMessage());
		}
	}
	
	public void createTeams(){
		String[][] allTeams = TeamList.getAllTeams();
		
		for(int x=0;x<allTeams.length;x++){
			_allTeams[x] = new Team(allTeams[x][0],allTeams[x][1]);
		}
	}
	
	public Team[] getTeams(){
		return _allTeams;
	}
}
