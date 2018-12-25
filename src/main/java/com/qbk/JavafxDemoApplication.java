package com.qbk;

import com.qbk.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**  添加启动参数：-Dfile.encoding=GB18030
 *  与boot结合
 *   AbstractJavaFxApplicationSupport extends Application
 *  这里的 Stage 是一个主容器，它就是我们通常所认为的窗口（有边，高和宽，还有关闭按钮）。
 在这个 Stage 里面，你可以放置一个 Scene，当然你可以切换别的 Scene，而在这个 Scene 里面，我们就可以放置各种各样的控件
 */
@SpringBootApplication
public class JavafxDemoApplication  extends AbstractJavaFxApplicationSupport {

	@Value("${server.port}")
	private String serverPort;

	public static String port;

	//托盘图标
	private TrayIcon trayIcon;

	public static Stage stage ;

	public static void main(String[] args) {
		//启动窗口
		launchApp(JavafxDemoApplication.class, MainView.class, args);
	}
	@PostConstruct
	private void initLoad(){
		port = this.serverPort ;
	}

	@Override
	public void start(final Stage stage) throws Exception {
		super.start(stage);
		//多次使用显示和隐藏设置false
		Platform.setImplicitExit(false);
		//使用托盘
		enableTray(stage);

		stage.setTitle("中间件");
		stage.setHeight(680);
		stage.setWidth(800);
		//固定窗体大小
		// stage.setResizable(false);

		//窗口关闭事件
		stage.setOnCloseRequest(arg0->stage.hide());

		//图标化属性 监听
		ReadOnlyBooleanProperty readOnlyBooleanProperty = stage.iconifiedProperty();
		readOnlyBooleanProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//true 最小化 。
				if(observable.getValue()){
					//stage.hide();
				}else {
					//stage.show();
					stage.setHeight(680);
					stage.setWidth(800);
				}
			}
		});

		JavafxDemoApplication.stage = stage ;

	}

	/**
	 *  使用托盘
	 */
	private void enableTray(final Stage stage) {

		PopupMenu popupMenu = new PopupMenu();
		java.awt.MenuItem openItem = new java.awt.MenuItem("显示");
		java.awt.MenuItem hideItem = new java.awt.MenuItem("最小化");
		java.awt.MenuItem quitItem = new java.awt.MenuItem("退出");

		ActionListener acl = e -> {
			MenuItem item = (MenuItem) e.getSource();
			//多次使用显示和隐藏设置false
			Platform.setImplicitExit(false);

			if (item.getLabel().equals("退出")) {
				SystemTray.getSystemTray().remove(trayIcon);
				Platform.exit();
				return;
			}
			if (item.getLabel().equals("显示")) {
				Platform.runLater(() -> stage.show());
			}
			if (item.getLabel().equals("最小化")) {
				Platform.runLater(() -> stage.hide());
			}

		};

		//双击事件方法
		MouseListener sj = new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//多次使用显示和隐藏设置false
				Platform.setImplicitExit(false);

				if (e.getClickCount() == 2) {
					if (stage.isShowing()) {
						Platform.runLater(() -> stage.hide());
					}else{
						Platform.runLater(() -> stage.show());
					}
				}
			}
		};

		openItem.addActionListener(acl);
		quitItem.addActionListener(acl);
		hideItem.addActionListener(acl);

		popupMenu.add(openItem);
		popupMenu.add(hideItem);
		popupMenu.add(quitItem);

		try {
			//添加系统托盘图标
			SystemTray tray = SystemTray.getSystemTray();
			BufferedImage image = ImageIO.read(JavafxDemoApplication.class
					.getResourceAsStream("/icon/station.jpg"));
			trayIcon = new TrayIcon(image, "中间件", popupMenu);
			trayIcon.setToolTip("中间件");
			tray.add(trayIcon);
			trayIcon.addMouseListener(sj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
