package com.Student.Service;

import com.Student.Entity.Student;
import com.Student.Exception.ResourceNotFound;
import com.Student.Payload.DTO;
import com.Student.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository sr;
    @Autowired
    private ModelMapper mm;

    public DTO MapToDTO(Student s){
        DTO dt = mm.map(s, DTO.class);
        return dt;
    }
    public Student MapToEntity(DTO d){
        Student s = mm.map(d, Student.class);
        return s;
    }
     public DTO add(DTO d){
         Student st = MapToEntity(d);
         Student save = sr.save(st);
         return  MapToDTO(save);
     }
     public DTO update(Long id, DTO d){
         Student st = MapToEntity(d);
         st.setId(id);
         Student save = sr.save(st);
         return MapToDTO(save);
     }
     public String delete(Long id){
         sr.deleteById(id);
         return "Student deleted successfully";
     }
     public List<DTO> getAll(int pageSize,int pageNo, String sortBy, String orderBy){
         Sort sort=orderBy.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
         Page<Student> all = sr.findAll(page);
         List<Student> content = all.getContent();
         List<DTO> dt = content.stream().map(e -> MapToDTO(e)).collect(Collectors.toList());
         return dt;
     }
     public DTO getById(Long id){
         Student st = sr.findById(id).orElseThrow(
                 () -> new ResourceNotFound("Record not found with this it" + id));
         return MapToDTO(st);
     }
}
