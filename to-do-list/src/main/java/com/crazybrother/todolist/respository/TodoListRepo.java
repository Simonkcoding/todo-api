package com.crazybrother.todolist.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crazybrother.todolist.entity.ToDoListEntity;

@Repository
public interface TodoListRepo extends JpaRepository<ToDoListEntity, Integer> {

}
