import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import projetFinal.config.AppConfig;

public class Testa {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
		
		context.close();
	}
	
}
