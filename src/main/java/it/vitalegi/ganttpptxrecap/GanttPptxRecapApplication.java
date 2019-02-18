package it.vitalegi.ganttpptxrecap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

		createGanttPptxReport.execute(date("2018-10-05"), date("2019-02-15"), new ArrayList<>());
	}

	private Date date(String source) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(source);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
