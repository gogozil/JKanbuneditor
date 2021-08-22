package application;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;

public class CharacterRepresentationList {
	int KanjiNum;
    private CharacterRepresentation[] crs;

    public CharacterRepresentationList(int kanjinum,GridPane gp) {
    	KanjiNum=kanjinum;
    	crs=new CharacterRepresentation[KanjiNum];
    	
    	for(int i=KanjiNum-1;i>=0;i--) {
    		crs[i]=new CharacterRepresentation(i);
    		crs[i].add_to_GridPane(gp);
        }

    }
    
    public void update() {
    	for(int i=0;i<KanjiNum;i++) {
            crs[i].clear();
            if(i<AnnotatedText.ac.size()) {
            	crs[i].setAnnotatedCharacter(AnnotatedText.ac.get(i));
                KanaCandidates k2=AnnotatedText.generateCandidates(crs[i].kanji.getText());
            	crs[i].add_list(k2);
            	crs[i].update();
            }else {
               crs[i].judou.setText("");
            }
        	}

    }
}
