package com.share.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BigUpload
 */
@WebServlet("/page/BigUpload")
public class BigUpload extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIR = "C:\\uploadfiles";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int resumableChunkNumber = getResumableChunkNumber(request);
		ResumableInfo info = getResumableInfo(request);
		// String tname= UPLOAD_DIR+"\\" +
		// this.generateFileName(info.resumableFilename);
		// String tname= UPLOAD_DIR+"\\" +
		// this.generateFileName(info.resumableFilename);
		// System.out.println(info.resumableFilePath);
		RandomAccessFile raf = new RandomAccessFile(info.resumableFilePath,
				"rw");
		// Seek to position
		raf.seek((resumableChunkNumber - 1) * (long) info.resumableChunkSize);
		// Save to file
		InputStream is = request.getInputStream();
		long readed = 0;
		long content_length = request.getContentLength();
		byte[] bytes = new byte[1024 * 100];
		while (readed < content_length) {
			int r = is.read(bytes);
			if (r < 0) {
				break;
			}
			raf.write(bytes, 0, r);
			readed += r;
		}
		raf.close();
		// Mark as uploaded.
		info.uploadedChunks.add(new ResumableInfo.ResumableChunkNumber(
				resumableChunkNumber));
		if (info.checkIfUploadFinished()) { // Check if all chunks uploaded, and
											// change filename

			String tname = UPLOAD_DIR + "\\"
					+ this.generateFileName(info.resumableFilename);
			String oname=info.resumableFilename;
			System.out.println(info.resumableFilePath);
			System.out.println(tname);
			File newf = new File(tname);
			File oldf = new File(info.resumableFilePath);
			if (oldf.exists()) {
				oldf.renameTo(newf);
			} else {
				if (info.resumableFilePath.lastIndexOf(".temp") > 0) {
					oldf = new File(info.resumableFilePath.substring(0,
							info.resumableFilePath.lastIndexOf(".temp")));
					oldf.renameTo(newf);
				}
			}

			response.setCharacterEncoding("UTF-8");
			ResumableInfoStorage.getInstance().remove(info);

			response.getWriter().print(tname+"~"+oname);
		} else {
			response.setCharacterEncoding("UTF-8");
			//System.out.println(info.resumableFilePath);
			response.getWriter().print("upload");
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int resumableChunkNumber = getResumableChunkNumber(request);

		ResumableInfo info = getResumableInfo(request);

		if (info.uploadedChunks
				.contains(new ResumableInfo.ResumableChunkNumber(
						resumableChunkNumber))) {
			response.getWriter().print("Uploaded."); // This Chunk has been
														// Uploaded.
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private int getResumableChunkNumber(HttpServletRequest request) {
		return HttpUtils
				.toInt(request.getParameter("resumableChunkNumber"), -1);
	}

	private ResumableInfo getResumableInfo(HttpServletRequest request)
			throws ServletException {
		String base_dir = UPLOAD_DIR;

		int resumableChunkSize = HttpUtils.toInt(
				request.getParameter("resumableChunkSize"), -1);
		long resumableTotalSize = HttpUtils.toLong(
				request.getParameter("resumableTotalSize"), -1);
		String resumableIdentifier = request
				.getParameter("resumableIdentifier");
		String resumableFilename = request.getParameter("resumableFilename");
		String resumableRelativePath = request
				.getParameter("resumableRelativePath");
		// Here we add a ".temp" to every upload file to indicate NON-FINISHED
		String resumableFilePath = new File(base_dir, resumableFilename)
				.getAbsolutePath() + ".temp";

		ResumableInfoStorage storage = ResumableInfoStorage.getInstance();

		ResumableInfo info = storage.get(resumableChunkSize,
				resumableTotalSize, resumableIdentifier, resumableFilename,
				resumableRelativePath, resumableFilePath);
		if (!info.vaild()) {
			storage.remove(info);
			throw new ServletException("Invalid request params.");
		}
		return info;
	}

	/**
	 * 用日期和随机数格式化文件名避免冲突
	 * 
	 * @param fileName
	 * @return
	 */
	private String generateFileName(String fileName) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = sf.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}
}
