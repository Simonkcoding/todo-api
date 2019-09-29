package com.crazybrother.todolist.controller;

import com.crazybrother.todolist.entity.ToDoListEntity;
import com.crazybrother.todolist.entity.Todo;
import com.crazybrother.todolist.respository.TodoListRepo;
import com.crazybrother.todolist.respository.TodoRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoListController.class)
public class TodoListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TodoListRepo todoListRepo;

    @MockBean
    TodoRepo todoRepo;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldGetTodoListById() throws Exception {

        Date date1 = Date.from(LocalDate.of(2000, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(LocalDate.of(2000, 10, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        ToDoListEntity todoObj = new ToDoListEntity(date1,date2);

        List<Todo> todos = new ArrayList<>();

        Todo todo1 = new Todo("mother's birthday", "important");
        Todo todo2 = new Todo("lunglung's birthday", "null");

        todos.add(todo1);
        todos.add(todo2);

        todoObj.setTodos(todos);

        String outputJson = mapper.writeValueAsString(todoObj);

        when(todoListRepo.findById(1)).thenReturn(java.util.Optional.of(todoObj));

        this.mockMvc.perform(get("/todolist/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
}