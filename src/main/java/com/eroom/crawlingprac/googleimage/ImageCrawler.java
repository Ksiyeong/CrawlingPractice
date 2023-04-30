package com.eroom.crawlingprac.googleimage;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Component
@Slf4j
public class ImageCrawler {
    public List<String> getImageUrls(String searchKeyword) {
        Document document = null;

        try {
            document = Jsoup.connect("https://www.google.com/search?q=" + searchKeyword + "&tbm=isch").get();
        } catch (IOException e) {
            log.error(e.getMessage()); // 에러처리행동 추가하세요
        }

        Elements imageElements = document.getElementsByTag("img");

        List<String> imageUrls = new ArrayList<>();
        for (int i = 1; i < imageElements.size(); i++) { // 맨처음 0인덱스는 구글로고가 잡힘
            imageUrls.add(imageElements.get(i).attr("src"));
        }

        return imageUrls;
    }
}
