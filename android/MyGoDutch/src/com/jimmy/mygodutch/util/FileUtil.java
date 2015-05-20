package com.jimmy.mygodutch.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/** * �ļ��ϴ������ز����� * @author ������ * */
public class FileUtil {
	/**
	 * * �ļ��ϴ� * @paramserverUrl�ϴ���������url:
	 * http://192.168.0.8:8080/upload/UploadServlet *
	 * 
	 * @paramuploadFilePath�ֻ��ϵ��ļ�·���� /mnt/sdcard/upload/writchie.xml; * @return
	 */
	public static boolean uploadFile(String serverUrl, String uploadFilePath) {
		boolean flag = false;
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "******";
		URL url;
		try {
			url = new URL(serverUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");

			httpURLConnection.setRequestProperty("Charset", "UTF-8");

			httpURLConnection.setRequestProperty("Content-Type",

			"multipart/form-data;boundary=" + boundary);

			DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
			dos.writeBytes(twoHyphens + boundary + end);
			dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\""

					+ uploadFilePath.substring(uploadFilePath.lastIndexOf("/") + 1)

					+ "\"" + end);// file��Ӧ<input type="file" name="file" />
			dos.writeBytes(end);
			FileInputStream fis = new FileInputStream(uploadFilePath);
			byte[] buffer = new byte[8192]; // 8k
			int count = 0;
			while ((count = fis.read(buffer)) != -1) {
				dos.write(buffer, 0, count);
			}
			fis.close();
			dos.writeBytes(end);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
			dos.flush();
			InputStream is = httpURLConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String result = br.readLine();// �������˷��صĽ��
			// Toast.makeText(this, result, Toast.LENGTH_LONG).show();
			dos.close();
			is.close();
			flag = true;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}
}
