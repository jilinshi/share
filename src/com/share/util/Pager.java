package com.share.util;

public class Pager {
	public int start;
	public int end;

	public Pager(String page, String rows) {
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// ÿҳ��ʾ����
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// ÿҳ�Ŀ�ʼ��¼ ��һҳΪ1 �ڶ�ҳΪnumber +1
		start = ((intPage - 1) * number) + 1;
		end = (intPage) * number;
	}
}
