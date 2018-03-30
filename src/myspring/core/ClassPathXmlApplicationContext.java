package myspring.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ClassPathXmlApplicationContext implements BeanFactory {

	XmlConfig xmlConfig;
	String configLocation;
	List<Bean> beanList = new ArrayList();
	/**
	 * ioc����������װ��bean
	 */
	Map<String, Object> ioc = new HashMap();

	public ClassPathXmlApplicationContext(String configLocation) {
		this.configLocation = configLocation;
		try {
			readXml(configLocation);
			createAllBean();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Object getBean(String beanName) {
		return ioc.get(beanName);
	}

	/**
	 * ����Xml�ļ�
	 * 
	 * @param configLocation
	 * @throws DocumentException
	 */
	public void readXml(String configLocation) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(configLocation);

		// ����ȡ���ڵ㣬ֱ�ӻ�ȡbean��ǩ
		String xpath = "//bean";
		List<Element> list = document.selectNodes(xpath);
		for (Element beanEle : list) {
			Bean bean = new Bean();
			List<Attribute> beanAttrs = beanEle.attributes();
			String id = beanEle.attributeValue("id");
			String className = beanEle.attributeValue("class");
			bean.setId(id);
			bean.setClassName(className);
			List<Element> proList = beanEle.elements("property");
			if (proList != null) {
				for (Element proEle : proList) {
					Property prop = new Property();
					prop.setName(proEle.attributeValue("name"));
					prop.setValue(proEle.attributeValue("value"));
					prop.setRef(proEle.attributeValue("ref"));
					bean.getPropertys().add(prop);
				}
			}
			beanList.add(bean);
		}
	}

	/**
	 * ����bean��ʵ������
	 * 
	 * @param bean
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Object createBean(Bean bean) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class cls = Class.forName(bean.getClassName());
		Object obj = cls.newInstance();


		// ��bean�����Է�װ��ʵ��obj
		if (bean.getPropertys() != null) {
			
			//����setter�������Ա����ע��
			for (Property p : bean.getPropertys()) {
				Method method = null;
				method = BeanUtil.getSetterMethod(obj, p.getName());

				if (null != p.getValue()) {
					method.invoke(obj, p.getValue());
				}

				if (null != p.getRef()) {
					Object refObj = ioc.get(p.getRef());
					method.invoke(obj, refObj);
				}
			}
		}
		return obj;
	}

	/**
	 * ʵ��������bean
	 */
	public void createAllBean() {
		Object obj = null;
		if (beanList != null) {
			for (Bean bean : beanList) {
				try {
					obj = createBean(bean);
					ioc.put(bean.getId(), obj);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
