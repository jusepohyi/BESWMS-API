import java.util.Date;
import java.util.Scanner;

class Admin extends Main{

    private String username;
    private String password;

    Recorder[] recorder = new Recorder[100];
    Purok[] purok = new Purok[100];
    static Resident[] resident = new Resident[100];
    static Schedule[] schedule = new Schedule[100];

    static int recSortNum = 3;
    static int resSortNum = 3;

    public Admin(){
        username = "admin";
        password = "admin";
    }

    public boolean login(String a, String b){
        return getUsername().equals(a) && getPassword().equals(b);
    }

    //-----------------------------------  Checking duplicate sh8s and some sh8s----------------------------------------
    public boolean isDuplicateRecorderUsername(String abc){
        int check = 0;
        for(int i = 0; i < recorderCount; i++){
            if(recorder[i].getRecUsername().equals(abc)){
                check = 1;
                break;
            }
        }
        return check == 1;
    }

    public boolean isPasswordNotCorrect(String abc){
        Scanner in = new Scanner(System.in);
        if(abc.length() < 4){
            System.out.println("Password too short! Please Try again.");
            return true;
        } else {
            System.out.print("Confirm password: ");
            String confirm = in.nextLine();
            if(confirm.equals(abc)){
                return false;
            } else {
                System.out.println("Password does not match! Try Again");
                return true;
            }
        }
    }

    public boolean isContactNumNotCorrect(String abc){
        if(abc.length() != 11){
            System.out.println("Contact Number must be 11 digits! Try again.");
            return true;
        } else {
            if (abc.matches("[0-9]+")) {
                return false;
            } else {
                System.out.println("Contact number must not contain letters! Try again.");
                return true;
            }
        }
    }

    public boolean isDuplicatePurokName(String abc){
        int check = 0;
        for (int i = 0; i < purokCount; i++) {
            if(purok[i].getName().equalsIgnoreCase(abc)){
                check = 1;
                break;
            }
        }
        return check == 1;
    }

    public boolean thisDateCompareToCurrentDate(Date a, Date b){
        if (a.getTime() < b.getTime()) {
            System.out.println("Date has already passed! Try again.");
            return true;
        } else {
            return false;
        }
    }

    public boolean compareSchedulesForTodaysDateToRecord(int m, Date current){
        int check = 0;
        for(int i = 0; i < purok[m].schedCount; i++){
            if(purok[m].schedule[i].date.getTime() == current.getTime()){
                check = 1;
                setIndex(i);
                break;
            }
        }
        if(check == 0){
            System.out.println("There is no schedule for recording today.");
            return true;
        } else {
            return false;
        }
    }

    public boolean isDuplicatePurokSched(Date abc, int m){
        int check = 0;
        for (int i = 0; i < purok[m].schedCount; i++) {
            if (purok[m].schedule[i].date.getTime() == abc.getTime()) {
                check = 1;
                break;
            }
        }
        return check == 1;
    }

    public void getSameSchedKey(int m, int n){
        for(int i = 0; i < scheduleCount; i++){
            if(purok[m].schedule[n].getSchedKey() == schedule[i].getSchedKey()){
                setIndex(i);
                break;
            }
        }
    }

    public void getPurokIndex(String abc){
        for (int i = 0; i < purokCount; i++) {
            if (abc.equalsIgnoreCase(purok[i].getName())) {
                setIndex(i);
                break;
            }
        }
    }

    public void getResIndex(int m){
        for(int i = 0; i <purok[getIndex()].resCount; i++){
            if(purok[getIndex()].resident[i].getResKey() == resident[m].getResKey()){
                setIndex2(i);
                break;
            }
        }
    }

    public void getPurokIndexToMove(String abc){
        for(int i = 0; i < purokCount; i++){
            if(purok[i].getName().equalsIgnoreCase(abc)){
                setIndex3(i);
                break;
            }
        }
    }

