package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DbUtil {
	private static DbUtil dbUtil = new DbUtil();
	public static DbUtil getInstance() {
		return dbUtil;
	}
	public String htmlSource(String link,String encoding) {
		String result = "";
		StringBuilder stringBuilder = null;
		try {
			URL url = new URL(link);
			URLConnection uc = url.openConnection();
			uc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			InputStream inputStream = uc.getInputStream();
			InputStreamReader in = new InputStreamReader(inputStream,encoding);
			BufferedReader reader = new BufferedReader(in);
			
			String line = "";
			stringBuilder = new StringBuilder();
			while((line=reader.readLine())!=null) {
				stringBuilder.append(line+"\n\r");
			}
			result = stringBuilder.toString();
			
			reader.close();
			in.close();
			inputStream.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return result;
	}
	public void downloadImage(String filePath,String imgURL) {
		String fileName = imgURL.substring(imgURL.lastIndexOf("/"));
		File files = new File(filePath);
		
		if(!files.isDirectory()) {
			files.mkdirs();
		}
		
		try {
			URL url = new URL(imgURL);
			HttpURLConnection connect = (HttpURLConnection) url.openConnection();
			InputStream in = connect.getInputStream();
			File file = new File(filePath+fileName);
			
			FileOutputStream out = new FileOutputStream(file);
			int ch = 0;
			while((ch=in.read())!=-1) {
				out.write(ch);
			}
			out.close();
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
