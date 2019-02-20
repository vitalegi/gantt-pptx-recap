package it.vitalegi.ganttpptxrecap.service;

import java.awt.Rectangle;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Giorgio Vitale
 */
@Service
public class GetIntervalAxisServiceImpl {

	public List<Rectangle> getAxes(Rectangle drawingArea, List<LocalDate> intervals) {

		LocalDate from = intervals.get(0);
		LocalDate to = intervals.get(intervals.size() - 1);
		System.out.println(">>> " + from + " " + to);
		List<Rectangle> axes = new ArrayList<>();

		int slices = intervals.size() - 1;

		double sliceWidth = drawingArea.getWidth() / slices;

		for (int i = 0; i < intervals.size(); i++) {

			int x = (int) Math.round(i * sliceWidth);
			x = (int) getCoordinateService.getAbsoluteX(drawingArea, from, to, intervals.get(i));
			axes.add(new Rectangle(x, (int) drawingArea.getY(), 0, (int) drawingArea.getHeight()));
		}

		return axes;
	}

	@Autowired
	private GetCoordinateServiceImpl getCoordinateService;
}
