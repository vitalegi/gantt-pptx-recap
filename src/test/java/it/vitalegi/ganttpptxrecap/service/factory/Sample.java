package it.vitalegi.ganttpptxrecap.service.factory;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Giorgio Vitale
 */
@Getter
@Setter
public class Sample {

	public static Sample build(String a, Sample b) {

		Sample obj = new Sample();
		obj.setA(a);
		obj.setB(b);
		return obj;
	}

	private String a;
	private Sample b;
}
