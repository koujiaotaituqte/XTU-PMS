package com.example.service;
import com.example.entity.Admin;
import com.example.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AdminService {

    @Resource
    AdminMapper adminMapper;


    public void add(Admin admin){
        //根据新的账号查询 数据库 是否存在同样账号
        Admin dbadmin = adminMapper.selectByUsername(admin.getUsername());
        if(dbadmin != null){
            throw new CustomException("账号重复","500");
        }
        //默认密码为账号
        if(StringUtils.isBlank(admin.getPassword())){
            String password = admin.getUsername();
            admin.setPassword(password);
        }
        adminMapper.insert(admin);
    }
    public void update(Admin admin){
        adminMapper.updateById(admin);
    }
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

    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize, Admin admin) {
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list=adminMapper.selectAll(admin);
        return new PageInfo<>(list);
    }


}
