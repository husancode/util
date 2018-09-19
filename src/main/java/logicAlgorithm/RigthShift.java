
package logicAlgorithm;

/**
 * <p>
 *     字符数组循环右移N位，
 *     时间复杂度o(n)，空间复杂度o(n);
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @modification History=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class RigthShift {

    private static char[] target = new char[]{'1','2','3','4','5','6','7','8'};

    private static String targetStr = "12345678";

    private static char[] arrayRightShift(int shiftNum){
        char[] c = new char[target.length];
        int size = target.length;
        shiftNum = shiftNum%size;
        if(shiftNum > 0){
            int index = size-shiftNum;
            for(int i=0;i<size;i++){
                c[i] = target[index];
                index = (index+1)%size;
            }
        }else{
            return target;
        }
        return c;
    }

    private static String stringRightShift(int shiftNum){
        shiftNum = shiftNum%targetStr.length();
        if(shiftNum > 0){
            int index = targetStr.length() - shiftNum;
            return targetStr.substring(index)+ targetStr.substring(0, index);
        }else{
            return targetStr;
        }
    }


    public static void main(String[] args) {
        System.out.println(arrayRightShift(7));
        System.out.println(stringRightShift(7));
    }
}
