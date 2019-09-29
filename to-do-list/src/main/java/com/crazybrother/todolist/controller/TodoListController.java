package com.crazybrother.todolist.controller;

import com.crazybrother.todolist.entity.ToDoListEntity;
import com.crazybrother.todolist.respository.TodoListRepo;
import com.crazybrother.todolist.respository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/todolist")
public class TodoListController {
    @Autowired
    private TodoListRepo listRepo;

    @Autowired
    private TodoRepo entityRepo;

    @GetMapping("/{id}")
    public Optional<ToDoListEntity> getTodoListById(@PathVariable int id){

        return listRepo.findById(id);
    }
}
