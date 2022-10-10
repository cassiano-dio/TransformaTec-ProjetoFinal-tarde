package com.example.demo.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.payload.response.TodoResponse;

//Interface de consulta de Todos na API do JSON Placeholder
@FeignClient(url = "https://jsonplaceholder.typicode.com/todos", name="jsonplaceholder")
public interface TodoInterface {
    
    @GetMapping("/{id}")
    TodoResponse getTodoById(@PathVariable("id") long id);

}
