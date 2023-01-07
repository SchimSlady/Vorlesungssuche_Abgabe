package View;

import java.awt.*;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Controller.VL_Controller;
import Model.VL;

/**
 * Die Klasse VL_Suche_View stellt ein Fenster zusammen, dass ein
 * Vorlesungsverzeichnis repräsentiert, in welchem Vorlesungen gespeichert und
 * durchsucht werden können
 * 
 * @author Tim Schaible
 * 
 */

public class VL_Suche_View extends JFrame {

	// Controller
	public VL_Controller controller;

	// Schriftart
	public Font font;

	// Farbe
	public static Color color;

	// Container
	public Container c;

	// Willkommensbereich
	public JPanel willkommenPanel;
	public JTextArea willkommen;

	// Input Bereich mit 3 Textfeldern und einem Button
	public JPanel inputPanelGesamt;
	public JPanel inputPanelTitel;
	public JPanel inputPanelProf;
	public JPanel inputPanelSemester;
	public JTextField textField1;
	public JTextField textField2;
	public JTextField textField3;
	public JButton hinzufügen;

	// Suchbereich mit 2 Textfelder und einem Button
	public JPanel suchPanel;
	public JTextField sucheProf;
	public JTextField sucheSemester;
	public JButton suchen;

	// Auswahlmöglichkeiten aus vorimplementierten Vorlesungen
	public JPanel auswahlPanel;
	public JComboBox<VL> cb;

	// Anzeige der Suchergebnisse
	public JPanel ergebnisPanel;
	public JComboBox<VL> cb2;

	// ausgewählte Vorlesungen und Export
	public JPanel gewähltePanel;
	public JComboBox<VL> cbGewählt;
	public JButton exportieren;

	/**
	 * Konstruktor der Klasse VL_Suche_View
	 * 
	 * @param vorlesungen      
	 * 							Set von vorgefertigten Vorlesungen die später vom
	 *                          Benutzer ausgewählt werden können.
	 * 
	 * @param meineVorlesungen 
	 * 							Set in dem später die vom Benutzer ausgewählten
	 *                          Vorlesungen gespeichert werden
	 */
	public VL_Suche_View(TreeSet<VL> vorlesungen, TreeSet<VL> meineVorlesungen) {
		c = getContentPane();
		setTitle("Vorlesungsverzeichnis");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 850);
		c.setLayout(new GridLayout(7, 1));

		font = new Font("Arial", Font.PLAIN, 16);
		color = Color.white;

		// Willkommensbereich erstellen
		willkommenPanel = new JPanel();
		willkommenPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		willkommenPanel.setLayout(new GridLayout(1, 1, 10, 10));

		willkommen = new JTextArea(
				"Herzlich Wilkommen im Vorlesungsverzeichnis.\n\n"
						+ "*Klicken Sie eine der Vorlesungen aus dem vorimplementierten Verzeichnis an um diese für Sie auszuwählen. \n"
						+ "*Benutzen Sie die Eingabefelder um das Verzeichnis nach einem bestimmten Professor oder einem bestimmten Semester zu durchsuchen.\n"
						+ "*Geben Sie für die Suche den Professornachnamen, 'WiSe' für Wintersemester oder 'SoSe' für Sommersemester an.\n"
						+ "*Um eine Vorlesung hinzuzufügen, die Sie nicht im Verzeichnis gefunden haben nutzen Sie die Eingabefelder. ");
		willkommen.setFont(font);
		willkommen.setEditable(false);
		willkommen.setBackground(Color.gray);
		willkommen.setForeground(Color.white);

		willkommenPanel.add(willkommen);

		c.add(willkommenPanel);

