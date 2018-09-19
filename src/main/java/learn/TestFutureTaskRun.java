package learn;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2016/11/23
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class TestFutureTaskRun {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            3, 6, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));


    public static List<FutureTask> produceList(){
        List<FutureTask> list = new ArrayList<>();
        for(int i=0; i<100;i++){
            list.add(new FutureTask());
        }
        return list;
    }

    public static void main(String[] args) throws Exception{
        List<FutureTask> taskList = produceList();
        for(FutureTask task : taskList){
            executor.execute(task);
        }
        System.out.println("work shubmit");
        for(FutureTask task : taskList){
            System.out.println(task.get(10,TimeUnit.MILLISECONDS));
        }

    }
}
