package com.example.service;
import com.example.entity.Admin;
import com.example.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AdminService {

    @Resource
    AdminMapper adminMapper;


    public String admin(String name){
        if(name.equals("admin")){
            return "admin";
        } else {
            throw new CustomException("账号错误");
        }
    }

    public List<Admin> selectAll(){
        return adminMapper.selectAll(null);
    }

    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize, String name) {
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list=adminMapper.selectAll(name);
        return new PageInfo<>(list);
    }
}
