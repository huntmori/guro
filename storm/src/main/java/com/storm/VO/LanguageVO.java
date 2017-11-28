package com.storm.VO;

public class LanguageVO 
{
	private	int		id;
	private	String	name;
	private	String	supported_interface;
	private	String	supported_voice;
	private	String	supported_subtitle;
	
	private	int		languageNo;
	private	String	 languageName;
	private	boolean	languageSubtitle;
	private	boolean	languageVoice;
	private	boolean	languageInterface;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSupported_interface() {
		return supported_interface;
	}
	public void setSupported_interface(String supported_interface) {
		this.supported_interface = supported_interface;
	}
	public String getSupported_voice() {
		return supported_voice;
	}
	public void setSupported_voice(String supported_voice) {
		this.supported_voice = supported_voice;
	}
	public String getSupported_subtitle() {
		return supported_subtitle;
	}
	public void setSupported_subtitle(String supported_subtitle) {
		this.supported_subtitle = supported_subtitle;
	}
		
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
	
}
