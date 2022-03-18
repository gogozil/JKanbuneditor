package application;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class CharacterRepresentation {
    public Label kanji;
    public PunctuationLabel judou;
    private LabelSelectable kaeriten_choice;
    public LabelSelectable okuriganaLS, yomiganaLS;
    public int character_number;
    public AnnotatedCharacter ac;
    
    public CharacterRepresentation(int num) {
    	kanji=new Label();
		//kanji.setFont(new Font("YuMincho +36p Kana Medium",48));
		//Font.loadFont(CharacterRepresentation.class.getResource("ipaexm.ttf").toExternalForm(), 10);
		kanji.setFont(Font.loadFont(CharacterRepresentation.class.getResource("SourceHanSerifCN-Regular.ttf").toExternalForm(), 48));
		okuriganaLS=new LabelSelectable(LabelSelectable.OKURIGANA);
		yomiganaLS=new LabelSelectable(LabelSelectable.YOMIGANA);
		kanji.setText("");
        kaeriten_choice=new LabelSelectable(LabelSelectable.KAERITEN);
        String[] kaeriten={"","レ","一","二","三","四","一レ","上","中","下","甲","乙","丙"};
        kaeriten_choice.addAll(kaeriten);
    	judou=new PunctuationLabel();
    	character_number=num;
    }
    
    public void add_list(KanaCandidates kc) {
    	for(int i=0;i<kc.get_okurigana_size();i++) {
    	okuriganaLS.add_children(kc.get_okurigana_candidates(i));
    	}
    }
    
    public void add_to_GridPane(GridPane gp) {
    	int row=gp.getRowCount();
    	int col=gp.getColumnCount();
    	int i=character_number;
    	int x=col-4*(i/10)-2;
    	int y=i%10;
    	gp.add(kanji, x, 2*y+1);
    	gp.add(okuriganaLS.lb,x+1, y*2+1,1,3);
    	gp.add(okuriganaLS.mb, x+1, y*2+2);
    	GridPane.setHalignment(kaeriten_choice.lb, HPos.RIGHT);
    	gp.add(kaeriten_choice.lb, x-1, y*2+1,1,3);
    	gp.add(kaeriten_choice.mb, x-1, y*2+2);
    	gp.add(yomiganaLS.lb, x+1, y*2+1);
    	gp.add(yomiganaLS.mb, x+1, y*2+1);
    	gp.add(okuriganaLS.saidoku, x-1, y*2+1);
    	GridPane.setHalignment(judou, HPos.RIGHT);
    	judou.setTextOverrun(OverrunStyle.CLIP);
    	gp.add(judou,x,2*y+2);
    }
    
    public void clear() {
    	okuriganaLS.remove();
    	yomiganaLS.remove();
    	kaeriten_choice.setText("");
    	kanji.setText("");
    	}
    
    public void setKaeriten(String s) {
    	kaeriten_choice.setText(s);
    }
    
    public void setOkurigana(String s) {
    	okuriganaLS.change(s);
    	okuriganaLS.add(s);
    }
    public void setAnnotatedCharacter(AnnotatedCharacter ac_arg) {
    	this.ac=ac_arg;
    	update();
    }
    public void update() {
    	okuriganaLS.setAnnotatedCharacter(ac);
    	yomiganaLS.setAnnotatedCharacter(ac);
    	kaeriten_choice.setAnnotatedCharacter(ac);
    	kanji.setText(ac.kanji);
    	judou.setText(ac.judou);
    	setKaeriten(ac.selected_kaeriten);
    	setOkurigana(ac.selected_okurigana);
    }
}
