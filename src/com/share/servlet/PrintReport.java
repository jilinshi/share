package com.share.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.share.dto.UserDTO;

/**
 * Servlet implementation class PrintReport
 */
@WebServlet("/page/PrintReport")
public class PrintReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrintReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String path = getServletContext().getRealPath("/")
				+ "page\\html\\content\\report\\collating_report.jasper";

		System.out.println(path);
		// ��������

		ServletOutputStream outputStream = response.getOutputStream();
		// ���������
		InputStream inputStream = new FileInputStream(new File(path));
		try {
			// ������ҳ��PDF�ļ�
			HashMap map = new HashMap();
			map.put("a1", "ʪ���");

			ArrayList<UserDTO> list2 = new ArrayList<UserDTO>();
			UserDTO e = new UserDTO();
			e.setIdno("��������");
			list2.add(e);
			e = new UserDTO();
			e.setIdno("��������1");
			list2.add(e);
			e = new UserDTO();
			e.setIdno("��������2");
			list2.add(e);
			e = new UserDTO();
			e.setIdno("��������3");
			list2.add(e);
			JasperRunManager.runReportToPdfStream(inputStream, outputStream,
					map, new JRBeanCollectionDataSource(list2));
			// ����PDF��ʽ
			response.setContentType("application/pdf");
			outputStream.flush();
			outputStream.close();
		} catch (JRException e) {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.setContentType("text/plain");
			response.getOutputStream().print(stringWriter.toString());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
