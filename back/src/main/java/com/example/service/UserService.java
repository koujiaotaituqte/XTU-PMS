package com.example.service;

import com.example.entity.Account;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;




    public void add(User user){
        //根据新的账号查询 数据库 是否存在同样账号
        User dbuser = userMapper.selectByUsername(user.getUsername());
        if(dbuser != null){
            throw new CustomException("账号重复","500");
        }
        //默认密码为账号
        user.setRole("USER");
        if(StringUtils.isBlank(user.getPassword())){

            String password = user.getUsername();
            user.setPassword(password);

            //如果没取名，默认和账号一致
            if(StringUtils.isBlank(user.getName())){
                user.setName(user.getUsername());
            }
        }
        userMapper.insert(user);
    }
    public void update(User user){
        userMapper.updateById(user);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }
    public void deleteBatch(List<User> list) {
        for(User user : list){
            this.deleteById(user.getId());
        }
    }
    public String user(String name){
        if(name.equals("user")){
            return "user";
        } else {
            throw new CustomException("账号错误");
        }
    }

    public List<User> selectAll(User user){
        return userMapper.selectAll(user);
    }

    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, User user) {
        //开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<User> list=userMapper.selectAll(user);
        return new PageInfo<>(list);
    }


    public User login(Account account) {
        //验证账号是否存在
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if (dbUser == null) {
            throw new CustomException("账号不存在");
        }
        //验证密码
        if (!account.getPassword().equals(account.getPassword())) {//密码不正确
            throw new CustomException("账号或密码错误");
        }
        return dbUser ;
    }

    public void register(User user) {
        this.add(user);

    }
}
