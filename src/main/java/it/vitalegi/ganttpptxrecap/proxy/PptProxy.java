package it.vitalegi.ganttpptxrecap.proxy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFConnectorShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.vitalegi.ganttpptxrecap.bean.Style;

/**
 * @author Giorgio Vitale
 */
@Component
@Scope(value = "prototype")
public class PptProxy {

	private XMLSlideShow ppt;

	public void createPresentation() {

		ppt = new XMLSlideShow();

	}

	public Dimension getPageSize() {
		return ppt.getPageSize();
	}

	public void savePresentation(OutputStream os) {

		try {
			ppt.write(os);
			os.close();
			ppt.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void savePresentation(String outputFile) {

		OutputStream os;
		try {
			os = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		savePresentation(os);
	}

	public List<XSLFSlideLayout> getLayouts() {

		List<XSLFSlideLayout> layouts = new ArrayList<>();

		ppt.getSlideMasters().stream() //
				.map(XSLFSlideMaster::getSlideLayouts) //
				.forEach(sls -> Arrays.stream(sls).forEach(sl -> layouts.add(sl)));

		return layouts;
	}

	public XSLFSlideLayout getLayout(SlideLayout slideLayout) {

		return getLayouts().stream() //
				.filter(l -> l.getType() == slideLayout) //
				.findFirst().orElse(null);
	}

	public void drawLine(XSLFSlide slide, int x, int y, int width, int height, Style style) {

		drawLine(slide, new Rectangle(x, y, width, height), style);
	}

	public void drawLine(XSLFSlide slide, Rectangle rectangle, Style style) {

		XSLFConnectorShape line = slide.createConnector();

		line.setAnchor(rectangle);

		if (style.getFillColor() != null) {
			line.setFillColor(style.getFillColor().getColor());
		}
		if (style.getLineColor() != null) {
			line.setLineColor(style.getLineColor().getColor());
		}
	}

	public XSLFSlide createSlide(SlideLayout slideLayout) {

		XSLFSlideLayout layout = getLayout(slideLayout);

		return ppt.createSlide(layout);
	}

	public TextBlockBuilder addText(XSLFSlide slide) {

		return new TextBlockBuilder(slide.createTextBox());
	}

	public AutoShapeBuilder<XSLFAutoShape> addShape(XSLFSlide slide) {

		return new AutoShapeBuilder<XSLFAutoShape>(slide.createAutoShape());
	}
}
