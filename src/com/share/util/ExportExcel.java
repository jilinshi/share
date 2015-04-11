package com.share.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@SuppressWarnings("all")
public class ExportExcel {
	private int LENGTH = 60000;

	public <T> ByteArrayOutputStream genExcelData(LinkedHashMap title, List<HashMap> rs) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		Iterator columes = null;
		
		//������ʽ
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("����");
		font.setBoldweight((short) 600);
		font.setColor(HSSFFont.COLOR_NORMAL);
		HSSFCellStyle styleTitle = workbook.createCellStyle();
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ˮƽ����
		styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ����
		styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// �±߿�
		styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// ��߿�
		styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
		styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);// �ϱ߿�
		styleTitle.setFont(font);
		
		
		//������ʽ
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ˮƽ����
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ����
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// �±߿�
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// ��߿�
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// �ϱ߿�
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			columes = title.keySet().iterator();
			if (null != rs && rs.size() > 0) {

				int sheetcnt = rs.size() / LENGTH;
				int restcnt = rs.size() % LENGTH;

				if (sheetcnt > 0) {
					for (int i = 0; i < sheetcnt; i++) {
						sheet = workbook.createSheet("��" + (i + 1) + "ҳ");
						row = sheet.createRow(0);
						int c = 0;
						columes = title.keySet().iterator();
						while (columes.hasNext()) {
							String tempcolname = columes.next().toString();
							String colname = tempcolname;
							String colvalue = (String) title.get(colname);
							cell = row.createCell(c);
							cell.setCellValue(colvalue);
							cell.setCellStyle(styleTitle);
							c++;
						}
						for (int j = i * LENGTH; j < (i + 1) * LENGTH; j++) {
							row = sheet.createRow(j - (i * LENGTH) + 1);
							Object o = rs.get(j);
							JSONObject jsonObject = JSONObject.fromObject(o);
							c = 0;
							columes = title.keySet().iterator();
							while (columes.hasNext()) {
								String[] tempcolname = columes.next().toString().split(",");
								String colname = tempcolname[0];
								String colvalue ="";
								try{
								  colvalue = jsonObject.getString(colname);
								}catch(Exception e){
									colvalue="";
								}
								cell = row.createCell(c);
								if (null != colvalue) {
									if ("java.math.BigDecimal".equals(colvalue.getClass().getName())) {
										cell.setCellValue(colvalue.toString());
									} else if ("java.sql.Timestamp".equals(colvalue.getClass().getName())) {
										cell.setCellValue(colvalue.toString().substring(0, 10));
									} else {
										cell.setCellValue(colvalue.toString());
									}
								}
								cell.setCellStyle(style);
								c++;
							}
						}
						//�Զ���Ӧ���
						for(int q = 0 ; q<title.size() ;q++){
							sheet.autoSizeColumn(q, true);
						}
						
					}

					if (restcnt > 0) {
						sheet = workbook.createSheet("��" + (sheetcnt + 1) + "ҳ");
						row = sheet.createRow(0);
						int c = 0;
						columes = title.keySet().iterator();
						while (columes.hasNext()) {
							String tempcolname = columes.next().toString();
							String colname = tempcolname;
							String colvalue = (String) title.get(colname);
							cell = row.createCell(c);
							cell.setCellValue(colvalue);
							cell.setCellStyle(styleTitle);
							c++;
						}
						for (int j = sheetcnt * LENGTH; j < rs.size(); j++) {
							row = sheet.createRow(j - (sheetcnt * LENGTH) + 1);
							Object o = rs.get(j);
							JSONObject jsonObject = JSONObject.fromObject(o);
							c = 0;
							columes = title.keySet().iterator();
							while (columes.hasNext()) {
								String[] tempcolname = columes.next().toString().split(",");
								String colname = tempcolname[0];
								String colvalue ="";
								try{
								  colvalue = jsonObject.getString(colname);
								}catch(Exception e){
									colvalue="";
								}
								cell = row.createCell(c);
								if (null != colvalue) {
									if ("java.math.BigDecimal".equals(colvalue.getClass().getName())) {
										cell.setCellValue(colvalue.toString());
									} else if ("java.sql.Timestamp".equals(colvalue.getClass().getName())) {
										cell.setCellValue(colvalue.toString().substring(0, 10));
									} else {
										cell.setCellValue(colvalue.toString());
									}
								}
								cell.setCellStyle(style);
								c++;
							}
						}
						//�Զ���Ӧ���
						for(int q = 0 ; q<title.size() ;q++){
							sheet.autoSizeColumn(q, true);
						}
					}
				} else {
					sheet = workbook.createSheet("��һҳ");
					row = sheet.createRow(0);
					int c = 0;
					columes = title.keySet().iterator();
					while (columes.hasNext()) {
						String tempcolname = columes.next().toString();
						String colname = tempcolname;
						String colvalue = (String) title.get(colname);
						cell = row.createCell(c);
						cell.setCellValue(colvalue);
						cell.setCellStyle(styleTitle);
						c++;
					}

					for (int j = sheetcnt * LENGTH; j < restcnt; j++) {
						row = sheet.createRow(j - (sheetcnt * LENGTH) + 1);
						Object o = rs.get(j);
						JSONObject jsonObject = JSONObject.fromObject(o);
						c = 0;
						columes = title.keySet().iterator();
						while (columes.hasNext()) {
							String[] tempcolname = columes.next().toString().split(",");
							String colname = tempcolname[0];
							String colvalue ="";
							try{
							  colvalue = jsonObject.getString(colname);
							}catch(Exception e){
								colvalue="";
							}
							cell = row.createCell(c);
							if (null != colvalue) {
								if ("java.math.BigDecimal".equals(colvalue.getClass().getName())) {
									cell.setCellValue(colvalue.toString());
								} else if ("java.sql.Timestamp".equals(colvalue.getClass().getName())) {
									cell.setCellValue(colvalue.toString().substring(0, 10));
								} else {
									cell.setCellValue(colvalue.toString());
								}
							}
							cell.setCellStyle(style);
							c++;
						}
					}
					//�Զ���Ӧ���
					for(int q = 0 ; q<title.size() ;q++){
						sheet.autoSizeColumn(q, true);
					}
				}
			}
			workbook.write(baos);
		} catch (RuntimeException re) {
			re.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return baos;
	}
}
