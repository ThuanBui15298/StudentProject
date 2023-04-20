package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Student;
import com.thuanthanh.StudentProject.Entity.Subject;
import com.thuanthanh.StudentProject.Repository.ClassRepository;
import com.thuanthanh.StudentProject.Repository.StudentRepository;
import com.thuanthanh.StudentProject.Service.StudentService;
import com.thuanthanh.StudentProject.StringUtil.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    public static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    Utils utils = new Utils();
    @Override
    public void add(Student student, Integer classid) {
        try{
            Student sd = new Student();
            sd.setCode(student.getCode());
            if(studentRepository.existsByCode(student.getCode())){
                throw new RuntimeException("Mã sinh viên đã tồn tại!");
            }
            if(student.getCode().isEmpty() || student.getCode() == null){
                throw new RuntimeException("Mã sinh viên không được bỏ trống!");
            }
            sd.setName(student.getName());
            if(student.getName().isEmpty() || student.getName() == null){
                throw new RuntimeException("Tên sinh viên không được để trống!");
            }
            sd.setAddress(student.getAddress());
            if(student.getAddress().isEmpty() || student.getAddress() == null){
                throw new RuntimeException("Địa chỉ không được để trống!");
            }
            sd.setBirthDay(student.getBirthDay());
            sd.setStatus(1);
            sd.setDeleted(0);
            sd.setSex(student.getSex());
            sd.setAClass(classRepository.findByIdAndStatusAndDeleted(classid,1,0));
            sd.setCreatTime(new Date());
            studentRepository.save(sd);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Student update(Student student, Integer id,Integer classid) {
        try{
            Student sd = studentRepository.findById(id).get();
            sd.setName(student.getName());
            if(student.getName().isEmpty() || student.getName() == null){
                throw new RuntimeException("Tên sinh viên không được để trống!");
            }
            sd.setAddress(student.getAddress());
            if(student.getAddress().isEmpty() || student.getAddress() == null){
                throw new RuntimeException("Địa chỉ không được để trống!");
            }
            sd.setBirthDay(student.getBirthDay());
            sd.setStatus(1);
            sd.setDeleted(0);
            sd.setSex(student.getSex());
            sd.setAClass(classRepository.findByIdAndStatusAndDeleted(classid,1,0));
            sd.setUpdateTime(new Date());
            studentRepository.save(sd);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return student;
    }

    @Override
    public void delete(List<Integer> id) {
        try {
            studentRepository.deleted(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Page<Student> search() {
        return null;
    }

    @Override
    public List<Map<String, Object>> top5() {
        try{
            List<Map<String,Object>> top5SVpoint = studentRepository.top5point();
            if(top5SVpoint.isEmpty()){
                throw new RuntimeException("không có dữ liệu!");
            }
            return top5SVpoint;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> quantitySV() {
        try {
            Map<String,Object> sv = studentRepository.sv();
            if(sv.isEmpty()){
                throw new RuntimeException("Không có dữ liệu!");
            }
            Map<String,Object> svboy = studentRepository.svboy();
            if(svboy.isEmpty()){
                throw new RuntimeException("Không có dữ liệu!");
            }
            Map<String,Object> svgrid = studentRepository.svgrid();
            if(svgrid.isEmpty()){
                throw new RuntimeException("Không có dữ liệu!");
            }
            Map<String,Object> top4under = studentRepository.top4under();
            if(svgrid.isEmpty()){
                throw new RuntimeException("Không có dữ liệu!");
            }
            List<Map<String,Object>> getall = new ArrayList<>();
            getall.add(sv);
            getall.add(svboy);
            getall.add(svgrid);
            getall.add(top4under);
            return getall;
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
