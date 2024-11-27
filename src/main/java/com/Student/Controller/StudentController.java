package com.Student.Controller;

import com.Student.Payload.DTO;
import com.Student.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Student")
public class StudentController {

    @Autowired
    private StudentService ss;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody DTO d, BindingResult result){
        if(result.hasErrors()){

            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        DTO add = ss.add(d);
        return new ResponseEntity<>(add,HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<DTO> update(@RequestParam Long id, @RequestBody DTO d){
        DTO update = ss.update(id, d);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id){
        String s = ss.delete(id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<DTO>> getAll(@RequestParam(name = "pageSize",required = false,defaultValue = "5") int pageSize,
                                            @RequestParam(name="pageNo",required = false,defaultValue = "0")int pageNo,
                                            @RequestParam(name = "sortBy",required = false,defaultValue = "name")String sortBy,
                                            @RequestParam(name = "orderBy",required = false,defaultValue = "asc") String orderBy){
        List<DTO> all = ss.getAll(pageSize, pageNo, sortBy, orderBy);
        return new ResponseEntity<>(all, HttpStatus.OK);

    }
    @GetMapping("/st/{id}")
    public ResponseEntity<DTO> getById(@PathVariable long id){
        DTO dto = ss.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
