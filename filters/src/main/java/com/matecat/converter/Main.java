package com.matecat.converter;

import com.matecat.converter.server.MatecatConverterServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {

	private static Logger LOGGER = LoggerFactory.getLogger(Main.class);


	public static void main(String[] args) throws Exception {
		// TODO: UTF-8 셋팅을 beanstalk에서 하는 방법을 찾지 못해서 임시적으로 막아 놓음
		// UTF-8 기반으로 AWS가 셋팅되어 있어서 동작에는 문제가 없음
//		if (Charset.defaultCharset() != StandardCharsets.UTF_8) {
//			throw new Exception("Java default charset is " + Charset.defaultCharset() + ", must be UTF-8. Fix your configuration.");
//		}

		// Init the server
		MatecatConverterServer server = new MatecatConverterServer();

		// Shutdown gracefully when receiving SIGTERM or similar
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOGGER.info("Shutdown signal received, stopping the server...");
			server.stop();
			LOGGER.info("Server stopped successfully. Good bye!");
		}));
	}

}
