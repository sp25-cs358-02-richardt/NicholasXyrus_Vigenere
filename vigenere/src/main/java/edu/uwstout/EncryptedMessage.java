package edu.uwstout;

    /*
     * EncryptedMessage class encrypt and decrypt a message with a key using a vigenere cipher
     * @author Nick, Xyrus
     */ 
public class EncryptedMessage {

    String mEncryptedMsg;  //holds the encrypted message

    /*
     * Constructor for EncryptedMessage
     * @param msg the unencrypted message
     * @param key the encryption key
     */
    public EncryptedMessage(String msg, String key) {
        // Regex that determines that if the key does not contain only letters if that is the case
        // set mEncryptedMsg to null and return.
        if (!key.matches("[a-zA-Z]+")){
            mEncryptedMsg = null;
            return;
        }
        
        // replaces spaces with '{', any invalid character with a blank space, sets all the text
        // to lowercase and finally converts the msg to a character array msgArray.
        msg = msg.replace(' ', '{').toLowerCase();
        msg = msg.replaceAll("[^A-Za-z\\{]","");
        key = key.toLowerCase();
        char [] msgArray = msg.toCharArray();
        for(int i = 0; i < msg.length(); i++) {
            // If statement is used to check that msgArray[i] is a letter or '{' if not it's skipped
            if(Character.isLetter(msgArray[i]) || msgArray[i] == '{') {
                // Fetches the offset and limits it to the range of key's length.
                int offset = key.charAt(i % key.length());
                if(Character.isLetter(offset) || offset == '{') {
                    // Checks to see if msgArray[i] is the last possible character and that
                    // the offset would move it beyond the possible letters then offset msgArray[i]
                    // back by 'a' - 1
                    if(msgArray[i] == '{' && offset > 'a') {
                        msgArray[i] = 'a' - 1;
                    }
                // subtract 'a' from the offset so that the offset is aligned starting from 0
                // rather than 97 add add it to msgArray[i].
                offset -= 'a';
                msgArray[i] += offset;
                }
            }
        }
        // convert the character array msgArray into a String and replace mEncryptedMsg with the value.
        mEncryptedMsg = new String(msgArray);
    }

    /*
     * Constructor for EncryptedMessage
     * @param encryptedMsg the encrypted message
     */
    public EncryptedMessage(String encryptedMsg) {
        mEncryptedMsg = encryptedMsg;
    }

    /*
     * The mEncryptedMsg of EncryptedMessage
     * @return the encrypted message
     * @throws "Unauthorized Use" if mEncryptedMsg is null
     */
    public String getMessage() throws Exception {
        if (mEncryptedMsg==null) {
            throw new Exception("Unauthorized Use");
        }
        return mEncryptedMsg;
    }

    /*
     * Decrypts EncryptedMessage's mEncryptedMsg using a key
     * @param key the encryption key
     * @return the decrypted message
     * @throws "Unauthorized Use" if mEncryptedMsg is null
     */
    public String decryptMessage(String key) throws Exception {
        if (!key.matches("[a-zA-Z]+")){
            throw new Exception("Unauthorized Use");
        }

        key = key.toLowerCase();
        // removes the invalid characters from mEncryptedMsg sets it to lowercase and creates the
        // char array msgArray using the chars in the String.
        char [] msgArray = mEncryptedMsg.replaceAll("[^A-Za-z\\{]","").toLowerCase().toCharArray();
        for (int i=0; i < msgArray.length; i++) {
            if (Character.isLetter(msgArray[i]) || msgArray[i] == '{') {
                // The offset is limited to the range of key's length.
                int offset = key.charAt(i % key.length());
                // The first lowercase letter ascii is subtracted from the offset  
                msgArray[i] -= offset - 'a';
                // msgArray[i] is increased by 11 so that 'a' will result in 0 when modded by 27
                // then 97 is added to align it back with the lower case letter.
                msgArray[i] = (char) ((msgArray[i] + 11) % 27 + 97);
                // If the char '{' if found replace it it a space char ' '. 
                if (msgArray[i] == '{') {
                    msgArray[i] = ' ';
                }
            }
        }
        // The decrypted message.
        return new String(msgArray);
    }
}
