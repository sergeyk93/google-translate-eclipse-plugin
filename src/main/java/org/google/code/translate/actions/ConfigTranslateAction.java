package org.google.code.translate.actions;

import javax.swing.JDialog;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.google.code.translate.TranslateHelper;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class ConfigTranslateAction extends ActionDelegate implements IEditorActionDelegate {
	
	private static ConfigTranslateAction instance = null;
	
	static {
		try {
			instance = new ConfigTranslateAction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ConfigTranslateAction getInstance() {
		return instance;
	}
	
   private static TranslateHelper translateHelper;
	  
	/**
	 * The constructor.
	 * @throws Exception 
	 */
	public ConfigTranslateAction () throws Exception {
       translateHelper = new TranslateHelper();		
	}

	/**
	 * @see ActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
/*		MessageBox box = new MessageBox(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		box.setMessage("Executing: " + getClass());
		box.open();
		*/
		
		JDialog dialog = new TranslateConfigDialog(null);
		
		dialog.setVisible(true);
	}
	
	/**
	 * @see IEditorActionDelegate#setActiveEditor(IAction, IEditorPart)
	 */
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		//System.out.println("setActiveEditor");
	}
	
}
