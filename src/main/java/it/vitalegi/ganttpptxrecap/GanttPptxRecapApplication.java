package it.vitalegi.ganttpptxrecap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.vitalegi.ganttpptxrecap.bean.Task;
import it.vitalegi.ganttpptxrecap.service.CreateGanttPptxReportImpl;

@SpringBootApplication
public class GanttPptxRecapApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GanttPptxRecapApplication.class, args);
	}

	@Autowired
	private CreateGanttPptxReportImpl createGanttPptxReport;

	@Override
	public void run(String... args) throws Exception {

		createGanttPptxReport.execute(date("2018-10-05"), date("2019-02-15"), //
				Arrays.asList(//
						Task.build("task 1", date("2018-10-10"), date("2018-11-10")),
						Task.build("task 2", date("2018-11-10"), date("2018-12-10"))));
	}

	private LocalDate date(String source) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(source, formatter);
	}
}
