package com.example.task4itunes.controllers;
import com.example.task4itunes.data_access.CustomerRepository;
import com.example.task4itunes.models.SearchResult;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerThy {
    CustomerRepository crep = new CustomerRepository();

    //get random artists
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRandomArtist(Model model){
        var randomArtist = crep.getRandomArtist();
        System.out.println(randomArtist.get(0).getName());
        model.addAttribute("randomArtists", crep.getRandomArtist());
        model.addAttribute("randomSongs", crep.getRandomSong());
        model.addAttribute("randomGenres", crep.getRandomGenre());
        return "index";
    }

    //query a song, not finalized and not working...
    @RequestMapping(value = "/{name}")
    public String getSongInfo(@PathVariable String name, Model model){
        model.addAttribute("customer", crep.getSongInfo(name));
        return "viewSearchResults";
    }
}
