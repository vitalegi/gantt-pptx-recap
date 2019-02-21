package it.vitalegi.ganttpptxrecap.bean;

import it.vitalegi.ganttpptxrecap.bean.area.Area;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giorgio Vitale
 */
@Getter
@Setter
@ToString
public class Label extends Style {
	private String format;
	private Area relativePosition;
}
