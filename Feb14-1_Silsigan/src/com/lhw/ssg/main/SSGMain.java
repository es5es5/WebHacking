package com.lhw.ssg.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SSGMain {

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
		int rank =1;
		
		// ���̹� �����ð�
		while ((temp = br.readLine()) != null) {
			if (temp.contains("var svt = \"")) {
				temp = temp.split("var svt = \"")[1].split("\";")[0];
				year = Integer.parseInt(temp.substring(0, 4));
				month = Integer.parseInt(temp.substring(4, 6));
				day = Integer.parseInt(temp.substring(6, 8));
				hour = Integer.parseInt(temp.substring(8, 10));
				min = Integer.parseInt(temp.substring(10, 12));
				sec = Integer.parseInt(temp.substring(12, 14));
				System.out.printf("%d�� %d�� %d�� %d�� %d�� %d��", year, month, day, hour, min, sec);
			}
			if (temp.contains("class=\"ah_k\"")) { // �ǽð� �˻���
				if (rank<=20) {
					System.out.println("�ǽð� ���� "+ rank++ + "�� "+ temp.split("<span class=\"ah_k\">")[1].split("</span>")[0]);
				}
			}
		}
		con.disconnect();
		br.close();
	}
}
