package com.perficient.userservice.services;

import com.perficient.userservice.entities.UserEntity;
import com.perficient.userservice.jpa.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UsersRepository usersRepository;

    public boolean createUser(UserEntity userEntity) {
        usersRepository.save(userEntity);

        return true;
    }

    public List<UserEntity> getUsers() {
        return usersRepository.findAll();
    }

    public UserEntity getUserById(UUID uuid) {
        return usersRepository.findById(uuid).get();
    }

    public UserEntity updateUser(UserEntity userEntity, UUID uuid) {
        UserEntity existingUser = usersRepository.findById(uuid).get();

        existingUser.setFirstName(userEntity.getFirstName());
        existingUser.setLastName(userEntity.getLastName());
        existingUser.setAge(userEntity.getAge());
        existingUser.setGender(userEntity.getGender());
        existingUser.setEmailAddress(userEntity.getEmailAddress());
        existingUser.setPhoneNumber(userEntity.getPhoneNumber());

        usersRepository.save(existingUser);

        return existingUser;
    }

    // public void deleteUser(UUID uuid)

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
