package bean;

/**
 * @Auther: husan
 * @Date: 2019/12/13 14:50
 * @Description:
 */
public class Person2 extends BaseInfo {

	private String name;

	private Integer age;

	private int status;

	public Person2(){

	}

	public Person2(Integer age){
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
