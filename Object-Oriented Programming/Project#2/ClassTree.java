package ClassViewer;

import javax.swing.tree.*;
import javax.swing.event.*;

public class ClassTree implements TreeModel {
	protected Cinfo com;
	
	public ClassTree(Cinfo c) {
		com = c;
	}
	
	public Object getChild(Object parent, int index) {
		if(parent instanceof Cinfo) {
			Cinfo c = (Cinfo) parent;//
			if(index < c.getMethod().size())
				return c.getMethod().get(index);
			if(index >= c.getMethod().size())
				return c.getVariable().get(index-c.getMethod().size());
		}else if(parent instanceof Minfo) {
			Metinfo m = (Metinfo)parent;
			return m.implyMember.get(index);
		}
		return null;
	}
	
	public int getChildCount (Object parent) {
		if(parent instanceof Cinfo) {
			Cinfo c = (Cinfo)parent;
			return c.member.size();
		}else if(parent instanceof Minfo) {
			Metinfo m = (Metinfo)parent;
			return m.implyMember.size();
		}
		return 0;
	}
	
	public int getIndexOfChild(Object parent, Object child) {
		if(parent instanceof Cinfo) {
			Cinfo c = (Cinfo)parent;
			if(child instanceof Metinfo) {
				return c.getIndexOf((Metinfo)child);
			}else if(child instanceof Vinfo) {
				return c.getIndexOf((Vinfo)child);
			}
		}else if(parent instanceof Metinfo) {
			Metinfo m = (Metinfo)parent;
			return m.implyMember.indexOf((Vinfo)child);
		}
		return -1;
	}
	
	public Object getRoot() {
		return com;
	}
	
	public boolean isLeaf (Object node) {
		if(node instanceof Cinfo) {
			return false;
		}else if(node instanceof Metinfo) {
			return false;
		}
		else return true;
	}
	
	public void addTreeModelListener (TreeModelListener l) {}
	public void removeTreeModelListener (TreeModelListener l) {}
	public void valueForPathChanged (TreePath path, Object newValue) {}

}
