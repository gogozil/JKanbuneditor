package application;

import java.util.ArrayList;

public class AnnotatedCharacter {
	String kanji;
	String selected_okurigana;
	String selected_yomigana;
	String selected_kaeriten;
	String saidoku;
	boolean okiji;
	ArrayList<String> okurigana_List;
	ArrayList<String> yomigana_List;
	String yomigana;
	String judou;
	int bracket;
	int endbracket;
	
	public AnnotatedCharacter(String kanji,String judou) {
		this.kanji=kanji;
		this.judou=judou;
		selected_kaeriten="";
		selected_yomigana="";
		selected_okurigana="";
		saidoku="";
		bracket=0;
		endbracket=0;
		okiji=false;
	}
	
	public String get_reading(boolean ifsaidoku) {
		String reading;
		if(okiji) {
			return selected_okurigana;
		}
		if(saidoku=="") {ifsaidoku=false;}
		if(ifsaidoku) {
			reading=saidoku;
		}else {
			reading=kanji+selected_okurigana;
		}
		return reading;
	}
	
	
}
