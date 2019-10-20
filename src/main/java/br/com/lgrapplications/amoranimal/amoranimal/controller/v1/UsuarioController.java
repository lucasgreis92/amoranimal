package br.com.lgrapplications.amoranimal.amoranimal.controller.v1;


import br.com.lgrapplications.amoranimal.amoranimal.model.documents.Usuario;
import br.com.lgrapplications.amoranimal.amoranimal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rs/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("autenticar")
    public boolean autenticar(@RequestBody Usuario usuario){
        return usuarioRepository.findAll().stream().filter(usu -> {
           return usu.getUsuario().equalsIgnoreCase(usuario.getUsuario())
                   && usu.getSenha().equalsIgnoreCase(usuario.getSenha());
        }).findFirst().isPresent();
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
