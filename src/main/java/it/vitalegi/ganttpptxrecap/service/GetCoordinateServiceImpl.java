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

		return drawingArea.getX() + getX(drawingArea.getWidth(), from, to, target);
	}

	public double getX(Rectangle drawingArea, LocalDate from, LocalDate to, LocalDate target) {

		return getX(drawingArea.getWidth(), from, to, target);
	}

	public double getX(double width, LocalDate from, LocalDate to, LocalDate target) {

		double w = width * getXRatio(from, to, target);
		System.out.println("> "  + target + " " + (int)w);
		return w;
	}

	public double getXRatio(LocalDate from, LocalDate to, LocalDate target) {

		long complete = ChronoUnit.DAYS.between(from, to);

		long current = ChronoUnit.DAYS.between(from, target);

		return 1.0 * current / complete;
	}
}
