package 포켓몬키우기;

public class PKDTO {
	private int LIVE,ATK, DEF, LV, HP, XP, STT, FTG, CNDTN;
	private String ID, PW, PKNAME;
	
	public PKDTO(int LIVE,int ATK,int DEF,int LV,int HP,int XP, int STT, int FTG, int CNDTN,String ID, String PW, String PKNAME) {
		this.LIVE = LIVE;
		this.ATK = ATK; 
		this.DEF = DEF; 
		this.LV = LV;
		this.HP = HP;  
		this.XP = XP;
		this.ID = ID;
		this.PW = PW;
		this.PKNAME = PKNAME;
		this.STT = STT;
		this.FTG = FTG; 
		this.CNDTN = CNDTN;	
	}

	public int getLIVE() {
		return LIVE;
	}

	public void setLIVE(int lIVE) {
		LIVE = lIVE;
	}

	public int getATK() {
		return ATK;
	}

	public void setATK(int aTK) {
		ATK = aTK;
	}

	public int getDEF() {
		return DEF;
	}

	public void setDEF(int dEF) {
		DEF = dEF;
	}

	public int getLV() {
		return LV;
	}

	public void setLV(int lV) {
		LV = lV;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getXP() {
		return XP;
	}

	public void setXP(int xP) {
		XP = xP;
	}

	public int getSTT() {
		return STT;
	}

	public void setSTT(int sTT) {
		STT = sTT;
	}

	public int getFTG() {
		return FTG;
	}

	public void setFTG(int fTG) {
		FTG = fTG;
	}

	public int getCNDTN() {
		return CNDTN;
	}

	public void setCNDTN(int cNDTN) {
		CNDTN = cNDTN;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	public String getPKNAME() {
		return PKNAME;
	}

	public void setPKNAME(String pKNAME) {
		PKNAME = pKNAME;
	}//lv 
	public String toString() {
		return "[레벨=" + LV + ", 포만감=" + STT + ", 피로도=" + FTG + ", 컨디션=" + CNDTN + ", 경험치=" + XP + ", HP=" + HP
	            + ", 공격력=" + ATK + ", 방어력=" + DEF +"]";
	}
	
}
