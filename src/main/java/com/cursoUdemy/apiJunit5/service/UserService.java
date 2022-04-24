package com.cursoUdemy.apiJunit5.service;

import com.cursoUdemy.apiJunit5.model.User;
import com.cursoUdemy.apiJunit5.repository.UserRepository;
import com.cursoUdemy.apiJunit5.service.exceptions.DataIntegratyViolationException;
import com.cursoUdemy.apiJunit5.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        validEmail(user);
        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findByName(String name){
        return userRepository.findBynameLike("%"+name+"%");
    }

    public User update(User user){
        validEmail(user);
        return userRepository.save(user);
    }

    private void validEmail(User user){
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent() && !userOptional.get().getId().equals(user.getId())){
            throw new DataIntegratyViolationException("O email informado já esta cadastrado");
        }
    }
}
