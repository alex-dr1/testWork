package ru.alex.testwork.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QPredicate {
	List<Predicate> predicates = new ArrayList<>();

	public static QPredicate builder(){
		return new QPredicate();
	}

	public <T> QPredicate add(Optional<T> object, Function<T, Predicate> function){
		object.ifPresent(obj->predicates.add(function.apply(obj)));
		return this;
	}

	public Predicate buildAnd(){
		return ExpressionUtils.allOf(predicates);
	}
}
