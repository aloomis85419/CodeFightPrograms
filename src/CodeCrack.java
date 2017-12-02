import java.util.Arrays;

/**
 * Created by aloom on 12/1/2017.
 */
public class CodeCrack {

    public static void main(String[]args){
        CodeCrack cc = new CodeCrack();
        System.out.println(cc.killKthBit(37,4));
    }
/*
    int killKthBit(int n, int k)
    {
        Character c;
        String binary = Integer.toBinaryString(n);
        Integer[]bits = new Integer[binary.length()];
        int pos = binary.length() - k;
        for (int i = 0; i < binary.length(); i++) {
            c = binary.charAt(i);
            bits[i] = Integer.parseInt(c.toString());
        }
        if(bits[pos] == 1){
            bits[pos] = 0;
        }
        n = 0;
        for(int i = 0; i < bits.length; i++){
            if (bits[i] == 1){
                int power = (int)Math.pow(2,i);
                n += power;
            }
        }
        return n;
    }
    */


    int killKthBit(int n, int k) {
        System.out.println(n & ~(1 << k - 1));
        return n;
    }
}
