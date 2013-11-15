package org.geymer.users.service;

import org.geymer.users.dao.UserGroupRepository;
import org.geymer.users.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 14.11.13
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class UserGroupService {
    @Autowired
    UserGroupRepository userGroupRepository;

    public List<UserGroup> findAll() {
        return userGroupRepository.findAll(new Sort(Sort.Direction.ASC,"title"));
    }

    public UserGroup findOne(Integer id) {
        return userGroupRepository.findOne(id);
    }
    public List<UserGroup> findByNameContaining(String name){
      return  userGroupRepository.findByTitleContaining(name);
    }
    public void delete(Integer id) {
        if (this.findOne(id) != null) userGroupRepository.delete(id);
    }

    public UserGroup save(UserGroup userGroup) {
        return userGroupRepository.saveAndFlush(userGroup);
    }


}
