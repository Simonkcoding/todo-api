package com.crazybrother.todolist.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crazybrother.todolist.entity.Todo;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Integer> {

    @Query("Select x from Todo x where x.description like '%mother%'")
    List<Todo> findDescriptionWithMother();

}
