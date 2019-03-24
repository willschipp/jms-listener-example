package io.platformconsulting.jms.security;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.security.*;
import java.security.cert.Certificate;
import java.util.Base64;

public class SignatureTool {

    private String keystoreLocation = "server.keystore";

    private String keystorePassword = "welcome";

    private String keyName = "mykey";

    private KeyStore keyStore;

    public void loadKeyStore() throws Exception {
        //location
        keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new ClassPathResource(keystoreLocation).getInputStream(),keystorePassword.toCharArray());
    }

    private PrivateKey getPrivateKey() throws Exception {
        Assert.notNull(keyStore,"Keystore not loaded");
        return (PrivateKey) keyStore.getKey(keyName,keystorePassword.toCharArray());
    }

    private PublicKey getPublicKey() throws Exception {
        Assert.notNull(keyStore,"Keystore not loaded");
        Certificate certificate = keyStore.getCertificate(keyName);
        return certificate.getPublicKey();
    }

    public String sign(String data) throws Exception {
        Signature sigature = Signature.getInstance("SHA1withRSA");
        sigature.initSign(getPrivateKey());
        sigature.update(data.getBytes());
        byte[] signed = sigature.sign();
        return Base64.getEncoder().encodeToString(signed);
    }

    public boolean isValid(String data,String signature) throws Exception {
        Signature sigature = Signature.getInstance("SHA1withRSA");
        sigature.initVerify(getPublicKey());
        sigature.update(data.getBytes());
        return sigature.verify(Base64.getDecoder().decode(signature));

    }
}
