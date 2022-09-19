package com.dt.virtualanm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Virtual ANM",
		description = "The virtual ANM microservice represents the Wet Christmas Tree (Árvore de Natal Molhada in Portuguese), which is an assembly"
				+ " of valves that regulate the flow stream of pipes in an oil well. The ANM is also connected to the wellhead and controls access"
				+ " to the tubing as the well begins pumping oil. The ANM resource also models a series of valves present in the flow path,"
				+ " such as master valve M1 and M2, wing valve W1 and W2, and cross-over valve XO and PXO."
				+ " When the POST creation method is invoked, all master and wing valves are set with the value open as default, and all the cross-over"
				+ " valves are set with the value closed. These values, however, can be changed using the update PUT method.",
		version = "1.0.0"), servers = { @Server(url = "http://localhost:8084", description = "Local Docker deployment URL") })
public class VirtualAnmApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualAnmApplication.class, args);
	}

}
