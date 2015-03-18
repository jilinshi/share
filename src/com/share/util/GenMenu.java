package com.share.util;

import java.math.BigDecimal;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.share.dto.MenuDTO;

public class GenMenu {
	/**
	 * 
	 * @param menus
	 * @return
	 * 
	 * 
	 *         <li class="">
	 *         <a data-url="<%=basePath%>page/html/content/index.html"
	 *         href="#<%=basePath%>page/html/content/index.html"> <i
	 *         class="menu-icon fa fa-tachometer"></i> <span class="menu-text">
	 *         Dashboard </span> </a>
	 * 
	 *         <b class="arrow"></b></li>
	 */
	public String getMenuStr(List<MenuDTO> menus) {
		Document menudoc = DocumentHelper.createDocument();
		// <ul class="nav nav-list">
		Element root = menudoc.addElement("ul");
		root.addAttribute("class", "nav nav-list")
				.addAttribute("id", "aa" + -1);
		// System.out.println(menudoc.asXML());
		for (MenuDTO s : menus) {
			if (s.getPmId().equals(new BigDecimal(-1))) {
				Element li = root.addElement("li");
				addMenuItem(s, li);

			} else {
				Element parentul = this.getNodeAttribute("ul", "id",
						"aa" + s.getPmId(), menudoc);
				Element parentli = this.getNodeAttribute("li", "id",
						"bb" + s.getPmId(), menudoc);

				if (null == parentul && parentli != null) {
					parentul = parentli.addElement("ul")
							.addAttribute("class", "submenu")
							.addAttribute("id", "aa" + s.getPmId());
					Element li = parentul.addElement("li");
					addMenuItem(s, li);
				} else if (null != parentul && parentli != null) {
					Element li = parentul.addElement("li");
					addMenuItem(s, li);
				}

			}

		}
		// System.out.println(menudoc.asXML());
		return menudoc.asXML();
	}

	private void addMenuItem(MenuDTO s, Element li) {
		li.addAttribute("class", " ").addAttribute("id", "bb" + s.getMenuId());
		Element a = li.addElement("a");

		Element i = a.addElement("i");
		i.setText("***");
		i.addAttribute("class", s.getIco());
		Element span = a.addElement("span");
		span.addAttribute("class", "menu-text").setText(s.getMenuname());

		if ("#".equals(s.getMenuurl())) {
			a.addAttribute("class", "dropdown-toggle");
			Element b1 = a.addElement("b");
			b1.setText("***");
			b1.addAttribute("class", "arrow fa fa-angle-down");
			a.addAttribute("href", "#");

		} else {
			a.addAttribute("href", "#*u*" + s.getMenuurl());
			a.addAttribute("data-url", "*u*" + s.getMenuurl());
		}

		Element b = li.addElement("b");
		b.setText("***");
		b.addAttribute("class", "arrow");
	}

	public Element getNodeAttribute(String nodeName, String key, String value,
			Document menudoc) {
		Node node = menudoc.selectSingleNode("//" + nodeName + "[@" + key
				+ "='" + value + "']");
		if (null == node) {
			return null;
		} else {
			return (Element) node;
		}

	}
}
