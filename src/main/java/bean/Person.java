package bean;

/**
 * @Auther: husan
 * @Date: 2019/12/13 11:31
 * @Description:
 */
public class Person extends BaseInfo {

	private String name;

	private Integer age;

	private int status;

	public Person(){

	}

	public Person(Integer age){
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
