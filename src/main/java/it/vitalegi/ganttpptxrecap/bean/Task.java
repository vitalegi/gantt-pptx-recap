package it.vitalegi.ganttpptxrecap.bean;

import java.util.Date;

import javax.annotation.Generated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Task {

	protected String name;
	protected Date from;
	protected Date to;
}
