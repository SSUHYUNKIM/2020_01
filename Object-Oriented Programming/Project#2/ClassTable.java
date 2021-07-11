package ClassViewer;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class ClassTable extends AbstractTableModel{
	Object o;
	String [][] s;
	public void SourceTableModel(Object o) {
		this.o = o;
		if(o instanceof Cinfo){
			ArrayList<Dept> c = ((Cinfo)o).member;
			s = new String [c.size()][3];
			for(int i = 0; i<c.size(); i++) {
				for(int j =0; j < 3; j++) {
					switch(j) {
					case 0: s[i][j] = c.get(i).name;
					break;
					case 1: s[i][j] = c.get(i).type; 
					break;
					case 2: s[i][j] = c.get(i).access; 
					break;
					}
				}
			}
		}else if(o instanceof Vinfo) {
			s = new String[1][2];
			s[0][0] = ((Vinfo)o).getVarName();	
			s[0][1] = ((Vinfo)o).getVarImply().replace("[", "").replace("]", "");
	}		
}
	
	@Override
	public int getColumnCount() {
		if(o instanceof Cinfo) return 3;
		else if(o instanceof Vinfo) return 2;
		return 0;
	}
	
	@Override
	public int getRowCount() {
		if(o instanceof Cinfo){
			return ((Cinfo)o).getMethod().size();
		}else if(o instanceof Vinfo){
			return 1;
		}
		return 0;
	}
	
	@Override
	public String getColumnName(int col){
		if(o instanceof Cinfo){
			switch(col){
			case 0 : return "Name";
			case 1 : return "Type";
			case 2 : return "Access";
			}
		} else if(o instanceof Vinfo){
			switch(col){
			case 0 : return "Name";
			case 1 : return "Methods";
			}
		}
		return null;
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		return s[row][col];
	}
	
	@Override
	public Class<?> getColumnClass(int col){
		return getValueAt(0,col).getClass();
	}
}
	
	
	
	
