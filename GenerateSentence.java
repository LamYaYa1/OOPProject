package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dataio.Utils;
import model.DataMgr;
import model.Sentence;
import model.StockIndex;

public class GenerateSentence {
	
	public GenerateSentence() {
		exchangeIndex();
	}
	
	private void exchangeIndex() {
		Random random = new Random();
		int N = 0; // Random cho mẫu câu  
		List<StockIndex> stockIndexList = DataMgr.getStockIndexList();
		String line = null; //Câu nhận được cho dữ liệu đầu ra ...
		StockIndex stockIndex = null;
		Sentence sentence = null;
		for(int i = 0; i < stockIndexList.size(); i++) {
			// Chỉ xử lý ngày hiện tại, còn nếu có ngày đầu tuần, cuối tuần thì chỉ so sáng 2 đầu. 
			if(stockIndexList.size() == 10 && i < 4) {
				continue;
			}
			stockIndex = stockIndexList.get(i);
			if(stockIndex.getPointChange() > 0) {
				sentence = getSentenceWithLabel("TĂNG");
				N = random.nextInt(sentence.getSentenceList().size());
				line = sentence.getSentenceList().get(N);
				DataMgr.getDataOutput().add(getSentenceForStockIndex(stockIndex, line));
			} else if(stockIndex.getPointChange() < 0) {
				sentence = getSentenceWithLabel("Giảm");
				N = random.nextInt(sentence.getSentenceList().size());
				line = sentence.getSentenceList().get(N);
				DataMgr.getDataOutput().add(getSentenceForStockIndex(stockIndex, line));
			} else {
				sentence = getSentenceWithLabel("Tham Chiếu");
				N = random.nextInt(sentence.getSentenceList().size());
				line = sentence.getSentenceList().get(N);
				DataMgr.getDataOutput().add(getSentenceForStockIndex(stockIndex, line));
			}
		}
	}
	
	private String getSentenceForStockIndex(StockIndex stockIndex, String line) {
		if(line.contains("indexName")) {
			line = line.replace("indexName", stockIndex.getIndexName());
		}
		if(line.contains("pointChange")) {
			line = line.replace("pointChange", Math.abs(stockIndex.getPointChange()) + "");
		}
		if(line.contains("perChange")) {
			line = line.replace("perChange", stockIndex.perChange() + "");
		}
		if(line.contains("currentPoint")) {
			line = line.replace("currentPoint", stockIndex.getCurrentPoint() + "");
		}
		if(line.contains("volume")) {
			if(line.contains("triệu cổ phiếu")) {
				line = line.replace("volume", Utils.roundDouble(stockIndex.getVolume()/Math.pow(10, 6)) + "");
			} else {
				line = line.replace("volume", stockIndex.getVolume() + "");
			}
		}
		if(line.contains("exchangePrice")) {
			if(line.contains("tỷ đồng")) {
				line = line.replace("exchangePrice", Utils.roundDouble(stockIndex.exchangePrice()/Math.pow(10, 9)) + "");
			} else if(line.contains("triệu đồng")) {
				line = line.replace("exchangePrice", Utils.roundDouble(stockIndex.exchangePrice()/Math.pow(10, 6)) + "");
			} else {
				line = line.replace("exchangePrice", stockIndex.exchangePrice() + "");
			}
		}
		return line;
	}
	
	private Sentence getSentenceWithLabel(String label) {
		List<Sentence> sentenceTypeList = DataMgr.getSentenceTypeList();
		Sentence sentence = null;
		for(int j = 0; j < DataMgr.getSentenceTypeList().size(); j++) {
			sentence = DataMgr.getSentenceTypeList().get(j);
			if(sentence.getTypeName().equalsIgnoreCase(label)) {
				return sentence;
			}
		}
		return null;
	}
}
