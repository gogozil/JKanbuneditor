package application;

import java.util.ArrayList;
import java.util.HashSet;

public class KanaCandidates {
	ArrayList<String[]> okurigana_list;
	ArrayList<String[]> yomigana_list;
	
	public KanaCandidates() {
		okurigana_list=new ArrayList<String[]>();
		yomigana_list=new ArrayList<String[]> ();

	}
	
    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }

	public void add_okurigana_candidates(String okurigana,String type,String yomi) {
		okurigana_list.add(new String[]{okurigana,type,yomi});
	}
	
	public String get_okurigana(int i) {
		return okurigana_list.get(i)[0];
	}
	public int get_okurigana_size() {
		return okurigana_list.size();
	}
	
	public String[] get_okurigana_candidates(int i) {
		String[] s= {okurigana_list.get(i)[0]};
		String stem=okurigana_list.get(i)[0];
		String yomi=okurigana_list.get(i)[2];
		if(okurigana_list.get(i)[1].isEmpty()) {return s;}
		switch(okurigana_list.get(i)[1]) {
		case "vh4":
		stem=replaceLast(stem,"フ","");
		return new String[]{yomi+stem+"フ",stem+"ハ",stem+"ヒ",stem+"フ",stem+"フコト",stem+"ヘ"};
		case "vsh":
		stem=replaceLast(stem,"ス","");
		return new String[]{yomi+stem+"ス",stem+"セ",stem+"シ",stem+"ス",stem+"スル",stem+"スルコト",stem+"セヨ"};
		default:
		break;
		}
		return s;
	}
	
	
}
