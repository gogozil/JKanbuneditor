package application;

import java.util.ArrayList;
import java.util.HashSet;

public class KanaCandidates {
	ArrayList<String> okurigana_list;
	ArrayList<String> yomigana_list;
	ArrayList<String> type_list;

	
	public KanaCandidates() {
		okurigana_list=new ArrayList<String>();
		yomigana_list=new ArrayList<String> ();
		type_list=new ArrayList<String> ();
	}
	
    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }

	public void add_okurigana_candidates(String okurigana,String type,String yomi) {
		okurigana_list.add(okurigana);
		yomigana_list.add(yomi);
		type_list.add(type);
	}
	
	public String get_okurigana(int i) {
		return okurigana_list.get(i);
	}
	public int get_okurigana_size() {
		return okurigana_list.size();
	}
	
	public String[] get_okurigana_candidates(int i) {
		String stem=okurigana_list.get(i);
		String[] s=new String[] {stem};
		if(type_list.get(i).isEmpty()) {return s;}
		switch(type_list.get(i)) {
		case "vh4":
			stem=replaceLast(stem,"フ","");
			return new String[]{stem+"フ",stem+"ハ",stem+"ヒ",stem+"フコト",stem+"ヘ"};
		case "vsh":
		String lastst=stem.substring(stem.length() - 1);
		if(lastst.equals("ス")) {
			stem=replaceLast(stem,"ス","");
		return new String[]{stem+"ス",stem+"セ",stem+"シ",stem+"ス",stem+"スル",stem+"スルコト",stem+"セヨ"};
		}else {
			stem=replaceLast(stem,"ズ","");
			return new String[]{stem+"ズ",stem+"ゼ",stem+"ジ",stem+"ズ",stem+"ズル",stem+"ズルコト",stem+"ゼヨ"};
		}
		case "vr4":
			stem=replaceLast(stem,"ル","");
			return new String[]{stem+"ル",stem+"ラ",stem+"リ",stem+"ル",stem+"ルコト",stem+"レ"};
		default:
			break;
		}
		return s;
	}
	
	public String[] get_yomigana_candidates(int i) {
		String yomi=yomigana_list.get(i);
		String[] s=new String[] {yomi};
		return s;
	}
	
}
