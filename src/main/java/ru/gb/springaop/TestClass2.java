package ru.gb.springaop;

import org.springframework.stereotype.Component;

@Component
public class TestClass2 {

    @Timer()
    public void annotatedMethod(){
        System.out.println("Аннотированный метод отработал");
    }
}
