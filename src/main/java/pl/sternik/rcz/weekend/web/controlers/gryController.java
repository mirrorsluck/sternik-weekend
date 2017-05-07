package pl.sternik.rcz.weekend.web.controlers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.rcz.weekend.entities.gra;
import pl.sternik.rcz.weekend.entities.Status;
import pl.sternik.rcz.weekend.services.KlaserService;
import pl.sternik.rcz.weekend.services.NotificationService;

@Controller
public class gryController {

    @Autowired
    // @Qualifier("spring")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @RequestMapping(value = "/gry/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<gra> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            gra gra = result.get();
            model.addAttribute("gra", gra);
            return "gra";
        } else {
            notifyService.addErrorMessage("Cannot find gra #" + id);
            model.clear();
            return "redirect:/gry";
        }
    }

    @RequestMapping(value = "/gry/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<gra> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<gra> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            gra gra = result.get();
            return new ResponseEntity<gra>(gra, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find gra #" + id);
            model.clear();
            return new ResponseEntity<gra>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/gry", params = { "save" }, method = RequestMethod.POST)
    public String savegra(gra gra, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "gra";
        }
        Optional<gra> result = klaserService.edit(gra);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis gry udany");
        else
            notifyService.addErrorMessage("Zapis gry nieudany");
        model.clear();
        return "redirect:/gry";
    }

    @RequestMapping(value = "/gry", params = { "create" }, method = RequestMethod.POST)
    public String creategra(gra gra, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "gra";
        }
        klaserService.create(gra);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej gry udany");
        return "redirect:/gry";
    }

    @RequestMapping(value = "/gry", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final gra gra, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
        return "redirect:/gry";
    }

    @RequestMapping(value = "/gry/create", method = RequestMethod.GET)
    public String showMainPages(final gra gra) {
        gra.setDataNabycia(Calendar.getInstance().getTime());
        return "gra";
    }
}