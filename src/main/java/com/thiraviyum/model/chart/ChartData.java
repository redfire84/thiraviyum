package com.thiraviyum.model.chart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

public class ChartData {

	private List<ColData> cols;
	private List<RowData> rows;
	
	public List<ColData> getCols() {
		return cols;
	}
	public void setCols(List<ColData> cols) {
		this.cols = cols;
	}
	public void addCol(ColData col) {
		Assert.notNull(col);
		if (cols == null) {
			cols = new ArrayList<ColData>();
		}
		cols.add(col);
	}
	public List<RowData> getRows() {
		return rows;
	}
	public void setRows(List<RowData> rows) {
		this.rows = rows;
	}
	public void addRow(RowData row) {
		Assert.notNull(row);
		if (rows == null) {
			rows = new ArrayList<RowData>();
		}
		rows.add(row);
	}
}
