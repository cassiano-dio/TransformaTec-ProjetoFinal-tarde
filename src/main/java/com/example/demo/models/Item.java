package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long userId;

    private String username;

    //Valores de campos inseridos pelo usuário
    private String name;

    // Valor virá da API de criptomoedas
    private Double price;

    // Id do Todo para pesquisa na API do JSON Placeholder
    private Long todoId;

    // Símbolo para pesquisa de preço criptomeda
    private String symbol;

    // Os valores desses campos virão da api do JSON Placeholder (OpenFeign)
    private String description;
    private Boolean status;

    public Item() {}

    public Item(String username, String name, Double price, String description, Boolean status) {
        this.username = username;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
