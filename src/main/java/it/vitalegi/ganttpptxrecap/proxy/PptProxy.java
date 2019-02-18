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
import org.apache.poi.xslf.usermodel.XSLFConnectorShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
		savePresentation( os);
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

	public void drawLine(XSLFSlide slide, int x, int y, int width, int height, Color color) {

		drawLine(slide, new Rectangle(x, y, width, height), color);
	}

	public void drawLine(XSLFSlide slide, Rectangle rectangle, Color color) {

		XSLFConnectorShape line = slide.createConnector();

		line.setAnchor(rectangle);
		line.setFillColor(color);
	}



	public XSLFSlide createSlide(SlideLayout slideLayout) {

		XSLFSlideLayout layout = getLayout(slideLayout);

		return ppt.createSlide(layout);
	}
}
