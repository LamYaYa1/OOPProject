package dataio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

import model.DataMgr;
import model.Sentence;
import model.StockIndex;

public class FileRead {
	
	private FileReader fr;
	private BufferedReader br;
	private static final String COMMA_DELIMITER = ",";
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public FileRead(String filename) {
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Not found " + filename);
		}
		readFile(filename);
	}
	
	private void readFileCsv(String filename) { // Doc file stockIndex.txt
		List<StockIndex> stockIndexList = DataMgr.getStockIndexList();
		try {
			Calendar calendar = Calendar.getInstance();
			String line;
			String currentTime = formatter.format(calendar.getTime());
			String lastTime = currentTime;

			Utils util = new Utils();
			int count = -1; // Kiem tra doc het ngay day chua
			
			if(Utils.checkWeekend()) {
				if(Utils.checkEndOfMonth()) {
					calendar.add(Calendar.DATE, - calendar.get(Calendar.DAY_OF_MONTH) + 1);
					lastTime = formatter.format(calendar.getTime());
				} else {
					calendar.add(Calendar.DATE, - 6);
					lastTime = formatter.format(calendar.getTime());
				}
			}
			
			while((line = br.readLine()) != null) {
				/* Nếu hôm nay là cuối tuần hay tháng : Đọc dữ liệu đầu tuần và cuối.
			       Vừa sinh ra thay đổi so với hôm qua và có thể so với tuần qua hoặc tháng */
				
				if(line.equals(currentTime) || line.equals(lastTime)) {
					count = 0;
					continue;
				}
				
				if(count != -1) {
					String[] splitData = line.split(COMMA_DELIMITER);
					StockIndex stockIndex = new StockIndex(splitData[0], Float.parseFloat(splitData[1]), Float.parseFloat(splitData[2]), Float.parseFloat(splitData[3]), Float.parseFloat(splitData[4]), Float.parseFloat(splitData[5]));
					stockIndexList.add(stockIndex);
					count++;
				} else {
					continue;
				}
				
				if(count == 5) {
					count = -1;
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error while reading " + filename);
			try {
				if(br != null) br.close();
				if(fr != null) fr.close();
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	private void readFileText(String filename) { // Doc file sentence.txt 
		String line;
		Sentence sentence = null;
		try {
			while((line = br.readLine()) != null) {
				if(line.charAt(line.length()-1) == ':') { // Ký tự cuối cùng của Label là ':', còn các câu khác là dấu '.' .
					sentence = new Sentence();
					sentence.setTypeName(line.replace(":", ""));
					DataMgr.getSentenceTypeList().add(sentence);
					continue;
				}
				sentence.addSentence(line);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error while reading " + filename);
			try {
				if(br != null) br.close();
				if(fr != null) fr.close();
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	private void readFile(String filename) {
		if(filename.contains("stockIndex")) {
			readFileCsv(filename);
		} else {
			readFileText(filename);
		
		}
	}
}
