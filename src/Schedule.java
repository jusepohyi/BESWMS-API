import java.text.*;
import java.util.*;
public class Schedule {

    Date date;
    Date currentDate = new Date(); //Current date, for comparing dates.

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("E, dd-MMM-yyyy");

    public Resident[] resident = new Resident[100];
    int schedResCount = 0;

    int dd;
    int MM;
    int yyyy;
    String purok;

    int schedKey = Main.scheduleCount;

    public Schedule(int day, int month, int year, String purok) throws ParseException {
        dd = day;
        MM = month;
        yyyy = year;
        this.purok = purok;
        date = dateFormat.parse(dd + "-" + MM +"-"+ yyyy);
        setSchedKey(schedKey);
    }
    //-----------------------------------  Checking duplicate sh8s and some sh8s----------------------------------------
    public boolean duplicateResidentInSched(int x){
        int check = 0;
        for(int i = 0; i < schedResCount; i++){
            if(resident[i].getResKey() == x){
                check = 1;
                break;
            }
        }
        return check == 1;
    }
    //----------------------------------------------Add Methods---------------------------------------------------------
    public void addResident(Resident res, String grbgdtls){
        resident[schedResCount] = res;
        resident[schedResCount].setGarbageDetails(grbgdtls);

        for(int x = 0; x < Admin.residentCount; x++) {
            if (resident[schedResCount].getResKey() == Admin.resident[x].getResKey()) {
                Admin.resident[x].setRecorded(1);
                System.out.println(Admin.resident[x].getFirstName() + " " + Admin.resident[x].getTotalFines());
            }
        }

        resident[schedResCount].setRecorded(1);
        schedResCount++;
    }
    //----------------------------------------------View Methods--------------------------------------------------------

    public void viewRecordedResidents(){
        System.out.println("DATE: " +getSched());
        System.out.println("RECORDED RESIDENTS:");
        System.out.println("-----------------------------------------------------------------------------");

        for(int i = 0; i < schedResCount; i++){
            if(resident[i].getRecorded() == 1) {
                System.out.println(i+1+"). ");
                System.out.println("Name:\t"+resident[i].getLastName()+", "+resident[i].getFirstName()+" "+resident[i].getMiddleName());
                System.out.println("Garbage Details: ");
                System.out.println(resident[i].getGarbageDetails());
                System.out.println("-----------------------------------------------------------------------------");
            }
        }
        System.out.println();
    }
    //----------------------------------------------Edit Methods--------------------------------------------------------
    public void editSchedule(int day, int month, int year)throws ParseException {
        setDd(day); setMM(month); setYyyy(year);
        date = dateFormat.parse(dd + "-" + MM +"-"+ yyyy);
    }
    //----------------------------------------------Sort Methods--------------------------------------------------------
    //---------------------------------------------Search Methods-------------------------------------------------------
    //-------------------------------------------------Getters----------------------------------------------------------
    public Date getDate() {
        return date;
    }

    /*public int getDateOfthisSh8(){
        return date.get();
    }*/

    public String getSched(){
        return dateFormat1.format(date);
    }

    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public int getMM() {
        return MM;
    }

    public void setMM(int MM) {
        this.MM = MM;
    }

    public int getYyyy() {
        return yyyy;
    }

    public void setYyyy(int yyyy) {
        this.yyyy = yyyy;
    }

    public String getPurok() {
        return purok;
    }

    public void setPurok(String purok) {
        this.purok = purok;
    }

    public int getSchedKey() {
        return schedKey;
    }

    public void setSchedKey(int schedKey) {
        this.schedKey = schedKey;
    }

    public int getDifferenceDays() {
        int daysdiff = 0;
        long diff =  date.getTime() - currentDate.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        daysdiff = (int) diffDays;
        return daysdiff;
    }

    //-------------------------------------------------Setters----------------------------------------------------------

    /*public void setResidentFines(){
        System.out.println("gg");
        System.out.println(schedResCount);
        for(int i = 0; i < schedResCount; i++){
            if(resident[i].getRecorded() == 0){
                resident[i].updateFines();
                System.out.println(resident[i].getTotalFines());
                System.out.println("in");
                resident[i].setFinedOn(getDate().toString());
            }
        }
    }*/
}
