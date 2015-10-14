package it.infocamere.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalcOre {
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Calendar calOttoOre = new GregorianCalendar();
		calOttoOre.set(Calendar.HOUR_OF_DAY, 8);
		calOttoOre.set(Calendar.MINUTE, 0);
		System.out.println("*** PROGRAMMA DI CALCOLO ORE IC ***");
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("Ora inizio mattina: ");
			Ora oraInizioMattina = new Ora(dataIn.readLine());
			System.out.print("Ora fine mattina: ");
			Ora oraFineMattina = new Ora(dataIn.readLine());
			System.out.print("Ora inizio pome: ");
			Ora oraInizioPome = new Ora(dataIn.readLine());

			Calendar calendarInizioMattina = new GregorianCalendar();
			calendarInizioMattina.set(Calendar.HOUR_OF_DAY, oraInizioMattina.getOra());
			calendarInizioMattina.set(Calendar.MINUTE, oraInizioMattina.getMinuti());

			Calendar calendarFineMattina = new GregorianCalendar();
			calendarFineMattina.set(Calendar.HOUR_OF_DAY, oraFineMattina.getOra() - 1);
			calendarFineMattina.set(Calendar.MINUTE, oraFineMattina.getMinuti());

			Calendar calendarInizioPome = new GregorianCalendar();
			calendarInizioPome.set(Calendar.HOUR_OF_DAY, oraInizioPome.getOra() - 1);
			calendarInizioPome.set(Calendar.MINUTE, oraInizioPome.getMinuti());

			if (calendarFineMattina.before(calendarInizioMattina)) {
				throw new Exception("L'ora di fine mattina deve essere successiva all'ora di inizio");
			}
			if (calendarInizioPome.before(calendarInizioMattina) || calendarInizioPome.before(calendarFineMattina)) {
				throw new Exception("L'ora di inizio pome deve essere successiva all'ora di inizio e fine mattina");
			}

			long oreMattina = calendarFineMattina.getTimeInMillis() - calendarInizioMattina.getTimeInMillis();

			long oreRimanenti = calOttoOre.getTimeInMillis() - oreMattina;
			Calendar calOreRimanenti = new GregorianCalendar();
			calOreRimanenti.setTimeInMillis(oreRimanenti);

			Calendar calFine = new GregorianCalendar();
			calFine.set(Calendar.HOUR_OF_DAY, calendarInizioPome.get(Calendar.HOUR_OF_DAY) + calOreRimanenti.get(Calendar.HOUR_OF_DAY));
			calFine.set(Calendar.MINUTE, calendarInizioPome.get(Calendar.MINUTE) + calOreRimanenti.get(Calendar.MINUTE));

			System.out.println("Ora fine giornata 8 ore: " + dateFormat.format(new Date(calFine.getTimeInMillis())));
			dataIn.readLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Il formato delle ore deve essere HH.mm");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
