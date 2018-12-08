package com.qbk;

import com.qbk.view.MainStageView;
import com.qbk.view.TestView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.function.Consumer;

/**
 *  与boot结合
 *
 *   AbstractJavaFxApplicationSupport extends Application
 */
@SpringBootApplication
public class JavafxDemoApplication  extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) {
//		SpringApplication.run(JavafxDemoApplication.class, args);

    	//启动窗口
		launchApp(JavafxDemoApplication.class, TestView.class, args);
	}

	/**
	 * Start.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		super.start(stage);
	}



}
