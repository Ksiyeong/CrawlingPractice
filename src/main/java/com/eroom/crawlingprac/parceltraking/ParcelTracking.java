package com.eroom.crawlingprac.parceltraking;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParcelTracking {
    private String cjUrl = "https://www.doortodoor.co.kr/parcel/%20/%20doortodoor.do?fsp_action=PARC_ACT_002&fsp_cmd=retrieveInvNoACT&invc_no=";
    private String cjCssQuery = "#tabContents > ul > li.first.focus > div > div:nth-child(2) > div > table > tbody > tr";

    public List<ParcelTrackingResponseDto> traceAParcel(Long trackingId) {
        List<ParcelTrackingResponseDto> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(cjUrl + trackingId).get();
            Elements elements = doc.select(cjCssQuery);

            for (int i = 1; i < elements.size(); i++) {
                result.add(new ParcelTrackingResponseDto(
                        elements.get(i).select("> td:nth-child(1)").text(),
                        elements.get(i).select("> td:nth-child(2)").text(),
                        elements.get(i).select("> td:nth-child(3)").text(),
                        elements.get(i).select("> td:nth-child(4)").text()
                ));
            }


        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    public static void main(String[] args) {
        ParcelTracking parcelTracking = new ParcelTracking();

        List<ParcelTrackingResponseDto> result = parcelTracking.traceAParcel(567257542471L);

        for (ParcelTrackingResponseDto response : result) {
            System.out.println(response.getStep());
            System.out.println(response.getDateTime());
            System.out.println(response.getStatus());
            System.out.println(response.getBranch());
        }
    }
}
