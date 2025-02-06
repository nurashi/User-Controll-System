package kz.applicationweb.usercontrollsystemoop.service;

import kz.applicationweb.usercontrollsystemoop.dto.request.*;
import kz.applicationweb.usercontrollsystemoop.model.user.Student;

public interface IStudentService {
    public Student createStudent(CreateStudentRequest request);

    public Student updateStudent(Long id, CreateStudentRequest request);

    public void deleteStudent(Long id);
}
