package com.sv.engine.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.Test;

import com.sv.article.Article;
import com.sv.engine.StockAnalyzer;
import com.sv.stock.MarketResult;
import com.sv.stock.Stock;
import com.sv.stock.TradingDay;
import com.sv.stock.TradingDayList;

public class StockAnalyzerTests {
	
	@Test
	public void testPredictedResultPopulation(){
		DateTime latestDate = DateTime.parse("2016-11-01T12:00:00");
		
		TradingDay latestTDay = createTradingDay(latestDate, 153.0, -0.25, 0.40, 0.75);
		TradingDay tDayMinus1 = createTradingDay(latestDate.minusDays(1), 151.0, 0.10, 0.8);
		TradingDay tDayMinus2 = createTradingDay(latestDate.minusDays(2), 144.0, -0.30, -0.20, 0.22);
		
		Stock ibm = new Stock();
		ibm.setTicker("IBM");
		ibm.setDisplayName("International Business Machines");
		ibm.setTradingDays(new TradingDayList());
		ibm.getTradingDays().addAll(Arrays.asList(latestTDay, tDayMinus1, tDayMinus2));
		
		StockAnalyzer analyzer = new StockAnalyzer(ibm);
		analyzer.predictStockResults();
		
		for(TradingDay tDay : ibm.getTradingDays()){
			System.out.println(tDay.getDate() + " | " + tDay.getSentimentScore() + " | " + tDay.getActual().getAdjClose() + " | " + tDay.getPredicted().getAdjClose());
		}
	}
	
	private TradingDay createTradingDay(DateTime date, double adjClose, double... articleSentiments){
		TradingDay tradingDay = new TradingDay();
		tradingDay.setDate(date);
		tradingDay.setArticles(new ArrayList<Article>());
		
		MarketResult result = new MarketResult();
		result.setAdjClose(adjClose);
		tradingDay.setActual(result);
		
		for(double articleScore : articleSentiments){
			Article article = new Article();
			article.setSentiment(articleScore);
			article.setDate(date);
			tradingDay.getArticles().add(article);
		}
		
		return tradingDay;
	}
}
