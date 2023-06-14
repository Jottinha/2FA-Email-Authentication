package com.project.auth.utils;

import java.util.Random;

public class CodeGenerate {
    public String randomCode(){
        int min = 1000;
        int max = 9999;

        Random generate = new Random();
        return Integer.toString(generate.nextInt(max - min + 1) + min);
    }
}
