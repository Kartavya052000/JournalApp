package com.kartavya.journalApp.service;

import com.kartavya.journalApp.entity.User;
import com.kartavya.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private static final PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
    public boolean saveNewUser(User user) {
        try{
            user.setPassword(passwordEncorder.encode(user.getPassword()));
            user.setRoles((Arrays.asList("USER")));
            userRepository.save(user);
            return true;
        }catch(Exception e){
            log.error("Error occured for {} :",user.getUsername(),e);
            return false;
        }

    }

    public void saveUser(User user) {
        userRepository.save(user);

    }
    public void saveAdmin(User user) {
        user.setPassword(passwordEncorder.encode(user.getPassword()));
        user.setRoles((Arrays.asList("USER","ADMIN")));
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
    return userRepository.findById(id);
}
   public void deleteById(ObjectId id){
       userRepository.deleteById(id);
   }
   public User findByUserName(String userName){
        return userRepository.findByUsername(userName);
   }
}
