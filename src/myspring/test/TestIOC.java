package myspring.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import myspring.bean.Address;
import myspring.bean.User;
import myspring.core.Bean;
import myspring.core.BeanFactory;
import myspring.core.ClassPathXmlApplicationContext;
import myspring.core.Property;

public class TestIOC {


	@Test
	public void testCpac() {
		String configLocation = "src/applicationContext.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		User user =(User)context.getBean("user");
		Address address = (Address)context.getBean("address");
		System.out.println(address);
		System.out.println(user.toString());
	}



}











