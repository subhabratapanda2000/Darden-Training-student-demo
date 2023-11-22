package com.example.studentdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.studentdemo.controller.StudentDemoController;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.repository.StudentDemoRepo;

@SpringBootTest
class StudentDemoApplicationTests {


	@Autowired
	StudentDemoController service;
	
	@MockBean
	StudentDemoRepo repo;
	boolean expected=true;
	
	@Test
	void createStudentTest() {
		Student st=new Student(1, "abcd", 21, 3000.00);
		when(repo.save(st)).thenReturn(st);
		assertEquals("Data saved successfully.", service.insertStudent(st));
	}
	
	@Test
	void findAllStudent() {
		when(repo.findAll()).thenReturn(Stream
				.of(new Student(1, "abcd", 21, 3000.00), new Student(2, "efgh", 21, 3000.00)).collect(Collectors.toList()));
		assertEquals(2, service.getAllStudent().size());
	}
	
	@Test
	void findAllStudentWithEmptyList() {
		when(repo.findAll()).thenReturn(new ArrayList<Student>());
		assertEquals(null, service.getAllStudent());
	}
	
	@Test
	void getUserbyIdTest() {
		int id=1;
		Student st=new Student(1, "abcd", 21, 3000.00);
		when(repo.findById(id)).thenReturn(java.util.Optional.of(st));
		assertTrue(java.util.Optional.of(st).isPresent());
		assertEquals(st, service.getStudentDetailsById(id));
	}
	
	@Test
	 void testFindStudentByInvalidId() {
       int invalidStudentId = 100;
       when(repo.findById(invalidStudentId)).thenReturn(Optional.empty());
       Student st = service.getStudentDetailsById(invalidStudentId);
       assertEquals(null, st);
   }
	
	
	
	@Test
	void updateStudentTest() {
		Student st=new Student(1, "abcd", 21, 3000.00);
		int id=1;
		when(repo.save(st)).thenReturn(st);
		when(repo.findById(id)).thenReturn(java.util.Optional.of(st));
		assertEquals("Data update successfully", service.updateStudent(st,id));
	}
	
	@Test
	 void testUpdateStudentByInvalidId() {
      int invalidStudentId = 100;
      Student st=new Student(1, "abcd", 21, 3000.00);
      when(repo.findById(invalidStudentId)).thenReturn(Optional.empty());
      String st1 = service.updateStudent(st,invalidStudentId);
      assertEquals("Invalid Id", st1);
  }
	
	@Test
	 void deleteStudentById() {
		int id=1;
		Student st=new Student(1, "abcd", 21, 3000.00);
		when(repo.findById(id)).thenReturn(java.util.Optional.of(st));
		
		assertEquals("Deleted successfully", service.deleteStudentById(1));
		
	}
	
	@Test
	 void deleteStudentByInvalidId() {
        int invalidStudentId = 100;
        when(repo.findById(invalidStudentId)).thenReturn(Optional.empty());

        String st = service.deleteStudentById(invalidStudentId);

        // Verifying that the deleteById method is not called
        verify(repo, never()).deleteById(anyInt());
        assertEquals("Invalid Id", st);
    }
		
	
	
	@Test
	 void deleteAllStudent() {
		verify(repo, never()).deleteAll();
		assertEquals("All data deleted", service.deleteAllStudent());
		

	}
	
	@Test
    public void testStudentConstructorAndGetters() {
   
        Student student = new Student();

        student.setId(1);
        student.setName("Subhabrata");
        student.setAge(20);
        student.setSal(50000.0);

        assertEquals(1, student.getId());
        assertEquals("Subhabrata", student.getName());
        assertEquals(20, student.getAge());
        assertEquals(50000.0, student.getSal()); 
    }
	
	//gfjhmjggjhgmjh
	
	

}
