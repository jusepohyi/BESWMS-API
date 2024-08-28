import java.text.*;
class Purok{

    private String name;

    public Schedule[] schedule = new Schedule[100];
    public Resident[] resident = new Resident[100];

    int schedCount = 0;
    int resCount = 0;

    int purokTotalMoney;

    public Purok(String name){
        this.name = name;
        purokTotalMoney = 0;
    }
    //----------------------------------------------Add Methods---------------------------------------------------------
    public void addSchedule(Schedule sched) throws ParseException {
        schedule[schedCount] = sched;
        schedCount++;
    }
    public void addResident(Resident res){
        resident[resCount] = res;
        resCount++;
    }
    //----------------------------------------------View Methods--------------------------------------------------------
    public void viewSpecificResident(int i){
        System.out.println("   ---------------------------- Resident Info ----------------------------   ");
        System.out.println(i+1+"). ");
        System.out.println("Name:\t"+resident[i].getLastName()+", "+resident[i].getFirstName()+" "+resident[i].getMiddleName());
        System.out.println("Contact Number:\t"+resident[i].getContactNum());
        System.out.println("Purok:\t" + resident[i].getPurok());
        System.out.println("Balance:\t"+ resident[i].getTotalFines());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();
    }

    public void viewSpecificResidentAfterPaying(int i, int payment){
        System.out.println("   ---------------------------- Resident Info ----------------------------   ");
        System.out.println(i+1+"). ");
        System.out.println("Name:\t"+resident[i].getLastName()+", "+resident[i].getFirstName()+" "+resident[i].getMiddleName());
        System.out.println("Contact Number:\t"+resident[i].getContactNum());
        System.out.println("Purok:\t" + resident[i].getPurok());
        System.out.println("Balance:\t"+ resident[i].getTotalFines());
        resident[i].pay(payment); //yes
        System.out.println("Paid:\t"+"-"+payment);
        System.out.println("New Bal:\t" + resident[i].totalFines);
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();
    }

    public void viewResidentsTotalPaid(int m){
        System.out.println(("#"+(m+1)+". ")+ "\t----- Purok " + getName() + " -----\t");
        System.out.println("Total amount collected: P" + getPurokTotalMoney());
        System.out.println("Residents: ");
        for(int i = 0; i < resCount; i++){
            System.out.println("Name:\t"+resident[i].getLastName()+", "+resident[i].getFirstName()+" "+resident[i].getMiddleName() + " - P" + resident[i].totalPaid);
        }
    }
    //----------------------------------------------Edit Methods--------------------------------------------------------
    //---------------------------------------------Delete Methods-------------------------------------------------------
    public void deleteSchedule(int x){
        for(int i = x; i < schedCount-1; i++){
            schedule[i] = schedule[i+1];
        }
        schedCount--;
    }

    public void deletePurok(){
        for(int i = 0; i < resCount-1; i++){
            resident[i] = null;
            resCount--;
        }
        for(int i = 0; i < schedCount-1; i++){
            schedule[i] = null;
            schedCount--;
        }
    }

    public void deleteResident(int x){
        for(int i = x; i < resCount-1; i++){
            resident[i] = resident[i+1];
        }
        resCount--;
    }
    //----------------------------------------------Sort Methods--------------------------------------------------------
    public void sortSchedule(String a){
        if(a.equals("1")){
            for (int i = 1; i < schedCount; i++) {
                for (int j = 0; j < schedCount - i; j++) {
                    if (schedule[j].getDifferenceDays() > schedule[j+1].getDifferenceDays()){
                        Schedule temp = schedule[j];
                        schedule[j] = schedule[j+1];
                        schedule[j+1] = temp;
                    }
                }
            }
        } else if (a.equals("2")){
            for (int i = 0; i < schedCount; i++) {
                for (int j = 1; j < schedCount - i; j++) {
                    if(schedule[j-1].getDifferenceDays() < schedule[j].getDifferenceDays()){
                        Schedule temp = schedule[j-1];
                        schedule[j-1] = schedule[j];
                        schedule[j] = temp;
                    }
                }
            }
        }
    }

    public void sortResidents(String a, String b){
        if(a.equals("1")){
            if(b.equals("1")){
                for (int i = 1; i < resCount; i++) {
                    for (int j = 0; j < resCount - i; j++) {
                        if (((resident[j].getFirstName()).compareTo((resident[j + 1].getFirstName()))) > 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            } else if(b.equals("2")){
                for (int i = 0; i < resCount; i++) {
                    for (int j = 0; j < resCount-1-i; j++) {
                        if (((resident[j].getFirstName()).compareTo((resident[j+1].getFirstName()))) < 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j+1];
                            resident[j+1] = temp;
                        }
                    }
                }
            }
        } else if (a.equals("2")){
            if(b.equals("1")){
                for (int i = 1; i < resCount; i++) {
                    for (int j = 0; j < resCount - i; j++) {
                        if (((resident[j].getMiddleName()).compareTo((resident[j + 1].getMiddleName()))) > 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            } else if (b.equals("2")) {
                for (int i = 0; i < resCount; i++) {
                    for (int j = 0; j < resCount - 1 - i; j++) {
                        if (((resident[j].getMiddleName()).compareTo((resident[j + 1].getMiddleName()))) < 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            }
        } else if (a.equals("3")){
            if(b.equals("1")){
                for (int i = 1; i < resCount; i++) {
                    for (int j = 0; j < resCount - i; j++) {
                        if (((resident[j].getLastName()).compareTo((resident[j + 1].getLastName()))) > 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            } else if (b.equals("2")) {
                for (int i = 0; i < resCount; i++) {
                    for (int j = 0; j < resCount - 1 - i; j++) {
                        if (((resident[j].getLastName()).compareTo((resident[j + 1].getLastName()))) < 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            }
        }
    }
    //---------------------------------------------Search Methods-------------------------------------------------------
    //-------------------------------------------------Getters----------------------------------------------------------
    public String getName() {
        return Main.capitalize(name);
    }

    public int getPurokTotalMoney(){
        for(int i = 0; i < resCount; i++){
            purokTotalMoney = purokTotalMoney + resident[i].getTotalPaid();
        }
        return purokTotalMoney;
    }

    public String getPurokName(){
        return "Purok " + getName();
    }
    //-------------------------------------------------Setters----------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setResidentFines(){
        for(int i = 0; i < resCount; i++){
            if(resident[i].getRecorded() == 0){
                resident[i].updateFines();

            }
        }
    }

    public void resetRecorded(){
        for(int i = 0; i < resCount; i++){
            resident[i].setRecorded(0);
        }
    }
}