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
public class SecondStudentService {
	@Autowired
	private StudentRepository studentRepository;	
	private WebClient connectToThird = WebClient.create("http://thirdserv:8082/student3");

	public StudentEntity create(StudentEntity student) {
		StudentEntity newStudent = new StudentEntity();
		newStudent.setMiddlename(student.getMiddlename());
		newStudent.setLastname(connectToThird.post().uri("/create").body(Mono.just(student), StudentEntity.class)
				.retrieve().bodyToMono(StudentEntity.class).block().getLastname());
		return newStudent;
	}

	public StudentEntity get(Long id) {
		try {
			StudentEntity student = new StudentEntity();
			student.setMiddlename(studentRepository.findById(id).get().getMiddlename());
			student.setLastname(
					connectToThird.get().uri("/get?id={id}", id).retrieve().bodyToMono(String.class).block());
			return student;
		} catch (Exception e) {
			return null;
		}
	}

	public List<StudentEntity> getAll() {
		List<StudentEntity> students = new ArrayList<StudentEntity>();
		students = connectToThird.get().uri("/getall").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<StudentEntity>>() {
				}).block();
		for (StudentEntity student : students) {
			for (StudentEntity studentEntity : studentRepository.findAll()) {
				if (student.getId() == studentEntity.getId()) {
					student.setMiddlename(studentEntity.getMiddlename());
				}
			}
		}
		return students;
	}

	public StudentEntity update(Long id, StudentEntity student) {
		StudentEntity studentEntity = connectToThird.put().uri("/update?id={id}", id)
				.body(Mono.just(student), StudentEntity.class).retrieve().bodyToMono(StudentEntity.class).block();
		studentEntity.setMiddlename(student.getMiddlename());
		return studentEntity;
	}

	public StudentEntity delete(Long id) {
		StudentEntity student = new StudentEntity();
		student = connectToThird.delete().uri("/delete?id={id}", id).retrieve().bodyToMono(StudentEntity.class).block();
		student.setMiddlename(studentRepository.findById(id).get().getMiddlename());
		return student;
	}

}
