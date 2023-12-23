package com.doo.selenium.tistory.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TstoryService {

    private static WebElement element;

    private static int MAX_NUMBER = 13;

    private static String MOBILE = "Y";

    public void run(String key) {

        //(base) ➜  ~ mv -f Downloads/chromedriver /Users/doo/bin/
        System.setProperty("webdriver.chrome.driver", "/Users/doo/bin/chromedriver");
        String userAgent = "Mozilla/5.0 (Linux; Android 9; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.83 Mobile Safari/537.36"; //모바일 에이전트

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함

        Random random = new Random();
        int start = random.nextInt(MAX_NUMBER);


        String url = "https://ldh-6019.tistory.com/";

        switch (key) {
            case "5AMSUNG":
                url = "https://5amsung.tistory.com/";
                break;
            case "FATHER":
                url = "https://father-lys.tistory.com/";
        }


        for (var i = start; i < MAX_NUMBER; i++) {
            try {

                if (random.nextInt(5) % 2 == 0) {
                    MOBILE = "Y";
                } else {
                    MOBILE = "N";
                }

                if (MOBILE.equals("Y")) {
                    options.addArguments("user-agent=" + userAgent);
                }

                // WebDriver 객체 생성
                ChromeDriver driver = new ChromeDriver(options);

                // 빈 탭 생성
                driver.executeScript("window.open('about:blank','_blank');");
//
//            // 탭 목록 가져오기
                List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

                // 첫번째 탭으로 전환
                driver.switchTo().window(tabs.get(0));
                driver.get(url + i);
                Thread.sleep(1000);

                /*
                int frameCont = OptionalInt.of(driver.findElements(By.tagName("iframe")).size()).getAsInt();

                System.out.println("FrameCont ::" + frameCont);
                if (frameCont > 0) {
                    driver.switchTo().frame(0);
                } else {
                    driver.close();
                    driver.quit();
                    continue;
                }
                if (MOBILE.equals("Y")) {

                    WebElement webElement = Optional.of(driver.findElement(By.cssSelector("div#content>div>a"))).orElse(null);
//                    WebElement webElement = Optional.of(driver.findElement(By.cssSelector("div.inner_cm>a"))).orElse(null);
//                    WebElement webElement = Optional.of(driver.findElement(By.id("content"))).orElse(null);



                    if(webElement == null){
                        continue;
                    }
//                    element = driver.findElement(By.cssSelector("div.inner_cm>a"));
//                    element = driver.findElement(By.id("content"));
                    element = driver.findElement(By.cssSelector("div#content>div>a"));
//                    WebElement webElement = Optional.of(driver.findElement(By.cssSelector("div.content>div>a"))).orElse(null);


                    if (element != null && element.getTagName().equals("a")) {
                        System.out.println("ccchhheck :::: ");
                        element.click();
                    }

                } else {
                    List<WebElement> frame_g = driver.findElements(By.tagName("a"));
                    System.out.println(frame_g.size());
                    if (frame_g.size() > 1 && i % 3 == 0) {
                        Thread.sleep(2000);
                        frame_g.stream().forEach(
                                x -> {
                                    x.click();
                                }
                        );
                    }
                }
                */

                Thread.sleep(2000);

                System.out.println(driver);
                driver.close();

                driver.quit();
                if (i == 20) {
                    i = 8;
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
    }
}
