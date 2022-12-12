package com.security.jwt.modele;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class utilisateur {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    private String nom;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String mot2passe;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<role> roles ;
}
