package org.google.code.translate.actions;

import org.google.code.translate.KeyValuePair;
import org.google.code.translate.LanguageEntryRenderer;
import org.google.code.translate.TranslateHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class TranslateConfigurationForm {
  private JPanel rootComponent;

  private JComboBox comboBox = new JComboBox();
  private JLabel label = new JLabel("Select translation:");

  private JTextField proxyHost = new JTextField(25);
  private JTextField proxyPort = new JTextField(5);

  public TranslateConfigurationForm() {
    rootComponent = new JPanel();

    rootComponent.setLayout(new BorderLayout());

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    rootComponent.add(panel1, BorderLayout.CENTER);
    rootComponent.add(panel2, BorderLayout.NORTH);

    panel1.add(label);
    panel1.add(comboBox);

    panel2.add(new JLabel("Proxy Host/Port: "));
    panel2.add(proxyHost);
    panel2.add(proxyPort);

    comboBox.removeAllItems();
    comboBox.setModel(createModel());
    comboBox.setRenderer(new LanguageEntryRenderer());

    if (comboBox.getModel().getSize() > 0) {
      comboBox.setSelectedIndex(0);
    }
  }

  public JComboBox getComboBox() {
    return comboBox;
  }

  private ComboBoxModel createModel() {
    List items;
    try {
      TranslateHelper translateHelper = new TranslateHelper();

      items = translateHelper.getLangPairs();
    }
    catch (Exception e) {
      items = new ArrayList();
    }

    return new DefaultComboBoxModel(items.toArray());
  }

  /**
   * Gets the root component of the form.
   *
   * @return root component of the form
   */
  public JComponent getRootComponent() {
    return rootComponent;
  }

  /**
   * Setter for property 'data'.
   *
   * @param data Value to set for property 'data'.
   */
  public void setData(TranslateConfiguration data) {
    String langPair = data.getLangPair();

    ComboBoxModel model = comboBox.getModel();

    boolean ok = false;

    for (int i = 0; i < model.getSize() && !ok; i++) {
      KeyValuePair item = (KeyValuePair) model.getElementAt(i);

      if (item.getKey().equals(langPair)) {
        comboBox.setSelectedItem(item);
        ok = true;
      }
    }
    
    comboBox.setSelectedItem(data.getLangPair());
    proxyHost.setText(data.getProxyHost());
    proxyPort.setText(data.getProxyPort());    
  }

  public void getData(TranslateConfiguration data) {
    KeyValuePair selectedItem = (KeyValuePair) comboBox.getSelectedItem();

    if (selectedItem != null) {
      data.setLangPair(selectedItem.getKey());
    }

    if(proxyHost != null) {
      data.setProxyHost(proxyHost.getText().trim());
  }

    if(proxyPort != null) {
      data.setProxyPort(proxyPort.getText().trim());
    }
  }


  public boolean isModified(TranslateConfiguration data) {
    KeyValuePair selectedItem = (KeyValuePair) comboBox.getSelectedItem();

    boolean isModified = selectedItem != null ?
      !selectedItem.getKey().equals(data.getLangPair()) :
      data.getLangPair() != null;

    isModified |= proxyHost != null ?
           !proxyHost.getText().equals(data.getProxyHost()) :
           data.getProxyHost() != null;

    isModified |= proxyPort != null ?
           !proxyPort.getText().equals(data.getProxyPort()) :
           data.getProxyPort() != null;

    return isModified;
  }

}
