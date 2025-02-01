package com.learn.graphqlsecurity.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.learn.graphqlsecurity.model.Coffee;
import com.learn.graphqlsecurity.model.Size;
import com.learn.graphqlsecurity.service.CoffeeService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CoffeeController
{
	private final CoffeeService coffeeService;

	public CoffeeController(CoffeeService coffeeService)
	{
		this.coffeeService = coffeeService;
	}

	@Secured("ROLE_USER")
	@QueryMapping(value = "allCoffee")
	public List<Coffee> findAll()
	{
		log.info("All Coffees: {}", coffeeService.findAll());
		return coffeeService.findAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@MutationMapping(value = "createCoffee")
	public Coffee create(@Argument String name, @Argument Size size)
	{
		return coffeeService.create(name, size);
	}
}