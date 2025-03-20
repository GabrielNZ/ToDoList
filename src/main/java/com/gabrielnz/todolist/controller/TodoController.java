package com.gabrielnz.todolist.controller;

import com.gabrielnz.todolist.entity.Todo;
import com.gabrielnz.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> list(){
        return ResponseEntity.ok().body(todoService.list());
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@RequestBody @Valid Todo todo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(todo.getId()).toUri();
        return ResponseEntity.created(uri).body(todoService.create(todo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Todo>> update(@PathVariable Long id, @RequestBody Todo todo) {
        return ResponseEntity.ok().body(todoService.update(id,todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Todo>> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(todoService.delete(id));
    }
}
