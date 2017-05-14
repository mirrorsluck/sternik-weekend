package pl.sternik.rcz.weekend.web.controlers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.sternik.rcz.weekend.entities.Gra;
import pl.sternik.rcz.weekend.entities.Status;
import pl.sternik.rcz.weekend.services.KlaserService;
import pl.sternik.rcz.weekend.services.NotificationService;


@Controller
public class KlaserController {

    @Autowired
    @Qualifier("spring-data")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notificationService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @ModelAttribute("gamesAll")
    public List<Gra> populateGames() {
        return this.klaserService.findAll();
    }

    @ModelAttribute("gamesToSell")
    public List<Gra> populateGamesToSell() {
        return this.klaserService.findAllToSell();
    }
    
    @ModelAttribute("gamesDublety")
    public List<Gra> populateGamesDublety() {
        return this.klaserService.findAllDublety();
    }


    @ModelAttribute("gamesLast3")
    public List<Gra> populateLast3Games() {
        return this.klaserService.findLatest3();
    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/gry", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages",  notificationService.getNotificationMessages());
        return "klaser";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "tosell";
    }

    @RequestMapping("/dublety")
    public String showDubletyPage() {
        return "dublety";
    }
}
