package org.graviton.model;


import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "points")
    private int points;

    @Column(name = "email")
    @Email(message = "Veuillez saisir une adresse email valide")
    private String email;

    @Column(name = "password")
    @Length(min = 4, message = "Le mot de passe doit contenir au moins 4 caractères")
    private String password;

    @Transient
    private String confirmedPassword;

    @Column(name = "name")
    @NotEmpty(message = "Veuillez renseigner votre nom d'utilisateur")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "question")
    @NotEmpty(message = "Veuillez renseigner votre question secrète")
    private String question;

    @Column(name = "answer")
    @NotEmpty(message = "Veuillez renseigner votre réponse secrète")
    private String answer;

    @Column(name = "last_connection")
    private String lastConnection;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Player> players;
}