    public boolean isPurokPresent(String abc){
        int check = 0;
        for (int i = 0; i < purokCount; i++) {
            if (abc.equalsIgnoreCase(purok[i].getName())) {
                check = 1;
            }
        }
        if (check == 0) {
            System.out.println("There are no purok with name " + abc + "! Try again. ");
            return true;
        } else {
            return false;
        }
    }

    //----------------------------------------------Add Methods---------------------------------------------------------
    public void addRecorders(Recorder rec){
        recorder[recorderCount] = rec;
        recorderCount++;
    }
    public void addPurok(Purok pur){
        purok[purokCount] = pur;
        purokCount++;
    }
    public void addSchedule(Schedule sched){
        schedule[scheduleCount] = sched;

    }
    public void addResident(Resident res){
        resident[residentCount] = res;
    }
    //----------------------------------------------View Methods--------------------------------------------------------
    public void viewSpecificRecorder(int i){
        System.out.println("   ---------------------------- Recorder Info ----------------------------   ");
        System.out.println(i+1+"). ");
        System.out.println("Name:\t"+recorder[i].getLastName()+", "+recorder[i].getFirstName()+" "+recorder[i].getMiddleName());
        System.out.println("Contact Number:\t"+recorder[i].getContactNum());
        System.out.println("Username:\t"+recorder[i].getRecUsername());
        System.out.println("Password:\t"+recorder[i].getRecPassword());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();
    }

    public void viewPurokResident(int m){
        System.out.println("Residents: ");
        if(purok[m].resCount == 0){
            System.out.println("There are no residents for this purok! Add residents first. ");
        } else {
            if(resSortNum == 1) {
                for (int i = 0; i < purok[m].resCount; i++) {
                    System.out.println((i + 1 + "). ") + "Name:\t" + purok[m].resident[i].getFirstName() + " " + purok[m].resident[i].getMiddleName() + " " + purok[m].resident[i].getLastName());
                }
                System.out.println();
            } else if (resSortNum == 2){
                for (int i = 0; i < purok[m].resCount; i++) {
                    System.out.println((i + 1 + "). ") + "Name:\t" + purok[m].resident[i].getMiddleName() + ", " + purok[m].resident[i].getFirstName() + " " + purok[m].resident[i].getLastName());
                }
                System.out.println();
            } else if (resSortNum == 3){
                for (int i = 0; i < purok[m].resCount; i++) {
                    System.out.println((i + 1 + "). ") + "Name:\t" + purok[m].resident[i].getLastName() + ", " + purok[m].resident[i].getFirstName() + " " + purok[m].resident[i].getMiddleName());
                }
            }
        }
    }

    public void viewSchedulesByPurok(){
        for(int i = 0; i < purokCount; i++){
            System.out.println(("#"+(i+1)+". ")+ "\t----- Purok " + purok[i].getName() + " -----\t");
            for(int j = 0; j < purok[i].schedCount; j++){
                System.out.println(j + 1 + "). " + purok[i].schedule[j].getSched() + " (" +purok[i].schedule[j].getDifferenceDays() + " day/s left).");
            }
            System.out.println();
        }
    }

    /*public void announcement(){
        System.out.println("--------------------- ANNOUNCEMENTS ---------------------");
        if(scheduleCount == 0){
            System.out.println("There are no announcements yet.");
        } else {
            System.out.println("Upcoming Schedules: ");
            sortDiffDays();
            viewAllSchedules();
        }
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }*/

    public void userProfile(int i){
        System.out.println("   ----------------------------- User Profile -----------------------------   ");
        System.out.println("Username:\t" + recorder[i].getRecUsername());
        System.out.println("Name:\t"+recorder[i].getLastName()+", "+recorder[i].getFirstName()+" "+recorder[i].getMiddleName());
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();
    }

