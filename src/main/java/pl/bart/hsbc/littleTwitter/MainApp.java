package pl.bart.hsbc.littleTwitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("pl.bart.hsbc.littleTwitter.controller")
public class MainApp {
	
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

}
