package com.example.loginPage.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.example.loginPage.model.Users;
import com.example.loginPage.repository.UsersRepository;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;

    //Spring Security para lidar com senhas
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // REGISTRAR: Transforma senha em hash e salva
    public Users salvarUsuario(Users user) {
    String senhaCriptografada = encoder.encode(user.getPassword());
    user.setPassword(senhaCriptografada);
    return usersRepository.save(user);
}

    public Optional<Users> verificarLogin(String email, String senhaDigitada) {
        String emailLimpo = email.trim().toLowerCase();
        Optional<Users> usuarioNoBanco = usersRepository.findByEmail(emailLimpo);

        if (usuarioNoBanco.isPresent()) {
            if (encoder.matches(senhaDigitada, usuarioNoBanco.get().getPassword())) {
                return usuarioNoBanco;
            }
        }
        
        return Optional.empty();
    }

}