    public void viewAllSchedules(){
        for(int i = 0; i < scheduleCount; i++){
            System.out.println((i+1)+"). "+schedule[i].getSched()+" - "+schedule[i].getPurok() + " (" +schedule[i].getDifferenceDays() + " day/s left).");
        }
    }

    public void viewfiveschedsonly(){
        for(int i = 0; i < scheduleCount; i++){
            System.out.println((i+1)+"). "+schedule[i].getSched()+" - "+schedule[i].getPurok() + " (" +schedule[i].getDifferenceDays() + " day/s left).");
            if(i == 5){
                break;
            }
        }
    }

    /*public void viewAllResidents(){
        System.out.println("   ---------------- List of Residents ----------------   ");
        if(resSortNum == 1) {
            for (int i = 0; i < residentCount; i++) {
                System.out.println((i + 1 + "). ") + "Name:\t" + resident[i].getFirstName() + " " + resident[i].getMiddleName() + " " + resident[i].getLastName() + " - Purok " + resident[i].getPurok());
            }
            System.out.println();
        } else if (resSortNum == 2){
            for (int i = 0; i < residentCount; i++) {
                System.out.println((i + 1 + "). ") + "Name:\t" + resident[i].getMiddleName() + ", " + resident[i].getFirstName() + " " + resident[i].getLastName() + " - Purok " + resident[i].getPurok());
            }
            System.out.println();
        } else if (resSortNum == 3){
            for (int i = 0; i < residentCount; i++) {
                System.out.println((i + 1 + "). ") + "Name:\t" + resident[i].getLastName() + ", " + resident[i].getFirstName() + " " + resident [i].getMiddleName() + " - Purok " + resident[i].getPurok());
            }
            System.out.println();
        }
    }*/

