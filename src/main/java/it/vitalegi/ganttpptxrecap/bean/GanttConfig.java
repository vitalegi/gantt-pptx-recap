package it.vitalegi.ganttpptxrecap.bean;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giorgio Vitale
 */
@Getter
@Setter
@ToString
public class GanttConfig {

	LocalDate from;
	LocalDate to;
	List<Task> tasks;
}
