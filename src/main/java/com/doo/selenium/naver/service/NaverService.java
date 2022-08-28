package com.doo.selenium.naver.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NaverService {

    private static WebElement element;

    public void run() {

        System.setProperty("webdriver.chrome.driver", "/Users/doo/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // 전체화면으로 실행
//        options.addArguments("--window-size= 200, 200"); //실행되는 브라우저 크기를 지정할 수 있습니다. - 이것도 안먹히는데
//        options.addArguments("--start-fullscreen");            // 브라우저가 풀스크린 모드(F11)로 실행됩니다.
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
//        options.addArguments("user-agent=" + userAgent);
        options.addArguments("--blink-settings=imagesEnabled=false");            // 브라우저에서 이미지 로딩을 하지 않습니다.
        options.addArguments("--mute-audio");            // 브라우저에 음소거 옵션을 적용합니다.
        options.addArguments("incognito");            // 시크릿 모드의 브라우저가 실행됩니다.


        ChromeDriver driver = new ChromeDriver(options);


        driver.executeScript("window.open('about:blank','_blank');");
        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());


        driver.switchTo().window(tabs.get(0));

        List<String> list = new ArrayList<>();
        list.add("구찌");

        int i = 0;
        for (String key : list) {

            String listUrl = "https://search.shopping.naver.com/search/all?frm=NVSHATC&origQuery=" + key + "&pagingIndex=" + i + "&pagingSize=40&productSet=total&query=" + key + "&sort=rel&timestamp=&viewType=list";

            // 웹페이지 요청
            driver.get(listUrl);



//
//
//            Elements title = element.select("div.basicList_title__VfX3c>a");
//            Elements price = element.select("strong.basicList_price__euNoD>span>span.price_num__S2p_v");
//            Elements category = element.select("div.basicList_depth__SbZWF span");
//            List<String> categoryLists = category.stream().map(x -> x.text()).collect(Collectors.toList());
//
//            Elements image = element.select("a.thumbnail_thumb__Bxb6Z > img");

            try {

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.getStackTrace();
            }


            List<WebElement> muchoCheese = driver.findElements(By.cssSelector("li.basicList_item__0T9JD"));



        muchoCheese.stream().forEach(e -> {
            String imageUrl = e.findElement(By.cssSelector("a.thumbnail_thumb__Bxb6Z>img")).getAttribute("src");
            System.out.println(imageUrl);
//            Double confidence = Double.valueOf(e.findElement(By.cssSelector("td:nth-child(4)")).getText());
//            String result = e.findElement(By.cssSelector("td:nth-child(5)")).getText();
//            String registeredDate = e.findElement(By.cssSelector("td:nth-child(7)")).getText();
        });

            i++;
        }


    }
}
