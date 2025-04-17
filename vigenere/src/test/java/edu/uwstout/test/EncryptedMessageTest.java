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
    void testMixedCase() {
        
        EncryptedMessage message = new EncryptedMessage("Hello","BabA");
        try {
            assertEquals("iemlp", message.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }
    }

    @Test
    void testConvertingSpacesToCurlyBracket() {
        
        EncryptedMessage message = new EncryptedMessage("hello world","a");
        try {
            assertEquals("hello{world", message.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }
    }

    @Test
    void testInvalidCharInMessage() {
        
        EncryptedMessage message = new EncryptedMessage("he/him","a");
        try {
            assertEquals(null, message.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }
    }

    @Test
    void testInvalidCharInKey() {
        
        EncryptedMessage message = new EncryptedMessage("hello world","2");
        try {
            assertEquals(null, message.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }
    }

    @Test
    void testRollOver() {
        
        EncryptedMessage message = new EncryptedMessage("hello world","a");
        try {
            assertEquals("hello{world", message.getMessage()); 
            EncryptedMessage newMessage = new EncryptedMessage(message.getMessage(), "b");
            assertEquals("ifmmpaxpsme", newMessage.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }
    }

    @Test
    void testRollOverLongerKey() {
        
        EncryptedMessage message = new EncryptedMessage("hello world","ab");
        try {
            assertEquals("hflmoawprmd", message.getMessage()); 
            EncryptedMessage newMessage = new EncryptedMessage(message.getMessage(), "ab");
            assertEquals("hglnobwqrnd", newMessage.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }
    }

    @Test
    void testDecryptSingleLetterKey() {
        EncryptedMessage message = new EncryptedMessage("hello");
        try {
            assertEquals("jgnnq", message.decryptMessage("c"));
        } catch (Exception e) {
            fail("Unexpected exception on getMesage");
            e.printStackTrace();
        }

    }

   
}
