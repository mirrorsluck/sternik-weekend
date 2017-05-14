package pl.sternik.rcz.weekend.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.rcz.weekend.entities.Gra;
import pl.sternik.rcz.weekend.repositories.springdata.GraRepository;



@Service
@Qualifier("spring-data")
public class KlaserServiceJPAImpl implements KlaserService {

    @Autowired
    private GraRepository bazaDanych;

    @Override
    public List<Gra> findAll() {
        List<Gra> l = new ArrayList<>();
        for (Gra item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Gra> findAllToSell() {
        List<Gra> l = new ArrayList<>();
        for (Gra item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public Optional<Gra> findById(Long id) {
        return Optional.ofNullable(bazaDanych.findByNumerKatalogowy(id));
    }

    @Override
    public Optional<Gra> create(Gra moneta) {
        return Optional.of(bazaDanych.save(moneta));
    }

    @Override
    public Optional<Gra> edit(Gra moneta) {
        return Optional.of(bazaDanych.save(moneta));
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        bazaDanych.delete(id.intValue());
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<Gra> findLatest3() {
        return Collections.emptyList();
    }

	@Override
	public List<Gra> findAllDublety() {
		// TODO Auto-generated method stub
		return null;
	}

}
