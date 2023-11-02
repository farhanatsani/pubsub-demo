package com.pubsub.demo.config;

import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CredentialsConfig implements CredentialsProvider {

    @Override
    public Credentials getCredentials() throws IOException {

        InputStream is = new ClassPathResource("service-account.json")
                .getInputStream();

        return GoogleCredentials
                .fromStream(new ClassPathResource("service-account.json")
                        .getInputStream());
    }
}
