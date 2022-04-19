package application;


import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

public class LabelSelectable {
	Label lb,saidoku;
	MenuButton mb;
	String currentRoman;
	String currentString;
	boolean new_choice;
	int type;
	public static final int YOMIGANA=1;
	public static final int OKURIGANA=0;
	public static final int KAERITEN=2;
	boolean from_changed=true;
	public AnnotatedCharacter ac;

	
	public LabelSelectable(int type) {
		lb=new Label();
		mb=new MenuButton();
		saidoku=new Label();
		new_choice=true;
		this.type=type;
    	lb.setWrapText(true);
    	lb.setFont(new Font("monospaced",11));
    	lb.setTextOverrun(OverrunStyle.CLIP);
    	lb.setText("");
    	saidoku.setWrapText(true);
    	saidoku.setFont(new Font("monospaced",11));
    	saidoku.setTextOverrun(OverrunStyle.CLIP);
    	saidoku.setText("");

    	lb.setStyle("-fx-margin:-2px;-fx-border:-2px;-fx-padding:-4px;");
    	mb.setStyle("-fx-margin:-2px;-fx-border:-2px;-fx-padding:-4px;");
    	if(type!=KAERITEN) {
		mb.setOnKeyTyped(event->{key_input(event);});
		mb.setOnKeyReleased(event->{key_delete(event);});
    	}
        currentRoman="";
	}
	
    public void key_input(KeyEvent ev) {
        String s=ev.getCharacter();
    	if(s.equals("\n")) {
    		return;
    	}
    	currentRoman+=s;
    	System.out.print(currentRoman);

    	currentString=AnnotatedText.convert_text_type(currentRoman,type+2);
    	System.out.println(currentString);
    	if(new_choice) {
    		if(!currentString.equals("")) {
    	    add_children(new String[] {currentString});
    		new_choice=false;
    		}
    	}else
    	{
    		mb.getItems().remove(mb.getItems().size()-1);
    	    add_children(new String[] {currentString});
    	}
    	update();
    }  
    
    public void key_delete(KeyEvent ev) {
    	String[] stl;
    	if(ev.getCode()==KeyCode.BACK_SPACE||ev.getCode()==KeyCode.DELETE) {
    		if(currentRoman.length()>=1) {
    			currentRoman=currentRoman.substring(0, currentRoman.length()-1);
    			System.out.println("BACK SPACE");
    		}else {currentRoman="";}
    	currentString=AnnotatedText.convert_text_type(currentRoman,type+2);
    	System.out.println(currentString);
    	update();
    }

    }
    
    public void change(String s) {
		int hyphen=s.indexOf(",");
		if(hyphen>=0) {
			lb.setText(s.substring(0,hyphen));
			saidoku.setText(s.substring(hyphen+1));
			}else{
			lb.setText(s);
			saidoku.setText("");
			}
    	currentString="";
    	currentRoman="";
    }

    
    public void addAll(String[] slist) {
    	for(int i=0;i<slist.length;i++) {
    		add(slist[i]);
    	}
    }
    

    
    public void selected(String s) {

        if(ac!=null) {
        	if(type==OKURIGANA) {
        		int hyphen=s.indexOf(",");
        		if(hyphen>=0) {
            		ac.selected_okurigana=s.substring(0,hyphen);
            		ac.saidoku=s.substring(hyphen+1);
            		System.out.println(ac.selected_okurigana+" "+ac.saidoku);
        		}else{
                	ac.selected_okurigana=s;
        		}
        	}else if(type==YOMIGANA) {
                	ac.selected_yomigana=s;
        	}else if(type==KAERITEN) {
        		ac.selected_kaeriten=s;
        	}
        }
        change(s);
    }
    
    public void add(String s) {
    	for(int i=0;i< mb.getItems().size();i++) {
    		if(mb.getItems().get(i).getText().equals(s)){return;}
    	}
    	MenuItem newmenu=new MenuItem(s);
    	newmenu.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        selected(newmenu.getText());
    	    }
    	});
    	mb.getItems().add(newmenu);
    }
    
    public void add_children(String[] children) {
    	if(children.length==1) {
        	MenuItem newmenu=new MenuItem(children[0]);
        	newmenu.setOnAction(new EventHandler<ActionEvent>() {
        	    @Override
        	    public void handle(ActionEvent event) {
        	        selected(newmenu.getText());
        	    }
        	});
        	mb.getItems().add(newmenu);
    	}else{
    	Menu newmenu=new Menu(children[0]);
    	for(int i=1;i<children.length;i++) {
        	MenuItem child=new MenuItem(children[i]);
        	child.setOnAction(new EventHandler<ActionEvent>() {
        	    @Override
        	    public void handle(ActionEvent event) {
        	        selected(child.getText());
        	    }
        	});
            newmenu.getItems().add(child);
    	}
    	mb.getItems().add(newmenu);
    	}

    }
    
    public void setAnnotatedCharacter(AnnotatedCharacter ac_args) {
    	this.ac=ac_args;
    }
    
    
    private void update() {
    	if(new_choice) {
    		if(!currentString.equals("")) {
    		add(currentString);
    		new_choice=false;
    		}
    	}else
    	{
    		mb.getItems().remove(mb.getItems().size()-1);
    		if(!currentString.equals("")) {add(currentString);}else{new_choice=true;}
    	}
    }
    
    public void remove() {
    	mb.getItems().clear();
    	add("");
    	lb.setText("");
    }
    
    public String get_Text() {
    	return lb.getText();
    }
    
    public void setText(String s) {
    	lb.setText(s);
    }
    
    public String get_saidokuText() {
    	return saidoku.getText();
    }


}
