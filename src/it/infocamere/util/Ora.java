package it.infocamere.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Ora {
	private int ora;
	private int minuti;
	
	public Ora(){
		ora = 0;
		minuti=0;
	}
	
	public Ora(String oraString) throws ParseException{
		unmarshalFromString(oraString);
	}

	public void unmarshalFromString(String oraString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH.mm");
		dateFormat.parse(oraString);
		int puntoIndex = oraString.indexOf(".");
		ora = Integer.valueOf(oraString.substring(0, puntoIndex));
		minuti = Integer.valueOf(oraString.substring(puntoIndex+1, oraString.length()));
	}

	public int getOra() {
		return ora;
	}

	public void setOra(int ora) {
		this.ora = ora;
	}

	public void setMinuti(int minuti) {
		this.minuti = minuti;
	}

	public int getMinuti() {
		return minuti;
	}
}
