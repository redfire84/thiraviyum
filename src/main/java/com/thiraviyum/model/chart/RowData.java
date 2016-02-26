package com.thiraviyum.model.chart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

public class RowData {

	private List<Column<?>> c;

	public List<Column<?>> getC() {
		return c;
	}
	public void setC(List<Column<?>> c) {
		this.c = c;
	}
	public void addColumn(Column<?> col) {
		Assert.notNull(col);
		if (c == null) {
			c = new ArrayList<Column<?>>();
		}
		c.add(col);
	}
}
