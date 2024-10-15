package admin_user.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import admin_user.model.Student;
import admin_user.model.User;



public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUser(User user);  // Add this method to retrieve a student by user
    List<Student> findByNameContainingOrDepartmentContaining(String name, String department);
}
