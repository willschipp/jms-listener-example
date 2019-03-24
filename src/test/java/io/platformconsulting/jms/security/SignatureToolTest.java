package io.platformconsulting.jms.security;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignatureToolTest {

    @Test
    public void sign() throws Exception {
        SignatureTool signatureTool = new SignatureTool();
        signatureTool.loadKeyStore();
        String output = signatureTool.sign("hello world");
        System.out.println(output);
        assertNotNull(output);
    }

    @Test
    public void isValid() throws Exception {
        //sign something
        SignatureTool signatureTool = new SignatureTool();
        signatureTool.loadKeyStore();
        String output = signatureTool.sign("hello world");
        //now verify it
        assertTrue(signatureTool.isValid("hello world",output));
    }
}