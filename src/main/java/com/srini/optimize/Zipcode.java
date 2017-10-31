package com.srini.optimize;
public class Zipcode {
	public int lowerbound;
	public int upperbound;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lowerbound;
		result = prime * result + upperbound;
		return result;
	}
	public int getLowerbound() {
		return lowerbound;
	}
	public void setLowerbound(int lowerbound) {
		this.lowerbound = lowerbound;
	}
	public int getUpperbound() {
		return upperbound;
	}
	public void setUpperbound(int upperbound) {
		this.upperbound = upperbound;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zipcode other = (Zipcode) obj;
		if (lowerbound != other.lowerbound)
			return false;
		if (upperbound != other.upperbound)
			return false;
		return true;
	}
}
