package com.perficient.userservice.services;

import com.perficient.userservice.entities.UserEntity;
import com.perficient.userservice.exceptions.ResourceNotFoundException;
import com.perficient.userservice.jpa.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UsersRepository usersRepository;

    public void createUser(UserEntity userEntity) {
        usersRepository.save(userEntity);
    }

    public List<UserEntity> getUsers() {
        return usersRepository.findAll();
    }

    public UserEntity getUserById(UUID uuid) {
        Optional<UserEntity> userEntityOptional = usersRepository.findById(uuid);

        if (userEntityOptional.isPresent()) {
            return userEntityOptional.get();
        } else {
            throw new ResourceNotFoundException("User", "uuid", uuid);
        }
    }

    public UserEntity updateUser(UserEntity userEntity, UUID uuid) {
        Optional<UserEntity> existingUser = usersRepository.findById(uuid);

        if (existingUser.isPresent()) {
            UserEntity currentUser = existingUser.get();

            currentUser.setFirstName(userEntity.getFirstName());
            currentUser.setLastName(userEntity.getLastName());
            currentUser.setAge(userEntity.getAge());
            currentUser.setGender(userEntity.getGender());
            currentUser.setEmailAddress(userEntity.getEmailAddress());
            currentUser.setPhoneNumber(userEntity.getPhoneNumber());

            usersRepository.save(currentUser);

            return currentUser;
        } else {
            throw new ResourceNotFoundException("User", "UUID", uuid);
        }

    }

     public void deleteUser(UUID uuid) {
        usersRepository.deleteById(uuid);
     }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
