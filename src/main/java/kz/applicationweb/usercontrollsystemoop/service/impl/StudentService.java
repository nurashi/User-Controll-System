package kz.applicationweb.usercontrollsystemoop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kz.applicationweb.usercontrollsystemoop.dto.request.CreateStudentRequest;
import kz.applicationweb.usercontrollsystemoop.model.user.Student;
import kz.applicationweb.usercontrollsystemoop.repository.StudentRepository;
import kz.applicationweb.usercontrollsystemoop.service.IStudentService;
import kz.applicationweb.usercontrollsystemoop.util.MappingUtils;

@Service
@Transactional
public class StudentService implements IStudentService {

    private final StudentRepository StudentRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public StudentService(StudentRepository StudentRepository,
                              MappingUtils mappingUtils) {
        this.StudentRepository = StudentRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public Student createStudent(CreateStudentRequest request) {
        Student Student = mappingUtils.convertToStudent(request);
        return StudentRepository.save(Student);
    }

    @Override
    public Student updateStudent(Long id, CreateStudentRequest request) {
        Student existingStudent = StudentRepository.findById(id)
                .orElseThrow();
        
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