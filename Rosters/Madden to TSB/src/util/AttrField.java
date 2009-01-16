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

package util;

import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AttrField{
    
    private static final long serialVersionUID = 1L;
    
    public static final int ATTR_FIELD_WIDTH = 30;
    public static final int ATTR_FIELD_HEIGHT = 20;
    
    private JLabel _label;
    private JTextField _field;
    
    public AttrField(){
        _label = new JLabel("N/A");
        _label.setBounds(0,0,ATTR_FIELD_WIDTH,ATTR_FIELD_HEIGHT);
        _label.setHorizontalAlignment(JLabel.CENTER);
        _label.setVerticalTextPosition(JLabel.BOTTOM);
        _field = new JTextField();
        _field.setBounds(0,ATTR_FIELD_HEIGHT,ATTR_FIELD_WIDTH,ATTR_FIELD_HEIGHT);
        _field.setHorizontalAlignment(JTextField.CENTER);
    }
    
    public AttrField(String label){
        _label = new JLabel(label);
        _label.setBounds(0,0,ATTR_FIELD_WIDTH,ATTR_FIELD_HEIGHT);
        _label.setHorizontalAlignment(JLabel.CENTER);
        _label.setVerticalTextPosition(JLabel.BOTTOM);
        _field = new JTextField();
        _field.setBounds(0,ATTR_FIELD_HEIGHT,ATTR_FIELD_WIDTH,ATTR_FIELD_HEIGHT);
        _field.setHorizontalAlignment(JTextField.CENTER);
    }
    
    public AttrField(String label, int x, int y){
        _label = new JLabel(label);
        _label.setBounds(x,y,ATTR_FIELD_WIDTH,ATTR_FIELD_HEIGHT);
        _label.setHorizontalAlignment(JLabel.CENTER);
        _label.setVerticalTextPosition(JLabel.BOTTOM);
        _field = new JTextField();
        _field.setBounds(x,y+ATTR_FIELD_HEIGHT,ATTR_FIELD_WIDTH,ATTR_FIELD_HEIGHT);
        _field.setHorizontalAlignment(JTextField.CENTER);
    }
    
    public AttrField(String label, String tooltip, int x, int y){
        _label = new JLabel(label);
        _label.setBounds(x,y,ATTR_FIELD_WIDTH,ATTR_FIELD_HEIGHT);
        _label.setHorizontalAlignment(JLabel.CENTER);
        _label.setVerticalTextPosition(JLabel.BOTTOM);
        _label.setToolTipText(tooltip);
        _field = new JTextField();
        _field.setBounds(x,y+ATTR_FIELD_HEIGHT,ATTR_FIELD_WIDTH,ATTR_FIELD_HEIGHT);
        _field.setHorizontalAlignment(JTextField.CENTER);
        _field.setToolTipText(tooltip);
    }
    
    public void addTo(Container c){
        c.add(_label);
        c.add(_field);
    }
    
    public void setLabel(String label){
        _label.setText(label);
    }
    
    public void setText(String text){
        _field.setText(text);
    }
    
    public void setTooltip(String toolTip){
        _label.setToolTipText(toolTip);
        _field.setToolTipText(toolTip);
    }

}
