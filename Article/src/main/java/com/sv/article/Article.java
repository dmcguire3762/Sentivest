package com.sv.article;

import org.joda.time.DateTime;

public class Article {
	private String text;
	private DateTime date;
	private double sentiment;  // bounded -1.0 to 1.0
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public double getSentiment() {
		return sentiment;
	}
	public void setSentiment(double sentiment) {
		this.sentiment = sentiment;
	}
	
}
