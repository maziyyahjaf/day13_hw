package sg.edu.nus.iss.Day13_HW;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class Day13HwApplication {

	 @Value("${data.dir}")
	private String dataDir;

	public static void main(String[] args) {

		SpringApplication.run(Day13HwApplication.class, args);
		
	}

	@Bean
	public String dataDir() {
		return dataDir;
	}

}
