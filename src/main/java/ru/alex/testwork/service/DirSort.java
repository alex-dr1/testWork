package ru.alex.testwork.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
@ToString
public class DirSort {
	String name;
	Sort.Direction direction;
}
