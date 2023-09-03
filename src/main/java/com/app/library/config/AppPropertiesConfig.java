package com.app.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class AppPropertiesConfig {

    @Value("${app.log.pretty.print}")
    private boolean logPrettyPrint;

    public boolean isLogPrettyPrint() {
        return logPrettyPrint;
    }

    public void setLogPrettyPrint(boolean logPrettyPrint) {
        this.logPrettyPrint = logPrettyPrint;
    }
}
