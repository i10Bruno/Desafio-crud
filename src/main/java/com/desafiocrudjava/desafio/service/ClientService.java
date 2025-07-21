package com.desafiocrudjava.desafio.service;

import com.desafiocrudjava.desafio.repositories.ClientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {


    @Autowired
    private ClientRepositories clientRepositories;


}
