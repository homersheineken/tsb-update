package TSB.types;

public class Player {
	
	Team _team;
	String _firstName;
	String _lastName;
	String _position;
	String _number;
	String[] _attributes;
	
	public Player(){
		_team = null;
		_firstName = "Generic";
		_lastName = "Player";
		_position = "N/A";
		_number = "0";
		_attributes = null;
	}
	
	public Player(Team team, String first, String last, String position, String number, String[] attributes){
		_team = team;
		_firstName = first;
		_lastName = last;
		_position = position;
		_number = number;
		_attributes = attributes;
	}
	
	public String getFullName(){
		return _firstName + " " + _lastName;
	}
	
	public String getFirstName(){
	    return _firstName;
	}
	
	public String getLastName(){
	    return _lastName;
	}
	
	public String[] getAttributes(){
	    return _attributes;
	}

}
