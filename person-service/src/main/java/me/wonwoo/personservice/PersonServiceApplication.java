package me.wonwoo.personservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

@SpringBootApplication
@RestController
public class PersonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonServiceApplication.class, args);
	}

	@GetMapping("/persons")
	public Flux<Person> getPerson() {
		return Flux.just(new Person(UUID.randomUUID().toString(), "wonwoo"),
				new Person(UUID.randomUUID().toString(), "kevin"));
	}

}

class Person {
	private String id;
	private String name;

	public Person(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
