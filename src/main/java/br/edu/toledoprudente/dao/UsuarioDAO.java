package br.edu.toledoprudente.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.edu.toledoprudente.entidades.Usuario;
import br.edu.toledoprudente.entidades.tipoUsuario;

@Repository
public class UsuarioDAO extends AbstractDao<Usuario,Integer> implements IUsuarioDAO, UserDetailsService {

    public Usuario getUsuarioLogado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String nome;    

		if (principal instanceof UserDetails) {
		    nome = ((UserDetails)principal).getUsername();
		} else {
		    nome = principal.toString();
		}
		return  findByUserName(nome);
	}


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = findByUserName(username);

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                                                                      user.getSenha(), 
                                                                      AuthorityUtils.createAuthorityList(getAuthorizations(user.getTipousuario())));


    }

    public String[] getAuthorizations(tipoUsuario perfils){
        String[] authorities = new String[1];
        authorities[0] = perfils.getDescricao();
        return authorities;
    }


    @Override
    public Usuario findByUserName(String username){
        List<Usuario> lista = this.createQuery("select u from Usuario u where u.Login like ?1", username);
        return lista.isEmpty() ? null : lista.get(0);
    }
    

}
