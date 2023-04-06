package application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class KanjiLabel extends Label {
	public AnnotatedCharacter ac;
	public boolean okiji;
	
	public KanjiLabel() {
    	this.setFont(new Font("monospaced",10));
    	this.setText("");
    	okiji=false;
    	this.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	setOkiji(!okiji);
            }
    	});
	}
	
	public void setOkiji(boolean okj) {
		okiji=okj;
		if(okiji) {
			this.setTextFill(Color.GRAY);
		}else {
			this.setTextFill(Color.BLACK);
		}
		ac.okiji=okiji;
	}
	
    public void setAnnotatedCharacter(AnnotatedCharacter ac_args) {
    	this.ac=ac_args;
    }
	
	
}
