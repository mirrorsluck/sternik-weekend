package pl.sternik.rcz.weekend.repositories;

import java.util.List;

import pl.sternik.rcz.weekend.entities.Gra;


public interface GryRepository {
    Gra create(Gra gra) throws GraAlreadyExistsException;
    Gra readById(Long id) throws NoSuchGraException;
    Gra update(Gra gra) throws NoSuchGraException;
    void deleteById(Long id) throws NoSuchGraException;
    List<Gra> findAll();
}