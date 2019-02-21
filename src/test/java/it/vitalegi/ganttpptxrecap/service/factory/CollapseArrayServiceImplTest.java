package it.vitalegi.ganttpptxrecap.service.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Giorgio Vitale
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CollapseArrayServiceImplTest {

	@Test
	public void shouldCreateResultIfEmptyInput() {
		assertNotNull(service.join(Sample.class));
	}

	@Test
	public void shouldSetPropertyIfInputPropertyExists() {
		Sample result = service.join(Sample.class, Sample.build("1", null));
		assertEquals("1", result.getA());
	}

	@Test
	public void shouldSetNullPropertyIfInputPropertyNotExists() {
		Sample result = service.join(Sample.class, Sample.build(null, null));
		assertEquals(null, result.getA());
	}

	@Test
	public void shouldSetFirstNonNullProperty() {
		Sample result = service.join(Sample.class, //
				Sample.build(null, null), Sample.build("1", null), Sample.build("2", null));
		assertEquals("1", result.getA());
	}

	@Test
	public void shouldSetFirstNonNullPropertyComplexObject() {
		Sample result = service.join(Sample.class, //
				Sample.build(null, Sample.build("AAA", null)));
		assertEquals("AAA", result.getB().getA());
	}

	@Autowired
	private CollapseArrayServiceImpl service;
}
