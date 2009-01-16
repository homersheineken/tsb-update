package types;

public class Team {
	
	private String _location;
	private String _name;
	private Player[] _players;
	
	public Team(){
		_location = "Generic";
		_name = "Team";
	}
	
	public Team(String locationIn, String nameIn){
		_location = locationIn;
		_name = nameIn;
	}
	
	public String getTeamName(){
		return _name;
	}
	
	public String getLocation(){
		return _location;
	}
	
	public void setTeamName(String team_name){
		_name = team_name;
	}
	
	public void setLocation(String loc){
		_location = loc;
	}
	
	public String getFullTeamName(){
		return _location + " " + _name;
	}
	
	public Player[] getPlayers(){
		return _players;
	}
	
	public void setPlayers(Player[] players){
		_players = players;
	}

}
