package com.sippnex.fileblade;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fileblade")
@ComponentScan(basePackages = {"com.sippnex.fileblade", "com.sippnex.fileblade.controller"})
public class FilebladeConfig {

    private String root;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
