package ru.gb.springaop;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

@Component
@Timer(level = Level.WARN)
public class TestClass {
    public void method1(){
        System.out.println("Метод 1 отработал (аннотированный класс)");
    }

    public void method2() throws InterruptedException {
        Thread.sleep(300);
        System.out.println("300 миллисекунд прошло и Метод 2 отработал (аннотированный класс)");
    }
}
