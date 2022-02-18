package com.perficient.userservice.jpa;

import com.perficient.userservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, UUID> {

    // UserEntity updateUserEntity(UserEntity userEntity, UUID uuid);
//    void insertUser(UserEntity userEntity);
//
//    UserEntity getUser(UUID uuid);
//
//    void updateUser(UserEntity userEntity);
//
//    void deleteUser(UUID uuid);
//
//    List<UserEntity> listUsers();

}
