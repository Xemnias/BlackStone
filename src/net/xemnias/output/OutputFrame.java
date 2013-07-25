package net.xemnias.output;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class OutputFrame
{
	JFrame frame = new JFrame("Output Console");
	 JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private OutputPanel mainPanel = new  OutputPanel();
	
	public OutputFrame(int x, int y, int width, int height)
	{
		frame.setSize(621, 838);
		//setSize(width, height);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnClearConsole = new JButton("Clear Console");
		btnClearConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((OutputPanel.Panel)tabbedPane.getSelectedComponent()).p.pane.setText("Console éffacée.\n\n");
			}
		});
		panel.add(btnClearConsole);
		
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.add("Game Console", mainPanel.p);
	}
	
	public void p(Object o)
	{
		mainPanel.p(o);

	}

	public void beginPart(String string) 
	{
		mainPanel.beginPart(string);
	}
	
	public void addTabulationPart(String str)
	{
		mainPanel.addTabulationPart(str);
	}
	

	public void removeTabulationPart()
	{
		mainPanel.removeTabulationPart();
	}
	
	public void stopPart() 
	{

		mainPanel.stopPart();
	}

	public void start() 
	{
		frame.setVisible(true);
	}

	public void stop() 
	{
		frame.setVisible(false);
	}
	
	public void add(OutputPanel p, String str)
	{
		tabbedPane.add(p.p, str);
	}

	public void add(OutputGraphics fpsout, String str) 
	{
		JScrollPane s = new JScrollPane(fpsout);
		fpsout.setParent(s);
		tabbedPane.add(s, str);
	}
}
