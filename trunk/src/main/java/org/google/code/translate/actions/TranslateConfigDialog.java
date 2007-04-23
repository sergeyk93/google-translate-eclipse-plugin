package org.google.code.translate.actions;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;


public class TranslateConfigDialog extends JDialog implements ActionListener {
    private JButton okButton = new JButton("Ok");;
    private JButton cancelButton = new JButton("Cancel");;
    private JButton applyButton = new JButton("Apply");;
    
//    private boolean answer = false;
    
	  public TranslateConfigDialog(Frame owner) { 
	    super(owner, true);
	    
	    setSize(250, 150);	    
	    
		TranslateConfigurationForm form = new TranslateConfigurationForm();
		
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);        
        applyButton.addActionListener(this);        
		
        Container contentPane = getContentPane();
        
        contentPane.add(form.getRootComponent(), "Center");
	    
	    JPanel panel = new JPanel();

	    panel.add(okButton);
	    panel.add(cancelButton);
	    panel.add(applyButton);
	    
        contentPane.add(panel, "South");	    
	  } 
	  protected JRootPane createRootPane() { 
	    JRootPane rootPane = new JRootPane();
	    KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
	    Action actionListener = new AbstractAction() { 
	      public void actionPerformed(ActionEvent actionEvent) { 
	        //setVisible(false);
            dispose();
	      } 
	    } ;
	    InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    inputMap.put(stroke, "ESCAPE");
	    rootPane.getActionMap().put("ESCAPE", actionListener);

	    return rootPane;
	  }
	  
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
        if(source == okButton) {
            //answer = true;
            //setVisible(false);
            dispose();
        }
        else if(source == cancelButton) {
            //answer = false;
            //setVisible(false);
            dispose();
        }
        else if(source == applyButton) {
            //answer = true;
        }        
      } 
	
} 