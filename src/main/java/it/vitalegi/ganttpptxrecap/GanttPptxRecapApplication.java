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

		createGanttPptxReport.execute(date("2018-10-01"), date("2019-02-01"), //
				Arrays.asList(//
						Task.build("task 1", date("2018-10-01"), date("2018-11-01")),
						Task.build("task 2", date("2018-11-01"), date("2018-12-01")),
						Task.build("task 2", date("2018-12-01"), date("2019-01-01")),
						Task.build("task 2", date("2019-01-01"), date("2019-02-01")),
						Task.build("task 2 task 2 task 2", date("2019-02-01"), date("2019-03-01")),
						Task.build("task 2", date("2018-10-01"), date("2018-10-15")),
						Task.build("task 2 task 2", date("2018-10-16"), date("2018-10-31"))//
				)//
		);
	}

	private LocalDate date(String source) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(source, formatter);
	}
}
