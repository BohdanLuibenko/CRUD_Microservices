package Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.StudentEntity;
import Repository.StudentRepository;

@Service
public class ThirdStudentService {
	@Autowired
	private StudentRepository studentRepository;

	public StudentEntity create(StudentEntity student) {
		StudentEntity newStudent = new StudentEntity();
		newStudent.setLastname(student.getLastname());
		return newStudent;
	}

	public String get(Long id) {
		try {
			StudentEntity student = new StudentEntity();
			student.setLastname(studentRepository.findById(id).get().getLastname());
			return student.getLastname();
		} catch (Exception e) {
			return "nope";
		}
	}

	public List<StudentEntity> getAll() {
		List<StudentEntity> students = new ArrayList<StudentEntity>();
		for (StudentEntity studentEntity : studentRepository.findAll()) {
			StudentEntity student = new StudentEntity();
			student.setId(studentEntity.getId());
			student.setLastname(studentEntity.getLastname());
			students.add(student);
		}
		return students;
	}

	public StudentEntity update(Long id, StudentEntity student) {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity = studentRepository.findById(id).get();
		studentEntity.setLastname(student.getLastname());
		return studentEntity;
	}

	public StudentEntity delete(Long id) {
		StudentEntity student = new StudentEntity();
		student.setId(id);
		student.setLastname(studentRepository.findById(id).get().getLastname());
		return student;
	}

}
