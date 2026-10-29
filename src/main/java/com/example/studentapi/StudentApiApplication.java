package com.example.studentapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@SpringBootApplication
@RestController
public class StudentApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }
    private final Map<Long, Map<String, Object>> students = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);
    // Корневой эндпоинт по заданию
    @GetMapping("/")
    public Map<String, String> getRootInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("student", "Arsen Kantpay");
        info.put("group", "IT2-207");
        info.put("studentId", "37705");
        info.put("version", "1.0");
        return info;
    }
    @PostMapping("/api/students")
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Map<String, Object> body) {
        long id = counter.getAndIncrement();
        body.put("id", id);
        students.put(id, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
    @GetMapping("/api/students")
    public List<Map<String, Object>> getAllStudents() {
        return new ArrayList<>(students.values());
    }
    @GetMapping("/api/students/{id}")
    public ResponseEntity<Map<String, Object>> getStudent(@PathVariable Long id) {
        Map<String, Object> s = students.get(id);
        if (s == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(s);
    }
    @PutMapping("/api/students/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        if (!students.containsKey(id)) return ResponseEntity.notFound().build();
        body.put("id", id);
        students.put(id, body);
        return ResponseEntity.ok(body);
    }
    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!students.containsKey(id)) return ResponseEntity.notFound().build();
        students.remove(id);
        return ResponseEntity.noContent().build();
    }
}
