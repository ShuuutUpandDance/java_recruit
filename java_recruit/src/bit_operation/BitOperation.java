package bit_operation;

/**
 * Created by Zhang on 2017/3/6.
 */
public class BitOperation {
    static final int N = 1000; //1~N
    public static void main(String[] args) {
        int[] data = new int[998];

        createData(data, 10, 100);  //尽量提高复用性
        findNums(data, data.length);
    }

    public static void createData(int[] data, int num1, int num2) { //指定没有哪两个数，可惜没法变成无序
        int count = 1;
        for (int i = 0; i < data.length; i++){
            if (count == num1 || count == num2) {
                count ++;
                data[i] = count;
            }
            data[i] = count;
            count ++;
        }

    }

    public static void findNums(int[] data, int length) {

        //先算正常1~N的异或值
        int normalXor = 0;
        for (int i = 1; i <= N; i++) {
            normalXor ^= i;
        }

        //再算data的异或值
        int dataXor = 0;
        for (int i = 0; i < length; i++) {
            dataXor ^= data[i];
        }

        //normalXor与dataXor异或，得缺失值的异或值
        int lostXor = normalXor ^ dataXor;

        //找到lostXor的第一个1位置
        int pos = findFirstOne(lostXor);

        int normal_num1_Xor = 0;
        int normal_num2_Xor = 0;
        for (int i = 1; i <= N; i++) {
            if(testBit(i,pos))
                normal_num1_Xor ^= i;
            else
                normal_num2_Xor ^= i;
        }

        int data_num1_Xor = 0;
        int data_num2_Xor = 0;
        for (int i = 0; i < length; i++){
            if (testBit(data[i],pos))
                data_num1_Xor ^= data[i];
            else
                data_num2_Xor ^= data[i];
        }

        System.out.println("the two numbers are "+ (normal_num1_Xor^data_num1_Xor) +" and " + (normal_num2_Xor ^ data_num2_Xor));
    }

    public static int findFirstOne(int value)  //取二进制中首个为1的位置
    {
        int pos=0;
        while((value&1)!=1)
        {
            value = value>>1;
            pos++;
        }
        return pos;
    }

    public static boolean testBit(int value,int pos) //测试某位是否为1
    {
        return ((value>>pos)&1) != 0;
    }
}
