package to.gizmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import to.gizmo.entities.User;
import to.gizmo.repositories.UserRepository;

import java.util.Optional;

public class DatabaseUserDetailsService implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> result = userRepository.findByUsername(username);
        if (!result.isPresent()) {
            throw new UsernameNotFoundException("");
        }

        return result.get();
    }
}
