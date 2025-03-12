package com.qbe.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.qbe.persistence.User;
import com.qbe.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public List<User> findByFirstNameEndsWith(String ending) {

		User user = User.builder().firstName(ending).build();
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreNullValues()
				.withMatcher("firstName", match -> match.endsWith().ignoreCase(true));

		Example example = Example.of(user, matcher);
		return userRepository.findAll(example);
	}

	public List<User> findByLastNameContains(String subString) {
		User user = User.builder().lastName(subString).build();
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains());

		Example example = Example.of(user, matcher);
		return userRepository.findAll(example);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public List<User> findByFirstName(String firstName) {
		User user = User.builder().firstName(firstName).build();
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());

		Example example = Example.of(user, matcher);
		return userRepository.findAll(example);
	}

	public List<User> findDisabledInTimePeriodAndEmailContains(String email, LocalDate fromDate, LocalDate toDate) {
		User user = User.builder().email(email).build();
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains());

		Example example = Example.of(user, matcher);
		int page = 0;
		int size = 100;
		String sortField = "createTimestamp";
		var sortDirection = Sort.Direction.ASC;
		Page<User> results = userRepository.findAll(getSpecAndExample(fromDate,toDate,example), PageRequest.of(page, size, Sort.by(sortDirection, sortField)));
		return results.getContent();
	}

	private Specification<User> getSpecAndExample(LocalDate fromDate, LocalDate toDate, Example<User> example){
		return (root, query, builder)->{
			final List<Predicate> predicates = new ArrayList<>();
			predicates.add(builder.lessThanOrEqualTo(root.get("disableTimestamp"), toDate));
			predicates.add(builder.greaterThanOrEqualTo(root.get("disableTimestamp"), fromDate));
			if (example != null){
				predicates.add(QueryByExamplePredicateBuilder.getPredicate(root,builder,example));
			}
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

}
