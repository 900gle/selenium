package com.doo.selenium.runner;


import com.doo.selenium.tistory.service.TstoryAsyncService;
import com.doo.selenium.tistory.service.TstoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;

@Component
@Command(name = "java -jar selenium.jar", mixinStandardHelpOptions = true,
        version = "1.0.0",
        description = "crawler"
)
@RequiredArgsConstructor
public class AppCommand implements Callable<Integer>, IExitCodeExceptionMapper {

    private final TstoryService tstoryService;
    private final TstoryAsyncService tstoryAsyncService;

    @ArgGroup(exclusive = true, multiplicity = "1", validate = false)
    Exclusive exclusive;
    @Parameters(index = "0", paramLabel = "site type", description = "value:[ T | N ]")
    private String type;

    @Parameters(index = "1", paramLabel = "blog type", description = "value:[ FATHER | 5AMSUNG | 900GLE | IVAVER | LDH ]")
    private String key;

    @Override
    public int getExitCode(Throwable exception) {
        exception.printStackTrace();
        return 0;
    }

    @Override
    public Integer call() throws Exception {
        switch (type) {
            case "T":
                tstoryAsyncService.run(key);
                break;
            default:
        }

        return ExitCode.OK;
    }

    static class Exclusive {

        @Option(names = {"-t", "--type"}, required = true, description = "selenium target site value")
        private boolean isType;

        @Option(names = {"-k", "--key"}, required = true, description = "selenium blog key value")
        private boolean isKey;

    }
}
