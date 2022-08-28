package com.doo.selenium.mathpix.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class MathpixService {

    private static WebElement element;

    public void getData() {

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

        driver.get("https://accounts.mathpix.com/ocr-api/2176285a-b09c-42c3-a6d0-ff4daa61e9a2/results");

        try {
            element = driver.findElement(By.name("email"));

            Thread.sleep(500);

            element.sendKeys("bbongdoo@아이디");
            element = driver.findElement(By.name("password"));
            element.sendKeys("패스워드");

            //전송
            element = driver.findElement(By.className("login"));
            element.submit();

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.getStackTrace();
        }

        // 웹페이지 요청
        driver.get("https://accounts.mathpix.com/ocr-api/2176285a-b09c-42c3-a6d0-ff4daa61e9a2/results");

        try {

            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }

        List<WebElement> muchoCheese = driver.findElements(By.cssSelector("table.user-data>tbody>tr"));

        muchoCheese.stream().forEach(e -> {
            String imageUrl = e.findElement(By.cssSelector("td.image-col>img")).getAttribute("src");
            Double confidence = Double.valueOf(e.findElement(By.cssSelector("td:nth-child(4)")).getText());
            String result = e.findElement(By.cssSelector("td:nth-child(5)")).getText();
            String registeredDate = e.findElement(By.cssSelector("td:nth-child(7)")).getText();
        });
    }
}
