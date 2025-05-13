package br.com.gabrielferreira.contratos.infrastructure.config;

import br.com.gabrielferreira.contratos.adapters.out.adapters.PasswordAdapter;
import br.com.gabrielferreira.contratos.adapters.out.adapters.PerfilAdapter;
import br.com.gabrielferreira.contratos.adapters.out.adapters.TelefoneAdapter;
import br.com.gabrielferreira.contratos.adapters.out.adapters.UsuarioAdapter;
import br.com.gabrielferreira.contratos.application.core.service.PerfilServiceImpl;
import br.com.gabrielferreira.contratos.application.core.service.TelefoneServiceImpl;
import br.com.gabrielferreira.contratos.application.core.service.TipoTelefoneServiceImpl;
import br.com.gabrielferreira.contratos.application.core.service.UsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public PerfilServiceImpl perfilService(PerfilAdapter perfilAdapter) {
        return new PerfilServiceImpl(perfilAdapter);
    }

    @Bean
    public UsuarioServiceImpl usuarioService(UsuarioAdapter usuarioAdapter,
                                             PerfilAdapter perfilAdapter,
                                             PasswordAdapter passwordAdapter) {
        return new UsuarioServiceImpl(usuarioAdapter, perfilService(perfilAdapter), passwordAdapter);
    }

    @Bean
    public TelefoneServiceImpl telefoneService(TelefoneAdapter telefoneAdapter,
                                               UsuarioAdapter usuarioAdapter,
                                               PerfilAdapter perfilAdapter,
                                               PasswordAdapter passwordAdapter) {
        UsuarioServiceImpl usuarioService = usuarioService(usuarioAdapter, perfilAdapter, passwordAdapter);
        return new TelefoneServiceImpl(telefoneAdapter, usuarioService);
    }

    @Bean
    public TipoTelefoneServiceImpl tipoTelefoneService() {
        return new TipoTelefoneServiceImpl();
    }
}
