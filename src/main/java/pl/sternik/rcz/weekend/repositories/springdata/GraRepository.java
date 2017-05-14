package pl.sternik.rcz.weekend.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.sternik.rcz.weekend.entities.Gra;
import pl.sternik.rcz.weekend.repositories.GryRepository;


@Repository
public interface GraRepository 
         extends JpaRepository<Gra, Integer>{
    public Gra findByNumerKatalogowy(Long id);
}
