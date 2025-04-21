package com.example.service;
import com.example.entity.Account;
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
            admin.setRole("ADMIN");
        }
        adminMapper.insert(admin);
    }
    public void update(Admin admin){
        adminMapper.updateById(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }
    public void deleteBatch(List<Admin> list) {
        for(Admin admin : list){
            this.deleteById(admin.getId());
        }
    }
    public String admin(String name){
        if(name.equals("admin")){
            return "admin";
        } else {
            throw new CustomException("账号错误");
        }
    }

    public List<Admin> selectAll(Admin admin){
        return adminMapper.selectAll(admin);
    }

    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize, Admin admin) {
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list=adminMapper.selectAll(admin);
        return new PageInfo<>(list);
    }


    public Admin login(Account account) {
        //验证账号是否存在
        Admin dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (dbAdmin == null) {
            throw new CustomException("账号不存在");
        }
        //验证密码
        if (!account.getPassword().equals(account.getPassword())) {//密码不正确
            throw new CustomException("账号或密码错误");
        }
        return dbAdmin;
    }
}
