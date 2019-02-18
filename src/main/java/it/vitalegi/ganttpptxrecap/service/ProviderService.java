package it.vitalegi.ganttpptxrecap.service;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Giorgio Vitale
 */
@Service
public class ProviderService {

	@Autowired
	private ApplicationContext ctx;

	public <T> T getBeanByType(final Class<T> claz) throws UnsupportedOperationException, BeansException {
		Map<String, T> beansOfType = ctx.getBeansOfType(claz);
		final int size = beansOfType.size();
		switch (size) {
		case 0:
			throw new UnsupportedOperationException("No bean found of type" + claz);
		case 1:
			String name = (String) beansOfType.keySet().iterator().next();
			return claz.cast(ctx.getBean(name, claz));
		default:
			throw new UnsupportedOperationException("Ambigious beans found of type" + claz);
		}
	}
}
