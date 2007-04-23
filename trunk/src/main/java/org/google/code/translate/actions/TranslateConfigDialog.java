package org.google.code.translate.actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TranslateConfigDialog extends JDialog implements ActionListener {
  private TranslateConfigurationForm form = new TranslateConfigurationForm();

  private JButton okButton = new JButton("Ok");
  private JButton cancelButton = new JButton("Cancel");
  private JButton applyButton = new JButton("Apply");

  private String langPair;
  private String proxyHost;
  private String proxyPort;

  private boolean answer = false;

  public TranslateConfigDialog(Frame owner) {
    super(owner, true);

    setSize(500, 200);

    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    applyButton.addActionListener(this);

    Container contentPane = getContentPane();

    contentPane.add(form.getRootComponent(), "Center");

    JPanel panel = new JPanel();

    panel.add(okButton);
    panel.add(cancelButton);
    panel.add(applyButton);

    contentPane.add(panel, "South");
  }

  protected JRootPane createRootPane() {
    JRootPane rootPane = new JRootPane();
    KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
    Action actionListener = new AbstractAction() {
      public void actionPerformed(ActionEvent actionEvent) {
        dispose();
      }
    };
    InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(stroke, "ESCAPE");
    rootPane.getActionMap().put("ESCAPE", actionListener);

    return rootPane;
  }

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == okButton) {
      saveProperties();
      answer = true;
      dispose();
      //setVisible(false);
    } else if (source == cancelButton) {
      answer = false;
      dispose();
      //setVisible(false);      
    } else if (source == applyButton) {
      answer = true;
      saveProperties();
    }
  }

  private void saveProperties() {
    TranslateConfiguration data = new TranslateConfiguration();

    form.getData(data);
    langPair = data.getLangPair();
    proxyHost = data.getProxyHost();
    proxyPort = data.getProxyPort();
  }

  public void loadProperties() {
    TranslateConfiguration data = new TranslateConfiguration();
    data.setLangPair(langPair);
    data.setProxyHost(proxyHost);
    data.setProxyPort(proxyPort);

    form.setData(data);
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

  public boolean isAnswer() {
    return answer;
  }

}