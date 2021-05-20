package com.example.onlinevote.services;



import com.example.onlinevote.models.User;
import com.example.onlinevote.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailService implements UserDetailsService,UserService{
    @Autowired
    private final UserRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UsersDetailService(UserRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = usersRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }


    @Override
    public User getUserByUsername(String username) {
        return usersRepository.findUserByUsername(username);
    }

    @Override
    public void changePassword(String name, String password) {
        User user=usersRepository.findUserByUsername(name);
        user.setPassword(passwordEncoder.encode(password));
        usersRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        return usersRepository.getUserById(id);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User save(User entity) {
        return usersRepository.save(entity);
    }

    @Override
    public void update(User entity) {
      usersRepository.save(entity);
    }

    @Override
    public void remove(User entity) {
      usersRepository.delete(entity);
    }
}