		// ComboBox erstellen mit vorimplementierten Vorlesungen
		auswahlPanel = new JPanel();
		auswahlPanel.setBorder(new TitledBorder(null, "Auswahl", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		auswahlPanel.setLayout(new GridLayout(1, 1, 10, 10));
		auswahlPanel.setBackground(color);

		cb = new JComboBox<VL>(vorlesungen.toArray(new VL[0]));
		cb.setFont(font);
		cb.setBackground(color);

		auswahlPanel.add(cb);

		c.add(auswahlPanel);

		// Sucheingabefelder und Suchbutton erstellen
		suchPanel = new JPanel();
		suchPanel.setBorder(new TitledBorder(null, "Suche", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		suchPanel.setLayout(new GridLayout(1, 3));
		suchPanel.setBackground(color);

		sucheProf = new JTextField("Dr. Prof. ");
		sucheProf.setBorder(
				new TitledBorder(null, "Suche nach Professor", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		sucheProf.setFont(font);

		sucheSemester = new JTextField();
		sucheSemester
				.setBorder(new TitledBorder(null, "Suche nach Semester", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		sucheSemester.setFont(font);

		suchen = new JButton("Suche starten");
		suchen.setFont(font);

		suchPanel.add(sucheProf);
		suchPanel.add(sucheSemester);
		suchPanel.add(suchen);

		c.add(suchPanel);

		// Anzeige der Suchergebnisse erstellen
		ergebnisPanel = new JPanel();
		ergebnisPanel.setBorder(new TitledBorder(null, "Suchergebnisse", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		ergebnisPanel.setLayout(new GridLayout(1, 1));
		ergebnisPanel.setBackground(color);

		cb2 = new JComboBox<VL>();
		cb2.setFont(font);
		cb2.setBackground(color);

		ergebnisPanel.add(cb2);

		c.add(ergebnisPanel);

		// Input Textfeld 1 und 2 erstellen
		inputPanelGesamt = new JPanel();
		inputPanelGesamt.setBorder(new TitledBorder(null, "Eingabe", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		inputPanelGesamt.setLayout(new GridLayout(1, 2, 10, 10));
		inputPanelGesamt.setBackground(color);

		inputPanelTitel = new JPanel();
		inputPanelTitel.setBorder(new TitledBorder(null, "Titel:", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		inputPanelTitel.setLayout(new GridLayout(1, 1, 10, 10));
		inputPanelTitel.setBackground(color);

		inputPanelProf = new JPanel();
		inputPanelProf.setBorder(new TitledBorder(null, "Professor:", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		inputPanelProf.setLayout(new GridLayout(1, 2, 10, 10));
		inputPanelProf.setBackground(color);

		inputPanelGesamt.add(inputPanelTitel);
		inputPanelGesamt.add(inputPanelProf);

		textField1 = new JTextField();
		textField1.setFont(font);

		textField2 = new JTextField("Dr. Prof. ");
		textField2.setFont(font);

		inputPanelTitel.add(textField1);
		inputPanelProf.add(textField2);

		c.add(inputPanelGesamt);

		// Input Textfeld 3 und hinzufügen Button erstellen
		inputPanelSemester = new JPanel();
		inputPanelSemester.setBorder(new TitledBorder(null, "Semester:", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		inputPanelSemester.setLayout(new GridLayout(1, 2, 10, 10));
		inputPanelSemester.setBackground(color);

		textField3 = new JTextField();
		textField3.setFont(font);

		hinzufügen = new JButton("Hinzufügen");
		hinzufügen.setFont(font);

		inputPanelSemester.add(textField3);
		inputPanelSemester.add(hinzufügen);

		c.add(inputPanelSemester);

		// Panel in dem gewählte Vorlesungen angezeigt werden
		gewähltePanel = new JPanel();
		gewähltePanel.setLayout(new BorderLayout());
		gewähltePanel.setBackground(color);
		gewähltePanel
				.setBorder(new TitledBorder(null, "Getroffene Auswahl", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));

		cbGewählt = new JComboBox<VL>(meineVorlesungen.toArray(new VL[0]));
		cbGewählt.setFont(font);
		cbGewählt.setBackground(color);

		exportieren = new JButton("Exportieren");
		exportieren.setFont(font);

		gewähltePanel.add(cbGewählt, BorderLayout.CENTER);
		gewähltePanel.add(exportieren, BorderLayout.EAST);

		c.add(gewähltePanel);

	}

	/**
	 * Setzt den für die View zuständigen Controller und fügt den verschiedenen
	 * Fensterelementen ihre Actionlistener hinzu
	 * 
	 * @param controller zu setzender Controller
	 */
	public void setController(VL_Controller controller) {
		this.controller = controller;
		hinzufügen.addActionListener(this.controller.getHinzufügenListener());
		suchen.addActionListener(this.controller.getSuchenListener());
		cb.addActionListener(this.controller.getcbListener());
		cb2.addActionListener(this.controller.getcblistenerSuche());
		exportieren.addActionListener(this.controller.getExportierenListener());
	}

}
