package com.desafiocrudjava.desafio.repositories;

import com.desafiocrudjava.desafio.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepositories  extends JpaRepository<Client,Long> {
}
