package pl.sternik.rcz.weekend.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.rcz.weekend.entities.gra;
import pl.sternik.rcz.weekend.repositories.graAlreadyExistsException;
import pl.sternik.rcz.weekend.repositories.gryRepository;
import pl.sternik.rcz.weekend.repositories.NoSuchgraException;


@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private gryRepository bazaDanych;

    @Override
    public List<gra> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<gra> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<gra> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchgraException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<gra> create(gra gra) {
        try {
            return Optional.of(bazaDanych.create(gra));
        } catch (graAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<gra> edit(gra gra) {
        try {
            return Optional.of(bazaDanych.update(gra));
        } catch (NoSuchgraException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchgraException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<gra> findLatest3() {
        return Collections.emptyList();
    }

	@Override
	public List<gra> findAllDublety() {
		  return bazaDanych.findAll();
	}

}
