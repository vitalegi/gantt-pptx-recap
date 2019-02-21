package it.vitalegi.ganttpptxrecap.bean;

import org.apache.poi.sl.usermodel.ShapeType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giorgio Vitale
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ShapeStyle extends Style {

	private ShapeType shapeType;
}
