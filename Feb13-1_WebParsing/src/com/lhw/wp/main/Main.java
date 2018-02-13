package com.lhw.wp.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Main {

	public static void main(String[] args) throws Exception {
		String target = "https://www.naver.com/";
		HttpsURLConnection con = (HttpsURLConnection) new URL(target).openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String temp = "";
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int min = 0;
		int sec = 0;
		
		
		// 네이버 서버시간
		while ((temp = br.readLine()) != null) {
			if (temp.contains("var svt = \"")) {
				temp = temp.split("var svt = \"")[1].split("\";")[0];
				year = Integer.parseInt(temp.substring(0, 4));
				month = Integer.parseInt(temp.substring(4, 6));
				day = Integer.parseInt(temp.substring(6, 8));
				hour = Integer.parseInt(temp.substring(8, 10));
				min = Integer.parseInt(temp.substring(10, 12));
				sec = Integer.parseInt(temp.substring(12, 14));

				System.out.printf("%d년 %d월 %d일 %d시 %d분 %d초", year, month, day, hour, min, sec);
			}
		}
		con.disconnect();
		br.close();
	}
}
