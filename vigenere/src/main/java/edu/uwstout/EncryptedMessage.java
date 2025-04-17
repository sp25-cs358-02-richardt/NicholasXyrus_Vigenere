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

    public EncryptedMessage(String encryptedMsg) {
        //warning:  do not store the unencrytped message in a 
        //  private member variable.
        mEncryptedMsg = encryptedMsg;
    }

    public String getMessage() throws Exception {
        if (mEncryptedMsg==null) {
            throw new Exception("Unauthorized Use");
        }
        return mEncryptedMsg;
    }

    public String decryptMessage(String key) throws Exception {
        if (!key.matches("[a-zA-Z]+")){
            mEncryptedMsg = null;
            return null;
        }
        key = key.toLowerCase();
        
        char [] msgArray = mEncryptedMsg.replaceAll("[^A-Za-z\\{]","").toLowerCase().toCharArray();
        for (int i=0; i < msgArray.length; i++) {
            if (Character.isLetter(msgArray[i]) || msgArray[i] == '{') {
                int offset = key.charAt(i % key.length());
                if (Character.isLetter(offset) || offset == '{') {
                    msgArray[i] += 'a' - offset;
                    if (msgArray[i] < 'a') {
                        msgArray[i] = (char) (msgArray[i] % 27 + 108);
                    }
                    if (msgArray[i] == '{') {
                        msgArray[i] = ' ';
                    }
                }
            }
        }
        return new String(msgArray);
    }

    //add any private helper methods that you would like to use
}
