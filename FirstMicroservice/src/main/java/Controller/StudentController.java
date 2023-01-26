package Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entity.StudentEntity;
import Service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@PostMapping("/create")
	public StudentEntity create(@RequestBody StudentEntity student) {
		return studentService.create(student);
	}

	@GetMapping("/get{id}")
	public StudentEntity get(@RequestParam Long id) {
		return studentService.get(id);
	}

	@GetMapping("/getall")
	public List<StudentEntity> getAll() {
		return studentService.getAll();
	}

	@PutMapping("/update{id}")
	public String update(@RequestParam Long id, @RequestBody StudentEntity student) {
		return ("update "+studentService.update(id, student));
	}

	@DeleteMapping("/delete{id}")
	public String delete(@RequestParam Long id) {
		return ("Deleted " + studentService.delete(id));
	}
}
