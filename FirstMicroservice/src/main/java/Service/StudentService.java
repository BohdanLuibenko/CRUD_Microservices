package Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import Entity.StudentEntity;
import Repository.StudentRepository;
import reactor.core.publisher.Mono;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	private WebClient connectToSecond = WebClient.create("http://secondserv:8081/student2");

	public StudentEntity create(StudentEntity student) {
		StudentEntity newStudent = new StudentEntity();
		newStudent = connectToSecond.post().uri("/create").body(Mono.just(student), StudentEntity.class).retrieve()
				.bodyToMono(StudentEntity.class).block();
		newStudent.setName(student.getName());
		studentRepository.save(newStudent);
		return newStudent;
	}

	public StudentEntity get(Long id) {
		try {
			StudentEntity student = new StudentEntity();
			student = connectToSecond.get().uri("/get?id={id}", id).retrieve().bodyToMono(StudentEntity.class).block();
			student.setId(id);
			student.setName(studentRepository.findById(id).get().getName());
			return student;
		} catch (Exception e) {
			return null;
		}
	}

	public List<StudentEntity> getAll() {
		List<StudentEntity> students = new ArrayList<StudentEntity>();
		students = connectToSecond.get().uri("/getall").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<StudentEntity>>() {
				}).block();
		for (StudentEntity student : students) {
			for (StudentEntity studentEntity : studentRepository.findAll()) {
				if (student.getId() == studentEntity.getId()) {
					student.setName(studentEntity.getName());
				}
			}
		}
		return students;
	}

	public StudentEntity update(Long id, StudentEntity student) {
		StudentEntity studentEntity = connectToSecond.put().uri("/update?id={id}", id)
				.body(Mono.just(student), StudentEntity.class).retrieve().bodyToMono(StudentEntity.class).block();
		studentEntity.setName(student.getName());
		studentRepository.save(studentEntity);
		return studentEntity;
	}

	public StudentEntity delete(Long id) {
		StudentEntity student = new StudentEntity();
		student = connectToSecond.delete().uri("/delete?id={id}", id).retrieve().bodyToMono(StudentEntity.class)
				.block();
		student.setName(studentRepository.findById(id).get().getName());
		studentRepository.delete(student);
		return student;
	}

}
