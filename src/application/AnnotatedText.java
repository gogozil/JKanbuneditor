package application;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class AnnotatedText {
	static public String annotated;
	static public String original;
	static public ArrayList<AnnotatedCharacter> ac;
	static public String[] dict_cc=new String[30];
    static private final String romaji="nn,kya,kyu,kyo,sha,shi,shu,she,sho,cha,chi,chu,che,cho,nya,nyu,nyo,hya,hyu,hyo,mya,myu,myo,rya,ryu,ryo,bya,byu,byo,pya,pyu,pyo,gya,gyu,gyo,jya,jyu,jyo,ja,ji,ju,je,jo,bya,byu,byo,pya,pyu,pyo,ka,ki,ku,ke,ko,sa,si,su,se,so,ta,ti,tu,te,to,na,ni,nu,ne,no,fa,fi,fu,fe,fo,ha,hi,hu,he,ho,ma,mi,mu,me,mo,wyi,wye,ya,yi,yu,ye,yo,ra,ri,ru,re,ro,wa,wi,wu,we,wo,ga,gi,gu,ge,go,za,zi,zu,ze,zo,da,di,du,de,do,ba,bi,bu,be,bo,a,i,u,e,o";
    static private final String katakana="ン,キャ,キュ,キョ,シャ,シ,シュ,シェ,ショ,チャ,チ,チュ,チェ,チョ,ニャ,ニュ,ニョ,ヒャ,ヒュ,ヒョ,ミャ,ミュ,ミョ,リャ,リュ,リョ,ビャ,ビュ,ビョ,ピャ,ピュ,ピョ,ギャ,ギュ,ギョ,ジャ,ジュ,ジョ,ジャ,ジ,ジュ,ジェ,ジョ,ビャ,ビュ,ビョ,ピャ,ピュ,ピョ,カ,キ,ク,ケ,コ,サ,シ,ス,セ,ソ,タ,チ,ツ,テ,ト,ナ,ニ,ヌ,ネ,ノ,ファ,フィ,フ,フェ,フォ,ハ,ヒ,フ,ヘ,ホ,マ,ミ,ム,メ,モ,ヰ,ヱ,ヤ,イ,ユ,イェ,ヨ,ラ,リ,ル,レ,ロ,ワ,ヰ,ウ,ヱ,ヲ,ガ,ギ,グ,ゲ,ゴ,ザ,ジ,ズ,ゼ,ゾ,ダ,ヂ,ヅ,デ,ド,バ,ビ,ブ,ベ,ボ,ア,イ,ウ,エ,オ";
    static private final String hiragana="ん,きゃ,きゅ,きょ,しゃ,し,しゅ,しぇ,しょ,ちゃ,ち,ちゅ,ちぇ,ちょ,にゃ,にゅ,にょ,ひゃ,ひゅ,ひょ,みゃ,みゅ,みょ,りゃ,りゅ,りょ,びゃ,びゅ,びょ,ぴゃ,ぴゅ,ぴょ,ぎゃ,ぎゅ,ぎょ,じゃ,じゅ,じょ,じゃ,じ,じゅ,じぇ,じょ,びゃ,びゅ,びょ,ぴゃ,ぴゅ,ぴょ,か,き,く,け,こ,さ,し,す,せ,そ,た,ち,つ,て,と,な,に,ぬ,ね,の,ふぁ,ふぃ,ふ,ふぇ,ふぉ,は,ひ,ふ,へ,ほ,ま,み,む,め,も,ゐ,ゑ,や,い,ゆ,いぇ,よ,ら,り,る,れ,ろ,わ,ゐ,う,ゑ,を,が,ぎ,ぐ,げ,ご,ざ,じ,ず,ぜ,ぞ,だ,ぢ,づ,で,ど,ば,び,ぶ,べ,ぼ,あ,い,う,え,お";
    static private final String[] okiji={"者","不","於"};
    
    static private String[] katakana_List,hiragana_List,romaji_List;
    static public final int HIRAGANA_TO_KATAKANA=0;
    static public final int KATAKANA_TO_HIRAGANA=1;
    static public final int ROMAJI_TO_KATAKANA=2;
    static public final int ROMAJI_TO_HIRAGANA=3;
    static final String[] judouconst= {"　","ヽ ","⚪︎ "};
    static private String[] dictionary;
    
	private AnnotatedText(String s) {
	}
	
	public static void load_dict() {
		Scanner sc;
		ArrayList<String> ret=new ArrayList();
		try {
			sc = new Scanner(new File("/Users/inagakiko/CCeditor/JKanbuneditor/src/application/Unihan-kJapaneseOnKun_Kanbun_Mod.txt"));
			int i=0;
			dictionary=new String[13375];
			while (sc.hasNext())  //returns a boolean value  
			{
			dictionary[i]=sc.next();
			i++;
			}
		} catch (FileNotFoundException e){
			
		}
	}
	
	static public void init() {
		katakana_List=katakana.split(",");
		hiragana_List=hiragana.split(",");
		romaji_List=romaji.split(",");
		load_dict();
	}
	
	static public String convert_text_type(String arg,int type) {
		String ret=arg;
		String[] replace_with;
		String[] replace_to;
		if(type==HIRAGANA_TO_KATAKANA) {
			replace_with=hiragana_List;
			replace_to=katakana_List;
		}else if(type==KATAKANA_TO_HIRAGANA){
			replace_with=katakana_List;
			replace_to=hiragana_List;			
		}else if(type==ROMAJI_TO_HIRAGANA){
			replace_with=romaji_List;
			replace_to=hiragana_List;			
		}else{
			replace_with=romaji_List;
			replace_to=katakana_List;
		}
		for(int i=0;i<arg.length();i++) {
			for(int j=0;j<hiragana_List.length;j++) {
				ret=ret.replace(replace_with[j],replace_to[j]);
			}
		}
		return ret;
	}
	
	static public void load_Text(String s) {
		ac=new ArrayList<AnnotatedCharacter>();
		s=s+" ";
		int i=0;
		while(i<s.length()-1) {
			if(s.substring(i+1,i+2).equals("、")||s.substring(i+1,i+2).equals("，")) {
			ac.add(new AnnotatedCharacter(s.substring(i,i+1),judouconst[1]));
			i+=2;
			}else if(s.substring(i+1,i+2).equals("。")){
			ac.add(new AnnotatedCharacter(s.substring(i,i+1),judouconst[2]));
			i+=2;
			}else {
			ac.add(new AnnotatedCharacter(s.substring(i,i+1),judouconst[0]));
			i++;
			}			
		}
		original=s;
	}
	
	static public String convert_Text() {
		String ret="";
		for(int i=0;i<ac.size();i++) {
			String judoutemp;
			if(ac.get(i).judou.equals(judouconst[0])) {judoutemp="";}else {judoutemp=ac.get(i).judou;}
			ret+=ac.get(i).kanji+"#"+ac.get(i).selected_okurigana+"#"+ac.get(i).selected_kaeriten+"#"+ac.get(i).selected_yomigana+"#"+judoutemp+"/";
		}
		return ret;
	}
	
	
	static public KanaCandidates generateCandidates(String search_character) {
		Scanner sc;
		KanaCandidates ret=new KanaCandidates();
			int i=0;
			for(i=0;i<dictionary.length;i++) {
			String[] s=dictionary[i].split(";");
			if(search_character.equals(s[0])) 
			{
				//System.out.println(s[1].substring(1));
				String[] kanas=s[1].split(",");
				int hyphen=-1;
				for(int j=0;j<kanas.length;j++) {
					hyphen=kanas[j].indexOf("-");
					if(hyphen>=0) {
						int colon=kanas[j].indexOf(":");
						String type="";
						String yomi;
						if(colon>=0) {
							type=kanas[j].substring(0,colon);
							yomi=kanas[j].substring(colon+1,hyphen);
						}else {
							yomi=kanas[j].substring(0,hyphen);
						}
						ret.add_okurigana_candidates(convert_text_type(kanas[j].substring(hyphen+1),HIRAGANA_TO_KATAKANA),type,yomi);
						}else {
						}
					
					}
				if(hyphen>=0) {break;}
				}
			}
			ret.add_okurigana_candidates("ヲ","n","");
			ret.add_okurigana_candidates("ニ","n","");
			ret.add_okurigana_candidates("ト","n","");
			ret.add_okurigana_candidates("ノ","n","");
			ret.add_okurigana_candidates("ハ","n","");



		return ret;
	}
	
	static String convert_original_to_kakikudashi() {
		String kakikudashi="";
		//ArrayList<AnnotatedCharacter> temp=new ArrayList<AnnotatedCharacter>();
		String brackets="";
		Stack<AnnotatedCharacter> kaeriten=new Stack<AnnotatedCharacter>();
		System.out.println(ac.size());
		int bnum=0;
		int onetwo=0;
		int updown=0;
		for(int i=0;i<ac.size();i++) {
			if(ac.get(i).selected_kaeriten.equals("レ")){
				brackets+=ac.get(i).kanji+"(";
				ac.get(i).bracket++;
				bnum++;
			}else if(ac.get(i).selected_kaeriten.equals("二")||ac.get(i).selected_kaeriten.equals("三")){
				brackets+=ac.get(i).kanji+"(";
				ac.get(i).bracket++;
				onetwo++;
				if(bnum>0) {
					onetwo+=bnum;
					System.out.println(String.valueOf(onetwo)+" "+String.valueOf(bnum));
					bnum=0;
				}
			}else if(ac.get(i).selected_kaeriten.equals("一")){
				brackets+=ac.get(i).kanji;
				while(onetwo>0) {
					brackets+=")";
					ac.get(i).endbracket++;
					onetwo--;
				}
			}else if(ac.get(i).selected_kaeriten.equals("一レ")){
				brackets+=ac.get(i).kanji+"(";
				ac.get(i).bracket++;
				bnum+=onetwo+1;
				onetwo=0;
			}else if(ac.get(i).selected_kaeriten.equals("中")||ac.get(i).selected_kaeriten.equals("下")) {
				brackets+=ac.get(i).kanji+"(";
				ac.get(i).bracket++;
				updown++;
				if(bnum>0) {
					updown+=bnum;
					System.out.println(String.valueOf(updown)+" "+String.valueOf(bnum));
					bnum=0;
				}
			}else if(ac.get(i).selected_kaeriten.equals("上")){
				brackets+=ac.get(i).kanji;
				while(updown>0) {
					brackets+=")";
					ac.get(i).endbracket++;
					updown--;
				}				
			}else{
				brackets+=ac.get(i).kanji;//muten
				while(bnum>0) {
						brackets+=")";
						ac.get(i).endbracket++;
						bnum--;
				}
			}
		}
		System.out.println(brackets);
		
		for(int i=0;i<ac.size();i++) {
			if(!(ac.get(i).saidoku.equals(""))) {
				kakikudashi+=ac.get(i).get_reading(false);
				System.out.println("Saidoku"+ac.get(i).get_reading(false));
			}
			
			if(ac.get(i).bracket>0) {
				kaeriten.push(ac.get(i));
				ac.get(i).bracket--;
			}else{
				if(!ac.get(i).saidoku.equals("")) {
					kakikudashi+=ac.get(i).get_reading(true);
				}else {
				kakikudashi+=ac.get(i).get_reading(false);
				}
			}
			while(ac.get(i).endbracket>0){
					kakikudashi+=kaeriten.pop().get_reading(true);
				//System.out.println(ac.get(i).endbracket);
				ac.get(i).endbracket--;
			}
			if(ac.get(i).judou.equals(judouconst[1])) {kakikudashi+="、";}
			if(ac.get(i).judou.equals(judouconst[2])) {kakikudashi+="。";}
		}
		return kakikudashi;
	}
	
	static void update_Text() {
		
	}
	
	public static void load_Annotated(String s) {
		ac=new ArrayList<AnnotatedCharacter>();
		String[] temp=s.split("/");
		for(int i=0;i<temp.length;i++) {
			String[] temp2=(temp[i]+" ").split("#",-1);
			System.out.println(temp[i]);
			if(temp2.length>=4) {
				if(temp2[4].contains(judouconst[1])) {ac.add(new AnnotatedCharacter(temp2[0],judouconst[1]));}
				else if(temp2[4].contains(judouconst[2])) {ac.add(new AnnotatedCharacter(temp2[0],judouconst[2]));}else {
					ac.add(new AnnotatedCharacter(temp2[0],judouconst[0]));
				}
			ac.get(i).selected_okurigana=temp2[1];
			System.out.println(temp2[0]+temp2[1]+temp2[2]+temp2[3]+temp2[4]);
			ac.get(i).selected_kaeriten=temp2[2];
			ac.get(i).selected_yomigana=temp2[3];
			}
		}
	}
}
