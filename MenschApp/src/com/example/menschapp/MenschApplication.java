package com.example.menschapp;

import android.app.Application;
import com.example.menschapp.util.*;;

public class MenschApplication extends Application {

//	private Kunde angemeldeterKunde;
	private MenschSystem obsStub;
	private Dice diceNumber;
	
//	public Kunde getAngemeldeterKunde() {
//		return angemeldeterKunde;
//	}
//	public void setAngemeldeterKunde(Kunde angemeldeterKunde) {
//		this.angemeldeterKunde = angemeldeterKunde;
//	}
	
	public Dice getDiceNumber() {
		return diceNumber;
	}
	public MenschSystem getObsStub() {
		return obsStub;
	}
	public void setObsStub(MenschSystem obsStub) {
		this.obsStub = obsStub;
	}
}
