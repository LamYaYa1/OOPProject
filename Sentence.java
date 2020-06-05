package model;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
	
	private String typeName; // Tên của loại: Tăng, Giảm, Nước ngoài... dựa vào label của các nhóm câu trong phần đọc.
	private List<String> sentenceList;
	
	public Sentence() {
		sentenceList = new ArrayList<>();
	}
	
	public void display() {
		System.out.println(typeName + " :");
		for(String items : sentenceList) {
			System.out.println(items);
		}
	}

	public void addSentence(String sentence) {
		sentenceList.add(sentence);
	}
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<String> getSentenceList() {
		return sentenceList;
	}
	
	public void setSentenceList(List<String> sentenceList) {
		this.sentenceList = sentenceList;
	}

}
