package org.geymer.users.service;

import org.geymer.users.dao.UserRepository;
import org.geymer.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 14.11.13
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> findAll(){
        return  userRepository.findAll();
    }
    public User findOne(Integer id){
        return userRepository.findOne(id);
    }


    public void delete(Integer id){
        if(this.findOne(id)!=null) userRepository.delete(id);
    }

    public User save(User user){
     return    userRepository.saveAndFlush(user);
    }
    public  List<User> findUsersNotInGroup(){
        return  userRepository.findByUserGroupIdIsNull();
    }
}
