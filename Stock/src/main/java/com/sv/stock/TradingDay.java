package com.sv.stock;

import java.util.Collection;
import org.joda.time.DateTime;
import com.sv.article.Article;

public class TradingDay {
	private MarketResult actual;
	private MarketResult predicted;
	private DateTime date;
	private double sentimentScore;
	private Collection<Article> articles;
	
	public MarketResult getActual() {
		return actual;
	}
	public void setActual(MarketResult actual) {
		this.actual = actual;
	}
	public MarketResult getPredicted() {
		return predicted;
	}
	public void setPredicted(MarketResult predicted) {
		this.predicted = predicted;
	}
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public Collection<Article> getArticles() {
		return articles;
	}
	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}
	public double getSentimentScore() {
		return sentimentScore;
	}
	public void setSentimentScore(double sentimentScore) {
		this.sentimentScore = sentimentScore;
	}
	
}
