package pl.sternik.rcz.weekend.services;

import java.util.List;
import java.util.Optional;

import pl.sternik.rcz.weekend.entities.Gra;


public interface KlaserService {
    List<Gra> findAll();

    List<Gra> findAllToSell();

    Optional<Gra> findById(Long id);

    Optional<Gra> create(Gra gra);

    Optional<Gra> edit(Gra gra);

    Optional<Boolean> deleteById(Long id);

    List<Gra> findLatest3();

	List<Gra> findAllDublety();
}