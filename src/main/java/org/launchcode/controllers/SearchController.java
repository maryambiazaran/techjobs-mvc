package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // DONE #1 - Create handler to process search request and display results
    @RequestMapping(value= "results")
    public String searchresults(
            Model model,
            @RequestParam String searchType,
            @RequestParam String searchTerm) {
        searchTerm = searchTerm.trim();

        ArrayList searchResults= new ArrayList();
        if (searchType.toLowerCase().equals("all")) {
            searchResults = JobData.findByValue(searchTerm);
        } else if (!searchType.isEmpty()) {
            searchResults = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs",searchResults);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchType", searchType);
        model.addAttribute("method", "post");



        return "search";
    }
}
