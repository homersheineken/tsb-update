import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import types.Player;
import types.Team;
import util.AttrField;
import util.WebRosters;
import info.Attributes;

public class RostersGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4934171452118883179L;
	
	private Team[] _allTeams = new Team[32];
	
	private JComboBox _teamsBox;
	private JComboBox _playersBox;
	private AttrField[] _attrFields;
	
	public final int MADDEN_ATTRIBUTE_COUNT = 48;
	public final int MADDEN_ATTRIBUTE_ROWS = 4;
	public final int MADDEN_ATTRIBUTE_COLS = 12;
	public final int TSB_ATTRIBUTE_COUNT = 14;

	public RostersGUI(){
		super();
		this.setBounds(10,10,500,350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		WebRosters wr = new WebRosters();
		
		_allTeams = wr.getTeams();
		String[] teamNames = new String[_allTeams.length];
		for(int t=0;t<_allTeams.length;t++){
			teamNames[t] = _allTeams[t].getFullTeamName();
		}
		
		createAttributeFields();
		
		_teamsBox = new JComboBox(teamNames);
		_teamsBox.setBounds(10,5,190,25);
		_teamsBox.addActionListener(this);
		this.add(_teamsBox);
		
		_playersBox = new JComboBox();
		_playersBox.setBounds(210,5,190,25);
		_playersBox.addActionListener(this);
		this.add(_playersBox);
		
		populatePlayers();
		getSelectedPlayer();
		
		this.setVisible(true);
		repaint();
	}
	
	public void createAttributeFields(){
	    _attrFields = new AttrField[MADDEN_ATTRIBUTE_COUNT];
	    
	    for(int x=0;x<MADDEN_ATTRIBUTE_COLS;x++){
            for(int y=0;y<MADDEN_ATTRIBUTE_ROWS;y++){
                int loc = x+(y*MADDEN_ATTRIBUTE_COLS);
                _attrFields[loc] = new AttrField(
                        Attributes.MaddenAttributes[loc][0], 
                        Attributes.MaddenAttributes[loc][1], 
                        (x*(AttrField.ATTR_FIELD_WIDTH+5))+35, 
                        (y*(AttrField.ATTR_FIELD_HEIGHT*2))+35
                    );
                _attrFields[loc].addTo(this);
            }
        }
	}
	
	public void populatePlayers(){
	    Team team = _allTeams[_teamsBox.getSelectedIndex()];
        String[] playerNames = new String[team.getPlayers().length];
        Player[] allPlayers = team.getPlayers();
        for(int t=0;t<playerNames.length;t++){
            Player temp = allPlayers[t];
            playerNames[t] = temp.getLastName() + " " + temp.getFirstName();
        }
        Arrays.sort(playerNames);
        Player[] tempPlayers = new Player[allPlayers.length];
        for(int p=0;p<playerNames.length;p++){
            for(int x=0;x<allPlayers.length;x++){
                if((allPlayers[x].getLastName()+" "+allPlayers[x].getFirstName()).equals(playerNames[p])){
                    playerNames[p] = allPlayers[x].getFullName();
                    tempPlayers[p] = allPlayers[x];
                    break;
                }
            }
        }
        _playersBox.removeAllItems();
        for(int p=0;p<playerNames.length;p++){
            _playersBox.addItem(playerNames[p]);
        }
        
        for(int x=0;x<tempPlayers.length;x++){
            allPlayers[x] = tempPlayers[x];
        }
        
        repaint();
	}
	
	public void getSelectedPlayer(){
	    Team team = _allTeams[_teamsBox.getSelectedIndex()];
	    Player selectedPlayer = team.getPlayers()[_playersBox.getSelectedIndex()];
	    
	    String[] attributes = selectedPlayer.getAttributes();
	    
	    for(int x=0;x<MADDEN_ATTRIBUTE_COLS;x++){
	        for(int y=0;y<MADDEN_ATTRIBUTE_ROWS;y++){
	            int loc = x+(y*MADDEN_ATTRIBUTE_COLS);
	            _attrFields[loc].setText(attributes[loc]);
	        }
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    try{
    		if(e.getSource() == _teamsBox){
    			populatePlayers();
    			_playersBox.setSelectedIndex(0);
                getSelectedPlayer();
    		}if(e.getSource() == _playersBox){
    		    getSelectedPlayer();
    		}
	    }catch(Exception ex){
	        System.out.println("RostersGUI.actionPerformed(...) Exception - "+ex.getMessage());
	    }
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new RostersGUI();

	}

}
