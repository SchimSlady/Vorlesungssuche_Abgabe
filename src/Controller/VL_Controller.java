package Controller;

import View.VL_Suche_View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Main.VL_Suche_Main;
import Model.VL;

/**
 * Die Klasse VL_Controller kümmert sich um die Verarbeitung der
 * Benutzereingaben aus der zugehörigen Klasse VL_Suche_View
 * 
 * @author Tim Schaible
 * 
 */

public class VL_Controller extends JFrame {

	private VL_Suche_View view; // Referenz auf das zugehörige View

	private static String drprof = "Dr. Prof. "; // Default_string für Textfelder mit Inhalt Professor

	FileWriter writer; // Deklarierung eines Filewriter
	File datei; // Deklarierung eines Files
	JFileChooser fileChooser;// Deklarierung eines FileChooser zum Export des Files

	/**
	 * Erzeugt ein neues Objekt der inneren Klasse hinzufügenListener und gibt es
	 * zurück
	 * 
	 * @return neues hinzufügenListener-Objekt
	 */
	public hinzufügenListener getHinzufügenListener() {
		return new hinzufügenListener();
	}

	/**
	 * Erzeugt ein neues Objekt der inneren Klasse suchenListener und gibt es zurück
	 * 
	 * @return neues suchenListener-Objekt
	 */
	public suchenListener getSuchenListener() {
		return new suchenListener();
	}

	/**
	 * Erzeugt ein neues Objekt der inneren Klasse cbListener und gibt es zurück
	 * 
	 * @return neues cbListener-Objekt
	 */
	public cbListener getcbListener() {
		return new cbListener();
	}

	/**
	 * Erzeugt ein neues Objekt der inneren Klasse cbListenerSuche und gibt es
	 * zurück
	 * 
	 * @return neues cbListenerSuche-Objekt
	 */
	public cbListenerSuche getcblistenerSuche() {
		return new cbListenerSuche();
	}

	/**
	 * Erzeugt ein neues Objekt der inneren Klasse exportierenListener und gibt es
	 * zurück
	 * 
	 * @return neues exportierenListener-Objekt
	 */
	public exportierenListener getExportierenListener() {
		return new exportierenListener();
	}

	/**
	 * Setzt die vom Controller zu verwaltende View
	 * 
	 * @param view zu verwaltende View
	 */
	public void setView(VL_Suche_View view) {
		this.view = view;

	}

	/**
	 * @author Tim Schaible
	 * 
	 *         Die innere Klasse hinzufügenListener implementiert das Interface
	 *         Actionlistener und dient dazu Benutzeraktionen auf der grafischen
	 *         Oberfläche zu verarbeiten. Hier werden die Eingaben des Benutzers
	 *         über die Textfelder verarbeitet und schließlich der Menge der vom
	 *         Benutzer gespeicherten Vorlesungen hinzugefügt.
	 *
	 */
	public class hinzufügenListener implements ActionListener {

		/*
		 * Diese Methode wird aufgerufen, wenn der Benutzer den hinzufügen Button
		 * betätigt. Es wird mit einer If Anweisung überprüft ob alle 3 Textfelder
		 * befüllt worden sind, erst dann wird die Vorlesung zum Treeset
		 * meineVorlesungen hinzugefügt. Ist das nicht der Fall bekommt der User eine
		 * Error Message.
		 *
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!view.textField1.getText().equals("") && !view.textField2.getText().equals(drprof)
					&& !view.textField3.getText().equals("")) {
				VL_Suche_Main.meineVorlesungen
						.add(new VL(view.textField1.getText(), view.textField2.getText(), view.textField3.getText()));
				JOptionPane.showMessageDialog(view, "Die Vorlesung wurde erfolgreich gespeichert", "Success",
						JOptionPane.INFORMATION_MESSAGE);

				view.cbGewählt.addItem(
						new VL(view.textField1.getText(), view.textField2.getText(), view.textField3.getText()));

				view.textField1.setText("");
				view.textField2.setText(drprof);
				view.textField3.setText("");

			} else {
				JOptionPane.showMessageDialog(view,
						"Bitte geben Sie in alle drei Textfelder eine Komponente ein um eine Vorlesung zu speichern",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * @author Tim Schaible
	 * 
	 *         Die innere Klasse suchenListener implementiert das Interface
	 *         Actionlistener und dient dazu Benutzeraktionen auf der grafischen
	 *         Oberfläche zu verarbeiten. Hier werden die Suchingaben des Benutzers
	 *         über die Textfelder verarbeitet, die vorher implementierten
	 *         Vorlesungen durchsucht und schließlich in einer ComboBox angezeigt.
	 *
	 */
	public class suchenListener implements ActionListener {

