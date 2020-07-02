package bean;

import com.google.common.base.Stopwatch;
import com.husan.lang.ObjectUtil;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: husan
 * @Date: 2019/12/13 11:32
 * @Description:
 */
public class CopyTest {

	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
		Person person = buildTestPerson();
		int count = 100000000;
		testSpringBeanUtils(person, count);
	}

	public static Person buildTestPerson(){
		Person person = new Person(10);
		person.setName("husan");
		person.setStatus(1);
		person.setCreateDate(new Date());
		person.setId("1231223");
		return person;
	}

	/**
	 *
	 * @param person
	 * @param count
	 */
	public static void testCglibBeanCopy(Person person, int count){
		Stopwatch stopwatch = Stopwatch.createStarted();
		System.out.println("testCglibBeanCopy start!");
		BeanCopier copier = BeanCopier.create(Person.class, Person2.class, false);

		for(int i=0; i<count; i++){
			Person2 target = new Person2();
			copier.copy(person, target, null);
		}
		System.out.println("testCglibBeanCopy stop!");
		stopwatch.stop();
		System.out.println("testCglibBeanCopier 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}

	public static void testApacheBeanUtils(Person person, int count) throws InvocationTargetException, IllegalAccessException {
		Stopwatch stopwatch = Stopwatch.createStarted();
		System.out.println("testApacheBeanUtils start!");
		Person target = new Person();
		for(int i=0; i<count; i++){
			BeanUtils.copyProperties(target, person);
		}
		System.out.println("testApacheBeanUtils stop!");
		stopwatch.stop();
		System.out.println("testApacheBeanUtils 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}

	public static void testSpringBeanUtils(Person person, int count){
		Stopwatch stopWatch = Stopwatch.createStarted();
		System.out.println("testSpringBeanUtils start!");
		for(int i=0; i<count; i++){
			Person2 target = new Person2();
			org.springframework.beans.BeanUtils.copyProperties(person, target);
		}
		System.out.println("testSpringBeanUtils stop!");
		stopWatch.stop();
		System.out.println("testSpringBeanUtils 耗时: " + stopWatch.elapsed(TimeUnit.MILLISECONDS));
	}

	public static void testFieldSet(Person person, int count) throws IllegalAccessException {
		Stopwatch stopWatch = Stopwatch.createStarted();
		System.out.println("testFieldSet start!");
		for(int i=0; i<count; i++){
			Person target = new Person();
			ObjectUtil.copy(person, target);
		}
		System.out.println("testFieldSet stop!");
		stopWatch.stop();
		System.out.println("testFieldSet 耗时: " + stopWatch.elapsed(TimeUnit.MILLISECONDS));
	}
}
