package org.google.code.translate.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.google.code.translate.TranslateHelper;

public class TranslateAction extends ActionDelegate implements IEditorActionDelegate {
  private static TranslateHelper translateHelper;
  private TextEditor textEditor;

  /**
   * The constructor.
   *
   * @throws Exception
   */
  public TranslateAction() throws Exception {
    translateHelper = new TranslateHelper();
  }

  /**
   * @see ActionDelegate#run(IAction)
   */
  public void run(IAction action) {
    ISelectionProvider selectionProvider = textEditor.getSelectionProvider();

    TextSelection selection = null;

    String selectedText = "";

    if (selectionProvider.getSelection() instanceof TextSelection) {
      selection = (TextSelection) selectionProvider.getSelection();
      selectedText = selection.getText();
    }

    ConfigTranslateAction configTranslateAction = ConfigTranslateAction.getInstance();
    String response = "";

    try {
      response = translateHelper.translate(selectedText, configTranslateAction.getLangPair());
    }
    catch (Exception e) {
      //e.printStackTrace();
    }

    IDocumentProvider documentProvider = textEditor.getDocumentProvider();
    IDocument document = documentProvider.getDocument(textEditor.getEditorInput());

    try {
      document.replace(selection.getOffset(), selection.getLength(), response);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
  }


  /**
   * @see IEditorActionDelegate#setActiveEditor(IAction,IEditorPart)
   */
  public void setActiveEditor(IAction action, IEditorPart targetEditor) {
    this.textEditor = (TextEditor) targetEditor;
  }


}
