package testService;


import java.io.ObjectInputFilter.Config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import projetFinal.config.AppConfig;

public class ContxtTest {

	@Test
	public void test() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
		
		context.close();
	}

}
