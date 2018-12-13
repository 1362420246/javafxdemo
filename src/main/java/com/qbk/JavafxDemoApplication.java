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
 *
 *   //    这里的 Stage 是一个主容器，它就是我们通常所认为的窗口（有边，高和宽，还有关闭按钮）。
 //    在这个 Stage 里面，你可以放置一个 Scene，当然你可以切换别的 Scene，而在这个 Scene 里面，我们就可以放置各种各样的控件

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
