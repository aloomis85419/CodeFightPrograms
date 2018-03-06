/**
 * Created by aloom on 12/1/2017.
 */
public class HammingWeight {

    public static void main(String[]args){
        HammingWeight hw = new HammingWeight();
        System.out.println(hw.rangeBitCount(3,10));
    }

    int rangeBitCount(int a, int b) {
        int hammingWeight = 0;
        String finalVal = "";
        for(int i = a; i <= b; i++){
            finalVal += Integer.toBinaryString(i);
        }
        System.out.println(finalVal);
        for(int i = 0; i < finalVal.length(); i++){
            if(finalVal.charAt(i) == '1'){
                hammingWeight++;
            }
        }
        return hammingWeight;
    }
    /*
    more efficient solution
    int rangeBitCount(int a, int b) {
        int t = 0;
        for (int i = a; i<=b; i++) {
            t += Integer.bitCount(i);
        }
        return t;
    }
    */
}
