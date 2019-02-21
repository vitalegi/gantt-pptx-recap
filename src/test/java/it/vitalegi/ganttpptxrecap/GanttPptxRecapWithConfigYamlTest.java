package it.vitalegi.ganttpptxrecap;

import java.io.FileInputStream;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import it.vitalegi.ganttpptxrecap.bean.GanttConfig;
import it.vitalegi.ganttpptxrecap.service.CreateGanttPptxReportImpl;
import it.vitalegi.ganttpptxrecap.util.JacksonUtil;

/**
 * @author Giorgio Vitale
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GanttPptxRecapWithConfigYamlTest {

	@Test
	public void runWithConfigYaml2() throws Exception {

		GanttConfig config = JacksonUtil.getValueYaml(GanttConfig.class, new FileInputStream("config.yaml"));

		System.out.println(ReflectionToStringBuilder.toString(config, ToStringStyle.MULTI_LINE_STYLE));

		createGanttPptxReport.execute(config);
	}

	@Autowired
	private CreateGanttPptxReportImpl createGanttPptxReport;
}
