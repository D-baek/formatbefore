package com.doosan.web.pxy;

import static org.junit.Assert.*;
import javax.servlet.ServletContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.mock.web.MockServletContext;
import  org.springframework.test.context.ContextConfiguration;
import  org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import  org.springframework.test.context.web.WebAppConfiguration;
import  org.springframework.test.web.servlet.MockMvc;
import  org.springframework.test.web.servlet.setup.MockMvcBuilders;
import  org.springframework.web.context.WebApplicationContext;
import  org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.doosan.web.cfg.RootConfig;
import com.doosan.web.cfg.ServletConfig;
import com.doosan.web.cfg.WebConfig;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@contextConfiguration(classes = {ServletConfig.class}, loader = AnnotationConfigWebContext}
@WebAppConfiguration
public class CalculatorTest {
	
//	final Calculator cal =new Calculator();
	
	@Test
	public void testSum() {
		fail("Not yet implemented");
			assertThat(cal.sum(1, 4), is(equalTo(5)));
	}

	@Ignore
	public void testSub() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testAbs() {
		fail("Not yet implemented");
	}

}
