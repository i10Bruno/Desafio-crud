package com.desafiocrudjava.desafio.controller;

import com.desafiocrudjava.desafio.dto.ClientDTO;
import com.desafiocrudjava.desafio.entities.Client;
import com.desafiocrudjava.desafio.repositories.ClientRepositories;
import com.desafiocrudjava.desafio.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public  ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto){
        dto=clientService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update ( @PathVariable Long id, @Valid @RequestBody ClientDTO dto){

        dto =clientService.update(id, dto);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id ){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
