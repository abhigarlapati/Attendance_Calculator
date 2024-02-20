package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class AttendanceCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceCalculatorApplication.class, args);
	}
	
	@GetMapping("/")
    public String showInputAttendancePage(Model model) {
        return "index";
    }
    
    @PostMapping("/calculate-attendance")
    public String calculateAttendance(
            @RequestParam(name = "lecture", required = false) Integer lecture,
            @RequestParam(name = "skill", required = false) Integer skill,
            @RequestParam(name = "lab", required = false) Integer lab,
            @RequestParam(name = "tutorial", required = false) Integer tutorial,
            Model model) {
        
        double lectureWeight = 100;
        double skillWeight = 25;
        double labWeight = 50;
        double tutorialWeight = 25;

        double totalWeight = lectureWeight + skillWeight + labWeight + tutorialWeight;
        double totalScore = 0;
        
        if(lecture ==null)
        {
        	totalWeight-=lectureWeight;
        }
        if(skill ==null) 
        {
        	totalWeight-=skillWeight;
        }
        if(lab ==null)
        {
        	totalWeight-=labWeight;
        }
        if(tutorial ==null)
        {
        	totalWeight-=tutorialWeight;
        }

        if (lecture != null) {
            totalScore += lecture * (lectureWeight / 100);
        }
        if (skill != null) {
            totalScore += skill * (skillWeight / 100);
        }
        if (lab != null) {
            totalScore += lab * (labWeight / 100);
        }
        if (tutorial != null) {
            totalScore += tutorial * (tutorialWeight / 100);
        }

        double attendancePercentage = (totalScore / totalWeight) * 100;
        
        model.addAttribute("attendancePercentage", attendancePercentage);
        return "result";
    }

}
