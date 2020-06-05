package app;

import controller.GenerateSentence;
import controller.HandleSentence;
import dataio.FileRead;
import model.DataMgr;
import model.Sentence;
import model.StockIndex;

// Test thôi sửa sau
public class App {
	public static void main(String[] args) {
		DataMgr datamgr = DataMgr.getInstance();
		FileRead fileRead1 = new FileRead("data/stockIndex.txt");
		FileRead fileRead2 = new FileRead("data/sentence.txt");
		HandleSentence handler = new HandleSentence();
		GenerateSentence generateSentence = new GenerateSentence();
		
		for(String items : DataMgr.getDataOutput()) {
			System.out.println(items);
		}
	}
}
