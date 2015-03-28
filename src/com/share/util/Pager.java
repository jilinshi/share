package com.share.util;

public class Pager {
	public int start;
	public int end;
	public int total;// ��ҳ��
	public long records;// �ܼ�¼��
	public int rows;
	public int pager;
	public int number;

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

	public Pager(String page, String rows, Long records) {
		int _records = records.intValue();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// ÿҳ��ʾ����
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// ÿҳ�Ŀ�ʼ��¼ ��һҳΪ1 �ڶ�ҳΪnumber +1
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
