
package Test;

import java.util.Date;

/**
 * <p>
 *     new Date().getTime();
 *     随时间预热，执行次数递增。
 *     1毫秒大概可执行86049次
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @modification History=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class TimeGeneratorTest {

    private static long lastTime = 0L;

    private static long count = 0;

    private static long maxCount = 0;

    public static long timeGen(){
        return new Date().getTime();
    }

    public static void main(String[] args) {

            for(long i=0; i<10000000L ; i++){
                long time = timeGen();
                if(lastTime == time){
                    count++;
                }else{
                    lastTime = time;
                    if(maxCount < count){
                        maxCount = count;
                    }
                    count=0;
                    System.out.println(maxCount);
                }
            }


    }

}
