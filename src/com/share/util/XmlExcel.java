package com.share.util;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XmlExcel {
	private static String filepath = "com/share/util/x-excel.xml";
	private static XmlExcel xmlexcel;
	
	public static XmlExcel getXmlexcel() {
		if (null == xmlexcel) {
			xmlexcel = new XmlExcel();
		}
		return xmlexcel;
	}
	
	public String getExcelValue(String colid) {

		String text = "";
		Document excel = this.readExcelXml();
		Element root = excel.getRootElement();
		Node colNode = root.selectSingleNode("/excel/table/col[@id='" + colid
				+ "']");
		if (null == colNode) {
		} else {
			text = colNode.getText();
		}
		return text;
	}

	public String getTableName(String tableid) {
		String tname = "";
		Document excel = this.readExcelXml();
		Element root = excel.getRootElement();
		Node tableNode = root.selectSingleNode("/excel/table[@id='" + tableid
				+ "']");
		if (null != tableNode) {
			Element etable = (Element) tableNode;
			tname = etable.attributeValue("title");
		}else{
			
		}
		return tname;
	}

	private Document readExcelXml() {
		String path = this.getClass().getClassLoader().getResource("").getPath() + filepath;
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(path));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	@SuppressWarnings("rawtypes")
	public LinkedHashMap<String, String> getTableMap(String tableid) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		Document excel = this.readExcelXml();
		Element root = excel.getRootElement();
		Node tableNode = root.selectSingleNode("/excel/table[@id='" + tableid
				+ "']");
		if (null != tableNode) {
			Element etable = (Element) tableNode;
			Iterator it = etable.elementIterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				map.put(e.attributeValue("id"), e.getText());
			}
		} else {

		}
		return map;
	}

	public static void main(String args[]) throws Exception {
		LinkedHashMap<String, String>  map = XmlExcel.getXmlexcel().getTableMap("vckinsurance");
		String name = XmlExcel.getXmlexcel().getTableName("vckinsurance");
		System.out.println(name);
	}
}
