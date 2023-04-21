package com.thuanthanh.StudentProject.Controller;

import com.thuanthanh.StudentProject.Entity.Class;
import com.thuanthanh.StudentProject.Service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("class")
public class ClassController {
    @Autowired
    private ClassService classService;
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Class c){
        try{
            classService.add(c);
            return ResponseEntity.ok("Add class success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Class c, @Param("id") Integer id){
        try{
            classService.update(c,id);
            return ResponseEntity.ok("Update class success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestParam List<Integer> id){
        try{
            classService.delete(id);
            return ResponseEntity.ok("Delete class success!");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("search")
    public ResponseEntity<?> search(@Param("code") String code){
        try{
            return new ResponseEntity<>(classService.search(code),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
