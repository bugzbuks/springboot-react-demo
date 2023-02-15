package com.example.demo.controllers;

import com.example.demo.models.GradesModel;
import com.example.demo.services.GradesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class GradeController {

    private static final Logger log = LogManager.getLogger(GradeController.class);

    @Autowired
    private GradesService services;

    public GradeController() {
    }

    @GetMapping("/sumgrades")
    public String sumGrades(@RequestParam List<Integer> score){
        float results = score.stream().reduce(0,(score1,score2) -> score1 + score2)/score.size();
        return "You got graded - " + results;
    }

    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("studentGrades",services.getAllGrades());
        return "grades";
    }

    @PostMapping("/submitGrades")
    public String submitGrade(@Valid @ModelAttribute("grades") GradesModel grades, BindingResult results){
        //Validate inputs
        if(results.hasErrors()) return "form";

        Boolean success = services.updateGrade(grades);
        if(!success) return "form";

        return "redirect:grades";
    }

    @PostMapping("/postGrade")
    @ResponseBody
    public ResponseEntity<?> postGrade(@RequestBody @Valid GradesModel grades,
                                                BindingResult results){
        //Validate inputs
        if(results.hasErrors()) {
            return new ResponseEntity<>("First error found = " + results.getAllErrors().get(0).toString(), HttpStatus.NOT_ACCEPTABLE);
        } else
        {
            Boolean success = services.updateGrade(grades);
            if(!success) {
                return new ResponseEntity<>("Failed to add the new grade", HttpStatus.INTERNAL_SERVER_ERROR);
            } else
            {
                return new ResponseEntity<>(grades, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/addGrades")
    public String addGrade(Model model){
        GradesModel grade = new GradesModel() ;
        model.addAttribute("grades",grade  );
        return "addGrades";
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required=false) UUID targetId) {
        log.info( "Getting ID = " + targetId);
        GradesModel grade = services.getGrade(targetId);
        model.addAttribute("grades",grade != null ? grade : new GradesModel() );
        return "form";
    }

    @GetMapping("/positiveMessage")
    public String getForm() {
        return "positiveMessage";
    }

    @GetMapping("/grades/{name}")
    public ResponseEntity<GradesModel> getGradeByName(@PathVariable String name) {
        GradesModel result = services.getGradeByName(name);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}