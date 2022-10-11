package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Item;
import com.example.demo.repositories.ItemRepository;

@RestController
@RequestMapping("/api")
public class ItemController{

    // Injetando a interface ItemRepository como dependência para uso dos métodos
    @Autowired
    private ItemRepository itemRepository;


    // Criando um item
    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody Item item){

        Item _item = itemRepository.save(item);

        return new ResponseEntity<Item>(_item, HttpStatus.OK);
    };

    // Buscando um item
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getById(@PathVariable("id") long id){

        Item item = itemRepository.findById(id);
    
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

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item item){

        Item _item = itemRepository.findById(id);

        if (_item == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        _item.setUserId(item.getUserId());
        _item.setName(item.getName());
        _item.setPrice(item.getPrice());
        _item.setDescription(item.getDescription());
        _item.setStatus(item.getStatus());

        // Salvando item editado no DB
        itemRepository.save(_item);

        return new ResponseEntity<Item>(_item, HttpStatus.OK);
    }
}
