package it.vitalegi.ganttpptxrecap;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import it.vitalegi.ganttpptxrecap.bean.GanttConfig;
import it.vitalegi.ganttpptxrecap.bean.Task;
import it.vitalegi.ganttpptxrecap.service.CreateGanttPptxReportImpl;
import it.vitalegi.ganttpptxrecap.util.JacksonUtil;

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

		GanttConfig value = JacksonUtil.getValueYaml(GanttConfig.class, new FileInputStream("config.yaml"));

		System.out.println(ReflectionToStringBuilder.toString(value, ToStringStyle.MULTI_LINE_STYLE));
	}

	private LocalDate date(String source) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(source, formatter);
	}
}