    public void viewSpecificResident(int i){
        System.out.println("   ------------------ Resident Info ------------------   ");
        System.out.println(i+1+"). ");
        System.out.println("Name:\t"+resident[i].getLastName()+", "+resident[i].getFirstName()+" "+resident[i].getMiddleName());
        System.out.println("Contact Number:\t"+resident[i].getContactNum());
        System.out.println("Purok:\t" + resident[i].getPurok());
        System.out.println("Total Fines:\t"+ resident[i].getTotalFines());
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

    public void viewSpecificResidentName(int m, int i){
        System.out.println(i+1+"). " + purok[m].resident[i].getLastName()+", "+purok[m].resident[i].getFirstName()+" "+purok[m].resident[i].getMiddleName());
    }

    //----------------------------------------------Edit Methods--------------------------------------------------------
    public void editRecorderName(String a, String b, String c, int m){
        recorder[m].setFirstName(a); recorder[m].setMiddleName(b); recorder[m].setLastName(c);
        recorder[m].setFullName();
    }
    public void editResidentName(String a, String b, String c, int m){
        resident[m].setFirstName(a); resident[m].setMiddleName(b); resident[m].setLastName(c);
        resident[m].setFullName();
    }
    //---------------------------------------------Delete Methods-------------------------------------------------------
    public void deleteSpecificRecorder(int m){
        for(int i = m; i < recorderCount-1; i++) {
            recorder[i] = recorder[i + 1];
        }
        recorderCount--;
    }
    public void deleteSpecificPurok(int m){//iapil ug delete ang residents ug sched
        for(int i = m; i < purokCount-1; i++) {
            purok[i] = purok[i + 1];
        }
        purokCount--;
    }
    public void deleteSpecificSchedule(int x){
        for(int i = x; i < scheduleCount-1; i++){
            schedule[i] = schedule[i+1];
        }
        scheduleCount--;
    }
    public void deletSpecificResident(int m){
        for (int i = m; i < residentCount - 1; i++) {
            resident[i] = resident[i + 1];
        }
        residentCount--;
    }
    public void deleteAllSchedulesOfPurok(int m){
        for(int i = 0; i < scheduleCount-1; i++) {
            for (int j = 0; j < purok[m].schedCount; j++) {
                if (schedule[i].getSchedKey() == purok[m].schedule[j].getSchedKey()) {
                    schedule[i] = schedule[i + 1];
                    scheduleCount--;
                }
            }
        }
    }
    public void deleteAllResidentsOfPurok(int m){
        for(int i = 0; i < residentCount-1; i++) {
            for (int j = 0; j < purok[m].resCount; j++) {
                if (resident[i].getResKey() == purok[m].resident[j].getResKey()) {
                    resident[i] = resident[i + 1];
                    residentCount--;
                }
            }
        }
    }
    public void deleteAllResidentinSched(int m){
        for(int i = 0; i < residentCount-1; i++) {
            for (int j = 0; j < purok[m].resCount; j++) {
                for(int k = 0; k < purok[m].schedule[j].schedResCount; k++) {
                    if (resident[i].getResKey() == purok[m].schedule[j].resident[k].getResKey()){
                        resident[i] = resident[i + 1];
                        residentCount--;
                    }
                }
            }
        }
    }
    public void deleteResidentInPurok(int m){
        for (int i = 0; i < purokCount; i++) {
            for (int j = 0; j < purok[i].resCount; j++) {
                if (resident[m].getResKey() == purok[i].resident[j].getResKey()) {
                    purok[i].deleteResident(j);
                }
            }
        }
    }
    //----------------------------------------------Sort Methods--------------------------------------------------------
    public void sortRecorders(String a, String b){
        if(a.equals("1")){
            recSortNum = 1;
            if(b.equals("1")){
                for (int i = 1; i < recorderCount; i++) {
                    for (int j = 0; j < recorderCount - i; j++) {
                        if (((recorder[j].getFirstName()).compareTo((recorder[j + 1].getFirstName()))) > 0) {
                            Recorder temp = recorder[j];
                            recorder[j] = recorder[j + 1];
                            recorder[j + 1] = temp;
                        }
                    }
                }
            } else if(b.equals("2")){
                for (int i = 0; i < recorderCount; i++) {
                    for (int j = 0; j < recorderCount-1-i; j++) {
                        if (((recorder[j].getFirstName()).compareTo((recorder[j+1].getFirstName()))) < 0) {
                            Recorder temp = recorder[j];
                            recorder[j] = recorder[j+1];
                            recorder[j+1] = temp;
                        }
                    }
                }
            }
        } else if (a.equals("2")){
            recSortNum = 2;
            if(b.equals("1")){
                for (int i = 1; i < recorderCount; i++) {
                    for (int j = 0; j < recorderCount - i; j++) {
                        if (((recorder[j].getMiddleName()).compareTo((recorder[j + 1].getMiddleName()))) > 0) {
                            Recorder temp = recorder[j];
                            recorder[j] = recorder[j + 1];
                            recorder[j + 1] = temp;
                        }
                    }
                }
            } else if (b.equals("2")) {
                for (int i = 0; i < recorderCount; i++) {
                    for (int j = 0; j < recorderCount - 1 - i; j++) {
                        if (((recorder[j].getMiddleName()).compareTo((recorder[j + 1].getMiddleName()))) < 0) {
                            Recorder temp = recorder[j];
                            recorder[j] = recorder[j + 1];
                            recorder[j + 1] = temp;
                        }
                    }
                }
            }
        } else if (a.equals("3")){
            recSortNum = 3;
            if(b.equals("1")){
                for (int i = 1; i < recorderCount; i++) {
                    for (int j = 0; j < recorderCount - i; j++) {
                        if (((recorder[j].getLastName()).compareTo((recorder[j + 1].getLastName()))) > 0) {
                            Recorder temp = recorder[j];
                            recorder[j] = recorder[j + 1];
                            recorder[j + 1] = temp;
                        }
                    }
                }
            } else if (b.equals("2")) {
                for (int i = 0; i < recorderCount; i++) {
                    for (int j = 0; j < recorderCount - 1 - i; j++) {
                        if (((recorder[j].getLastName()).compareTo((recorder[j + 1].getLastName()))) < 0) {
                            Recorder temp = recorder[j];
                            recorder[j] = recorder[j + 1];
                            recorder[j + 1] = temp;
                        }
                    }
                }
            }
        }
    }
    public void sortPurok(String a){
        if(a.equals("1")){
            for (int i = 1; i < purokCount; i++) {
                for (int j = 0; j < purokCount - i; j++) {
                    if (((purok[j].getName()).compareTo((purok[j + 1].getName()))) > 0) {
                        Purok temp = purok[j];
                        purok[j] = purok[j + 1];
                        purok[j + 1] = temp;
                    }
                }
            }
        } else if (a.equals("2")) {
            for (int i = 0; i < purokCount; i++) {
                for (int j = 0; j < purokCount - 1 - i; j++) {
                    if (((purok[j].getName()).compareTo((purok[j + 1].getName()))) < 0) {
                        Purok temp = purok[j];
                        purok[j] = purok[j + 1];
                        purok[j + 1] = temp;
                    }
                }
            }
        }
    }
    public void sortPurokSchedules(String a){
        for(int i = 0; i < purokCount; i ++){
            purok[i].sortSchedule(a);
        }
    }
    public void sortDiffDays(){
        for (int i = 1; i < scheduleCount; i++) {
            for (int j = 0; j < scheduleCount - i; j++) {
                if (((schedule[j].date.getTime() > schedule[j + 1].date.getTime()))) {
                    Schedule temp = schedule[j];
                    schedule[j] = schedule[j + 1];
                    schedule[j + 1] = temp;
                }
            }
        }
    }
    public void sortResidents(String a, String b){
        if(a.equals("1")){
            resSortNum = 1;
            if(b.equals("1")){
                for (int i = 1; i < residentCount; i++) {
                    for (int j = 0; j < residentCount - i; j++) {
                        if (((resident[j].getFirstName()).compareTo((resident[j + 1].getFirstName()))) > 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            } else if(b.equals("2")){
                for (int i = 0; i < residentCount; i++) {
                    for (int j = 0; j < residentCount-1-i; j++) {
                        if (((resident[j].getFirstName()).compareTo((resident[j+1].getFirstName()))) < 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j+1];
                            resident[j+1] = temp;
                        }
                    }
                }
            }
        } else if (a.equals("2")){
            resSortNum = 2;
            if(b.equals("1")){
                for (int i = 1; i < residentCount; i++) {
                    for (int j = 0; j < residentCount - i; j++) {
                        if (((resident[j].getMiddleName()).compareTo((resident[j + 1].getMiddleName()))) > 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            } else if (b.equals("2")) {
                for (int i = 0; i < residentCount; i++) {
                    for (int j = 0; j < residentCount - 1 - i; j++) {
                        if (((resident[j].getMiddleName()).compareTo((resident[j + 1].getMiddleName()))) < 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            }
        } else if (a.equals("3")){
            resSortNum = 3;
            if(b.equals("1")){
                for (int i = 1; i < residentCount; i++) {
                    for (int j = 0; j < residentCount - i; j++) {
                        if (((resident[j].getLastName()).compareTo((resident[j + 1].getLastName()))) > 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            } else if (b.equals("2")) {
                for (int i = 0; i < residentCount; i++) {
                    for (int j = 0; j < residentCount - 1 - i; j++) {
                        if (((resident[j].getLastName()).compareTo((resident[j + 1].getLastName()))) < 0) {
                            Resident temp = resident[j];
                            resident[j] = resident[j + 1];
                            resident[j + 1] = temp;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < purokCount; i++){
            purok[i].sortResidents(a,b);
        }
    }
    //---------------------------------------------Search Methods-------------------------------------------------------
    public void searchRecorder(String name){
        int check = 0;
        int yeah = 0;
        System.out.println("Recorders with '" + name + "': ");

        for (int i = 0; i < recorderCount; i++) {
            if (recorder[i].getFullName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(i+1+ ". " + sortedSh8(i));
                check = 1;
                yeah = i;
            }
        }
        if (check == 0) {
            System.out.println("None.");
        } else {
            boolean chooseRec;
            do {
                System.out.print("Choose Recorder [0] Back: ");
                int chooseRec1 = scanner.nextInt();
                scanner.nextLine();

                int m = chooseRec1 - 1;
                yeah = yeah+1;

                if (yeah <= m) {
                    System.out.println("There are only " + yeah + " Recorders with name '" + name + "'. Try again.");
                    chooseRec = true;
                } else if (m < 0) {
                    chooseRec = false;
                } else {
                    viewSpecificRecorder(m);
                    pressAnyKeyToContinue();
                    chooseRec = false;
                }
            }while (chooseRec);
        }
    }
    public void searchPurok(String name){
        int check = 0;
        int yeah = 0;
        System.out.println("Purok with '" + name + "': ");
        for (int i = 0; i < purokCount; i++) {
            if (purok[i].getName().toLowerCase().contains(name.toLowerCase())) {
                check = 1;
                yeah = i;
                System.out.println((i+1)+". " + purok[i].getPurokName());
            }
        }
        if (check == 0) {
            System.out.println("None.");
        } else {
            boolean choosePrk;
            do {
                System.out.print("Choose Recorder [0] Back: ");
                int chooseprk1 = scanner.nextInt();
                scanner.nextLine();

                int m = chooseprk1 - 1;
                yeah = yeah+1;

                if (yeah <= m) {
                    System.out.println("There are only " + yeah + " Puroks with name '" + name + "'. Try again.");
                    choosePrk = true;
                } else if (m < 0) {
                    choosePrk = false;
                } else {
                    viewSpecificPurokResAndSched(m);
                    pressAnyKeyToContinue();
                    choosePrk = false;
                }
            }while (choosePrk);
        }
    }
    public void searchResident(String name){
        int check = 0;
        int yeah = 0;
        System.out.println("Residents with '" + name + "': ");
        for (int i = 0; i < residentCount; i++) {
            if (resident[i].getFullName().toLowerCase().contains(name.toLowerCase())) {
                check = 1;
                yeah = i;
                System.out.println(i+1+ ". " + sortedSh8Res(i));
            }
        }
        if (check == 0) {
            System.out.println("None.");
        } else {
            boolean chooseres;
            do {
                System.out.print("Choose Recorder [0] Back: ");
                int chooseres1 = scanner.nextInt();
                scanner.nextLine();

                int m = chooseres1 - 1;
                yeah = yeah+1;

                if (yeah <= m) {
                    System.out.println("There are only " + yeah + " Residents with name '" + name + "'. Try again.");
                    chooseres = true;
                } else if (m < 0) {
                    chooseres = false;
                } else {
                    viewSpecificResident(m);
                    pressAnyKeyToContinue();
                    chooseres = false;
                }
            }while (chooseres);
        }
    }

    public void searchResidentInPUrok(String name, int m){
        int check = 0;
        System.out.println("Residents with '" + name + "': ");
        for (int i = 0; i < purok[m].resCount; i++) {
            if (purok[m].resident[i].getFullName().toLowerCase().contains(name.toLowerCase())) {
                check = 1;
                viewSpecificResidentName(m,i);
            }
        }
        if (check == 0) {
            System.out.println("None.");
        }
    }
    //-------------------------------------------------Getters----------------------------------------------------------
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    //-------------------------------------------------Setters----------------------------------------------------------
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //---------------------------------------------- printf view and sh8
    public String recViewFormat(){
        int max = 0;
        for (int i = 0; i < recorderCount; i++) {
            max = Math.max(recorder[i].getFullName().length(), max);
        }
        return "%"+ (-max/2) +"s%" + (-max) + "s\n";
    }
    public void viewAllRecorder(){
        if(recorderCount == 0){
            System.out.println();
            System.out.println("There are no recorders yet.");
        } else {
            System.out.println("                              LIST OF RECORDERS                              ");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf(recViewFormat(), "INDEX ", "NAME");
            System.out.println("-----------------------------------------------------------------------------");

            for (int i = 0; i < recorderCount; i++) {
                System.out.printf(recViewFormat(), (i + 1) + ". ", sortedSh8(i));
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println();
        }
    }
    public String sortedSh8(int i){
        if(recSortNum == 1){
            return recorder[i].getFirstName() + " " + recorder[i].getMiddleName() + " " + recorder[i].getLastName();
        } else if ( recSortNum == 2){
            return recorder[i].getMiddleName() + ", " + recorder[i].getFirstName() + " " + recorder[i].getLastName();
        } else {
            return recorder[i].getLastName() + ", " + recorder[i].getFirstName() + " " + recorder[i].getMiddleName();
        }
    }

    public void viewAllResident(){
        if(residentCount == 0){
            System.out.println();
            System.out.println("There are no residents yet.");
        } else {
            System.out.println("                              LIST OF RESIDENTS                              ");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf(resViewFormat2(), "INDEX ", "NAME", "PUROK");
            System.out.println("-----------------------------------------------------------------------------");

            for (int i = 0; i < residentCount; i++) {
                System.out.printf(resViewFormat2(), (i + 1) + ". ", sortedSh8Res(i), resident[i].getPurok());
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println();
        }
    }
    public String sortedSh8Res(int i){
        if(resSortNum == 1){
            return resident[i].getFirstName() + " " + resident[i].getMiddleName() + " " + resident[i].getLastName();
        } else if ( resSortNum == 2){
            return resident[i].getMiddleName() + ", " + resident[i].getFirstName() + " " + resident[i].getLastName();
        } else {
            return resident[i].getLastName() + ", " + resident[i].getFirstName() + " " + resident[i].getMiddleName();
        }
    }

    public String sortedSh8Res(int m, int i){
        if(resSortNum == 1){
            return purok[m].resident[i].getFirstName() + " " + purok[m].resident[i].getMiddleName() + " " + purok[m].resident[i].getLastName();
        } else if ( resSortNum == 2){
            return purok[m].resident[i].getMiddleName() + ", " + purok[m].resident[i].getFirstName() + " " + purok[m].resident[i].getLastName();
        } else {
            return purok[m].resident[i].getLastName() + ", " + purok[m].resident[i].getFirstName() + " " + purok[m].resident[i].getMiddleName();
        }
    }

    public String purokViewFormat(){
        int max = 0;
        for (int i = 0; i < purokCount ; i++) {
            max = Math.max(purok[i].getPurokName().length(), max);
        }
        return "%"+ (-max) +"s%s\n";
    }

    public void viewAllPurok(){
        if(purokCount == 0){
            System.out.println();
            System.out.println("There are no puroks yet.");
        } else {
            System.out.println("                               LIST OF PUROKS                                ");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf(purokViewFormat(), "INDEX ", "NAME");
            System.out.println("-----------------------------------------------------------------------------");

            for (int i = 0; i < purokCount ; i++) {
                System.out.printf(purokViewFormat(), (i + 1) + ". ", purok[i].getPurokName());
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println();
        }
    }

    public String schedViewFormat(){
        int max = 0;
        for (int i = 0; i < purokCount ; i++) {
            for(int j = 0; j < purok[i].schedCount; j++) {
                max = Math.max(purok[i].schedule[j].getSched().length(), max);
            }
        }
        return "%"+ -max/2 +"s%" + -max + "s\n";
    }

    public String resViewFormat(){
        int max = 0;
        for (int i = 0; i < purokCount ; i++) {
            for(int j = 0; j < purok[i].resCount; j++) {
                max = Math.max(purok[i].resident[j].getFullName().length(), max);
            }
        }
        return "%"+ (-max/2) +"s%" + (-max) + "s\n";
    }
    public String resViewFormat2(){
        int max = 0;
        for (int i = 0; i < purokCount ; i++) {
            for(int j = 0; j < purok[i].resCount; j++) {
                max = Math.max(purok[i].resident[j].getFullName().length(), max);
            }
        }
        return "%"+ (-max/2) +"s%" + (-max) + "s%" +(-max) + "s\n";
    }

    public void viewSpecificPurokResAndSched(int m){
        System.out.println("                                " + purok[m].getPurokName());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("                                  Schedules                                  ");
        System.out.println("-----------------------------------------------------------------------------");
        if(purok[m].schedCount == 0){
            System.out.println("There are no schedules for this purok.");
        } else {
        System.out.printf(schedViewFormat(), "INDEX", "DATE");
        System.out.println("-----------------------------------------------------------------------------");

            for (int i = 0; i < purok[m].schedCount; i++) {
                System.out.printf(schedViewFormat(), (i + 1) + ". ", purok[m].schedule[i].getSched() + " (" + purok[m].schedule[i].getDifferenceDays() + " day/s left.)");
            }
        }
        System.out.println();
        System.out.println("                                  Residents                                  ");
        System.out.println("-----------------------------------------------------------------------------");
        if(purok[m].resCount == 0){
            System.out.println("There are no residents for this purok.");
        } else {
        System.out.printf(resViewFormat(), "INDEX", "NAME");
        System.out.println("-----------------------------------------------------------------------------");

            for (int i = 0; i < purok[m].resCount; i++) {
                System.out.printf(resViewFormat(), (i + 1) + ". ", sortedSh8Res(m, i));
            }
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println();
    }

    public void viewPurokAndSche(int m){
        System.out.println(purok[m].getPurokName());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("                                  Schedules                                  ");
        System.out.println("-----------------------------------------------------------------------------");
        if(purok[m].schedCount == 0){
            System.out.println("There are no schedules for this purok.");
        } else {
            System.out.printf(schedViewFormat(), "INDEX", "DATE");
            System.out.println("-----------------------------------------------------------------------------");

            for (int i = 0; i < purok[m].schedCount; i++) {
                System.out.printf(schedViewFormat(), (i + 1) + ". ", purok[m].schedule[i].getSched() + " (" + purok[m].schedule[i].getDifferenceDays() + " day/s left.)");
            }

            System.out.println("-----------------------------------------------------------------------------");
            System.out.println();
        }
    }

    public void viewPurokResTotalPaid(int m){
        int max = 0;
        for (int i = 0; i < purok[m].resCount ; i++) {
            max = Math.max(purok[m].resident[i].getFullName().length(), max);
        }

        String format = "%"+ (-max/2) +"s%" + (-max) + "s%" +(-max/2) + "s\n";

        System.out.println("                                " + purok[m].getPurokName());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("                                   Treasury                                  ");
        System.out.println("-----------------------------------------------------------------------------");
        if(purok[m].resCount == 0){
            System.out.println("There are no residents for this purok.");
        } else {
            System.out.printf(format, "INDEX", "NAME", "PAID");
            System.out.println("-----------------------------------------------------------------------------");

            for(int i = 0; i < purok[m].resCount; i++){
                System.out.printf(format, (i + 1) + ". ", sortedSh8Res(m,i), "P" + purok[m].resident[i].getTotalPaid());
            }
            System.out.println("Total Money Collected: P" + purok[m].getPurokTotalMoney());
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println();
        }
    }

    public void announcement(){

        System.out.println("                                ANNOUNCEMENTS                                ");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Upcoming Schedules:");
        if(scheduleCount == 0){
            System.out.println("There are no schedules yet.");
        } else {
            sortDiffDays();
            viewfiveschedsonly();
        }
    }
}