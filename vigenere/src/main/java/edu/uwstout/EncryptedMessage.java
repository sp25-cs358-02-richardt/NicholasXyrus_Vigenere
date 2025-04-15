package edu.uwstout;

public class EncryptedMessage {

    String mEncryptedMsg;  //holds the encrypted message

    public EncryptedMessage(String msg, String key) {
        //warning:  do not store the unencrytped message in a 
        //  private member variable.
        msg = msg.toLowerCase();
        key = key.toLowerCase();
        char [] msgArray = msg.toCharArray();
        for(int i = 0; i < msg.length(); i++) {
            int offset = key.charAt(i % key.length()) - 'a';
            msgArray[i] += offset;
        }

        mEncryptedMsg = new String(msgArray);

    }

    public EncryptedMessage(String encryptedMsg) {
        //warning:  do not store the unencrytped message in a 
        //  private member variable.

    }

    public String getMessage() throws Exception {
        return mEncryptedMsg;
    }

    public String decryptMessage(String key) throws Exception {
        return null;
    }

    //add any private helper methods that you would like to use
}
