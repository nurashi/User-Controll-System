package kz.applicationweb.usercontrollsystemoop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kz.applicationweb.usercontrollsystemoop.dto.request.CreateStudentRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.StudentResponse;
import kz.applicationweb.usercontrollsystemoop.model.user.Student;
import kz.applicationweb.usercontrollsystemoop.repository.StudentRepository;
import kz.applicationweb.usercontrollsystemoop.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    private final StudentRepository studentRepository;
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentRepository studentRepository, 
                             StudentServiceImpl studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest request) {
        Student Student = studentService.createStudent(request);
        return new StudentResponse(Student);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        Optional<Student> item = studentRepository.findById(id);
        return item.map(StudentResponse::new).orElse(null);
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, 
                                          @Valid @RequestBody CreateStudentRequest request) {
        Student updatedStudent = studentService.updateStudent(id, request);
        return new StudentResponse(updatedStudent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}