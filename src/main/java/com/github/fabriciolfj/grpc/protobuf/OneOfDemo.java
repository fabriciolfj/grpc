package com.github.fabriciolfj.grpc.protobuf;

import com.github.fabriciolfj.protobuf.Credentials;
import com.github.fabriciolfj.protobuf.EmailCredentials;
import com.github.fabriciolfj.protobuf.PhoneOTP;

/*
* a classe vai credentials, vai aceitar apenas o ultimo inserido
* */
public class OneOfDemo {

    public static void main(String[] args) {
        EmailCredentials emailCredentials = EmailCredentials.newBuilder()
                .setEmail("Fabricio")
                .setPassword("1234")
                .build();

        PhoneOTP phoneOTP = PhoneOTP.newBuilder()
                .setCode(55)
                .setNumber(9875632)
                .build();

        Credentials credentials = Credentials.newBuilder()
                .setEmailMode(emailCredentials)
                .setPhoneMode(phoneOTP)
                .build();

        login(credentials);

    }

    private static void login(Credentials credentials) {
        switch (credentials.getModelCase()) {
            case EMAILMODE:
                System.out.println(credentials.getEmailMode());
                break;
            case PHONEMODE:
                System.out.println(credentials.getPhoneMode());
                break;
        }
    }
}
