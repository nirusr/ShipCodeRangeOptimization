package com.srini.optimize;

import java.util.Comparator;

public class ZipcodeCompByLower implements Comparator<Zipcode> {

	public int compare(Zipcode o1, Zipcode o2) {
		if ( o1.lowerbound == o2.lowerbound) {
			return 0;
		} 
		return o1.lowerbound < o2.lowerbound ? -1: 1;
		
	}

}
