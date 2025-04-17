package edu.uwstout;

public class EncryptedMessage {

    String mEncryptedMsg;  //holds the encrypted message

    public EncryptedMessage(String msg, String key) {
        //warning:  do not store the unencrytped message in a 
        //  private member variable.
        try {
        msg = msg.replace(' ', '{').toLowerCase();
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
                else {
                    throw new Exception();
                }
            }
            else {
                throw new Exception();
            }
        }
        mEncryptedMsg = new String(msgArray);
        }
        catch (Exception e) {
            mEncryptedMsg = null;

        }

    }

    public EncryptedMessage(String encryptedMsg) {
        //warning:  do not store the unencrytped message in a 
        //  private member variable.
        mEncryptedMsg = encryptedMsg;
    }

    public String getMessage() throws Exception {
        return mEncryptedMsg;
    }

    public String decryptMessage(String key) throws Exception {
        key = key.toLowerCase();
        char [] msgArray = mEncryptedMsg.toLowerCase().toCharArray();
        try {
            for (int i=0; i < msgArray.length; i++) {
                if (Character.isLetter(msgArray[i]) || msgArray[i] == '{') {
                    int offset = key.charAt(i % key.length());
                    msgArray[i] += 'a' - offset;
                } else {
                    throw new Exception();
                }
            }
            return new String(msgArray);
        }
        catch (Exception e) {
            mEncryptedMsg = null;

        }
        return null;
    }

    //add any private helper methods that you would like to use
}
