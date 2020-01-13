package com.wildcodeschool.spring.security.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private final List<Greeting> greetingList = new ArrayList<Greeting>();

	public GreetingController() {
		greetingList.add(new Greeting(counter.incrementAndGet(), String.format(template, "toto")));
		greetingList.add(new Greeting(counter.incrementAndGet(), String.format(template, "titi")));
		greetingList.add(new Greeting(counter.incrementAndGet(), String.format(template, "tata")));
	}

	@RequestMapping("/greeting/{id}")
	public Greeting greeting(@PathVariable(value = "id") int id) {

		try {
			return this.greetingList.get(id);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
}