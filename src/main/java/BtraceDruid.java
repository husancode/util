import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;


/**
 * @Auther: husan
 * @Date: 2019/8/19 14:15
 * @Description:
 */

@BTrace
public class BtraceDruid {

	private static long count;

	private static long recyleCount;

	private static Object object;

	@OnMethod(clazz="com.alibaba.druid.pool.DruidDataSource",
			method = "getConnection",
			location =@Location(Kind.RETURN))
	public static void traceGetConnection(@Self Object self, @Return Object result, @Duration long time){
		BTraceUtils.println("getConnection: "+ result+",self:"+ BTraceUtils.classOf(self));
		object = self;
		count++;
	}

	@OnMethod(clazz="com.alibaba.druid.pool.DruidDataSource",
			method = "recycle",
			location =@Location(Kind.RETURN))
	public static void traceRecycleConnection(@Self Object self, Object connection){
		BTraceUtils.println("recycle conn:"+ connection+",self:"+ BTraceUtils.classOf(self));
		recyleCount++;
	}

	@OnTimer(1000*10)
	public static void print(){
		BTraceUtils.println("counter:"+count);
		BTraceUtils.println("recycle:"+recyleCount);
		if(object != null){
			BTraceUtils.println("connectCount:"+BTraceUtils.getFloat(BTraceUtils.field(BTraceUtils.classOf(object),
					"connectCount"), object));
			BTraceUtils.println("recycleCount:"+BTraceUtils.getFloat(BTraceUtils.field(BTraceUtils.classOf(object),
					"recycleCount"), object));
			BTraceUtils.println("activePeak:"+BTraceUtils.getFloat(BTraceUtils.field(BTraceUtils.classOf(object),
					"activePeak"), object));
			BTraceUtils.println("poolingPeak:"+BTraceUtils.getFloat(BTraceUtils.field(BTraceUtils.classOf(object),
					"poolingPeak"), object));
			BTraceUtils.println("poolingCount:"+BTraceUtils.getFloat(BTraceUtils.field(BTraceUtils.classOf(object),
					"poolingCount"), object));
			BTraceUtils.println("activeCount:"+BTraceUtils.getFloat(BTraceUtils.field(BTraceUtils.classOf(object),
					"activeCount"), object));
			BTraceUtils.println();
		}

	}

}
