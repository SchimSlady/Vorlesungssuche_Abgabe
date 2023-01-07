package Model;

/**
 * Die Klasse VL repräsentiert den Aufbau einer Vorlesung aus bestimmten
 * Komponeneten
 * 
 * @author Tim Schaible
 * 
 */

public class VL implements Comparable<VL> {
	private String titel;
	private String prof;
	private String semester;

	/**
	 * Konstruktor, dem ein Titel der Vorlesung, der Name des Profs und das Semester
	 * übergeben wird.
	 * 
	 * @param titel    Titel der Vorlesung
	 * @param prof     Name des Professors
	 * @param semester entweder Sommersemester(SoSe) oder Wintersemester (WiSe)
	 * 
	 */
	public VL(String titel, String prof, String semester) {
		super();
		this.titel = titel;
		this.prof = prof;
		this.semester = semester;
	}

	/**
	 * Legt den Namen des Professors fest
	 * 
	 * @param prof einzustellender Name des Professors
	 */
	public void setProf(String prof) {
		this.prof = prof;
	}

	/**
	 * Liefert den Namen des Professors zurück
	 * 
	 * @return prof den Namen des Professors
	 */
	public String getProf() {
		return this.prof;
	}

	/**
	 * Legt den Titel der Vorlesung fest
	 * 
	 * @param titel Titel der Vorlesung
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}

	/**
	 * Liefert den Titel der Vorlesung zurück
	 * 
	 * @return titel den Titel der Vorlesung
	 */
	public String getTitel() {
		return this.titel;
	}

	/**
	 * Legt Semester fest in dem die Vorlesung stattfindet
	 * 
	 * @param semester Semester der Vorlesung
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * Liefert das Semester zurück in dem die Vorlesung stattfindet
	 * 
	 * @return semester entweder WiSe oder SoSe
	 */
	public String getSemester() {
		return this.semester;
	}

	/*
	 * legt die Ausgabe der Vorlesungen mit ihren Parameter bei einer Ausgabe auf
	 * der Benutzeroberfläche in einem String äußerlich fest
	 */
	public String toString() {
		return "Titel: " + this.titel + " | Prof: " + this.prof + " | Semester:" + this.semester + "\n";
	}

	@Override
	public int compareTo(VL o) {
		if (this == o)
			return 0;
		else
			return 1;
	}

}
