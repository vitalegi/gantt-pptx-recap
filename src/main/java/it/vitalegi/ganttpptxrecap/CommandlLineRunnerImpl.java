package it.vitalegi.ganttpptxrecap;

import java.io.FileInputStream;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import it.vitalegi.ganttpptxrecap.bean.GanttConfig;
import it.vitalegi.ganttpptxrecap.service.CreateGanttPptxReportImpl;
import it.vitalegi.ganttpptxrecap.util.JacksonUtil;

/**
 * @author Giorgio Vitale
 */
@Component
@Profile("!test")
public class CommandlLineRunnerImpl implements CommandLineRunner {


	@Autowired
	private CreateGanttPptxReportImpl createGanttPptxReport;

	@Override
	public void run(String... args) throws Exception {

		String configPath = args[0];

		GanttConfig config = JacksonUtil.getValueYaml(GanttConfig.class, new FileInputStream(configPath));

		System.out.println(ReflectionToStringBuilder.toString(config, ToStringStyle.MULTI_LINE_STYLE));

		createGanttPptxReport.execute(config);

	}
}
