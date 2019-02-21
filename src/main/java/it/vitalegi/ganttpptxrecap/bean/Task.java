package it.vitalegi.ganttpptxrecap.bean;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Task {

	public static Task build(String name, LocalDate from, LocalDate to) {

		Task task = new Task();
		task.setName(name);
		task.setFrom(from);
		task.setTo(to);
		return task;
	}

	protected String name;
	protected LocalDate from;
	protected LocalDate to;
	protected ShapeStyle style;
}
