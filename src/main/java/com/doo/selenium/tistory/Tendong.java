package com.doo.selenium.tistory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tendong {

    private static WebElement element;

    private static int MAX_NUMBER = 230;

    private static String MOBILE = "Y";

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/Users/doo/bin/chromedriver");
        String userAgent = "Mozilla/5.0 (Linux; Android 9; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.83 Mobile Safari/537.36"; //모바일 에이전트

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함

        Random random = new Random();
        int start = random.nextInt(MAX_NUMBER);


//            if (random.nextInt(5) % 2 == 0) {
//                MOBILE = "Y";
//            } else {
//                MOBILE = "N";
//            }
//
//            if (MOBILE.equals("Y")) {
//                options.addArguments("user-agent=" + userAgent);
//            }

            // WebDriver 객체 생성
            ChromeDriver driver = new ChromeDriver(options);

            // 빈 탭 생성
            driver.executeScript("window.open('about:blank','_blank');");

            // 탭 목록 가져오기
            List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

            // 첫번째 탭으로 전환
            driver.switchTo().window(tabs.get(0));
            driver.get("https://ldh-6019.tistory.com/" + 299);
            int frameCont = driver.findElements(By.tagName("iframe")).size();

            System.out.println("FrameCont ::" + frameCont);


            if (frameCont > 0) {
                driver.switchTo().frame(0);
            } else {
                driver.close();
                driver.quit();
            }
//            if (MOBILE.equals("Y")) {
//                element = driver.findElement(By.cssSelector("div#content>div>a"));
//                if (element != null && element.getTagName().equals("a")) {
//                    element.click();
//                }
//
//            } else {
//                List<WebElement> frame_g = driver.findElements(By.tagName("a"));
//                System.out.println(frame_g.size());
//                frame_g.stream().forEach(
//                        x -> {
//                            x.click();
//                        }
//                );
//
//            }

            driver.close();
            driver.quit();



    }

}




