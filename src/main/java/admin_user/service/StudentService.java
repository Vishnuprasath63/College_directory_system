package admin_user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import admin_user.model.Faculty;
import admin_user.model.Student;
import admin_user.model.User;
import admin_user.repositories.FacultyRepository;
import admin_user.repositories.StudentRepository;
import admin_user.repositories.UserRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    // Fetch a student by user email
    public Student getStudentByEmail(String email) {
        // Fetch the user by email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found for email: " + email );
        }

        // Fetch the student by user ID
        return studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student not found for user: " + user.getId()));
    }

    public List<Student> searchStudents(String name, String department) {
        return studentRepository.findByNameContainingOrDepartmentContaining(name, department);
    }
}