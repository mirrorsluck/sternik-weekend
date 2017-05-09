package pl.sternik.rcz.weekend.web.controlers;

import java.util.Date;

import javax.ws.rs.HeaderParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.rcz.weekend.entities.Gra;
import pl.sternik.rcz.weekend.entities.Status;
import pl.sternik.rcz.weekend.repositories.GraAlreadyExistsException;
import pl.sternik.rcz.weekend.repositories.GryRepository;
import pl.sternik.rcz.weekend.repositories.NoSuchGraException;



@Controller
public class WprawkiController {

    @Autowired
    @Qualifier("tablica")
    GryRepository baza;
    
    @RequestMapping(path = "/wprawki", method = RequestMethod.GET)
    public String wprawki(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }

    @GetMapping("/wprawki/{cos}")
    public String wprawki(@PathVariable String cos, ModelMap model) {
        model.addAttribute("cos", cos);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }

    @GetMapping("/wprawki2")
    @ResponseBody
    public String wprawkiParam(@RequestParam("cos") String cosParam, ModelMap model) {
        return "Wprawki z param cos=" + cosParam;
    }
    
    @GetMapping("/wprawki3")
    @ResponseBody
    public String wprawkiHeader(@RequestHeader("User-Agent") String cosParam, ModelMap model) {
        return "Uzywasz przegladarki:=" + cosParam;
    }
    
    @GetMapping(value = "/wprawki/gry/{id}/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Gra> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Gra m;
        try {
            m = baza.readById(id);
            return new ResponseEntity<Gra>(m, HttpStatus.OK);
            
        } catch (NoSuchGraException e) {
            System.out.println(e.getClass().getName());
            m = new Gra();
            m.setNumerKatalogowy(id);
            m.setWydawca("Polska");
            m.setStatus(Status.NOWA);
            m.setPEGI(10L);
            try {
                baza.create(m);
            } catch (GraAlreadyExistsException e1) {
                System.out.println(e1.getClass().getName());
            }
            return new ResponseEntity<Gra>(m, HttpStatus.CREATED);
        }
    }

}
