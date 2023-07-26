package com.todolist.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.todolist.todolist.entity.Todo;

@SpringBootTest
class TodoListApplicationTests {

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("New list", "Buy picks", true, '2');
	}

	@Test
	void testCreateTodoFailure() {
	}

}
