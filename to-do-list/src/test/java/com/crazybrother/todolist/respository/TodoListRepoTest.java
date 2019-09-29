package com.crazybrother.todolist.respository;

import com.crazybrother.todolist.entity.ToDoListEntity;
import com.crazybrother.todolist.entity.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TodoListRepoTest {

    @Autowired
    TodoListRepo todoListRepo;

    @Autowired
    TodoRepo todoRepo;

    ToDoListEntity toDoList;

    List<Todo> todos = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        todos.clear();
        todoRepo.deleteAll();
        todoListRepo.deleteAll();
    }

    private Todo createTodoAndAddtoTodos(String desc, String severity) {
        Todo tempTodo = new Todo(desc, severity);
        todos.add(tempTodo);
        return tempTodo;
    }

    @Test
    public void testCreateAndGetATodoListWithTodosOf2Todo() {
        // create 2 todos and throw them to todos
        Todo todo1 = createTodoAndAddtoTodos("mother's birthday", "important");
        Todo todo2 = createTodoAndAddtoTodos("brother's birthday", "unimportant");

        // create a todolistEntity with the todos
        Date date1 = Date.from(LocalDate.of(2000, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(LocalDate.of(2000, 10, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        toDoList= new ToDoListEntity(date1, date2);
        toDoList.setTodos(todos);

        // bi directional relation from todo to todolistEnyity
        todos.stream().forEach(todo->{
            todo.setTodolist(toDoList);
        });

        // test save
        toDoList = todoListRepo.save(toDoList);

        // test get
        ToDoListEntity toDoList2 = todoListRepo.findById(toDoList.getId()).get();
        assertEquals(toDoList.getEndDate(),toDoList2.getEndDate());
        assertEquals(toDoList.getBeginDate(),toDoList2.getBeginDate());
        assertEquals(toDoList.getTodos().get(0).getDescription(),toDoList2.getTodos().get(0).getDescription());
        assertEquals(toDoList.getTodos().get(0).getSeverity(),toDoList2.getTodos().get(0).getSeverity());
        assertEquals(toDoList.getTodos().size(),toDoList2.getTodos().size());
    }

    @Test
    public void testFindTodoWithMother(){

        // create 2 todos and throw them to todos
        Todo todo1 = createTodoAndAddtoTodos("mother's birthday", "important");
        Todo todo2 = createTodoAndAddtoTodos("brother's birthday", "unimportant");

        // create a todolistEntity with the todos
        Date date1 = Date.from(LocalDate.of(2000, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(LocalDate.of(2000, 10, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        toDoList= new ToDoListEntity(date1, date2);
        toDoList.setTodos(todos);

        // bi directional relation from todo to todolistEnyity
        todos.stream().forEach(todo->{
            todo.setTodolist(toDoList);
        });

        // test save
        toDoList = todoListRepo.save(toDoList);

        List<Todo> motherTodo = todoRepo.findDescriptionWithMother();
        assertEquals(1,motherTodo.size());
    }


}