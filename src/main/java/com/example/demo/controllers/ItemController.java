package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.interfaces.CryptoPriceInterface;
import com.example.demo.interfaces.TodoInterface;
import com.example.demo.models.Item;
import com.example.demo.payload.response.CryptoPriceResponse;
import com.example.demo.payload.response.TodoResponse;
import com.example.demo.repositories.ItemRepository;

@RestController
@RequestMapping("/api")
public class ItemController{

    // Injetando a interface ItemRepository como dependência para uso dos métodos
    @Autowired
    private ItemRepository itemRepository;

    // Injetando a interface CryptoPriceInterface como dependência para consumo da API de TODOS
    @Autowired
    private TodoInterface todoInterface;

    // Injetando a interface CryptoPriceInterface como dependência para consumo da API de preços de criptos
    @Autowired
    private CryptoPriceInterface cryptoPriceInterface;

    // Criando um item
    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody Item item){

        TodoResponse todoResponse = todoInterface.getTodoById(item.getTodoId());

        CryptoPriceResponse criptoPriceResponse = cryptoPriceInterface.getPriceBySymbol(item.getSymbol());

        // System.out.println("--------------------------Retorno da API de criptomoeda--------------------------");
        // System.out.println(criptoPriceResponse.getPrice());
        // System.out.println("--------------------------------------------------------------------------------------");

        // System.out.println("--------------------------Retorno da API do JSON Placeholder--------------------------");
        // System.out.println(todoResponse.getId());
        // System.out.println(todoResponse.getUserId());
        // System.out.println(todoResponse.getTitle());
        // System.out.println(todoResponse.isCompleted());
        // System.out.println("--------------------------------------------------------------------------------------");

        // Dado de preço recebido da API de criptomoedas sendo atribuido ao atributo de preço do item
        item.setPrice(criptoPriceResponse.getPrice());

        // Dados recebidos da API do JSON Placeholder sendo atribuidos ao novo item criado.
        item.setDescription(todoResponse.getTitle());
        item.setStatus(todoResponse.isCompleted());

        Item _item = itemRepository.save(item);

        return new ResponseEntity<Item>(_item, HttpStatus.OK);
    };

    // Buscando um item
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getById(@PathVariable("id") long id){

        Item item = itemRepository.findById(id);

        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
    
    // Listando todos os itens
    @GetMapping("/items")
    public ResponseEntity<List<Item>> listItems(){

        List<Item> items = itemRepository.findAll();

        if(items.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    // Listando itens por id de usuário
    @GetMapping("/users/{u_id}/items")
    public ResponseEntity<List<Item>> listItemsByUserId(@PathVariable("u_id") long id){

        List<Item> items = itemRepository.findByUser(id); 

        if(items.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    // Listando itens por status
    @GetMapping("/items/status") // /items/status?status=true/false
    public ResponseEntity<List<Item>> listItemsByStatus(@RequestParam boolean status){

        List<Item> items = itemRepository.findByStatus(status);

        if(items.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    // Atualizando um item
    @PutMapping("/items/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable("id") long id, @RequestBody Item item){

        Item _item = itemRepository.findById(id);

        if (_item == null) {
            return new ResponseEntity<Object>("Não foi possível executar a operação", HttpStatus.NOT_FOUND);
        }

        _item.setUserId(item.getUserId());
        _item.setName(item.getName());
        _item.setPrice(item.getPrice());
        _item.setDescription(item.getDescription());
        _item.setStatus(item.getStatus());

        // Salvando item editado no DB
        itemRepository.save(_item);

        return new ResponseEntity<Object>(_item, HttpStatus.OK);
    }

    // Removendo um item
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") long id){

        try {
            itemRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Não foi possível executar a operação", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Object>("Item excluído com sucesso!", HttpStatus.OK);

    }
}
