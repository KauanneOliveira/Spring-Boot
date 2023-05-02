package br.com.codelab.springboot2.service;

import br.com.codelab.springboot2.repository.CodeLabAnimeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodeLabUserDatailsService implements UserDetailsService {
    private final CodeLabAnimeUserRepository codeLabAnimeUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return (UserDetails) Optional.ofNullable(this.codeLabAnimeUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
