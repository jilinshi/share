package com.share.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class BigTxt {

	@SuppressWarnings("resource")
	public StringBuffer convertBigTxt(File f) {
		StringBuffer rs = new StringBuffer();
		try {
			final int BUFFER_SIZE = 0x1200000;// �����СΪ12M

			// System.out.println(BUFFER_SIZE);

			// File f = new File("C:\\uploadfiles\\201504021603542921.txt");

			int len = 0;
			Long start = System.currentTimeMillis();
			for (int z = 8; z > 0; z--) {
				MappedByteBuffer inputBuffer;

				inputBuffer = new RandomAccessFile(f, "r").getChannel().map(
						FileChannel.MapMode.READ_ONLY,
						f.length() * (z - 1) / 8, f.length() * 1 / 8);

				byte[] dst = new byte[BUFFER_SIZE];// ÿ�ζ���12M������
				for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {
					if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {
						for (int i = 0; i < BUFFER_SIZE; i++)
							dst[i] = inputBuffer.get(offset + i);
					} else {
						for (int i = 0; i < inputBuffer.capacity() - offset; i++)
							dst[i] = inputBuffer.get(offset + i);
					}
					int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
							: inputBuffer.capacity() % BUFFER_SIZE;

					len += new String(dst, 0, length).length();
					/*
					 * System.out.println(new String(dst, 0,
					 * length).toString()); System.out.println(new String(dst,
					 * 0, length).length() + "-" + (z - 1) + "-" + (8 - z + 1));
					 */

					rs.append(new String(dst, 0, length));

				}
			}
			// System.out.println(len);
			long end = System.currentTimeMillis();
			// System.out.println("��ȡ�ļ��ļ����ѣ�" + (end - start) + "����");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public List<String> readFile(String filename) {
		List<String> rs = new ArrayList<String>();
		File file = new File(filename);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file)); // ����Ƕ����ļ� �� new
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				rs.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return rs;
	}

	// @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		 
		BigTxt bt = new BigTxt();
		List<String> a= bt.readFile("C:\\uploadfiles\\201504021603542921.txt");
		System.out.println(a.get(0).split("	")[1]);
		
	}
}
