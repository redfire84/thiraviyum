package com.thiraviyum.model.chart;

public class Column<T> {

	/**
	 * Value
	 */
	private T v;
	/**
	 * Formatted Value
	 */
	private String f;
	
	public Column(T v, String f) {
		super();
		this.v = v;
		this.f = f;
	}
	public T getV() {
		return v;
	}
	public void setV(T v) {
		this.v = v;
	}
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
}
