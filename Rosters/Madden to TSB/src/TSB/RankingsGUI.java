package TSB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import TSB.info.Attributes;
import TSB.info.Positions;
import TSB.types.Player;
import TSB.types.Team;
import TSB.util.WebRosters;


public class RankingsGUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JTable _table;
	private JScrollPane _tablePane;
	private Team[] _allTeams;
	private JComboBox _positionBox;
	private Vector<Player> _allPlayers;
	
	private String[] _columns;
	private String[][] _data;

	public RankingsGUI(){
		super("Madden 09 Rankings");
		this.setBounds(10,10,1000,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		WebRosters wr = new WebRosters();		
		_allTeams = wr.getTeams();
		
		_positionBox = new JComboBox(Positions.allPositions);
		_positionBox.addActionListener(this);
		add(_positionBox);		
		
		_columns = new String[Attributes.MADDEN_ATTRIBUTE_COUNT+4];
		_columns[0] = "Player";
		_columns[1] = "Team";
		_columns[2] = "Pos.";
		_columns[3] = "#";
		for(int c=0;c<Attributes.MaddenAttributes.length;c++){
			_columns[c+4] = Attributes.MaddenAttributes[c][1];
		}
		
		loadAllPlayers();		

		_data = loadPosition((String)_positionBox.getSelectedItem());
		
		_table = new JTable(_data,_columns);
		_table.setAutoCreateRowSorter(true);
		_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		_tablePane = new JScrollPane(_table);
		_tablePane.setBounds(10,50,980,580);
		_tablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		_table.setFillsViewportHeight(true);
		this.add(_tablePane);
				
		this.setVisible(true);
		this.repaint();
	}
	
	public void loadAllPlayers(){
		_allPlayers = new Vector<Player>();
		for(int t=0;t<_allTeams.length;t++){
			Player[] players = _allTeams[t].getPlayers();
			for(int p=0;p<players.length;p++){
				_allPlayers.addElement(players[p]);
			}
		}
	}
	
	public String[][] loadPosition(String pos){
		Vector<String[]> players = new Vector<String[]>();
		for(int p=0;p<_allPlayers.size();p++){
			Player temp = _allPlayers.elementAt(p);
			if(temp.getPosition().equals(pos)){
				String[] tempData = new String[Attributes.MADDEN_ATTRIBUTE_COUNT+4];
				tempData[0] = temp.getFullName();
				tempData[1] = temp.getTeamName();
				tempData[2] = temp.getPosition();
				tempData[3] = temp.getNumber();
				String[] attrs = temp.getAttributes();
				for(int x=0;x<Attributes.MADDEN_ATTRIBUTE_COUNT;x++){
					tempData[x+4] = attrs[x];
				}
				players.add(tempData);
			}
		}
		
		String[][] returnData = new String[players.size()][Attributes.MADDEN_ATTRIBUTE_COUNT+4];
		for(int p=0;p<returnData.length;p++){
			String[] temp = players.elementAt(p);
			for(int a=0;a<temp.length;a++){
				returnData[p][a] = temp[a];
			}
		}
		return returnData;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == _positionBox){
			Object[][] data = loadPosition((String)_positionBox.getSelectedItem());
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RankingsGUI();
	}

}
