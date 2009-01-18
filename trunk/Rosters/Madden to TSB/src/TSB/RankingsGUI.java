import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;

import types.Team;
import util.WebRosters;


public class RankingsGUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JTable _table;
	private Team[] _allTeams;
	private JComboBox _positionBox;

	public RankingsGUI(){
		super("Madden 09 Rankings");
		this.setBounds(10,10,500,350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		WebRosters wr = new WebRosters();		
		_allTeams = wr.getTeams();
		
		
		
		_table = new JTable();
				
		this.setVisible(true);
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RankingsGUI();
	}

}
