package com.example.demo.repo;

import com.example.demo.controllers.GradeController;
import com.example.demo.models.GradesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class GradesRepo {
    private static final Logger log = LogManager.getLogger(GradesRepo.class);
    private List<GradesModel> studentGrades = new ArrayList<>();

    public GradesRepo() {
        this.studentGrades.add(new GradesModel("Louis","Potions",55));
        this.studentGrades.add(new GradesModel("Liam","Charms",66));
        this.studentGrades.add(new GradesModel("Luke","Force Classes",77));
    }

    public List<GradesModel> getAllGrades(){
        return studentGrades;
    }

    public GradesModel getGrade(UUID id){
        for (int i = 0; i < getAllGrades().size() && id != null; i++) {
            if (getAllGrades().get(i).getId().equals(id)) {
                log.info( "Found student = " + getAllGrades().get(i).getId());
                getAllGrades().get(i);
            }
        }
        return null;
    }

    public Boolean addGrade(GradesModel newGrade){
        return getAllGrades().add(newGrade);
    }

    public Boolean update(GradesModel newGrade, Integer index)
    {
        if(index > -1 && index < getAllGrades().size()){
            getAllGrades().set(index,newGrade);
            return true;
        }
        return false;
    }

    public Boolean deleteGrade(Integer index){
        if(getAllGrades().size()<index && index > -1){
            getAllGrades().remove(index);
            return true;
        }
        return false;
    }

    public Boolean deleteGrade(GradesModel grade){
        if(grade != null){
            return getAllGrades().remove(grade);
        }
        return false;
    }

}
