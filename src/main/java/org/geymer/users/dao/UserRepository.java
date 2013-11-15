package org.geymer.users.dao;

import org.geymer.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: babkamen
 * Date: 14.11.13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends JpaRepository<User,Integer>{
    @Override
    List<User> findAll();

    @Override
    User findOne(Integer integer);

    @Override
    <S extends User> S save(S entity);

    @Override
    void delete(Integer integer);
    List<User> findByUserGroupIdIsNull();


}
