package com.xcoding.springbootaction;

import com.xcoding.springbootaction.model.PersonModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootactionApplicationTests {

	@Value("${name}")
	private String name;
	@Value("${age}")
	private Integer age;
	@Autowired
	private PersonModel person;

	@Test
	public void getName() {
		System.out.println(name);
	}

	@Test
	public void getAge() {
		System.out.println(age);
	}

	@Test
	public void getPerson() {
		System.out.println(person.getName()+person.getAge());
	}

}