		/*
		 * Diese Methode wird aufgerufen wenn der User den Suchen Button betätigt. Es
		 * wird über vier verschieden If Anweisungen geprüft welche bzw. ob die
		 * Textfelder mit einer Suchanfrage befüllt wurden um dann das TreeSet mit den
		 * Vorimplementierten Vorlesungen zu durchlaufen und mit der Sucheingabe zu
		 * vergleichen. Stimmen diese hier überein werden die erfolgreichen
		 * Übereinstimmungen einer weiteren ComboBox mit den Suchergebnissen hinzugefügt.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			view.cb2.removeAllItems();
			view.cb2.addItem(new VL("Bitte", "Vorlesung", "wählen"));

			if (!view.sucheProf.getText().equals(drprof) && view.sucheSemester.getText().equals("")) {
				for (VL x : VL_Suche_Main.vorlesungen) {
					if (view.sucheProf.getText().equalsIgnoreCase(x.getProf())) {
						view.cb2.addItem(x);
					} else {
						continue;
					}
				}
				JOptionPane.showMessageDialog(view,
						"Alle Suchergebnisse wurden gefunden. \n Bitte wählen Sie die gewünschte Vorlesung aus.",
						"Success", JOptionPane.INFORMATION_MESSAGE);

			} else if (!view.sucheSemester.getText().equals("") && view.sucheProf.getText().equals(drprof)) {
				for (VL y : VL_Suche_Main.vorlesungen) {
					if (view.sucheSemester.getText().equalsIgnoreCase(y.getSemester())) {
						view.cb2.addItem(y);
					} else {
						continue;
					}
				}
				JOptionPane.showMessageDialog(view,
						"Alle Suchergebnisse wurden gefunden. \n Bitte wählen Sie die gewünschte Vorlesung aus.",
						"Success", JOptionPane.INFORMATION_MESSAGE);

			} else if (!view.sucheProf.getText().equals(drprof) && !view.sucheSemester.getText().equals("")) {
				for (VL z : VL_Suche_Main.vorlesungen) {
					if (view.sucheProf.getText().equalsIgnoreCase(z.getProf())
							&& view.sucheSemester.getText().equalsIgnoreCase(z.getSemester())) {
						view.cb2.addItem(z);
					} else {
						continue;
					}
				}
				JOptionPane.showMessageDialog(view,
						"Alle Suchergebnisse wurden gefunden. \n Bitte wählen Sie die gewünschte Vorlesung aus.",
						"Success", JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(view, "Bitte geben Sie eine Suchanfrage ein.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	/**
	 * @author Tim Schaible
	 * 
	 *         Die innere Klasse cbListener implementiert das Interface
	 *         Actionlistener und dient dazu Benutzeraktionen in der ComboBox der
	 *         Benutzeroberfläche mit den vorimplementierten Vorlesungen zu
	 *         verarbeiten. Die ausgewählten Vorlesungen werden schließlich dem
	 *         TreeSet mit den vom Benutzer ausgewählten Vorlesungen hinzugefügt.
	 */
	public class cbListener implements ActionListener {

