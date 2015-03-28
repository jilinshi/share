package com.share.util;

public class Pager {
	public int start;
	public int end;
	public int total;// 总页数
	public long records;// 总记录数
	public int rows;
	public int pager;
	public int number;

	public Pager(String page, String rows) {
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		start = ((intPage - 1) * number) + 1;
		end = (intPage) * number;

	}

	public Pager(String page, String rows, Long records) {
		int _records = records.intValue();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		start = ((intPage - 1) * number) + 1;
		end = (intPage) * number;
		total = (_records % number) == 0 ? (_records / number)
				: (_records / number) + 1;
		this.rows = number;
		this.records = records;
		this.pager = intPage;
	}

	public void setRecords(Long records) {
		int _records = records.intValue();
		total = (_records % rows) == 0 ? (_records / rows)
				: (_records / rows) + 1;
		this.records = records;

	}
}
