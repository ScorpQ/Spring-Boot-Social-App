package group.artifact.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import group.artifact.entities.User;
import group.artifact.repository.UserRepository;
import group.artifact.security.JwtUserDetails;

public class UserDetailServiceImp implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId);
        return JwtUserDetails.create(user);
    }

}
