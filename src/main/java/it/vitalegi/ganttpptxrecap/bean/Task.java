package it.vitalegi.ganttpptxrecap.bean;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Task {

	protected Integer level;
	protected String name;
	protected LocalDate from;
	protected LocalDate to;
	protected ShapeStyle style;
}
