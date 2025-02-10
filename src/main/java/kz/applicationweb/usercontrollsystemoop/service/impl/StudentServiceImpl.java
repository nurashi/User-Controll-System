package kz.applicationweb.usercontrollsystemoop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kz.applicationweb.usercontrollsystemoop.dto.request.CreateStudentRequest;
import kz.applicationweb.usercontrollsystemoop.model.user.Student;
import kz.applicationweb.usercontrollsystemoop.repository.StudentRepository;
import kz.applicationweb.usercontrollsystemoop.service.StudentService;
import kz.applicationweb.usercontrollsystemoop.util.MappingUtils;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository StudentRepository;
    private final MappingUtils mappingUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRepository StudentRepository,
            MappingUtils mappingUtils,
            PasswordEncoder passwordEncoder) {
        this.StudentRepository = StudentRepository;
        this.mappingUtils = mappingUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student createStudent(CreateStudentRequest request) {
        Student Student = mappingUtils.convertToStudent(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return StudentRepository.save(Student);
    }

    @Override
    public Student updateStudent(Long id, CreateStudentRequest request) {
        Student existingStudent = StudentRepository.findById(id)
                .orElseThrow();
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        mappingUtils.updateStudentFromRequest(request, existingStudent);

        return StudentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student Student = StudentRepository.findById(id)
                .orElseThrow();
        StudentRepository.delete(Student);
    }
}