package com.ifpb.sistemaLogin.sistema.login.repository;

import com.ifpb.sistemaLogin.sistema.login.model.entities.Session;
import org.springframework.data.repository.CrudRepository;


public interface SessionRepository extends CrudRepository<Session, String> {
}
