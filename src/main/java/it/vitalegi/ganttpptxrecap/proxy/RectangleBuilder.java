package it.vitalegi.ganttpptxrecap.proxy;

import java.awt.Rectangle;

/**
 * @author Giorgio Vitale
 */
public class RectangleBuilder {

	public static RectangleBuilder newInstance() {
		return new RectangleBuilder();
	}

	public Rectangle build() {
		return new Rectangle(x, y, width, height);
	}

	public RectangleBuilder setX(int x) {
		this.x = x;
		return this;
	}

	public RectangleBuilder setX(double x) {
		this.x = (int) x;
		return this;
	}

	public RectangleBuilder setY(int y) {
		this.y = y;
		return this;
	}

	public RectangleBuilder setY(double y) {
		this.y = (int) y;
		return this;
	}

	public RectangleBuilder setWidth(int width) {
		this.width = width;
		return this;
	}

	public RectangleBuilder setWidth(double width) {
		this.width = (int) width;
		return this;
	}

	public RectangleBuilder setHeight(int height) {
		this.height = height;
		return this;
	}

	public RectangleBuilder setHeight(double height) {
		this.height = (int) height;
		return this;
	}

	private int x;
	private int y;
	private int width;
	private int height;
}
