package it.vitalegi.ganttpptxrecap.service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;
import org.apache.poi.sl.usermodel.TextShape.TextAutofit;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.vitalegi.ganttpptxrecap.bean.Task;
import it.vitalegi.ganttpptxrecap.proxy.AutoShapeBuilder;
import it.vitalegi.ganttpptxrecap.proxy.PptProxy;

/**
 * @author Giorgio Vitale
 */
@Component
public class CreateGanttPptxReportImpl {

	public void execute(LocalDate from, LocalDate to, List<Task> tasks) {

		from = YearMonth.from(from).atDay(1);
		to = YearMonth.from(to).plusMonths(1).atDay(1);

		PptProxy pptProxy = providerService.getBeanByType(PptProxy.class);

		pptProxy.createPresentation();

		// pptProxy.getLayouts().forEach(layout -> System.out.println("Layout: " +
		// layout.getName()));

		XSLFSlide slide = pptProxy.createSlide(SlideLayout.TITLE_ONLY);

		XSLFTextShape title = slide.getPlaceholder(0);

		title.setText("Hello");

		Rectangle drawingArea = getDrawingArea(pptProxy.getPageSize());
		List<LocalDate> intervals = getIntervalService.getIntervals(from, to);
		List<Rectangle> axes = getIntervalAxisService.getAxes(drawingArea, intervals);

		pptProxy.addShape(slide)//
				.setLineColor(Color.RED)//
				.setAnchor(drawingArea);

		for (int i = 0; i < intervals.size(); i++) {
			LocalDate time = intervals.get(i);
			Rectangle axis = axes.get(i);

			pptProxy.drawLine(slide, axis, Color.RED);

			pptProxy.addText(slide)//
					.setAnchor((int) axis.getX() - 30, (int) axis.getY() - 25, 60, 20)//
					.setText(time.format(DateTimeFormatter.ofPattern("MM-yy")))//
					.setHorizontalCentered(true);
		}

		for (int i = 0; i < tasks.size(); i++) {
			Task task = tasks.get(i);

			int rectHeight = 30;
			int whiteSpace = 5;

			int y = whiteSpace * (i + 1) + rectHeight * i;

			int x = (int) getCoordinateService.getAbsoluteX(drawingArea, from, to, task.getFrom());
			int width = (int) getCoordinateService.getAbsoluteX(drawingArea, from, to, task.getTo()) - x;

			AutoShapeBuilder<XSLFAutoShape> shape = pptProxy.addShape(slide);
			shape.setAnchor(x, (int) drawingArea.getY() + y, width, rectHeight)//
					.setFillColor(Color.RED)//
					.setShapeType(ShapeType.CHEVRON)//
			;
			XSLFTextParagraph paragraph = shape.addNewTextParagraph();
			XSLFTextRun run = paragraph.addNewTextRun();
			run.setText(task.getName());
			run.setFontSize(14d);
			paragraph.setTextAlign(TextAlign.CENTER);
			shape.setVerticalAlignment(VerticalAlignment.MIDDLE);
		}
		pptProxy.savePresentation("test.pptx");
	}

	private Rectangle getDrawingArea(Dimension pageSize) {

		int width = (int) pageSize.getWidth();
		int height = (int) pageSize.getHeight();
		double margin = 0.2;

		return new Rectangle((int) (margin * width), //
				(int) (margin * height), //
				(int) ((1 - 2 * margin) * width), //
				(int) ((1 - 2 * margin) * height));
	}

	@Autowired
	private ProviderService providerService;

	@Autowired
	private GetIntervalServiceImpl getIntervalService;

	@Autowired
	private GetIntervalAxisServiceImpl getIntervalAxisService;

	@Autowired
	private GetCoordinateServiceImpl getCoordinateService;
}
