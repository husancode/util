package util;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *     分布式主键生成:唯一，顺序递增
 *     0---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---0000000000 00
 *     一共64位，第一位符号位0
 *     41位--为精确到毫秒级的时间---可以到Wed Sep 07 23:47:35 CST 2039
 *     10位机器id: 5位datacenter标识位+5位机器id ：可以连接zookeeper获取顺序节点保持唯一性
 *     12位自然递增计数：1毫秒内可以有2^12（4096）个不重复的id
 *

 *
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @modification History=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class IdGenerator {

    private static final long workIdBits = 10L;
    private static final long sequenceBits = 12L;
    private static final long timeStampLeftShift = workIdBits + sequenceBits;
    private static final long workidLeftShift = sequenceBits;
    private static final long sequenceMask = -1L ^ (-1L<< sequenceBits);
    private static final long maxWorkId = -1L ^ -1L<<workIdBits;

    private long workId;
    private long sequenceId = 0L;
    private long lastTimeStamp = -1L;

    public IdGenerator(long workId){
        if(workId <0 || workId > maxWorkId){
            throw new IllegalArgumentException(
                    String.format("workId can't be grater than %s or less than 0", maxWorkId));
        }
        this.workId = workId;
    }

    public  synchronized long nextId() throws Exception{
        long timeStamp = timeGen();
        if(timeStamp < lastTimeStamp){
            throw new Exception(String.format("Clock moved backwards for %d milliseconds", this.lastTimeStamp - timeStamp));
        }
        if(this.lastTimeStamp == timeStamp){
            this.sequenceId = (this.sequenceId+1) & this.sequenceMask;
            if(this.sequenceId == 0){
                timeStamp = tillNextStamp(this.lastTimeStamp);
            }
        }else{
            this.sequenceId = 0;
        }

        this.lastTimeStamp = timeStamp;

        return (timeStamp<< timeStampLeftShift) | (workId<< workidLeftShift) | sequenceId;
    }

    /**
     * 错误： this.sequenceId++ 返回的值不会递增
     * 可用  ++this.sequenceId
     */

    private  long incrSequence(){
        return (this.sequenceId++) & sequenceMask;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }

    private long tillNextStamp(long lastTimeStamp){
        long timeStamp = timeGen();
        while(timeStamp <= lastTimeStamp){
            timeStamp = timeGen();
        }
        return timeStamp;
    }

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        Set<Long> set = new HashSet<Long>(1000000);
        IdGenerator idGenerator = new IdGenerator(1);
        for(long i=0; i< 1000000L; i++){
            long id = idGenerator.nextId();
            System.out.println(id);
            if(set.contains(id)){
                System.err.println("------");
                System.exit(-1);
            }
            set.add(id);
        }
        long end = System.currentTimeMillis();
        System.out.println("cost time :"+ (end-start));

    }
}
