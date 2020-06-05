package model;

public class StockIndex {
	
	private String indexName; //VN-INDEX, HNX - INDEX, UPCOME
	private float currentPoint;
	private float pointChange;
	private float volume; // KLGD
	private float buyForeignerVolume; // NN mua
	private float sellForeignerVolume; // NN ban
	
	// indexName : VN-INDEX, Stock: list StockCode, points: currentCores, pointChanges: points with past Points
	public StockIndex(String indexName, float currentPoint, float pointChange, float volume, float buyForeignerVolume, float sellForeignerVolume) {
		setIndexName(indexName);
		setCurrentPoint(currentPoint);
		setPointChange(pointChange);
		setVolume(volume);
		setBuyForeignerVolume(buyForeignerVolume);
		setSellForeignerVolume(sellForeignerVolume);
	}
	
	public float perChange(){
		float pastPoint = currentPoint - pointChange;
		return pointChange/pastPoint;
	}
	
	public float exchangePrice() {
		if(indexName.equalsIgnoreCase("VNIndex")) {
			return volume*14000;
		} else if(indexName.equalsIgnoreCase("HNX-Index")) {
			return volume*12000;
		} else if(indexName.equalsIgnoreCase("VN30-Index")) {
			return volume*17000;
		} else if(indexName.equalsIgnoreCase("Upcome-Index")) {
			return volume*11000;
		} 
		return volume*11000;
	}

	// Getters and Setters
	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public float getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(float currentPoint) {
		this.currentPoint = currentPoint;
	}

	public float getPointChange() {
		return pointChange;
	}

	public void setPointChange(float pointChange) {
		this.pointChange = pointChange;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getBuyForeignerVolume() {
		return buyForeignerVolume;
	}

	public void setBuyForeignerVolume(float buyForeignerVolume) {
		this.buyForeignerVolume = buyForeignerVolume;
	}

	public float getSellForeignerVolume() {
		return sellForeignerVolume;
	}

	public void setSellForeignerVolume(float sellForeignerVolume) {
		this.sellForeignerVolume = sellForeignerVolume;
	}
}
