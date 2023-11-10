package com.example.Agencia.Client;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Client")
@Entity(name = "Client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="cpf")
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpf;
    private String name;
    private String email;
    private String phoneNumber;

    public Client(ClientRequestDTO data){
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber = data.phoneNumber();
        this.cpf = data.cpf();
    }

}