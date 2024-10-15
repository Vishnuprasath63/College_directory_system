package admin_user.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import admin_user.repositories.FacultyRepository;
import admin_user.repositories.StudentRepository; 

@Service
public class AdminService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Remove a faculty member by their ID
    @Transactional
    public void removeFaculty(Long facultyId) {
        if (facultyRepository.existsById(facultyId)) {
            facultyRepository.deleteById(facultyId);
        } else {
            throw new IllegalArgumentException("Faculty with ID " + facultyId + " not found.");
        }
    }

    // Remove a student by their ID
    @Transactional
    public void removeStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        } else {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found.");
        }
    }
}

