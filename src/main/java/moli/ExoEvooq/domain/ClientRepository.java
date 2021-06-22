package moli.ExoEvooq.domain;

import moli.ExoEvooq.infrastructure.persistance.ClientEntity;

import java.io.IOException;


public interface ClientRepository{

    Client findById(Long id);
    void save(Client clientToSave) throws IOException;

}
