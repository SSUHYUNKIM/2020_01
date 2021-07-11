package ClassViewer;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class Frame extends JFrame{
	public Frame(Cinfo c) {
		setTitle("Class Viewer");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panel panel = new Panel(c);
		this.add(panel);
		this.setJMenuBar(new Menu(c));
		this.setVisible(true);
	}
}

class Menu extends JMenuBar{
	Menu(final Cinfo c){
		super();
		
		JMenu m = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You hit Open");
			}
		});
		m.add(open);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		m.add(exit);

		add(m);
	}
}

class MyPanel extends JPanel{
	protected JScrollPane tree,table,source;
	protected JTextArea tableArea = new JTextArea(10,50);
	protected JTextArea sourceArea= new JTextArea(10,50);
	protected GridBagConstraints g;
	protected Cinfo c;
	protected Metinfo m;

	public MyPanel(Cinfo c){
		this.c = c;
		this.setLayout(new GridBagLayout());
		g = new GridBagConstraints();
		g.weighty = 1;

		setTreeArea(this.c);
		this.add(tree,g);
		setTableArea();
		this.add(table,g);
		setSourceArea();
		this.add(source,g);
	}

	public void setTreeArea(Cinfo c){
		ClassTree model = new ClassTree(c);
		String s;
		JTree jt = new JTree(model);

		jt.addTreeSelectionListener(CreateSelectionAction());
		jt.setBorder(BorderFactory.createEmptyBorder());
		tree = new ScrollPanel(jt);
		tree.setPreferredSize(new Dimension(50,100));
		tree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		tree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		g.fill = GridBagConstraints.BOTH;
		g.gridx = 0;
		g.gridy = 0;
		g.weightx = 0.2;
		g.insets = new Insets(10,10,10,10);
	}

	public TreeSelectionListener CreateSelectionAction(){
		
		return new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e){
				Object o = e.getPath().getLastPathComponent();

				if(o instanceof Cinfo){
					class_((Cinfo)o);
				}else if(o instanceof Metinfo){
					method_((Metinfo)o);
				}else if(o instanceof Vinfo){
					var_((Vinfo)o);
				}
			}
		};
	}

	protected void var_(Vinfo o) {
		tableArea.setText("Use\n-------------------------------\n");
		ClassTable tm = new ClassTable();
		JTable t = new JTable(tm);
		source.setPreferredSize(new Dimension(100,100));
		source.revalidate();
		source.setViewportView(t);
	}

	protected void method_(final Metinfo o) {
		classify cl = new classify();
		String a = ""; 
		for(int i=0;i<cl.MethodNum();i++)
			if(o.get_methodName().startsWith(cl.getmemName().get(i)))
				a += cl.getmemImply().get(i).replace(",","\n")
				.replace("[","").replace("]", "").replace(" ","");
		tableArea.setText("Use\n-------------------------------\n"+a);
		sourceArea.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent k) {
				if(k.isActionKey()){
					o.getTextAreaFromSource = sourceArea.getText();
					sourceArea.setText( o.getTextAreaFromSource);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});

		if((o.getTextAreaFromSource).equals(o.get_methodInnerInfoString())){
			sourceArea.setText( o.get_methodInnerInfoString() );
		}else{
			sourceArea.setText( o.get_methodInnerInfoString());
		}
		source.setPreferredSize(new Dimension(100,100));
		source.revalidate();
		source.setViewportView(sourceArea);
		this.repaint();
	}

	protected void class_(Cinfo o) {
		ClassTable tm = new ClassTable();
		JTable t = new JTable(tm);
		source.setPreferredSize(new Dimension(100,100));
		source.revalidate();
		source.setViewportView(t);
	}

	public void setTableArea(){
		tableArea.setBorder(BorderFactory.createCompoundBorder(
				tableArea.getBorder(),BorderFactory.createEmptyBorder()));
		tableArea.setText("Welcome to the C++ ClassViewer.");
		tableArea.setEditable(false);
		table = new ScrollPanel(tableArea);
		table.setPreferredSize(new Dimension(10,100));
		table.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		table.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		g.fill = GridBagConstraints.BOTH;
		g.gridx = 0;
		g.gridy = 1;
		g.weightx = 0.2;
		g.insets = new Insets(10, 10, 10, 10);
	}

	public void setSourceArea(){
		sourceArea = new JTextArea(10,30);
		source = new ScrollPanel(sourceArea);
		sourceArea.setBorder(BorderFactory.createCompoundBorder(
				sourceArea.getBorder(),BorderFactory.createEmptyBorder()));
		source.revalidate();
		source.setPreferredSize(new Dimension(100,100));
		g.fill = GridBagConstraints.BOTH;
		g.gridx = 1;
		g.gridy = 0;
		g.weightx = 0.8;
		g.gridheight = 2;
		g.insets = new Insets(10, 10, 10, 10);
	}

class ScrollPanel extends JScrollPane {
	public ScrollPanel(Component View) {
		super(View);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
}

	public static void main(String[] args) {
		Frame f = new Frame(null);
	}

}