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

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        return usersRepository.save(userEntity);
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


            currentUser.setFirstName(
                    isValidName(userEntity.getFirstName()) ?
                            userEntity.getFirstName() :
                            currentUser.getFirstName()
            );

            currentUser.setLastName(
                    isValidName(userEntity.getLastName()) ?
                            userEntity.getLastName() :
                            currentUser.getLastName()
            );

            currentUser.setAge(
                    isValidAge(userEntity.getAge()) ?
                            userEntity.getAge() :
                            currentUser.getAge()
            );

            currentUser.setGender(
                    userEntity.getGender() == null ?
                            currentUser.getGender() :
                            userEntity.getGender()
            );

            currentUser.setEmailAddress(
                    isValidEmail(userEntity.getEmailAddress()) ?
                            userEntity.getEmailAddress() :
                            currentUser.getEmailAddress()
            );


            currentUser.setPhoneNumber(
                    isValidPhoneNumber(userEntity.getPhoneNumber()) ?
                            userEntity.getPhoneNumber() :
                            currentUser.getPhoneNumber()
            );


            usersRepository.save(currentUser);

            return currentUser;
        } else {
            throw new ResourceNotFoundException("User", "UUID", uuid);
        }

    }

    public void deleteUser(UUID uuid) {

        // Check that user exists first, or else throw a resource not found exception
        usersRepository.findById(uuid).orElseThrow(() ->
                new ResourceNotFoundException("User", "UUID", uuid)
        );

        usersRepository.deleteById(uuid);
    }

     private boolean isValidName(String name) {
        return name != null &&
                !name.isBlank() &&
                !name.isEmpty();
     }

     private boolean isValidAge(int age) {
        return age >= 0;
     }

     private boolean isValidEmail(String email) {
         return email != null &&
                 !email.isBlank() &&
                 !email.isEmpty();
     }

     private boolean isValidPhoneNumber(String phoneNumber) {
         return phoneNumber != null &&
                 !phoneNumber.isBlank() &&
                 !phoneNumber.isEmpty();
     }
}
