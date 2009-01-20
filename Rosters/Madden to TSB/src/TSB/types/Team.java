package TSB.types;

public class Team {
		
	private String _location;
	private String _name;
	private String _short;
	private Player[] _players;
	
	public Team(){
		_location = "Generic";
		_name = "Team";
	}
	
	public Team(String locationIn, String nameIn, String shortIn){
		_location = locationIn;
		_name = nameIn;
		_short = shortIn;
	}
	
	public String getTeamName(){
		return _name;
	}
	
	public String getLocation(){
		return _location;
	}
	
	public String getShort(){
	    return _short;
	}
	
	public void setTeamName(String team_name){
		_name = team_name;
	}
	
	public void setLocation(String loc){
		_location = loc;
	}
	
	public void setShort(String ts){
	    _short = ts;
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
