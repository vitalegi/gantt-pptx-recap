package it.vitalegi.ganttpptxrecap.proxy;

import java.awt.Color;
import java.awt.Rectangle;

import org.apache.poi.sl.usermodel.LineDecoration.DecorationSize;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.TextShape.TextAutofit;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;

import lombok.AllArgsConstructor;

/**
 * @author Giorgio Vitale
 */
@AllArgsConstructor
public class AutoShapeBuilder<E extends XSLFAutoShape> {

	protected E shape;

	public AutoShapeBuilder<E> setText(String text) {
		shape.setText(text);
		return this;
	}

	public AutoShapeBuilder<E> setAnchor(Rectangle anchor) {
		shape.setAnchor(anchor);
		return this;
	}

	public AutoShapeBuilder<E> setAnchor(int x, int y, int width, int height) {
		shape.setAnchor(new Rectangle(x, y, width, height));
		return this;
	}

	public AutoShapeBuilder<E> setHorizontalCentered(Boolean horizontalCentered) {
		shape.setHorizontalCentered(horizontalCentered);
		return this;
	}

	public AutoShapeBuilder<E> setShapeType(ShapeType shapeType) {
		shape.setShapeType(shapeType);
		return this;
	}

	public AutoShapeBuilder<E> setFillColor(Color color) {
		shape.setFillColor(color);
		return this;
	}

	public AutoShapeBuilder<E> setLineColor(Color color) {
		shape.setLineColor(color);
		return this;
	}

	public AutoShapeBuilder<E> setLineHeadLength(DecorationSize decorationSize) {
		shape.setLineHeadLength(decorationSize);
		return this;
	}

	public AutoShapeBuilder<E> setTextAutofit(TextAutofit textAutofit) {
		shape.setTextAutofit(textAutofit);
		return this;
	}

	public AutoShapeBuilder<E> setVerticalAlignment(VerticalAlignment verticalAlignment) {
		shape.setVerticalAlignment(verticalAlignment);
		return this;
	}

	public XSLFTextParagraph addNewTextParagraph() {
		return shape.addNewTextParagraph();
	}

	public XSLFTextParagraph getLastTextParagraph() {
		return shape.getTextParagraphs().get(shape.getTextParagraphs().size() - 1);
	}

}
