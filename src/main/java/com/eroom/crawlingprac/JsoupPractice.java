package com.eroom.crawlingprac;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupPractice {
    public static void getStockPriceList() {
        String url = "http://nplus.doortodoor.co.kr/web/info.jsp?slipno="; // 원하는 url
        String slipno = "운송장번호를입력해주세요";

        Document doc;

        try {
            doc = Jsoup.connect(url+slipno)
                    .get();
            Elements elements = doc.select("body > center > table:nth-child(9) > tbody > tr");

            for (Element element : elements) {
                System.out.println(element.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getStockPriceList();
    }
}
