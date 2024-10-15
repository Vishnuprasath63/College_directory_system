package admin_user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin_user.model.Faculty;
import admin_user.model.Student;
import admin_user.service.FacultyService;
import admin_user.service.StudentService;

import java.security.Principal;
import java.util.List;

@Controller
public class FacultyController {

    @Autowired
    private FacultyService facultyService;


    @GetMapping("/faculty/dashboard")
    public String facultyDashboard(Principal principal, Model model) {
        try {
            // Get the logged-in user's email from the principal
            String email = principal.getName();

            // Fetch the student by the user's email
            Faculty faculty =facultyService.getFacultyByEmail(email);
            if (faculty == null) {
                model.addAttribute("error", "Faculty not found for this ID");
                return "error"; // Render an error view
            }
            // Add student to the model
            model.addAttribute("faculty", faculty);
            return "faculty/dashboard"; // Ensure this template exists
        } catch (RuntimeException e) {
            // Log and handle the exception, show an error page
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("error", "faculty not found for this email");
            return "error"; // Render an error view
        }
    }


}
