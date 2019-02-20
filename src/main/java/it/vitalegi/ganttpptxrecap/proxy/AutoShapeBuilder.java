package it.vitalegi.ganttpptxrecap.proxy;

import java.awt.Color;
import java.awt.Rectangle;

import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.LineDecoration.DecorationSize;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;

import lombok.AllArgsConstructor;

/**
 * @author Giorgio Vitale
 */
@AllArgsConstructor
public class AutoShapeBuilder {

	protected XSLFAutoShape shape;

	public AutoShapeBuilder setText(String text) {
		shape.setText(text);
		return this;
	}

	public AutoShapeBuilder setAnchor(Rectangle anchor) {
		shape.setAnchor(anchor);
		return this;
	}

	public AutoShapeBuilder setAnchor(int x, int y, int width, int height) {
		shape.setAnchor(new Rectangle(x, y, width, height));
		return this;
	}

	public AutoShapeBuilder setHorizontalCentered(Boolean horizontalCentered) {
		shape.setHorizontalCentered(horizontalCentered);
		return this;
	}

	public AutoShapeBuilder setShapeType(ShapeType shapeType) {
		shape.setShapeType(shapeType);
		return this;
	}

	public AutoShapeBuilder setFillColor(Color color) {
		shape.setFillColor(color);
		return this;
	}

	public AutoShapeBuilder setLineColor(Color color) {
		shape.setLineColor(color);
		return this;
	}

	public AutoShapeBuilder setLineHeadLength(DecorationSize decorationSize) {
		shape.setLineHeadLength(decorationSize);
		return this;
	}
}
