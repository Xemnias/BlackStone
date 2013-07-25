package net.xemnias.output;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class OutputPanel
{
	JScrollPane scrollPane = new JScrollPane();
	Panel p = new Panel(this);
	public OutputPanel() {
		p.setLayout(new BorderLayout(0, 0));
		
		p.add(scrollPane);
		pane.setEditable(false);
		pane.setFont(new Font("Verdana", Font.PLAIN, 12));
		pane.setForeground(Color.WHITE);
		pane.setBackground(Color.GRAY);
		scrollPane.setViewportView(pane);
		
	}
	JEditorPane pane = new JEditorPane();
	private String prefix = "";

	public void p(Object o)
	{
		if(!pane.getText().isEmpty())
			pane.setText(pane.getText()+ "\n" +prefix+ o);
		else
			pane.setText(""+prefix +o);
		int x;
		pane.selectAll();
		x = pane.getSelectionEnd();
		pane.select(x,x);
	}

	public void beginPart(String string) 
	{
		prefix = "---> ";
		p(string);
		prefix = "   > ";
	}
	
	public void addTabulationPart(String str)
	{
		prefix = "   ---> ";
		p(str);
		prefix = "         > ";
	}
	

	public void removeTabulationPart()
	{
		prefix = "   > ";
	}
	
	public void stopPart() 
	{
		prefix = "";
	}
	
	public class Panel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		public OutputPanel p;
		public Panel(OutputPanel outputPanel) {
			p = outputPanel;
		}
		
	}
}
