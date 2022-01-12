package com.doo.selenium.tistory;

import com.doo.selenium.utils.FileRead;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blog {


    private final static int MAX_COUNT = 150;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/doo/bin/chromedriver");

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함

//        List<String> ips = FileRead.getIps();
        Random random = new Random();
        int start = random.nextInt(MAX_COUNT);

        for (var i = start; i < MAX_COUNT; i++) {
            try {

//
////                프록시
//                int ipKey = random.nextInt(ips.size());
//                String proxyIpPort = ips.get(ipKey);
//                Proxy proxy = new Proxy();
//                proxy.setHttpProxy(proxyIpPort);
//                proxy.setSslProxy(proxyIpPort);
//                options.setCapability("proxy", proxy);

                // WebDriver 객체 생성
                ChromeDriver driver = new ChromeDriver(options);

//            // 빈 탭 생성
                driver.executeScript("window.open('about:blank','_blank');");
//
//            // 탭 목록 가져오기
                List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

                // 첫번째 탭으로 전환
                driver.switchTo().window(tabs.get(0));

                driver.get("https://ldh-6019.tistory.com/" + i);

                Thread.sleep(1000);
                int frameCont = driver.findElements(By.tagName("iframe")).size();
                System.out.println(frameCont);

                driver.switchTo().frame(0);
                List<WebElement> frame_g = driver.findElements(By.tagName("a"));
                System.out.println(frame_g.size());
                if (frame_g.size() > 1 && (i % 3) == 0 ){

                    frame_g.stream().skip(0).limit(1).forEach(
                            x -> {
                                System.out.println(x.getTagName());
                                x.click();
                            }
                    );
                    Thread.sleep(4000);

                }
                driver.close();
                driver.quit();
                if (i == MAX_COUNT) {
                    i = 8;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
    }
}




