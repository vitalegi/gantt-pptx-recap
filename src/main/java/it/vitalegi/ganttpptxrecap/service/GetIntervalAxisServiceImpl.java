package it.vitalegi.ganttpptxrecap.service;

import java.awt.Rectangle;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Giorgio Vitale
 */
@Service
public class GetIntervalAxisServiceImpl {

	public List<Rectangle> getAxes(Rectangle drawingArea, List<LocalDate> intervals) {

		List<Rectangle> axes = new ArrayList<>();

		int slices = intervals.size() - 1;

		double sliceWidth = drawingArea.getWidth() / slices;

		int xOffset = (int)drawingArea.getX();
		int yOffset = (int)drawingArea.getY();

		for (int i = 0; i <= slices; i++) {

			int x = (int)Math.round(i * sliceWidth);

			axes.add(new Rectangle(xOffset + x, yOffset, 0, (int)drawingArea.getHeight()));
		}

		return axes;
	}
}
