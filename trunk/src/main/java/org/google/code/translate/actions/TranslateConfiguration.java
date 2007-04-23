package org.google.code.translate.actions;

import javax.swing.*;

/**
 * This class represents configuration component for translate action.
 *
 * @author Alexander Shvets
 * @version 1.0 04/07/2007
 */
public class TranslateConfiguration {

  private TranslateConfigurationForm form;

  private String langPair = "en|ru";

  private String proxyHost;
  private String proxyPort;

  public String getLangPair() {
    return langPair;
  }

  public void setLangPair(final String langPair) {
    this.langPair = langPair;
  }

  public String getProxyHost() {
    return proxyHost;
  }

  public void setProxyHost(final String proxyHost) {
    this.proxyHost = proxyHost;
  }

  public String getProxyPort() {
    return proxyPort;
  }

  public void setProxyPort(final String proxyPort) {
    this.proxyPort = proxyPort;
  }

  public boolean isModified() {
    return form != null && form.isModified(this);
  }

  public JComponent createComponent() {
    if (form == null) {
      form = new TranslateConfigurationForm();
    }

    return form.getRootComponent();
  }

  /**
   * Stores settings from form to configuration bean.
   *
   */
  public void apply() {
    if (form != null) {
      form.getData(this);
    }
  }

  /**
   * Restores form values from configuration.
   */
  public void reset() {
    if (form != null) {
      form.setData(this);
    }
  }

}
