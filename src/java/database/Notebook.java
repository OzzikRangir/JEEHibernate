package database;
// Generated 2019-01-13 17:59:56 by Hibernate Tools 4.3.1



/**
 * Notebook generated by hbm2java
 */
public class Notebook  implements java.io.Serializable {


     private int idZeszytu;
     private String nazwa;
     private Integer liczbaStron;
     private Boolean czyKartkiNumerowane;

    public Notebook() {
    }

	
    public Notebook(int idZeszytu) {
        this.idZeszytu = idZeszytu;
    }
    public Notebook(int idZeszytu, String nazwa, Integer liczbaStron, Boolean czyKartkiNumerowane) {
       this.idZeszytu = idZeszytu;
       this.nazwa = nazwa;
       this.liczbaStron = liczbaStron;
       this.czyKartkiNumerowane = czyKartkiNumerowane;
    }
   
    public int getIdZeszytu() {
        return this.idZeszytu;
    }
    
    public void setIdZeszytu(int idZeszytu) {
        this.idZeszytu = idZeszytu;
    }
    public String getNazwa() {
        return this.nazwa;
    }
    
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public Integer getLiczbaStron() {
        return this.liczbaStron;
    }
    
    public void setLiczbaStron(Integer liczbaStron) {
        this.liczbaStron = liczbaStron;
    }
    public Boolean getCzyKartkiNumerowane() {
        return this.czyKartkiNumerowane;
    }
    
    public String getYesNo(boolean bool){
        
        if(bool)
            return "Tak";
        else
            return "Nie";
    }
    
    public void setCzyKartkiNumerowane(Boolean czyKartkiNumerowane) {
        this.czyKartkiNumerowane = czyKartkiNumerowane;
    }




}

