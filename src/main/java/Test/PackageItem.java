package Test;

public class PackageItem {
	private String name;
	private PackageState state;
	private String size;

	private Person person;
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public PackageState getState() {
		return state;
	}
 
	public void setState(PackageState state) {
		this.state = state;
	}
 
	public String getSize() {
		return size;
	}
 
	public void setSize(String size) {
		this.size = size;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "PackageItem [name=" + name + ", size=" + size + ", state="
				+ state + "]";
	}
}
