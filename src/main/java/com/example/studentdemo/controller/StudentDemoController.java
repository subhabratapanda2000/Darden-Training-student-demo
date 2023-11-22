package com.example.studentdemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentdemo.model.Student;
import com.example.studentdemo.repository.StudentDemoRepo;

@RestController
@RequestMapping("/student")
public class StudentDemoController {
	@Autowired
	StudentDemoRepo repo;
	
		@PostMapping("/create")
		public String insertStudent(@RequestBody Student st) {
			repo.save(st);
			return "Data saved successfully.";
			
		}
		
		@GetMapping("/getStudentById/{id}")
		public Student getStudentDetailsById(@PathVariable("id") int id) {
			Optional<Student> op = repo.findById(id);
			Student st=null;
			if(op.isPresent()) {
				st= op.get();
				return st;
			}
			else {
				return null;
			}
			
			
		}
		
		@GetMapping("/allStudent")
		public List<Student> getAllStudent(){
		
			List<Student> list=new ArrayList<>();
			repo.findAll().forEach(list::add);
			if(!list.isEmpty()) {
				return list;
			}
			else {
				return null;
			}
			
		}
		
		@PutMapping("/update/{id}")
		public String updateStudent(@RequestBody Student student,@PathVariable("id") int id) {
			
				Optional<Student> op = repo.findById(id);
				Student st=null;
				if(op.isPresent()) {
					st= op.get();
					student.setId(st.getId());
					repo.save(student);
					return "Data update successfully";
				}
				else {
					return "Invalid Id";
				}
				
			
		}
		
		@DeleteMapping("/deleteStudentById/{id}")
		public String deleteStudentById(@PathVariable("id") int id) {
			Optional<Student> op = repo.findById(id);
			if(op.isPresent()) {
				repo.deleteById(id);
				return "Deleted successfully";
			}
			else {
				return "Invalid Id";
			}
			
			
		}
		
		@DeleteMapping("/deleteAll")
		public String deleteAllStudent() {
				repo.deleteAll();
				return "All data deleted";
			
		}
		
		
		
		

}
