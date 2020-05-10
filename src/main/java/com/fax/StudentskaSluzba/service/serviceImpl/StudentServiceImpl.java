package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.mapper.StudentMapper;
import com.fax.StudentskaSluzba.model.Course;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.model.User;
import com.fax.StudentskaSluzba.modeldto.StudentDTO;
import com.fax.StudentskaSluzba.repository.StudentRepository;
import com.fax.StudentskaSluzba.repository.UserRepository;
import com.fax.StudentskaSluzba.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findOneById(Long id) {
        return studentRepository.findOneById(id);
    }
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }
    @Override
    public Optional<Student> findOneByCardNumber(String cardNumber) {
        return studentRepository.findOneByCardNumber(cardNumber);
    }
    @Override
    public List<Student> findAllByLastName(String lastName) {
        return studentRepository.findAllByLastName(lastName);
    }


    @Override
    public List<Student> findAllByCourse(Long courseId) {
        return studentRepository.fetchStudentsByCourseInnerJoin(courseId);
    }

    @Override
    public List<Student> saveAll(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    @Override
    public List<Student> freeStudentsByCourse(Long courseId) {
        return studentRepository.fetchStudentsByFalseCourseInnerJoin(courseId);
    }

    public StudentDTO insertStudentAndUser(StudentDTO studentDTO, User user){
        //User u = userRepository.save(user);
        Student student = studentMapper.toStudent(studentDTO);
        student.setUser(user);
        student=studentRepository.save(student);
        return studentMapper.toStudentDTO(student);
    }

    @Override
    public Student findByUser(User user) {
        return studentRepository.findByUser(user);
    }

    @Transactional
    public Student studentUserTransaction(User user,Student student){
        user=userRepository.save(user);
        student.setUser(user);
        student= studentRepository.save(student);
        return student;
    }
}
