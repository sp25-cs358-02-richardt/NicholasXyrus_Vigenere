package edu.uwstout.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.uwstout.EncryptedMessage;

public class EncryptedMessageTest {

    @Test
    void testGetMessage() {
        
        EncryptedMessage message = new EncryptedMessage("hello","a");
        try {
            assertEquals("hello", message.getMessage());
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
