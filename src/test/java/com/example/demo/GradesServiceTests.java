package com.example.demo;

import com.example.demo.models.GradesModel;
import com.example.demo.repo.GradesRepo;
import com.example.demo.services.GradesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GradesServiceTests {
    @Mock
    private GradesRepo repo;

    @InjectMocks
    private GradesService service;

    @Test
    public void getGradesFromRepo(){
        when(repo.getAllGrades()).thenReturn(Arrays.asList(
            new GradesModel("Harry", "Potions", 10),
            new GradesModel("Louis","Charms",43)
        ));
        List<GradesModel> grades = repo.getAllGrades();
        assertEquals("Harry", grades.get(0).getName());
    }

    @Test
    public void lookupStudentByIdTest(){
        GradesModel tempGrade = new GradesModel("Harry", "Potions", 10.0);
        GradesModel tempGrade2 = new GradesModel("Louis","Charms",43.0);
        GradesModel tempGrade3 = new GradesModel("Unknown","Unknown",0.0);
        when(repo.getAllGrades()).thenReturn(Arrays.asList(
                tempGrade,
                tempGrade2
        ));
        Integer indexFound = service.lookupStudentById(tempGrade.getId());
        assertEquals(0,indexFound);
        indexFound = service.lookupStudentById(tempGrade2.getId());
        assertEquals(1,indexFound);
        indexFound = service.lookupStudentById(tempGrade3.getId());
        assertEquals(-1,indexFound);
    }

    @Test
    public void updateGradeTest(){
        GradesModel tempGrade = new GradesModel("Harry", "Potions", 10.0);
        GradesModel tempGradeNew = new GradesModel("Ron", "Charms", 50.0);
        GradesModel tempGradeUpdate = tempGrade;
        tempGradeUpdate.setScore(100.0);

        when(repo.getAllGrades()).thenReturn(Arrays.asList(
                tempGrade
        ));
        //when(repo.getGrade(tempGradeUpdate.getId())).thenReturn(tempGradeUpdate);
        //when(repo.getGrade(tempGrade2.getId())).thenReturn(tempGrade2);
        service.updateGrade(tempGradeNew);
        verify(repo,times(1)).addGrade(tempGradeNew);
        verify(repo,times(0)).update(tempGradeNew,0);

        service.updateGrade(tempGradeUpdate);
        verify(repo,times(1)).update(tempGradeUpdate,0);
        verify(repo,times(0)).addGrade(tempGradeUpdate);

    }
}
