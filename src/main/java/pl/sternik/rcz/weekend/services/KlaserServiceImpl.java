package pl.sternik.rcz.weekend.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.rcz.weekend.entities.Gra;
import pl.sternik.rcz.weekend.repositories.GraAlreadyExistsException;
import pl.sternik.rcz.weekend.repositories.GryRepository;
import pl.sternik.rcz.weekend.repositories.NoSuchGraException;


@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private GryRepository bazaDanych;

    @Override
    public List<Gra> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Gra> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Gra> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchGraException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Gra> create(Gra gra) {
        try {
            return Optional.of(bazaDanych.create(gra));
        } catch (GraAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Gra> edit(Gra gra) {
        try {
            return Optional.of(bazaDanych.update(gra));
        } catch (NoSuchGraException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchGraException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Gra> findLatest3() {
        return Collections.emptyList();
    }

	@Override
	public List<Gra> findAllDublety() {
		  return bazaDanych.findAll();
	}

}
