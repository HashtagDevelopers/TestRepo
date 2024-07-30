package com.hashTagMIS.Test;

import java.io.IOException;
import org.apache.logging.log4j.*;

import org.testng.annotations.Test;

import LibraryFiles.BaseClass;

public class Log4j2 extends BaseClass {
	 Logger log= LogManager.getLogger(Log4j2.class);	
	@Test
	public void Log4j2Demo() throws IOException, InterruptedException {
        initialiseBrowser();
		System.out.println("logger Demo");
       	
		log.info("for info only");
		log.error("for error only");
		log.warn("for warn only");
		log.fatal("for fatal only");
		System.out.println("logger Demo23");
}
}
