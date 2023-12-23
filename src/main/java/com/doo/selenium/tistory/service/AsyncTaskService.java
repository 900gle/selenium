package com.doo.selenium.tistory.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncTaskService {

    private static String MOBILE = "Y";

    @Async("executor")
    public CompletableFuture<Integer> task(int i, String url) {

        //(base) ➜  ~ mv -f Downloads/chromedriver /Users/doo/bin/
//        https://googlechromelabs.github.io/chrome-for-testing/
        System.setProperty("webdriver.chrome.driver", "/Users/doo/bin/chromedriver");
        String userAgent = "Mozilla/5.0 (Linux; Android 9; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.83 Mobile Safari/537.36"; //모바일 에이전트

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함

        Random random = new Random();

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
            Thread.sleep(1500);

            driver.close();
            driver.quit();

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }


        return CompletableFuture.supplyAsync(() -> {
            return 1;
        });
    }

}
