package myspring.core;

import java.util.ArrayList;
import java.util.List;

public class Bean {
	
	private String id;
	
	private String className;
	
	private List<Property> propertys = new ArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Property> getPropertys() {
		return propertys;
	}

	public void setPropertys(List<Property> propertys) {
		this.propertys = propertys;
	}

	@Override
	public String toString() {
		return "Bean [id=" + id + ", className=" + className + ", propertys=" + propertys + "]";
	}
	
	
	
}
