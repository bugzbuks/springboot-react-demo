package com.example.demo.services;

import com.example.demo.controllers.GradeController;
import com.example.demo.models.GradesModel;
import com.example.demo.repo.GradesRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GradesService {
    private static final Logger log = LogManager.getLogger(GradesService.class);

    @Autowired
    GradesRepo repo;

    public List<GradesModel> getAllGrades(){
        return repo.getAllGrades();
    }

    public GradesModel getGrade(UUID id){
        Integer index = lookupStudentById(id);
        if (index > -1)
            return getAllGrades().get(index);
        else
            return null;
    }

    public void addGrade(GradesModel newGrade){
        repo.addGrade(newGrade);
    }

    public Boolean updateGrade(GradesModel newGrade)
    {
        Integer index = lookupStudentById(newGrade.getId());
        if (index > -1)
            return repo.update(newGrade,index);
        else
            return repo.addGrade(newGrade);
    }

    public Boolean deleteGrade(Integer index){
        return repo.deleteGrade(index);
    }

    /**
     * Search for ta student by name in the list of students
     * @param targetId the UUID to search for
     * @return Index in the list where the student can be found. It returns -1 if not found
     */
    public Integer lookupStudentById(UUID targetId) {
        List<GradesModel> listOfStudents = getAllGrades();
        for (int i = 0; i < listOfStudents.size() && targetId != null; i++) {
            if (listOfStudents.get(i).getId().equals(targetId)) {
                log.info( "Found student = " + listOfStudents.get(i).getName());
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param name
     * @return
     */
    public GradesModel getGradeByName(String name) {
        List<GradesModel> listOfStudents = getAllGrades();
        for (int i = 0; i < listOfStudents.size() && name != null; i++) {
            if (listOfStudents.get(i).getName().equalsIgnoreCase(name)) {
                log.info( "Found student = " + listOfStudents.get(i).getName());
                return listOfStudents.get(i);
            }
        }
        return null;
    }
}
