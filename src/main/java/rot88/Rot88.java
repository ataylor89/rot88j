package rot88;

public class Rot88 {
    
    public Rot88() {}

    public String rot88(String message) {
        String result = "";
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            int sourceCodePoint = (int) ch;
            int targetCodePoint = (sourceCodePoint + 0x88000) % 0x110000;
            result += (char) targetCodePoint;
        }
        return result;
    }

}
