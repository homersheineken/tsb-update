/*
 *  $URL$
 *  $Revision$
 *  $Author$
 *  $Date$
 *  
 *  $Copyright-Start$
 *
 *  Copyright (c) 2009
 *  RedPrairie Corporation
 *  All Rights Reserved
 *
 *  This software is furnished under a corporate license for use on a
 *  single computer system and can be copied (with inclusion of the
 *  above copyright) only for use on such a system.
 *
 *  The information in this document is subject to change without notice
 *  and should not be construed as a commitment by RedPrairie Corporation.
 *
 *  RedPrairie Corporation assumes no responsibility for the use of the
 *  software described in this document on equipment which has not been
 *  supplied or approved by RedPrairie Corporation.
 *
 *  $Copyright-End$
 */

package TSB;

import java.io.File;
import java.util.Vector;

import jxl.Workbook;
import jxl.write.Number;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import TSB.info.Attributes;
import TSB.info.Positions;
import TSB.types.Player;
import TSB.types.Team;
import TSB.util.WebRosters;

public class AttributesToXLS {
    
    private Team[] _allTeams;
    private Vector<Player> _allPlayers;
    
    public AttributesToXLS() {
        
        WebRosters wr = new WebRosters();       
        _allTeams = wr.getTeams();
        
        loadAllPlayers();
        
        try{
            WritableWorkbook workbook = Workbook.createWorkbook(new File("docs/Madden Attribute Rankings.xls"));
            WritableCellFormat headerFormat =
                new WritableCellFormat(new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.BOLD));
            
            for(int x=0;x<Positions.allPositions.length;x++){
                WritableSheet tempSheet = workbook.createSheet(Positions.allPositions[x], x);
                int playerCount = 0;
                tempSheet.addCell(new Label(0,0,"Player",headerFormat));
                tempSheet.addCell(new Label(1,0,"Pos.",headerFormat));
                tempSheet.addCell(new Label(2,0,"Team",headerFormat));
                tempSheet.addCell(new Label(3,0,"#",headerFormat));
                for(int h=0;h<Attributes.MaddenAttributes.length;h++){
                    tempSheet.addCell(new Label(h+4,0,Attributes.MaddenAttributes[h][1],headerFormat));
                }
                
                for(int p=0;p<_allPlayers.size();p++){
                    Player temp = _allPlayers.elementAt(p);
                    if(temp.getPosition().equals(Positions.allPositions[x])){
                        playerCount++;
                        tempSheet.addCell(new Label(0,playerCount,temp.getFullName()));
                        tempSheet.addCell(new Label(1,playerCount,temp.getPosition()));
                        tempSheet.addCell(new Label(2,playerCount,temp.getTeamShort()));
                        tempSheet.addCell(new Number(3,playerCount,Integer.parseInt(temp.getNumber())));
                        for(int a=0;a<temp.getAttributes().length;a++){
                            tempSheet.addCell(new Number(a+4,playerCount,Integer.parseInt(temp.getAttributes()[a])));
                        }
                    }
                }                
            }
            workbook.write();
            workbook.close();
            System.out.println("Completed.  Sheets created: "+workbook.getSheets().length);
        }catch(Exception e){
            System.out.println("AttributesToXLS Exception - "+e.getMessage());
        }
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

    public static void main(String[] args) {
        new AttributesToXLS();
    }

}
