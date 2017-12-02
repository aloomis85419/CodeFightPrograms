import java.util.Arrays;

/**
 * Created by aloom on 12/1/2017.
 */
public class BitPacker {


    public static void main(String[]args){
        int[]i = {24, 0, 1};
      BitPacker bp = new BitPacker();
      bp.arrayPacking(i);

    }
    int arrayPacking(int[] a) {
        String[]intsAsBytes = new String[a.length];
        String packed = "";
        String pad = "";
        String finalVal = "";
        int num = 0;
        int n = 0;
        for(int i = 0; i < a.length; i++){
           packed = Integer.toBinaryString(a[i]);
           intsAsBytes[i] = packed;
        }
        for(int i = 0; i < intsAsBytes.length; i++){
            int padNum = 8 - intsAsBytes[i].length();
            while(padNum > 0){
                pad += 0;
                padNum --;
            }
            intsAsBytes[i] = pad + intsAsBytes[i];
            pad = "";
        }
        for(int k = intsAsBytes.length - 1; k >= 0; k--) {
            finalVal += intsAsBytes[k];
        }
        for(int i = finalVal.length() - 1; i > 0 ; i--){
            if (finalVal.charAt(i) == '1') {
                num += Math.pow(2, n);
            }
            n++;
        }
        System.out.println(num);
        return num;
    }
    /*
        better solution since mine was long
        int arrayPacking(int[] a) {
    int b = 0;
    for (int i = a.length; i-->0; ) {
        b *= 256;
        b += a[i];
    }
    return b;
}
     */

}
