package com.mynotes.grpc.service.oneof;

import com.mynotes.models.oneof.EmailLogin;
import com.mynotes.models.oneof.Login;
import com.mynotes.models.oneof.PhoneLogin;

public class OneOfTest {

    public static void main(String[] args) {

        EmailLogin emailLogin = EmailLogin.newBuilder()
            .setEmail("dummy@email.com")
            .setPassword("dummy").build();
        PhoneLogin phoneLogin = PhoneLogin.newBuilder()
            .setNumber(123123123)
            .setCode(1234).build();
        Login login = Login.newBuilder()
            .setEmailMode(emailLogin).build();
        print(login);
        // If both are set, the last one overrides everything
        System.out.println("=====");
        login = Login.newBuilder()
            .setEmailMode(emailLogin)
            .setPhoneMode(phoneLogin).build();
        print(login);
    }

    static void print(Login login) {
        switch (login.getMethodCase()) {
            case EMAILMODE:
                System.out.println("Email");
                System.out.println(login);
                break;
            case PHONEMODE:
                System.out.println("Phone");
                System.out.println(login);
                break;
            default:
                System.out.println("Invalid");
        }
    }

}
