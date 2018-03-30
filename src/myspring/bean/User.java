package myspring.bean;

public class User {

	private String userName;
	private Address address;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", address=" + address + "]";
	}

}
