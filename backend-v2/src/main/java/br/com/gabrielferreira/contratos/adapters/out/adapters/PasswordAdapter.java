package br.com.gabrielferreira.contratos.adapters.out.adapters;

import br.com.gabrielferreira.contratos.application.ports.out.PasswordEncoderOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordAdapter implements PasswordEncoderOutput {

    // TODO : Implementar o PasswordEncoder
    @Override
    public String encode(String senha) {
        return senha;
    }
}
