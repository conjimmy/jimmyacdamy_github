/**
 * 
 */
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
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jimmy.mygodutch.model.UserMsg;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂ava联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class HttpUtil
{
	// 创建HttpClient对象
	public static HttpClient httpClient = new DefaultHttpClient();
	public static final String BASE_URL ="http://10.4.44.120:8080/GoDutchServer/android/";//"http://121.40.91.193:8080/godutch/android/";
		//"http://192.168.1.20:8808/auction/android/";
	/**
	 * 
	 * @param url 发送请求的URL
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public static String getRequest(String url)
		throws Exception
	{
		// 创建HttpGet对象。
		HttpGet get = new HttpGet(url);
		// 发送GET请求
		HttpResponse httpResponse = httpClient.execute(get);
		// 如果服务器成功地返回响应
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			// 获取服务器响应字符串
			String result = EntityUtils
				.toString(httpResponse.getEntity());
			return result;
		}
		return null;
	}
	public static Bitmap getImg(String urlStr)
			throws Exception
		{
			
		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();
		//conn.setRequestMethod("GET");
		conn.setConnectTimeout(10000);
		conn.connect();
		InputStream inStream = conn.getInputStream();
			return BitmapFactory.decodeStream(inStream);
		}
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

					+ "\"" + end);// file对应<input type="file" name="file" />
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
			String result = br.readLine();// 服务器端返回的结果
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
	public static String saveUserMsg(String urlStr,InputStream in,UserMsg usermsg,String file)
			throws Exception
		{
		HttpPost post = new HttpPost(urlStr);
			MultipartEntity entity=new MultipartEntity();
//		 for(Map.Entry<String ,String> ent:data.entrySet())
//		 {String key=ent.getKey();
//			 String val=ent.getValue();
//			 entity.addPart(key,new StringBody(val,Charset.forName("UTF-8")));
//		 }
		 if(usermsg.getFromuid()!=null)
			 entity.addPart("fromuid",new StringBody(usermsg.getFromuid().toString(),Charset.forName("UTF-8")));
		 if(usermsg.getFromuname()!=null)
			 entity.addPart("fromuname",new StringBody(usermsg.getFromuname().toString(),Charset.forName("UTF-8")));
		 if(usermsg.getContent()!=null)
			 entity.addPart("content",new StringBody(usermsg.getContent().toString(),Charset.forName("UTF-8")));
		 
		 entity.addPart("file",new InputStreamBody(in,"multipart/form-data",file));
		 post.setEntity(entity);
		 HttpResponse httpResponse = httpClient.execute(post);
		 if (httpResponse.getStatusLine()
					.getStatusCode() == 200)
				{
					// 获取服务器响应字符串
					String result = EntityUtils
						.toString(httpResponse.getEntity());
					return result;
				}
				return null;
		}
	/**
	 * 
	 * @param url 发送请求的URL
	 * @param params 请求参数
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public static String postRequest(String url
		, Map<String ,String> rawParams)throws Exception
	{
		// 创建HttpPost对象。
		HttpPost post = new HttpPost(url);
		// 如果传递参数个数比较多的话可以对传递的参数进行封装
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : rawParams.keySet())
		{
			//封装请求参数
			params.add(new BasicNameValuePair(key , rawParams.get(key)));
		}
		// 设置请求参数
		post.setEntity(new UrlEncodedFormEntity(
			params, HTTP.UTF_8));
		// 发送POST请求
		HttpResponse httpResponse = httpClient.execute(post);
		// 如果服务器成功地返回响应
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			// 获取服务器响应字符串
			String result = EntityUtils
				.toString(httpResponse.getEntity());
			return result;
		}
		return null;
	}
}
