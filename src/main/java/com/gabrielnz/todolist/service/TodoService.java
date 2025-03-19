package com.gabrielnz.todolist.service;

import com.gabrielnz.todolist.entity.Todo;
import com.gabrielnz.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> list() {
        Sort sort = Sort.by("priority").descending().and(Sort.by("name").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }


    public List<Todo> update(Long id, Todo todo) {
        Todo todoReference = todoRepository.getReferenceById(id);
        todoReference.setId(todo.getId());
        todoReference.setDone(todo.isDone());
        todoReference.setName(todo.getName());
        todoReference.setPriority(todo.getPriority());
        todoReference.setDescription(todo.getDescription());
        todoRepository.save(todoReference);
        return list();
    }

    public List<Todo> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }
}
