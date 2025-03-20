package com.gabrielnz.todolist.service;

import com.gabrielnz.todolist.entity.Todo;
import com.gabrielnz.todolist.repository.TodoRepository;
import com.gabrielnz.todolist.service.exception.DataBaseException;
import com.gabrielnz.todolist.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public List<Todo> list() {
        Sort sort = Sort.by("priority").ascending().and(Sort.by("name").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }


    public List<Todo> update(Long id, Todo todo) {
        if(!todoRepository.existsById(id)){
            throw new NotFoundException(id);
        }
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
        try {
            if (!todoRepository.existsById(id)) {
                throw new NotFoundException(id);
            }
            todoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
        return list();
    }
}
