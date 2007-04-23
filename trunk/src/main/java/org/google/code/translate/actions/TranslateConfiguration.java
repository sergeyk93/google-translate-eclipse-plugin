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

  public String getLangPair() {
    return langPair;
  }

  public void setLangPair(final String langPair) {
    this.langPair = langPair;
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
   * @throws ConfigurationException
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
