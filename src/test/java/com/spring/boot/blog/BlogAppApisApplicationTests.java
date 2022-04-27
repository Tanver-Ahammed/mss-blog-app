package com.spring.boot.blog;

import com.spring.boot.blog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		String className=this.userRepository.getClass().getName();
		String packageName=this.userRepository.getClass().getPackageName();
		System.err.println(className);
		System.err.println(packageName);
	}

}
