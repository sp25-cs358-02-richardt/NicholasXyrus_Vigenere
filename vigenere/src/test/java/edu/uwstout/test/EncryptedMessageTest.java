package edu.uwstout.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.uwstout.EncryptedMessage;

public class EncryptedMessageTest {

    @Test
    void testGetMessage() {
        
        EncryptedMessage message = new EncryptedMessage("hello","c");
        try {
            assertEquals("jgnnq", message.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }
    }

    @Test
    void testMultiLetterKeyGetMessage() {
        
        EncryptedMessage message = new EncryptedMessage("hello","babab");
        try {
            assertEquals("iemlp", message.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }
    }

    @Test
    void testShorterKeyThanMessage() {
        
        EncryptedMessage message = new EncryptedMessage("hello","baba");
        try {
            assertEquals("iemlp", message.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }
    }

    @Test
    void testDecryptMessage() {
        EncryptedMessage message = new EncryptedMessage("hello");
        try {
            assertEquals("hello", message.decryptMessage("a"));
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }

    }

   
}
