package com.desafiocrudjava.desafio.service;

import com.desafiocrudjava.desafio.dto.ClientDTO;
import com.desafiocrudjava.desafio.entities.Client;
import com.desafiocrudjava.desafio.repositories.ClientRepositories;
import com.desafiocrudjava.desafio.service.exceptions.DatabaseException;
import com.desafiocrudjava.desafio.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {


    @Autowired
    private ClientRepositories clientRepositories;
    @Transactional(readOnly = true)
    public ClientDTO findByid(Long id){
        Optional<Client> result = clientRepositories.findById(id);
        Client client =result.orElseThrow(()-> new ResourceNotFoundException("recurso não encontrado"));

        return new  ClientDTO(client);

    }




    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = clientRepositories.findAll(pageable);
        return result.map(x-> new ClientDTO(x));


    }


    public ClientDTO insert(ClientDTO dto){

        Client entity= new Client();
        CopyEntityToDto(entity, dto);

        return  new ClientDTO(entity);


    }
    public ClientDTO update (Long id, ClientDTO dto) {

        try {
            Client entity = clientRepositories.getReferenceById(id);
            CopyEntityToDto(entity, dto);
            return new ClientDTO(entity);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("recurso não encontrado");
        }
    }

    public void  delete(Long id){

        if (!clientRepositories.existsById(id)){
            throw  new ResourceNotFoundException(" recurso não encontrado");


        }
        try {
            clientRepositories.deleteById(id);
        } catch (DataIntegrityViolationException e){

            throw  new DatabaseException("falha de integridade referencail");

        }
    }








    public void CopyEntityToDto (Client entity, ClientDTO dto){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity = clientRepositories.save(entity);


    }


}
