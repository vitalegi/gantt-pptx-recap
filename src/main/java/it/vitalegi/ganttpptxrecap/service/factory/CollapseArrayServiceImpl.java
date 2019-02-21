package it.vitalegi.ganttpptxrecap.service.factory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;

import org.springframework.stereotype.Component;

/**
 * @author Giorgio Vitale
 */
@Component
public class CollapseArrayServiceImpl {

	public <E> E join(Class<E> clazz, @SuppressWarnings("unchecked") E... objs) {

		System.out.println("> process");
		E join = newInstance(clazz);

		PropertyDescriptor[] propertiesDescriptor = getPropertiesDescriptor(clazz);

		Function<PropertyDescriptor, Object> getFirstValue = getFirstNonNullValue(objs);

		Arrays.stream(propertiesDescriptor) //
				.map(log("> Property: ", PropertyDescriptor::getName)) //
				.forEach(pd -> {
					setValue(pd, join, getFirstValue.apply(pd));
				});

		return join;
	}

	private <E> E newInstance(Class<E> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private <E> PropertyDescriptor[] getPropertiesDescriptor(Class<E> clazz) {
		try {
			return Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
		} catch (IntrospectionException e1) {
			throw new RuntimeException(e1);
		}
	}

	private <T> Function<T, T> log(String prefix, Function<T, String> consumer) {
		return (e) -> {
			System.out.println(prefix + consumer.apply(e));
			return e;
		};
	}

	private void setValue(PropertyDescriptor pd, Object obj, Object value) {

		Method setter = pd.getWriteMethod();
		try {
			setter.invoke(obj, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("Errore nel set del valore di " + pd.getName() + " su " + obj, e);
		}
	}

	private <E> Function<PropertyDescriptor, Object> getFirstNonNullValue(E[] objs) {
		return (pd) -> {
			for (Object obj : objs) {
				Object value = getValue(pd, obj);
				if (value != null) {
					return value;
				}
			}
			return null;
		};
	}

	private static Object getValue(PropertyDescriptor pd, Object obj) {
		if (obj == null) {
			return null;
		}
		Method getter = pd.getReadMethod();
		try {
			return getter.invoke(obj);
		} catch (Exception e) {
			throw new RuntimeException("Errore nel recupero del valore di " + pd.getName() + " da " + obj, e);
		}
	}
}
