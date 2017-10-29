package com.srini.optimize;
import java.util.Comparator;

public class ZipcodeComp implements Comparator<Zipcode> {

	
	public int compare(Zipcode o1, Zipcode o2) {
		// TODO Auto-generated method stub
		int comp = 0;
		if ( ( o1.getLowerbound() == o2.getLowerbound() ) && ( o1.getUpperbound() == o2.getUpperbound()) ) {
			comp = 0;
		}
		if (( o1.getLowerbound() == o2.getLowerbound()) && (o1.getUpperbound() > o2.getUpperbound()) ) {
			return 1;
		}
		if (( o1.getLowerbound() == o2.getLowerbound()) && (o1.getUpperbound() < o2.getUpperbound()) ) {
			 return -1;
		}
		
		if ( ( o1.getLowerbound() > o2.getLowerbound() ) && ( o1.getUpperbound() > o2.getUpperbound()) ) {
			comp =  1;
		}
		
		if ( ( o1.getLowerbound() < o2.getLowerbound() ) && ( o1.getUpperbound() < o2.getUpperbound()) ) {
			comp =  -1;
		}
		return comp;
	}

}
