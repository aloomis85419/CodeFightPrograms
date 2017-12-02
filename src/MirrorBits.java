import java.util.Arrays;

/**
 * Created by aloom on 12/1/2017.
 */
public class MirrorBits {

    public static void main(String[]args){
        MirrorBits mb = new MirrorBits();
        mb.mirrorBits(10);
    }

    int mirrorBits(int a) {
        String binary = Integer.toBinaryString(a);
        char[]c = binary.toCharArray();
        char c1;
        int start = 0;
        for (int i = binary.length() - 1; i > start; i--){
            c1 = c[start];
            c[start] = c[i];
            c[i] = c1;
            start++;
        }
        binary = String.copyValueOf(c);
        a= Integer.parseInt(binary,2);//convert back to base 2;
        System.out.println(a);
        return  a;
    }


/*playing with profiling and performance management
    int mirrorBits(int a) {
        int b = 0;
        while (a > 0) {
            b <<= 1;
            b |= a & 1;
            a >>= 1;
        }

        return b;
    }
    */

}
