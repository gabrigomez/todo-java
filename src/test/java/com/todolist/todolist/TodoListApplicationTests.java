package com.todolist.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.todolist.todolist.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodoListApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("New list", "Buy picks", true, 1);

		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()     //make the request
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].name").isEqualTo(todo.getName())
			.jsonPath("$[0].description").isEqualTo(todo.getDescription())
			.jsonPath("$[0].finished").isEqualTo(todo.isFinished())
			.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}


	@Test
	void testCreateTodoFailure() {
		var todoInvalid = new Todo("", "Make barbecue", false, 1);

		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todoInvalid)
			.exchange()     //make the request
			.expectStatus().isBadRequest();
	}

}
