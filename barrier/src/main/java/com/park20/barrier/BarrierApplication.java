package com.park20.barrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Objects;

@SpringBootApplication
@EnableScheduling
public class BarrierApplication implements ApplicationRunner {

	@Autowired
	private ConfigurableEnvironment env;

	public static void main(String[] args) {
		SpringApplication.run(BarrierApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String open = Objects.equals(env.getProperty("isEntry"), "true") ? "Entry" : "Exit";

		System.out.println(open + " gate at port "+ env.getProperty("server.port"));
		/*
		// Check if the application is started with key-value pairs
		if (args.containsOption("key1") && args.containsOption("key2")) {
			String value1 = args.getOptionValues("key1").get(0);
			String value2 = args.getOptionValues("key2").get(0);

			// Update properties
			environment.getPropertySources().get("applicationConfig: [classpath:/application.properties]")
					.getSource().put("key1", value1);
			environment.getPropertySources().get("applicationConfig: [classpath:/application.properties]")
					.getSource().put("key2", value2);

			System.out.println("Properties updated: key1=" + value1 + ", key2=" + value2);
		}
		*/

	}

}
