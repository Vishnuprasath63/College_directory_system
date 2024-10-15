package admin_user.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import admin_user.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Admin dashboard view
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    // Faculty list view
    @GetMapping("/admin/faculty-list")
    public String facultyList() {
        return "faculty-list"; // Replace with logic to fetch actual faculty data
    }

    // Student list view
    @GetMapping("/admin/student-list")
    public String studentList() {
        return "student-list"; // Replace with logic to fetch actual student data
    }

    // Remove faculty
    @PostMapping("/admin/faculty/remove/{id}")
    public String removeFaculty(@PathVariable("id") Long facultyId, RedirectAttributes redirectAttributes) {
        try {
            adminService.removeFaculty(facultyId);
            redirectAttributes.addFlashAttribute("message", "Faculty with ID " + facultyId + " removed successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/faculty-list";
    }

    // Remove student
    @PostMapping("/admin/student/remove/{id}")
    public String removeStudent(@PathVariable("id") Long studentId, RedirectAttributes redirectAttributes) {
        try {
            adminService.removeStudent(studentId);
            redirectAttributes.addFlashAttribute("message", "Student with ID " + studentId + " removed successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/student-list";
    }
}

