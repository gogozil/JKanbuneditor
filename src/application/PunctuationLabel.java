package application;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class PunctuationLabel extends Label {
	public AnnotatedCharacter ac;

	public PunctuationLabel() {
    	this.setFont(new Font("monospaced",10));
    	this.setText("");
    	this.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	if(getText().equals(AnnotatedText.judouconst[1])) {setText(AnnotatedText.judouconst[2]);}
            	else if(getText().equals(AnnotatedText.judouconst[2])) {setText(AnnotatedText.judouconst[0]);}
            	else if(getText().equals(AnnotatedText.judouconst[0])) {setText(AnnotatedText.judouconst[1]);}
            	ac.judou=getText();
            }
    	});
    	
    	// TODO Auto-generated constructor stub
	}
    public void setAnnotatedCharacter(AnnotatedCharacter ac_args) {
    	this.ac=ac_args;
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
