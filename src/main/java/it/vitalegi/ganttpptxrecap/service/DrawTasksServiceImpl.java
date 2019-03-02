package it.vitalegi.ganttpptxrecap.service;

import java.awt.Rectangle;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.vitalegi.ganttpptxrecap.bean.GanttConfig;
import it.vitalegi.ganttpptxrecap.bean.ShapeStyle;
import it.vitalegi.ganttpptxrecap.bean.Task;
import it.vitalegi.ganttpptxrecap.proxy.AutoShapeBuilder;
import it.vitalegi.ganttpptxrecap.proxy.PptProxy;
import it.vitalegi.ganttpptxrecap.service.factory.CollapseArrayServiceImpl;

/**
 * @author Giorgio Vitale
 */
@Service
@Scope(value = "prototype")
public class DrawTasksServiceImpl {

	private int maxLevel;

	public void drawTasks(PptProxy pptProxy, XSLFSlide slide, GanttConfig config, LocalDate from, LocalDate to,
			Rectangle drawingArea) {

		List<Task> tasks = config.getTasks();

		for (int i = 0; i < tasks.size(); i++) {
			Task task = tasks.get(i);
			drawTask(pptProxy, slide, task, getLevelAndUpdateMax(task), from, to, drawingArea, config);
		}
	}

	protected int getLevelAndUpdateMax(Task task) {

		Integer taskLevel = task.getLevel();
		if (taskLevel != null) {
			maxLevel = Math.max(maxLevel, taskLevel + 1);
			return taskLevel;
		}
		return maxLevel++;
	}

	protected void drawTask(PptProxy pptProxy, XSLFSlide slide, Task task, int rowLevel, LocalDate from, LocalDate to,
			Rectangle drawingArea, GanttConfig config) {

		ShapeStyle style = collapseArrayService.join(ShapeStyle.class, task.getStyle(), config.getDefaultTaskStyle());
		int rectHeight = config.getTaskHeight();
		int topMargin = config.getTaskTopMargin();

		int y = topMargin * (rowLevel + 1) + rectHeight * rowLevel;

		int x = (int) getCoordinateService.getAbsoluteX(drawingArea, from, to, task.getFrom());
		int width = (int) getCoordinateService.getAbsoluteX(drawingArea, from, to, task.getTo()) - x;

		AutoShapeBuilder<XSLFAutoShape> shape = pptProxy.addShape(slide);
		shape.setAnchor(x, (int) drawingArea.getY() + y, width, rectHeight)//
				.setFillColor(style.getFillColor().getColor())//
				.setShapeType(style.getShapeType())//
		;
		XSLFTextParagraph paragraph = shape.addNewTextParagraph();

		XSLFTextRun run = paragraph.addNewTextRun();

		run.setFontSize(style.getFontSize());
		run.setText(task.getName());
		paragraph.setTextAlign(style.getTextAlign());
		shape.setVerticalAlignment(VerticalAlignment.MIDDLE);
	}

	@Autowired
	private GetCoordinateServiceImpl getCoordinateService;

	@Autowired
	private CollapseArrayServiceImpl collapseArrayService;
}
