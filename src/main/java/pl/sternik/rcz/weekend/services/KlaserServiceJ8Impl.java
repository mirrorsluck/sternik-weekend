package pl.sternik.rcz.weekend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pl.sternik.rcz.weekend.entities.gra;
import pl.sternik.rcz.weekend.entities.Status;
import pl.sternik.rcz.weekend.repositories.graAlreadyExistsException;
import pl.sternik.rcz.weekend.repositories.gryRepository;
import pl.sternik.rcz.weekend.repositories.NoSuchgraException;


@Service
@Primary
public class KlaserServiceJ8Impl implements KlaserService {

    @Autowired
    @Qualifier("lista")
    private gryRepository gry;

    @Override
    public List<gra> findAll() {
        return gry.findAll();
    }

    @Override
    public List<gra> findLatest3() {
        return gry.findAll().stream().sorted((a, b) -> b.getDataNabycia().compareTo(a.getDataNabycia())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<gra> findById(Long id) {
        try {
            return Optional.of(gry.readById(id));
        } catch (NoSuchgraException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<gra> create(gra gra) {
        try {
            return Optional.of(gry.create(gra));
        } catch (graAlreadyExistsException e) {
            try {
                return Optional.of(gry.readById(gra.getNumerKatalogowy()));
            } catch (NoSuchgraException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<gra> edit(gra gra) {
        try {
            return Optional.of(gry.update(gra));
        } catch (NoSuchgraException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            gry.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchgraException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<gra> findAllToSell() {
        return gry.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DO_SPRZEDANIA))
                .collect(Collectors.toList());
    }

	@Override
	public List<gra> findAllDublety() {
	      return gry.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DUBLET))
	                .collect(Collectors.toList());
	}
}
