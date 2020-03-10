package by.epam.booking.service.user;

import com.sun.org.apache.bcel.internal.classfile.Code;

import java.util.HashMap;
import java.util.Map;

public enum CodeGenerator {

    GENERATOR;

    private final static int MAX = 90;
    private final static int MIN = 65;
    private Map<String, String> codes = new HashMap<>();

    public String getCode(String sessionId) {
        return codes.get(sessionId);
    }

    public void generateCode(String sessionId) {
        char[] chars = new char[6];

        chars[0] = (char) ((int) (Math.random() * (MAX - MIN + 1)) + MIN);
        chars[1] = (char) ((int) (Math.random() * (MAX - MIN + 1)) + MIN);
        chars[2] = (char) ((int) (Math.random() * (57 - 48 + 1)) + 48);
        chars[3] = (char) ((int) (Math.random() * (57 - 48 + 1)) + 48);
        chars[4] = (char) ((int) (Math.random() * (MAX - MIN + 1)) + MIN);
        chars[5] = (char) ((int) (Math.random() * (MAX - MIN + 1)) + MIN);

        codes.put(sessionId, String.valueOf(chars));
    }

    public boolean codeConfirm(String sessionId,String code){
        if(codes.containsKey(sessionId)){
            return codes.get(sessionId).equals(code);
        }else {
            return false;
        }
    }
}