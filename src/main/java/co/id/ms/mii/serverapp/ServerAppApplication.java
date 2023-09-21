package co.id.ms.mii.serverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerAppApplication.class, args);

		System.out.println("Server Is Running \n");
		System.out.println("Kucing Kampung");
		System.out.println("Kucing Anggora  ");
		System.out.println("Kucing Mesir");
		System.out.println("Kucing Oyen");
	}

}
