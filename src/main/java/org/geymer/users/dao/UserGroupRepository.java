package org.geymer.users.dao;

import org.geymer.users.entity.UserGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 14.11.13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public interface UserGroupRepository extends JpaRepository<UserGroup,Integer>{

    List<UserGroup> findByTitleContaining(String name);
}
