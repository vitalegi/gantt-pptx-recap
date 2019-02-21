package it.vitalegi.ganttpptxrecap.bean.area;

import java.awt.Rectangle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giorgio Vitale
 */
@Getter
@Setter
@ToString
public class AreaImpl extends Area {

	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
