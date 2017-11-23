package com.storm.crawlUtil;

import java.util.ArrayList;
import java.util.List;

import com.storm.CrawlVO.ReviewVO;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

public class WordExtractor 
{
	Komoran komoran;
	String sentence;
	public WordExtractor(){
		this("");
	}
	public WordExtractor(String word){
		komoran	=	new Komoran(".\\komoran\\models-full\\");
		this.sentence=word;
	}
	
	public ArrayList<String>	getKeywordArrayList()
	{
		return getKeywordArrayList(this.sentence);
	}
	
	public ArrayList<String>	getKeywordArrayList(String sentence)
	{
		ArrayList<String>	result = new ArrayList<String>();
		
		@SuppressWarnings("unchecked")
		List<List<Pair<String,String>>>	k_result = komoran.analyze(sentence);
		
		//System.out.println("==========================================================================");
		
		for(List<Pair<String, String>>search : k_result){
			for(Pair<String, String> wordMorph : search){
				if(	wordMorph.getSecond().equals("NNG")||
						wordMorph.getSecond().equals("NNP")||
						wordMorph.getSecond().equals("XR"))
					{
						//System.out.println(wordMorph);
						result.add(wordMorph.getFirst());
					}
			}
		}
		return result;
	}
	
	public ArrayList<String>	getKeywordsArrayList(ArrayList<ReviewVO> reviews)
	{
		ArrayList<String>	returnList	=	new ArrayList<String>();
		
		for(ReviewVO vo:reviews){
			@SuppressWarnings("unchecked")
			List<List<Pair<String,String>>>	result = komoran.analyze(vo.getText());
			System.out.println("==========================================================================");
			for(List<Pair<String, String>>search : result){
				for(Pair<String, String> wordMorph : search){
					if(	wordMorph.getSecond().equals("NNG")||
						wordMorph.getSecond().equals("NNP")||
						wordMorph.getSecond().equals("XR"))	{
						
						returnList.add(wordMorph.getFirst());
					}
				}
			}
		}
		return returnList;
	}
}
