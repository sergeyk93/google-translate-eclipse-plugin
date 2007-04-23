package org.google.code.translate.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionDelegate;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigTranslateAction extends ActionDelegate
  implements IEditorActionDelegate {
  private String langPair = "en|ru";
  private String proxyHost;
  private String proxyPort;

  private static ConfigTranslateAction instance = null;

  public static ConfigTranslateAction getInstance() {
    if (instance == null) {
      try {
        instance = new ConfigTranslateAction();
      } catch (Exception e) {
        e.printStackTrace();
      }

      instance.readProperties();
    }
    return instance;
  }

  private void readProperties() {
    String userDir = System.getProperty("user.dir");

    Properties props = new Properties();

    File file = new File(userDir + "/" + ".translate");

    if (file.exists()) {
      try {
        props.load(new FileInputStream(file));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    langPair = (String) props.get("lang.pair");
    proxyHost = (String) props.get("proxy.host");
    proxyPort = (String) props.get("proxy.port");
  }

  private void writeProperties() {
    String userDir = System.getProperty("user.dir");

    Properties props = new Properties();

    if (langPair != null) {
      props.put("lang.pair", langPair);
    }

    if (proxyHost != null) {
      props.put("proxy.host", proxyHost);
    }

    if (proxyPort != null) {
      props.put("proxy.port", proxyPort);
    }

    File file = new File(userDir + "/" + ".translate");

    try {
      props.store(new FileOutputStream(file), "translate properties");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
 /**
  * The constructor.
  *
  * @throws Exception
  */
  public ConfigTranslateAction() throws Exception {
    Properties systemProps = System.getProperties();

    if (proxyHost != null && proxyHost.trim().length() > 0) {
      systemProps.put("proxySet", "true");
      systemProps.put("proxyPort", proxyPort);
      systemProps.put("proxyHost", proxyHost);
    }

    systemProps.put("proxySet", "true");
    systemProps.put("proxyPort", "8080");
    systemProps.put("proxyHost", "proxy-server.bms.com");
  }

  /**
   * @see ActionDelegate#run(IAction)
   */
  public void run(IAction action) {
    readProperties();

    final TranslateConfigDialog dialog = new TranslateConfigDialog(null);
    dialog.setLangPair(langPair);
    dialog.setProxyHost(proxyHost);
    dialog.setProxyPort(proxyPort);
    dialog.loadProperties();

    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    dialog.addWindowListener(new WindowAdapter() {

      public void windowClosed(WindowEvent e) {
        System.out.println("windowClosed " + dialog.isAnswer());

        if (dialog.isAnswer()) {
          langPair = dialog.getLangPair();
          proxyHost = dialog.getProxyHost();
          proxyPort = dialog.getProxyPort();

          writeProperties();
        }
      }
    });

    dialog.setVisible(true);
  }

  /**
   * @see IEditorActionDelegate#setActiveEditor(IAction,IEditorPart)
   */
  public void setActiveEditor(IAction action, IEditorPart targetEditor) {
  }

  public String getLangPair() {
    return langPair;
  }

  public void setLangPair(String langPair) {
    this.langPair = langPair;
  }

  public String getProxyHost() {
    return proxyHost;
  }

  public void setProxyHost(String proxyHost) {
    this.proxyHost = proxyHost;
  }

  public String getProxyPort() {
    return proxyPort;
  }

  public void setProxyPort(String proxyPort) {
    this.proxyPort = proxyPort;
  }

}
