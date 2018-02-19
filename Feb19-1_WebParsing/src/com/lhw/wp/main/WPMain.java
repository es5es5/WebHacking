// 현재 동작 안 함.

package com.lhw.wp.main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WPMain {
	public static void main(String[] args) throws Exception {
		while (true) {
			String temp = "";
			FileWriter fw = null;
			int rank = 1;
			String target = "https://www.naver.com/";
			HttpsURLConnection con = (HttpsURLConnection) new URL(target).openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			// 네이버 서버시간
			while ((temp = br.readLine()) != null) {
				if (temp.contains("var svt = \"")) {
					fw = new FileWriter(temp.split("var svt = \"")[1].split("\";")[0]);
				}
				if (temp.contains("class=\"ah_k\"") && rank <= 20) { // 실시간 검색어
					fw.write("실시간 순위 " + rank++ + "위 " + temp.split("<span class=\"ah_k\">")[1].split("</span>")[0]
							+ "\r\n");
				}
				if (rank == 20) {
					fw.close();
				}
			}
			con.disconnect();
			br.close();
			Thread.sleep(10000);
		}
	}
}
