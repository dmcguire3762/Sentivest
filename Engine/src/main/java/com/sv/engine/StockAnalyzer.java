package com.sv.engine;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import com.sv.article.Article;
import com.sv.stock.MarketResult;
import com.sv.stock.Stock;
import com.sv.stock.TradingDay;

public class StockAnalyzer {
	private final Stock stock;
	
	public StockAnalyzer(Stock stock){
		this.stock = stock;
	}
	
	/**
	 * Predict the market result for each trading day,
	 * based on the sentiment of the articles for this stock.
	 */
	public void predictStockResults(){
		// for each day, starting with the oldest, calculate the sentiment score.
		stock.getTradingDays().sortByDate();
		for(TradingDay tradingDay : stock.getTradingDays()){
			// total sentiment score is the average of all articles for the day.
			double score = 0;
			for(Article article : tradingDay.getArticles()){
				score += article.getSentiment();
			}
			
			if(score != 0){
				tradingDay.setSentimentScore(score / tradingDay.getArticles().size());
			}
		}
		
		// for each stock, calculate the predicted result based on score.  weight the scores
		// by 1/(x + 1) where x is the number of days in the past from the stock being evaluated.
		// the predicted score is then the previous day's adjClose + the weighted score
		for(int i = 0; i < stock.getTradingDays().size(); i++){
			// date for stock being evaluated counts fully.
			double weightedScore = stock.getTradingDays().get(i).getSentimentScore();
			for(int j = 0; j < i; j++){
				weightedScore += calculateWeightedScore(stock.getTradingDays().get(i).getDate(), stock.getTradingDays().get(j).getDate(), stock.getTradingDays().get(j).getSentimentScore());
			}
			
			MarketResult predictedResult = new MarketResult();
			double close = 0;
			if(i > 0){
				close = stock.getTradingDays().get(i - 1).getActual().getAdjClose();
			} else {
				close = stock.getTradingDays().get(i).getActual().getAdjClose();
			}
			predictedResult.setAdjClose(close + close * (weightedScore / 10));
			stock.getTradingDays().get(i).setPredicted(predictedResult);
		}
	}

	private double calculateWeightedScore(DateTime currentDate, DateTime oldDate, double sentimentScore) {
		// TODO: int daysInPast = DateTimeUtils
		int daysInPast = 0;
		return sentimentScore * (1.0 / (1.0 + daysInPast));
	}
}
