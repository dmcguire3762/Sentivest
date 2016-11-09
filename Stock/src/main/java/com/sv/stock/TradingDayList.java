package com.sv.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TradingDayList extends ArrayList<TradingDay>{

	/**
	 * generated serial UID
	 */
	private static final long serialVersionUID = -4715327463062536199L;
	
	public void sortByDate(){
		Collections.sort(this, new TradingDayDateSorter());
	}
	
	/**
	 * Class to sort Trading Days chronologically, oldest first
	 * @author dmac
	 *
	 */
	public static class TradingDayDateSorter implements Comparator<TradingDay>{

		@Override
		public int compare(TradingDay o1, TradingDay o2) {
			if(o1.getDate().isAfter(o2.getDate())){
				return 1;
			} else {
				return -1;
			}
		}
		
	}
}
