package edu.uwstout;

public class EncryptedMessage {

    String mEncryptedMsg;  //holds the encrypted message

    public EncryptedMessage(String msg, String key) {
        //warning:  do not store the unencrytped message in a 
        //  private member variable.

        if (!key.matches("[a-zA-Z]+")){
            mEncryptedMsg = null;
            return;
        }
        
        msg = msg.replace(' ', '{').toLowerCase();
        msg = msg.replaceAll("[^A-Za-z\\{]","");
        key = key.toLowerCase();
        char [] msgArray = msg.toCharArray();
        for(int i = 0; i < msg.length(); i++) {
            if(Character.isLetter(msgArray[i]) || msgArray[i] == '{') {
                int offset = key.charAt(i % key.length());
                if(Character.isLetter(offset) || offset == '{') {
                    if(msgArray[i] == '{' && offset > 'a') {
                        msgArray[i] = 'a' - 1;
                    }
                offset -= 'a';
                msgArray[i] += offset;
                }
            }
        }
        mEncryptedMsg = new String(msgArray);
        

    }

    // EncryptedMessage constructor takes encryptedMsg and places it in the mEncryptedMsg variable.
    public EncryptedMessage(String encryptedMsg) {
        mEncryptedMsg = encryptedMsg;
    }

    public String getMessage() throws Exception {
        if (mEncryptedMsg==null) {
            throw new Exception("Unauthorized Use");
        }
        return mEncryptedMsg;
    }

    // Decrypts the mEncryptedMsg variable using key. If the key has character that are not letters
    // or '{' then the Unauthorized Use Exception is thown. Invalid characters as defined previously
    // are instead dropped if contained in mEncryptedMsg. 
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
                // The offset is limited to the range on key's length.
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
