package com.thiraviyum.model.chart;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateColumn extends Column<Date> {

	public DateColumn(Date v, String f) {
		super(v, f);
	}

	@JsonProperty(value = "v")
	public String getValue() {
		StringBuffer sf = new StringBuffer("Date(");
		Calendar cal = Calendar.getInstance();
		cal.setTime(getV());
		sf.append(cal.get(Calendar.YEAR)).append(",");
		sf.append(cal.get(Calendar.MONTH)).append(",");
		sf.append(cal.get(Calendar.DAY_OF_MONTH));
		sf.append(")");
		return sf.toString();
	}

}
