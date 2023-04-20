package com.thuanthanh.StudentProject.Service.Impl;

import com.thuanthanh.StudentProject.Entity.Department;
import com.thuanthanh.StudentProject.Repository.DepartmentRepository;
import com.thuanthanh.StudentProject.Service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    public static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public void add(Department department) {
        try{
            Department dp = new Department();
            dp.setCode(department.getCode());
            if(departmentRepository.existsByCode(department.getCode())){
                throw new RuntimeException("Mã phòng đã tồn tại!");
            }
            if(department.getCode().isEmpty() || department.getCode() == null){
                throw new RuntimeException("Mã phòng không được để trống!");
            }
            dp.setDescribe(department.getDescribe());
            if(department.getDescribe().isEmpty() || department.getDescribe() == null){
                throw new RuntimeException("Mô tả không được để trống!");
            }
            dp.setStatus(1);
            dp.setDeleted(0);
            dp.setCreatTime(new Date());
            departmentRepository.save(dp);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Department update(Department department, Integer id) {
        try{
            Department dp = departmentRepository.findById(id).get();
            dp.setCode(department.getCode());
            if(department.getCode().isEmpty() || department.getCode() == null){
                throw new RuntimeException("Mã phòng không được để trống!");
            }
            dp.setDescribe(department.getDescribe());
            if(department.getDescribe().isEmpty() || department.getDescribe() == null){
                throw new RuntimeException("Mô tả không được để trống!");
            }
            dp.setStatus(1);
            dp.setDeleted(0);
            dp.setUpdateTime(new Date());
            departmentRepository.save(dp);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(List<Integer> id) {
        try{
            departmentRepository.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}