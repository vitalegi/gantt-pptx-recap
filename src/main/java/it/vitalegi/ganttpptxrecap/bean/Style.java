package it.vitalegi.ganttpptxrecap.bean;

import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;

import it.vitalegi.ganttpptxrecap.bean.area.Area;
import it.vitalegi.ganttpptxrecap.bean.color.ColorPalette;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giorgio Vitale
 */
@Getter
@Setter
@ToString
public class Style {

	private Double fontSize;
	private TextAlign textAlign;
	private Area area;
	private ColorPalette fillColor;
	private ColorPalette lineColor;
}
