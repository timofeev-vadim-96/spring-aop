package ru.gb.springaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		TestClass test = context.getBean(TestClass.class);
		test.method2();
		test.method1();

		TestClass2 test2 = context.getBean(TestClass2.class);
		test2.annotatedMethod();
	}
}
