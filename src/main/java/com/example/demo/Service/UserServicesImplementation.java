package com.example.demo.Service;

import com.example.demo.Modeles.Role;
import com.example.demo.Modeles.User;
import com.example.demo.Repository.RoleRepo;
import com.example.demo.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServicesImplementation implements UserService, UserDetailsService {
  private final UserRepo userRepo;
  private final RoleRepo roleRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   User user = userRepo.findByUsername(username);
   if (user == null){
     log.error("Utilisateur non trouvé dans la base de données");
  throw new UsernameNotFoundException("Utilisateur non trouvé dans la base de données");
   }else {
     log.info("Utilisateur  trouvé dans la base de données : {}", username);
   }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
   user.getRoles().forEach(role -> {
     authorities.add(new SimpleGrantedAuthority(role.getNom()));
   });
    return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getMdp(),authorities);
  }
    @Override
    public User saveUser(User user) {
      log.info("Enregistrements d'un nouvel utilisateur",user.getNomPrenom());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
      log.info("Enregistrements d'un nouveau role {} dans la base de données",role.getNom());
      return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleNom) {
      log.info("Ajout du role {} au nouvel utilisateur {} dans la base de données",roleNom,username);
      User user = userRepo.findByUsername(username);
      Role role = roleRepo.findByNom(roleNom);
      user.getRoles().add(role);
      return ;
    }

    @Override
    public User getUser(String username) {
      log.info("récupération de l'utilisateur {} dans la base de données",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
      log.info("récupération des utilisateurs");
      return userRepo.findAll();
    }

}
