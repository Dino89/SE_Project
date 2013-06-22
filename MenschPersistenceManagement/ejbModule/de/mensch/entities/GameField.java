package de.mensch.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * int value 0 leeres feld
 * int value 1 blau
 * int value 2 rot
 * int value 3 grün
 * int value 4 gelb
 * 
 * int index 1 bis 4 startfelder blau
 * int index 5 bis 8 startfelder rot
 * int index 9 bis 12 startfelder grün
 * int index 13 bis 16 startfelder gelb
 * int index 17 bis 20 endfelder blau
 * int index 21 bis 24 endfelder rot
 * int index 25 bis 28 endfelder grün
 * int index 29 bis 32 endfelder gelb
 * int index 33 bis 72 spielfelder, startend mit dem blauen feld (auf der grafik field_1);
*/

@Entity
public class GameField implements Serializable {
	
	private static final long serialVersionUID = 12L;
	private ArrayList<Integer> fields = new ArrayList<>();
	
	@Id @GeneratedValue
	private int id;
	
	private GameField() {
		for(int i=0; i<=73;i++) {
			fields.add(0);
		}
		
		setField(0, 0);
		
		for(int i = 1; i<=4;i++) {
			setField(i, 1);
		}
		for(int i = 5; i<=8;i++) {
			setField(i, 2);
		}
		for(int i = 9; i<=12;i++) {
			setField(i, 3);
		}
		for(int i = 13; i<=16;i++) {
			setField(i, 4);
		}
		for(int i = 17; i<=72;i++) {
			setField(i, 0);
		}
	}
		
	public void setField(int index, int value) {
		this.fields.set(index, value);
	}
	
	public int getField(int index) {
		return this.fields.get(index);
	}

