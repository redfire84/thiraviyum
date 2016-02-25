package com.thiraviyum.model;

import java.util.Collections;
import java.util.List;

public class DataTable<T> {

	private List<T> data = Collections.emptyList();

	public void setData(List<T> data) {
		this.data = data;
	}

	public List<T> getData() {
		return data;
	}
}
