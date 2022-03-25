package com.doo.selenium.runner;

import com.doo.selenium.tistory.service.TstoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TstoryRunner implements CommandLineRunner {

    private final TstoryService tstoryService;

    @Override
    public void run(String... args) throws Exception {
        tstoryService.run();
    }
}
