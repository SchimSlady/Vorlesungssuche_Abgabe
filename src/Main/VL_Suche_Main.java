package Main;

import java.util.*;

import Controller.VL_Controller;
import Model.VL;
import View.VL_Suche_View;

/**
 * Die KLasse VL_Suche_Main enthält die main-Methode, aus der heraus die zu
 * anzeigende Benutzeroberfläche und der Controller erzeugt wird. Außerdem
 * werden die view-Klasse und der Controller hier miteinander bekannt gemacht.
 * Hier werden zusätzlich noch die beiden Treesets erstellt, die zum Speichern
 * der Vorlesungen benutzt werden.
 * 
 * @author Tim Schaible
 * 
 */

public class VL_Suche_Main {

	/*
	 * Deklaration von Variablen
	 */
	private static VL_Suche_View view;
	private static VL_Controller controller;
	
	public static TreeSet<VL> vorlesungen; //Treeset für vorimplementierte Vorlesungen
	public static TreeSet<VL> meineVorlesungen; //Treeset für vom Benutzer ausgewählte Vorlesungen

	/**
	 * Main-Methode der Klasse
	 * 
	 * @param args
	 * 
	 */

	public static void main(String[] args) {

		// erzeuge das generische Treeset vorlesungen des Typs VL
		vorlesungen = new TreeSet<VL>();

		/*
		 *  Füge der Menge einige vorgespeicherte Vorlesungen hinzu, die später zur
		 *	Auswahl dienen.
		 */
		vorlesungen.add(new VL("Volkswirtschaftslehre 1", "Dr. Prof. Puppe", "WiSe"));
		vorlesungen.add(new VL("Statistik 1", "Dr. Prof. Grothe", "SoSe"));
		vorlesungen.add(new VL("Künstliche Intelligenz", "Dr. Prof. Baumann", "SoSe"));
		vorlesungen.add(new VL("Mathematik 1", "Dr. Prof. Nestmann", "WiSe"));
		vorlesungen.add(new VL("Einführung ins Operation Research 1", "Dr. Prof. Stein", "SoSe"));
		vorlesungen.add(new VL("Technische Mechanik 1", "Dr. Prof. Fidilin", "SoSe"));
		vorlesungen.add(new VL("Sportwissenschaften 1", "Dr. Prof. Gomez", "WiSe"));
		vorlesungen.add(new VL("Volkswirtschftslehre 2", "Dr. Prof. Scheffel", "SoSe"));
		vorlesungen.add(new VL("Betriebswirtschaftslehre FR", "Dr. Prof. Ruckes", "WiSe"));
		vorlesungen.add(new VL("Informatik 1", "Dr. Prof. Sure-Vetter", "SoSe"));
		vorlesungen.add(new VL("Rechnungswesen", "Dr. Prof. Mustermann", "WiSe"));
		vorlesungen.add(new VL("Informatik 2", "Dr. Prof. Schmidt", "WiSe"));
		vorlesungen.add(new VL("Mathematik 2", "Dr. Prof. Nestmann", "SoSe"));
		vorlesungen.add(new VL("Betriebswirtschaftslehre UI", "Dr. Prof. Herzog", "WiSe"));
		vorlesungen.add(new VL("Betriebswirtschaftslehre PM", "Dr. Prof. Herzog", "SoSe"));

		/*
		 * erzeuge ein weiteres generishces Treeset des Typs VL in dem der Benutzer
		 * seine gewünschten Vorlesungen speichern kann.
		 */
		meineVorlesungen = new TreeSet<VL>();

		// erzeuge ein Fenster des Typs VL_Suche_View
		view = new VL_Suche_View(vorlesungen, meineVorlesungen);

		// erzeuge ein Controller des Typs VL_Controller
		controller = new VL_Controller();

		// Controller und view miteinander bekannt machen
		controller.setView(view);
		view.setController(controller);

		// setze Fenster sichtbar
		view.setVisible(true);

	}

}
