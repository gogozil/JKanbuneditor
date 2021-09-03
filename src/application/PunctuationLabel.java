package application;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class PunctuationLabel extends Label {

	public PunctuationLabel() {
    	this.setFont(new Font("monospaced",10));
    	this.setText("");
    	// TODO Auto-generated constructor stub
	}

	public PunctuationLabel(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PunctuationLabel(String arg0, Node arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
