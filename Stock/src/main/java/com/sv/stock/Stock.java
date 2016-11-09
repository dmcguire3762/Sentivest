package com.sv.stock;

public class Stock {
	private String ticker;
	private String displayName;
	private TradingDayList tradingDays;
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public TradingDayList getTradingDays() {
		return tradingDays;
	}
	public void setTradingDays(TradingDayList tradingDays) {
		this.tradingDays = tradingDays;
	}
	
}
