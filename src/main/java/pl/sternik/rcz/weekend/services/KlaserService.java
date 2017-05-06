package pl.sternik.rcz.weekend.services;

import java.util.List;
import java.util.Optional;

import pl.sternik.rcz.weekend.entities.gra;


public interface KlaserService {
    List<gra> findAll();

    List<gra> findAllToSell();

    Optional<gra> findById(Long id);

    Optional<gra> create(gra gra);

    Optional<gra> edit(gra gra);

    Optional<Boolean> deleteById(Long id);

    List<gra> findLatest3();

	List<gra> findAllDublety();
}