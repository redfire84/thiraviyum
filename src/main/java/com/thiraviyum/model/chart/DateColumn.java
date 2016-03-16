package com.thiraviyum.model.chart;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateColumn extends Column<LocalDate> {

	public DateColumn(LocalDate v, String f) {
		super(v, f);
	}

	@JsonProperty(value = "v")
	public String getValue() {
		StringBuffer sf = new StringBuffer("Date(");
		sf.append(getV().getYear()).append(",");
		sf.append(getV().getMonthValue() - 1).append(",");
		sf.append(getV().getDayOfMonth());
		sf.append(")");
		return sf.toString();
	}

}