		/*
		 * Diese Methode wird aufgerufen wenn der User aus der ComboBox mit
		 * vorimplemntierten Vorlesungen eine Vorlsung auswählt und fügt diese dann,
		 * falls noch nicht vorhanden, zum TreeSet meineVorlesungen hinzu in dem der
		 * User seine ausgewählten Vorlesungen speichern kann.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!VL_Suche_Main.meineVorlesungen.contains(view.cb.getSelectedItem())) {
				VL_Suche_Main.meineVorlesungen.add((VL) view.cb.getSelectedItem());
				JOptionPane.showMessageDialog(view, "Die gewählte Vorlesung wurde erfolgreich gespeichert", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				view.cbGewählt.addItem((VL) view.cb.getSelectedItem());
			} else {
				JOptionPane.showMessageDialog(view, "Die Vorlesung wurde bereits gewählt", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * @author Tim Schaible
	 * 
	 *         Die innere Klasse cbListenerSuche implementiert das Interface
	 *         Actionlistener und dient dazu Benutzeraktionen in der ComboBox der
	 *         Benutzeroberfläche mit den Suchergebnissen des Benutzers zu
	 *         verarbeiten. Die ausgewählten Vorlesungen werden schließlich auch dem
	 *         TreeSet mit den vom Benutzer ausgewählten Vorlesungen hinzugefügt.
	 */
	public class cbListenerSuche implements ActionListener {

		/*
		 * Diese Methode wird aufgerufen, wenn der User aus der ComboBox mit seinen
		 * Suchergebnisen eine Vorlesung auswählt und fügt diese dann, falls noch nicht
		 * vorhanden, zum TreeSet meineVorlesungen des Users hinzu.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (view.cb2.getSelectedIndex() != 0) {
				if (view.cb2.getSelectedItem() != null) {
					if (!VL_Suche_Main.meineVorlesungen.contains(view.cb2.getSelectedItem())) {
						VL_Suche_Main.meineVorlesungen.add((VL) view.cb2.getSelectedItem());
						JOptionPane.showMessageDialog(view, "Die gewählte Vorlesung wurde erfolgreich gespeichert",
								"Success", JOptionPane.INFORMATION_MESSAGE);
						view.cbGewählt.addItem((VL) view.cb2.getSelectedItem());

						view.sucheProf.setText(drprof);
						view.sucheSemester.setText("");
					} else {
						JOptionPane.showMessageDialog(view, "Die Vorlesung wurde bereits gewählt", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		}

	}

	/**
	 * @author Tim Schaible
	 * 
	 *         Die innere Klasse exportierenListener implementiert das Interface
	 *         Actionlistener und dient dazu dem Benutzer die Möglichkeit zu geben
	 *         über einen FileChooser und einen FilewWrtiter die gespeicherten
	 *         Vorlesungen in einer txt Datei zu exportieren und auf der Festplatte
	 *         des Computers zu speichern.
	 * 
	 */
	public class exportierenListener implements ActionListener {

		/*
		 * Diese Methode wird aufgerufen wenn der Benutzer den exportieren Button
		 * betätigt und führt mit Hilfe eines FileChooser dazu, dass man die gewählten
		 * Vorlesungen mit Hilfe eines Writers in eine Text-Datei speichern kann und
		 * beliebig auf dem eigenen Computer speichern kann.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			fileChooser = new JFileChooser();
			int value = fileChooser.showOpenDialog(null);
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setDialogTitle("Vorlesungen exportieren");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (value == JFileChooser.APPROVE_OPTION) {
				datei = fileChooser.getSelectedFile();
				try {
					writer = new FileWriter(datei, true);
					writer.write((String) VL_Suche_Main.meineVorlesungen.toString());
					writer.flush();
					writer.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			} else if (value == JFileChooser.CANCEL_OPTION) {
			} else if (value == JFileChooser.ERROR_OPTION) {

			}
		}

	}

}
