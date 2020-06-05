package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataMgr {
	private static List<String> keywordList; //VN-INDEX, UPCOME-INDEX ... có thể thêm 1 số keyword khác.
	private static List<Sentence> sentenceTypeList;
	private static List<StockIndex> stockIndexList;
	private static List<String> dataOutput;
	private static DataMgr instance = null;
	
	private DataMgr() {
		keywordList = new ArrayList<>();
		keywordList.add("VNIndex");
		keywordList.add("HNX-Index");
		keywordList.add("Upcome-Index");
		keywordList.add("VN30-Index");
		keywordList.add("HNX30-Index");
	
		stockIndexList = new ArrayList<>();
		sentenceTypeList = new ArrayList<>();
		dataOutput = new ArrayList<>();
	}
	
	public static DataMgr getInstance() {
		if(instance == null) {
			instance = new DataMgr();
		}
		return instance;
	}

	public static List<StockIndex> getStockIndexList() {
		return stockIndexList;
	}

	public static List<Sentence> getSentenceTypeList() {
		return sentenceTypeList;
	}	
	
	public static List<String> getKeywordList(){
		return keywordList;
	}
	
	public static List<String> getDataOutput(){
		return dataOutput;
	}
}
