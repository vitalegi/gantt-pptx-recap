package it.vitalegi.ganttpptxrecap.service;

import java.awt.Color;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.vitalegi.ganttpptxrecap.bean.GanttConfig;
import it.vitalegi.ganttpptxrecap.bean.Label;
import it.vitalegi.ganttpptxrecap.proxy.PptProxy;
import it.vitalegi.ganttpptxrecap.proxy.RectangleBuilder;

/**
 * @author Giorgio Vitale
 */
@Component
public class CreateGanttPptxReportImpl {

	public void execute(GanttConfig config) {

		LocalDate from = YearMonth.from(config.getFrom()).atDay(1);
		LocalDate to = YearMonth.from(config.getTo()).plusMonths(1).atDay(1);

		PptProxy pptProxy = providerService.getBeanByType(PptProxy.class);

		pptProxy.createPresentation();

		XSLFSlide slide = pptProxy.createSlide(SlideLayout.TITLE_ONLY);

		XSLFTextShape title = slide.getPlaceholder(0);

		title.setText("Hello");

		Rectangle drawingArea = config.getDrawingArea().getRectangle();

		List<LocalDate> intervals = getIntervalService.getIntervals(from, to);
		List<Rectangle> axes = getIntervalAxisService.getAxes(drawingArea, intervals);

		pptProxy.addShape(slide)//
				.setLineColor(Color.RED)//
				.setAnchor(drawingArea);

		for (int i = 0; i < intervals.size(); i++) {
			LocalDate time = intervals.get(i);
			Rectangle axis = axes.get(i);

			pptProxy.drawLine(slide, axis, config.getLabelStyle());

			Label labelStyle = config.getLabelStyle();

			Rectangle area = labelStyle.getRelativePosition().getRectangle();

			pptProxy.addText(slide)//
					.setAnchor(RectangleBuilder.newInstance()//
							.setX(axis.getX() + area.getX())//
							.setY(axis.getY() + area.getY())//
							.setWidth(area.getWidth())//
							.setHeight(area.getHeight())//
							.build())//
					.setText(time.format(DateTimeFormatter.ofPattern(labelStyle.getFormat())))//
					.setHorizontalCentered(true);
		}
		DrawTasksServiceImpl drawTasksService = providerService.getBeanByType(DrawTasksServiceImpl.class);

		drawTasksService.drawTasks(pptProxy, slide, config, from, to, drawingArea);

		pptProxy.savePresentation("test.pptx");
	}

	@Autowired
	private ProviderService providerService;

	@Autowired
	private GetIntervalServiceImpl getIntervalService;

	@Autowired
	private GetIntervalAxisServiceImpl getIntervalAxisService;

	private Log log = LogFactory.getLog(CreateGanttPptxReportImpl.class);
}
