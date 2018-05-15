package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import data.Person;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.swt.widgets.List;

public class mywindow {

	protected Shell shell;
	private Text vorname;
	private Label lblNachname;
	private Text nachname;
	private Text strasse;
	private Label lblStrasse;
	private Label lblPlz;
	private Text plz;
	private Text ort;
	private Label lblOrt;
	private Button btnWritejson;
	private Button btnAusDateiLesen;
	private List guilist;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mywindow window = new mywindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label lblVorna = new Label(shell, SWT.NONE);
		lblVorna.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblVorna.setBounds(22, 31, 55, 15);
		lblVorna.setText("vorname");
		
		vorname = new Text(shell, SWT.BORDER);
		vorname.setBounds(91, 28, 76, 21);
		
		Button btnDrcken = new Button(shell, SWT.NONE);
		btnDrcken.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		btnDrcken.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Person p = new Person();
				p.setVorname (getVorname().getText());
				p.setNachname (getNachname().getText());
				p.setStrasse(getStrasse().getText());
				p.setOrt(getOrt().getText());
				
				try {
				p.setPlz(Integer.parseInt(getPlz().getText()));
				}catch (NumberFormatException nfe) {
					
				System.out.println("falsche plz");
				MessageBox mb= new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				mb.setText("Fehler");
				mb.setMessage("PLZ im falschen Format");
				mb.open();
				System.out.println("mb closed");}
				
				Person.getPersonenListe().add(p);
				
				Person.getGuiList().add(p);
				
				clearMask();
				
				
				for (Person p1 : Person.getPersonenListe())
					System.out.println(p1);
						
			}
		});
		btnDrcken.setBounds(22, 226, 75, 25);
		btnDrcken.setText("dr\u00FCcken");
		
		lblNachname = new Label(shell, SWT.NONE);
		lblNachname.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblNachname.setBounds(22, 67, 55, 15);
		lblNachname.setText("nachname");
		
		nachname = new Text(shell, SWT.BORDER);
		nachname.setBounds(91, 64, 76, 21);
		
		strasse = new Text(shell, SWT.BORDER);
		strasse.setBounds(91, 104, 76, 21);
		
		lblStrasse = new Label(shell, SWT.NONE);
		lblStrasse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblStrasse.setBounds(22, 107, 55, 15);
		lblStrasse.setText("strasse");
		
		lblPlz = new Label(shell, SWT.NONE);
		lblPlz.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblPlz.setBounds(22, 148, 55, 15);
		lblPlz.setText("PLZ");
		
		plz = new Text(shell, SWT.BORDER);
		plz.setBounds(91, 145, 76, 21);
		
		ort = new Text(shell, SWT.BORDER);
		ort.setBounds(91, 187, 76, 21);
		
		lblOrt = new Label(shell, SWT.NONE);
		lblOrt.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblOrt.setBounds(22, 190, 55, 15);
		lblOrt.setText("ort");
		
		btnWritejson = new Button(shell, SWT.NONE);
		btnWritejson.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Person.write2Json();
				MessageBox mb= new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				mb.setText("to Json");
				mb.setMessage("set to Json");
				mb.open();
			}
		});
		btnWritejson.setBounds(113, 226, 75, 25);
		btnWritejson.setText("Write2JSON");
		
		btnAusDateiLesen = new Button(shell, SWT.NONE);
		btnAusDateiLesen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Person.ReadFromJson();
				MessageBox mb= new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				mb.setText("read Json");
				mb.setMessage("read Json");
				mb.open();
			}
		});
		btnAusDateiLesen.setBounds(209, 226, 95, 25);
		btnAusDateiLesen.setText("aus datei lesen");
		
		guilist = new List(shell, SWT.BORDER);
		guilist.setBounds(202, 14, 222, 194);

	}
	
	protected void clearMask() {
		getVorname().setText("");
		getNachname().setText("");
		getOrt().setText("");
		getPlz().setText("");
		getStrasse().setText("");
	}
	
	public Text getVorname() {
		return vorname;
	}
	public Text getNachname() {
		return nachname;
	}
	
	public Text getStrasse() {
		return strasse;
	}
	public Text getPlz() {
		return plz;
	}
	public Text getOrt() {
		return ort;
	}
	public Button getBtnWritejson() {
		return btnWritejson;
	}
	public Button getBtnAusDateiLesen() {
		return btnAusDateiLesen;
	}
	public List getList() {
		return guilist;
	}
}
