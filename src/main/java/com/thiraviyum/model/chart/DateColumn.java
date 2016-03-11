package com.thiraviyum.model.chart;

import java.time.LocalDate;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateColumn extends Column<LocalDate> {

	public DateColumn(LocalDate v, String f) {
		super(v, f);
	}

	@JsonProperty(value = "v")
	public String getValue() {
		StringBuffer sf = new StringBuffer("Date(");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getV().toEpochDay());
		sf.append(cal.get(Calendar.YEAR)).append(",");
		sf.append(cal.get(Calendar.MONTH)).append(",");
		sf.append(cal.get(Calendar.DAY_OF_MONTH));
		sf.append(")");
		return sf.toString();
	}

}