	public int getSize() {
		return this.fields.size();
	}
//	private int field_blue_1;
//	private int field_blue_2;
//	private int field_blue_3;
//	private int field_blue_4;
//	
//	private int field_red_1;
//	private int field_red_2;
//	private int field_red_3;
//	private int field_red_4;
//	
//	private int field_green_1;
//	private int field_green_2;
//	private int field_green_3;
//	private int field_green_4;
//	
//	private int field_yellow_1;
//	private int field_yellow_2;
//	private int field_yellow_3;
//	private int field_yellow_4;
//	
//	private int field_blue_house_1;
//	private int field_blue_house_2;
//	private int field_blue_house_3;
//	private int field_blue_house_4;
//	
//	private int field_red_house_1;
//	private int field_red_house_2;
//	private int field_red_house_3;
//	private int field_red_house_4;
//	
//	private int field_green_house_1;
//	private int field_green_house_2;
//	private int field_green_house_3;
//	private int field_green_house_4;
//	
//	private int field_yellow_house_1;
//	private int field_yellow_house_2;
//	private int field_yellow_house_3;
//	private int field_yellow_house_4;
//	
//	private int field_1;
//	private int field_2;
//	private int field_3;
//	private int field_4;
//	private int field_5;
//	private int field_6;
//	private int field_7;
//	private int field_8;
//	private int field_9;
//	private int field_10;
//	private int field_11;
//	private int field_12;
//	private int field_13;
//	private int field_14;
//	private int field_15;
//	private int field_16;
//	private int field_17;
//	private int field_18;
//	private int field_19;
//	private int field_20;
//	private int field_21;
//	private int field_22;
//	private int field_23;
//	private int field_24;
//	private int field_25;
//	private int field_26;
//	private int field_27;
//	private int field_28;
//	private int field_29;
//	private int field_30;
//	private int field_31;
//	private int field_32;
//	private int field_33;
//	private int field_34;
//	private int field_35;
//	private int field_36;
//	private int field_37;
//	private int field_38;
//	private int field_39;
//	private int field_40;
//	
//	public GameField() {
//		super();
//	}	
//	
//	public int getId() {
//		return id;
//	}
//	public int getField_blue_1() {
//		return field_blue_1;
//	}
//	public int getField_blue_2() {
//		return field_blue_2;
//	}
//	public int getField_blue_3() {
//		return field_blue_3;
//	}
//	public int getField_blue_4() {
//		return field_blue_4;
//	}
//	public int getField_red_1() {
//		return field_red_1;
//	}
//	public int getField_red_2() {
//		return field_red_2;
//	}
//	public int getField_red_3() {
//		return field_red_3;
//	}
//	public int getField_red_4() {
//		return field_red_4;
//	}
//	public int getField_green_1() {
//		return field_green_1;
//	}
//	public int getField_green_2() {
//		return field_green_2;
//	}
//	public int getField_green_3() {
//		return field_green_3;
//	}
//	public int getField_green_4() {
//		return field_green_4;
//	}
//	public int getField_yellow_1() {
//		return field_yellow_1;
//	}
//	public int getField_yellow_2() {
//		return field_yellow_2;
//	}
//	public int getField_yellow_3() {
//		return field_yellow_3;
//	}
//	public int getField_yellow_4() {
//		return field_yellow_4;
//	}
//	public int getField_blue_house_1() {
//		return field_blue_house_1;
//	}
//	public int getField_blue_house_2() {
//		return field_blue_house_2;
//	}
//	public int getField_blue_house_3() {
//		return field_blue_house_3;
//	}
//	public int getField_blue_house_4() {
//		return field_blue_house_4;
//	}
//	public int getField_red_house_1() {
//		return field_red_house_1;
//	}
//	public int getField_red_house_2() {
//		return field_red_house_2;
//	}
//	public int getField_red_house_3() {
//		return field_red_house_3;
//	}
//	public int getField_red_house_4() {
//		return field_red_house_4;
//	}
//	public int getField_green_house_1() {
//		return field_green_house_1;
//	}
//	public int getField_green_house_2() {
//		return field_green_house_2;
//	}
//	public int getField_green_house_3() {
//		return field_green_house_3;
//	}
//	public int getField_green_house_4() {
//		return field_green_house_4;
//	}
//	public int getField_yellow_house_1() {
//		return field_yellow_house_1;
//	}
//	public int getField_yellow_house_2() {
//		return field_yellow_house_2;
//	}
//	public int getField_yellow_house_3() {
//		return field_yellow_house_3;
//	}
//	public int getField_yellow_house_4() {
//		return field_yellow_house_4;
//	}
//	public int getField_1() {
//		return field_1;
//	}
//	public int getField_2() {
//		return field_2;
//	}
//	public int getField_3() {
//		return field_3;
//	}
//	public int getField_4() {
//		return field_4;
//	}
//	public int getField_5() {
//		return field_5;
//	}
//	public int getField_6() {
//		return field_6;
//	}
//	public int getField_7() {
//		return field_7;
//	}
//	public int getField_8() {
//		return field_8;
//	}
//	public int getField_9() {
//		return field_9;
//	}
//	public int getField_10() {
//		return field_10;
//	}
//	public int getField_11() {
//		return field_11;
//	}
//	public int getField_12() {
//		return field_12;
//	}
//	public int getField_13() {
//		return field_13;
//	}
//	public int getField_14() {
//		return field_14;
//	}
//	public int getField_15() {
//		return field_15;
//	}
//	public int getField_16() {
//		return field_16;
//	}
//	public int getField_17() {
//		return field_17;
//	}
//	public int getField_18() {
//		return field_18;
//	}
//	public int getField_19() {
//		return field_19;
//	}
//	public int getField_20() {
//		return field_20;
//	}
//	public int getField_21() {
//		return field_21;
//	}
//	public int getField_22() {
//		return field_22;
//	}
//	public int getField_23() {
//		return field_23;
//	}
//	public int getField_24() {
//		return field_24;
//	}
//	public int getField_25() {
//		return field_25;
//	}
//	public int getField_26() {
//		return field_26;
//	}
//	public int getField_27() {
//		return field_27;
//	}
//	public int getField_28() {
//		return field_28;
//	}
//	public int getField_29() {
//		return field_29;
//	}
//	public int getField_30() {
//		return field_30;
//	}
//	public int getField_31() {
//		return field_31;
//	}
//	public int getField_32() {
//		return field_32;
//	}
//	public int getField_33() {
//		return field_33;
//	}
//	public int getField_34() {
//		return field_34;
//	}
//	public int getField_35() {
//		return field_35;
//	}
//	public int getField_36() {
//		return field_36;
//	}
//	public int getField_37() {
//		return field_37;
//	}
//	public int getField_38() {
//		return field_38;
//	}
//	public int getField_39() {
//		return field_39;
//	}
//	public int getField_40() {
//		return field_40;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public void setField_blue_1(int field_blue_1) {
//		this.field_blue_1 = field_blue_1;
//	}
//	public void setField_blue_2(int field_blue_2) {
//		this.field_blue_2 = field_blue_2;
//	}
//	public void setField_blue_3(int field_blue_3) {
//		this.field_blue_3 = field_blue_3;
//	}
//	public void setField_blue_4(int field_blue_4) {
//		this.field_blue_4 = field_blue_4;
//	}
//	public void setField_red_1(int field_red_1) {
//		this.field_red_1 = field_red_1;
//	}
//	public void setField_red_2(int field_red_2) {
//		this.field_red_2 = field_red_2;
//	}
//	public void setField_red_3(int field_red_3) {
//		this.field_red_3 = field_red_3;
//	}
//	public void setField_red_4(int field_red_4) {
//		this.field_red_4 = field_red_4;
//	}
//	public void setField_green_1(int field_green_1) {
//		this.field_green_1 = field_green_1;
//	}
//	public void setField_green_2(int field_green_2) {
//		this.field_green_2 = field_green_2;
//	}
//	public void setField_green_3(int field_green_3) {
//		this.field_green_3 = field_green_3;
//	}
//	public void setField_green_4(int field_green_4) {
//		this.field_green_4 = field_green_4;
//	}
//	public void setField_yellow_1(int field_yellow_1) {
//		this.field_yellow_1 = field_yellow_1;
//	}
//	public void setField_yellow_2(int field_yellow_2) {
//		this.field_yellow_2 = field_yellow_2;
//	}
//	public void setField_yellow_3(int field_yellow_3) {
//		this.field_yellow_3 = field_yellow_3;
//	}
//	public void setField_yellow_4(int field_yellow_4) {
//		this.field_yellow_4 = field_yellow_4;
//	}
//	public void setField_blue_house_1(int field_blue_house_1) {
//		this.field_blue_house_1 = field_blue_house_1;
//	}
//	public void setField_blue_house_2(int field_blue_house_2) {
//		this.field_blue_house_2 = field_blue_house_2;
//	}
//	public void setField_blue_house_3(int field_blue_house_3) {
//		this.field_blue_house_3 = field_blue_house_3;
//	}
//	public void setField_blue_house_4(int field_blue_house_4) {
//		this.field_blue_house_4 = field_blue_house_4;
//	}
//	public void setField_red_house_1(int field_red_house_1) {
//		this.field_red_house_1 = field_red_house_1;
//	}
//	public void setField_red_house_2(int field_red_house_2) {
//		this.field_red_house_2 = field_red_house_2;
//	}
//	public void setField_red_house_3(int field_red_house_3) {
//		this.field_red_house_3 = field_red_house_3;
//	}
//	public void setField_red_house_4(int field_red_house_4) {
//		this.field_red_house_4 = field_red_house_4;
//	}
//	public void setField_green_house_1(int field_green_house_1) {
//		this.field_green_house_1 = field_green_house_1;
//	}
//	public void setField_green_house_2(int field_green_house_2) {
//		this.field_green_house_2 = field_green_house_2;
//	}
//	public void setField_green_house_3(int field_green_house_3) {
//		this.field_green_house_3 = field_green_house_3;
//	}
//	public void setField_green_house_4(int field_green_house_4) {
//		this.field_green_house_4 = field_green_house_4;
//	}
//	public void setField_yellow_house_1(int field_yellow_house_1) {
//		this.field_yellow_house_1 = field_yellow_house_1;
//	}
//	public void setField_yellow_house_2(int field_yellow_house_2) {
//		this.field_yellow_house_2 = field_yellow_house_2;
//	}
//	public void setField_yellow_house_3(int field_yellow_house_3) {
//		this.field_yellow_house_3 = field_yellow_house_3;
//	}
//	public void setField_yellow_house_4(int field_yellow_house_4) {
//		this.field_yellow_house_4 = field_yellow_house_4;
//	}
//	public void setField_1(int field_1) {
//		this.field_1 = field_1;
//	}
//	public void setField_2(int field_2) {
//		this.field_2 = field_2;
//	}
//	public void setField_3(int field_3) {
//		this.field_3 = field_3;
//	}
//	public void setField_4(int field_4) {
//		this.field_4 = field_4;
//	}
//	public void setField_5(int field_5) {
//		this.field_5 = field_5;
//	}
//	public void setField_6(int field_6) {
//		this.field_6 = field_6;
//	}
//	public void setField_7(int field_7) {
//		this.field_7 = field_7;
//	}
//	public void setField_8(int field_8) {
//		this.field_8 = field_8;
//	}
//	public void setField_9(int field_9) {
//		this.field_9 = field_9;
//	}
//	public void setField_10(int field_10) {
//		this.field_10 = field_10;
//	}
//	public void setField_11(int field_11) {
//		this.field_11 = field_11;
//	}
//	public void setField_12(int field_12) {
//		this.field_12 = field_12;
//	}
//	public void setField_13(int field_13) {
//		this.field_13 = field_13;
//	}
//	public void setField_14(int field_14) {
//		this.field_14 = field_14;
//	}
//	public void setField_15(int field_15) {
//		this.field_15 = field_15;
//	}
//	public void setField_16(int field_16) {
//		this.field_16 = field_16;
//	}
//	public void setField_17(int field_17) {
//		this.field_17 = field_17;
//	}
//	public void setField_18(int field_18) {
//		this.field_18 = field_18;
//	}
//	public void setField_19(int field_19) {
//		this.field_19 = field_19;
//	}
//	public void setField_20(int field_20) {
//		this.field_20 = field_20;
//	}
//	public void setField_21(int field_21) {
//		this.field_21 = field_21;
//	}
//	public void setField_22(int field_22) {
//		this.field_22 = field_22;
//	}
//	public void setField_23(int field_23) {
//		this.field_23 = field_23;
//	}
//	public void setField_24(int field_24) {
//		this.field_24 = field_24;
//	}
//	public void setField_25(int field_25) {
//		this.field_25 = field_25;
//	}
//	public void setField_26(int field_26) {
//		this.field_26 = field_26;
//	}
//	public void setField_27(int field_27) {
//		this.field_27 = field_27;
//	}
//	public void setField_28(int field_28) {
//		this.field_28 = field_28;
//	}
//	public void setField_29(int field_29) {
//		this.field_29 = field_29;
//	}
//	public void setField_30(int field_30) {
//		this.field_30 = field_30;
//	}
//	public void setField_31(int field_31) {
//		this.field_31 = field_31;
//	}
//	public void setField_32(int field_32) {
//		this.field_32 = field_32;
//	}
//	public void setField_33(int field_33) {
//		this.field_33 = field_33;
//	}
//	public void setField_34(int field_34) {
//		this.field_34 = field_34;
//	}
//	public void setField_35(int field_35) {
//		this.field_35 = field_35;
//	}
//	public void setField_36(int field_36) {
//		this.field_36 = field_36;
//	}
//	public void setField_37(int field_37) {
//		this.field_37 = field_37;
//	}
//	public void setField_38(int field_38) {
//		this.field_38 = field_38;
//	}
//	public void setField_39(int field_39) {
//		this.field_39 = field_39;
//	}
//	public void setField_40(int field_40) {
//		this.field_40 = field_40;
//	}
//	
}
