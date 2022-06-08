package com.payau.currency.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@EnableScheduling
@Component
public class USDRate {
    private String dollarCourse = "";

    @Scheduled(fixedDelay = 300000)
    public String getDollarCourse() {
        try {
//        Document document = Jsoup.connect("https://cbr.ru/").get();
//        Elements usdRate = document.getElementsByClass("main-indicator_rate");
            String filename = "C:\\Users\\payau\\Desktop\\JAVA_CORE\\currency\\src\\main\\resources\\templates\\rate.html";
            Document document = Jsoup.parse(new File(filename), "UTF-8");
            Elements usdRate = document.getElementsByClass("main-indicator_rate");
            for (int i = 0; i < usdRate.size(); i++) {
                Element element = usdRate.get(i);
                Elements elements = element.getElementsByClass("col-md-2 col-xs-9 _dollar");
                String str = element.getElementsByClass("col-md-2 col-xs-9 _dollar").text().trim();
                if (str.equals("USD")) {
                    System.out.println(element.text());
                    dollarCourse = element.getElementsByClass("_dollar").text() + " " + element.getElementsByClass("mono-num").text();
                    break;
                } else {
                    dollarCourse = "Course doesn't exist";
                }
            }
            return dollarCourse;
        } catch (IOException e) {
            e.printStackTrace();
            return "Ooops...! Something went wrong";
        }
    }
}
