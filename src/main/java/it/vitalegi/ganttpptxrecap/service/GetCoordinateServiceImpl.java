package it.vitalegi.ganttpptxrecap.service;

import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

/**
 * @author Giorgio Vitale
 */
@Service
public class GetCoordinateServiceImpl {

	public double getAbsoluteX(Rectangle drawingArea, LocalDate from, LocalDate to, LocalDate target) {

		long complete = ChronoUnit.DAYS.between(from, to);

		long current = ChronoUnit.DAYS.between(from, target);

		double width = drawingArea.getWidth() * current / complete;

		return drawingArea.getX() + width;
	}
}
