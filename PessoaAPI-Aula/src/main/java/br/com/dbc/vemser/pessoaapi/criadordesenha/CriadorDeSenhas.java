package br.com.dbc.vemser.pessoaapi.criadordesenha;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriadorDeSenhas {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senha = bCryptPasswordEncoder.encode("123");
        System.out.println(senha);
        // $2a$10$fP3fNbhDrkixHZHOqW4zKu9QdYiIWkhxH8NIXWcq7AQiUXAHivZEO
        // $2a$10$LMNeJo9gpeZ2mMO5BO2fGem8LWlYGIZuqsFEmg4uT09NbCly6ojq2
        // $2a$10$PjfEN2AgepvCUJe7qVnxEedrLKZV0OoIvx4X20YiiG4kxV9CAHxt2

        String minhaSenhaCript = "$2a$10$LMNeJo9gpeZ2mMO5BO2fGem8LWlYGIZuqsFEmg4uT09NCly6ojq2";
        boolean matches = bCryptPasswordEncoder.matches("123", minhaSenhaCript);
        System.out.println(matches);
        //true ou false
    }
}
