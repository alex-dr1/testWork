package ru.alex.testwork.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParseServiceImplTest {

	@Autowired
	ParseServiceImpl parseService;

	@Test
	void shouldParse(){
		assertEquals("stop main", parseService.run());
	}
}