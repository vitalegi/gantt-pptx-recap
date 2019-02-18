package it.vitalegi.ganttpptxrecap.service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.vitalegi.ganttpptxrecap.bean.Task;
import it.vitalegi.ganttpptxrecap.proxy.PptProxy;

/**
 * @author Giorgio Vitale
 */
@Component
public class CreateGanttPptxReportImpl {

	public void execute(Date from, Date to, List<Task> tasks) {

		PptProxy pptProxy = providerService.getBeanByType(PptProxy.class);

		pptProxy.createPresentation();

		pptProxy.getLayouts().forEach(layout -> System.out.println("Layout: " + layout.getName()));

		XSLFSlide slide = pptProxy.createSlide(SlideLayout.TITLE_ONLY);

		XSLFTextShape title = slide.getPlaceholder(0);

		title.setText("Hello");

		List<LocalDate> intervals = getIntervalService.getIntervals(from, to);

		Rectangle drawingArea = getDrawingArea(pptProxy.getPageSize());

		List<Rectangle> axes = getIntervalAxisService.getAxes(drawingArea, intervals);

		for (int i = 0; i < intervals.size(); i++) {
			LocalDate time = intervals.get(i);
			Rectangle axis = axes.get(i);

			pptProxy.drawLine(slide, axis, Color.RED);
		}

		pptProxy.savePresentation("test.pptx");
	}

	private Rectangle getDrawingArea(Dimension pageSize) {

		int width = (int) pageSize.getWidth();
		int height = (int) pageSize.getHeight();
		double margin = 0.2;

		return new Rectangle((int) (margin * width), (int) (margin * height), (int) ((1 - 2*margin) * width),
				(int) ((1 - 2*margin) * height));
	}

	@Autowired
	private ProviderService providerService;

	@Autowired
	private GetIntervalServiceImpl getIntervalService;

	@Autowired
	private GetIntervalAxisServiceImpl getIntervalAxisService;
}
