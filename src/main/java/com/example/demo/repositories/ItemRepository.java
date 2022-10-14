package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

    //Listar itens por status (true/false)
    List<Item> findByStatus(boolean status);

    // Buscar um item por Id
    Item findById(long id);

    // Listar itens por Id de usuário
    @Query(value = "SELECT * FROM items i WHERE i.username = :name", nativeQuery =  true)
    List<Item> findByUsername(@Param("name") String name);

    // Listar todos os itens
    List<Item> findAll();

    // Excluir um item
    void deleteById(long id);

    

}
