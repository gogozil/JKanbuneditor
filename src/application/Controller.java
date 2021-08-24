package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class Controller {

    @FXML
    private Tab annotated,originaltexttab,kakikudashi;
    public TextArea originalText,originalText_a,kakikudashiText;
    public GridPane gp,gp2;
    public FlowPane fp;
    private final int KanjiNUM=90;
    private CharacterRepresentation[] crs=new CharacterRepresentation[KanjiNUM];
    public CharacterRepresentationList clist;
    public boolean from_changed=true;
    private AnchorPane anchorpane_annotated;
    public MenuButton testmenu;
    
    @FXML
    public void initialize() {
        originalText.setText("子曰、主忠信，毋友不如己者，過則勿憚改。子曰、三軍可奪帥也，匹夫不可奪志也。 ");
    	/*Create Main Text*/
    	/* Substitute the menu language to Japanese*/
        annotated.setText("訓点");
        originaltexttab.setText("原文");
        kakikudashi.setText("書き下し");   
        RowConstraints row = new RowConstraints(50);
        ColumnConstraints col = new ColumnConstraints(50);
        //.getRowConstraints().add(row);
        //gp2.getColumnConstraints().add(col);
        //gp2.setGridLinesVisible(true);

        //anchorpane_annotated.getChildren().add(gp2);
        //AnchorPane.setRightAnchor(gp2,40.0);
        clist=new CharacterRepresentationList(KanjiNUM, gp);
        convert();
    }
    

    
    public void convert() {
    	AnnotatedText.load_Text(originalText.getText());
    	clist.update();
    }
    
    public void convert_to_text() {
    	originalText_a.setText(AnnotatedText.convert_Text());
    }
    
    public void kakikudashi_convert() {
    	kakikudashiText.setText(AnnotatedText.convert_original_to_kakikudashi());
    }
    
    public void load_from_annotated() {
    	AnnotatedText.load_Annotated(originalText_a.getText());
    	clist.update();
    }
    
    public void shift_left() {
    	clist.scroll(1,10);
    }
    
    public void shift_right() {
    	clist.scroll(-1,10);
    }
}