package myspring.core;

import java.lang.reflect.Method;

public class BeanUtil {

	public static Method getSetterMethod(Object obj,String propName){
		Method method = null;
		String methodName = "set"+propName.substring(0, 1).toUpperCase() + propName.substring(1);
		Method[] methods = obj.getClass().getMethods();
		
		for(int i=0;i<methods.length;i++){
			method = methods[i];
			if(method.getName().equals(methodName)){
				return method;
			}
		}
		
		return null;
	}
}
