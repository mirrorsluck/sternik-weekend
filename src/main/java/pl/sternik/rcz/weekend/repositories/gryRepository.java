package pl.sternik.rcz.weekend.repositories;

import java.util.List;

import pl.sternik.rcz.weekend.entities.gra;


public interface gryRepository {
    gra create(gra gra) throws graAlreadyExistsException;
    gra readById(Long id) throws NoSuchgraException;
    gra update(gra gra) throws NoSuchgraException;
    void deleteById(Long id) throws NoSuchgraException;
    List<gra> findAll();
}