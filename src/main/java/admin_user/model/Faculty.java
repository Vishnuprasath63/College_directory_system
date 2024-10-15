package admin_user.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;
    public String email;
    public String photo;
    public String contactDetails;
   

    // Storing subjects as a collection of strings
    @ElementCollection
    @CollectionTable(
        name = "faculty_subjects",  // Table to store subjects
        joinColumns = @JoinColumn(name = "faculty_id")
    )
    @Column(name = "subject")
    private Set<String> subject;

    public String department;
    public String qualifications;
   

    // One-to-Many relationship with Student entity
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Student> assignedStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Getters and setters are already provided

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public String getContactDetails() { return contactDetails; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }

    public Set<String> getSubject() { return subject; }
    public void setSubject(Set<String> subject) { this.subject = subject; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getQualifications() { return qualifications; }
    public void setQualifications(String qualifications) { this.qualifications = qualifications; }

    public Set<Student> getAssignedStudent() { return assignedStudent; }
    public void setAssignedStudent(Set<Student> assignedStudent) { this.assignedStudent = assignedStudent; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

