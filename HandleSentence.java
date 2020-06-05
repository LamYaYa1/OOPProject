package controller;

import java.util.List;

import dataio.Utils;
import model.DataMgr;
import model.Sentence;

public class HandleSentence {
	
	public HandleSentence() {
		modelSentence();
	}

	public void modelSentence() {
		sentenceForExchangeIndex();
		
	}
	
	private void sentenceForExchangeIndex() { // Xử lý câu biến động cho các sàn giao dịch, Top 30 mã CK...	
		List<Sentence> sentenceTypeList = DataMgr.getSentenceTypeList();
		for(int i = 0; i < sentenceTypeList.size(); i++) {
			// Câu tăng giảm biến động sàn chứng khoán 
			if(sentenceTypeList.get(i).getTypeName().equalsIgnoreCase("TĂNG") || sentenceTypeList.get(i).getTypeName().equalsIgnoreCase("GIẢM") || sentenceTypeList.get(i).getTypeName().equalsIgnoreCase("THAM CHIẾU")) {
				sentenceTypeList.get(i).setSentenceList(handleSentenceList(sentenceTypeList.get(i).getSentenceList()));
			}
			// Nhà đầu tư nước ngoài...
		}
	}
	
	private List<String> handleSentenceList(List<String> sentenceList) { //VN-INDEX : indexName, 883.9 = currentPoint ...
		for(int j = 0; j < sentenceList.size(); j++) {
			String line = sentenceList.get(j);
			String[] words = line.split("\\s+");
			for(int i = 0; i < words.length; i++) {
				if(DataMgr.getKeywordList().contains(words[i])) {
					words[i] = "indexName";
				} else if(Utils.isNumberContain(words[i])) {
					if(words[i].indexOf('%') != -1) {
						if(words[i].indexOf('(') != -1) {
							words[i] = "(perChange%)";
						} else {
							words[i] = "perChange%";
						}
					} else {
						if(words[i+1].contains("điểm")) {
							if(Float.parseFloat(words[i]) < 30) {
								words[i] = "pointChange";
							} else {
								words[i] = "currentPoint";
							}
						} else if(words[i+2].contains("đồng")) {
							words[i] = "exchangePrice";
						} else {
							words[i] = "volume";
						}
					}
				} 
			}
			line = "";
			for(int i = 0; i < words.length; i++) {
				line = line.concat(words[i] + " ");
			}
			sentenceList.remove(j);
			sentenceList.add(j, line);
		}
		return sentenceList;
	}
	
}
