package com.share.util;

public class Pager {
	public int start;
	public int end;

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
}
