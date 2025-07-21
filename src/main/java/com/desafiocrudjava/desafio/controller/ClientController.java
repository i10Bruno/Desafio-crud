package com.desafiocrudjava.desafio.controller;

import com.desafiocrudjava.desafio.dto.ClientDTO;
import com.desafiocrudjava.desafio.entities.Client;
import com.desafiocrudjava.desafio.repositories.ClientRepositories;
import com.desafiocrudjava.desafio.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired

    private ClientService clientService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO dto = clientService.findByid(id);
        return  ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){

        Page<ClientDTO> dto = clientService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
    public  ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto){
        dto=clientService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update ( @PathVariable Long id , @RequestBody ClientDTO dto){

        dto =clientService.update(id, dto);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id ){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
