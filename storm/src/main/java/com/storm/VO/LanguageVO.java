package com.storm.VO;

public class LanguageVO implements Comparable<LanguageVO>{
	private	int		languageNo;
	private	String	languageName;
	private	boolean	languageSubtitle;
	private	boolean	languageVoice;
	private	boolean	languageInterface;
	
	public int getLanguageNo() {
		return languageNo;
	}
	public void setLanguageNo(int languageNo) {
		this.languageNo = languageNo;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public boolean isLanguageSubtitle() {
		return languageSubtitle;
	}
	public void setLanguageSubtitle(boolean languageSubtitle) {
		this.languageSubtitle = languageSubtitle;
	}
	public boolean isLanguageVoice() {
		return languageVoice;
	}
	public void setLanguageVoice(boolean languageVoice) {
		this.languageVoice = languageVoice;
	}
	public boolean isLanguageInterface() {
		return languageInterface;
	}
	public void setLanguageInterface(boolean languageInterface) {
		this.languageInterface = languageInterface;
	}
	
	@Override
	public int compareTo(LanguageVO o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.languageNo, o.languageNo);
	}
	
	
}
