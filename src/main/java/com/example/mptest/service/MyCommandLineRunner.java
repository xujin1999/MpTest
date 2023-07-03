package com.example.mptest.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Resource
    private DiscardServer discardServer;
    @Override
    public void run(String... args) throws Exception {
        discardServer.run(8888);
    }
}
