package com.doo.selenium.tistory.service;

import com.doo.selenium.annotation.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TstoryAsyncService {

    private List<CompletableFuture<Integer>> completableFutures = new ArrayList<>();
    private final AsyncTaskService asyncTaskService;

//    private static WebElement element;

    private static int MAX_NUMBER = 60;

    @Timer
    public void run(String key) {

        String url = "https://father-lys.tistory.com/";
        switch (key) {
            case "5AMSUNG":
                url = "https://5amsung.tistory.com/";
                break;
            case "FATHER":
                url = "https://father-lys.tistory.com/";
        }

        for (int i = 0; i < 100; i++) {
            CompletableFuture<Integer> completableFuture = asyncTaskService.task((i % MAX_NUMBER), url);
            completableFutures.add(completableFuture);
        }

        for (CompletableFuture<Integer> future : completableFutures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
