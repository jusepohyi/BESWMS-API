import java.util.*;
import java.text.*;
class Main{

    public static int recorderCount = 0;
    public static int purokCount = 0;
    public static int residentCount = 0;
    public static int scheduleCount = 0;

    static Scanner scanner = new Scanner(System.in);

    public static void invalidOption(){
        System.out.println("Invalid Option! Try again.");
    }
    public static String capitalize(String str){
        String[] words = str.split("\\s");
        StringBuilder sb = new StringBuilder();

        for(String s: words){
            if(!s.equals("")){
                sb.append(Character.toUpperCase(s.charAt(0)));
                sb.append(s.substring(1));
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    public static void pressAnyKeyToContinue() {
        System.out.println("Press any key to continue.");
        scanner.nextLine();
    }
    static int index;
    public static void setIndex(int a){
        index = a;
    }
    public static int getIndex(){
        return index;
    }

    static int index2;
    public static void setIndex2(int a){
        index2 = a;
    }
    public static int getIndex2(){
        return index2;
    }

    static int index3;
    public static void setIndex3(int a){
        index3 = a;
    }
    public static int getIndex3(){
        return index3;
    }

    static int userIndex;
    public static void setUserIndex(int a){
        userIndex = a;
    }
    public static int getUserIndex(){
        return userIndex;
    }


    public static void main(String[] args) throws ParseException {
        Admin admin = new Admin();
        boolean inMainLogin = false;
        do {
            System.out.println("*****   Barangay Ecological Solid Waste Management System   *****");
            System.out.println();
            System.out.println("Login as...");
            System.out.println("[1] Admin");
            System.out.println("[2] Recorder");
            System.out.println();
            System.out.println("[0] Exit");
            System.out.print("Enter option: ");
            String loginIptions = scanner.nextLine();

            if (loginIptions.equals("1")) {
                boolean inAdminLogin = false;
                do {
                    System.out.println("-------------------------------- Admin Login --------------------------------");
                    System.out.print("Username: ");
                    String inputAdminUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String inputAdminPassword = scanner.nextLine();

                    if (admin.login(inputAdminUsername, inputAdminPassword)) {
                        boolean inAdminOptions = false;
                        do {
                            admin.announcement();
                            System.out.println("------------------------------- Admin Options -------------------------------");
                            System.out.println("[1] Recorder");
                            System.out.println("[2] Purok");
                            System.out.println("[3] Schedules");
                            System.out.println("[4] Residents");
                            System.out.println("[5] Treasury");
                            System.out.println("[6] Update Admin");
                            System.out.println();
                            System.out.println("[0] Logout");
                            System.out.print("Enter option: ");
                            String adminOptions = scanner.nextLine();

                            if (adminOptions.equals("1")) {
                                boolean inAdminRecorderOptions = false;
                                do {
                                    admin.viewAllRecorder();
                                    System.out.println("------------------------ Admin Options for Recorder -------------------------");
                                    System.out.println("[1] Add Recorder");
                                    System.out.println("[2] View Recorder");
                                    System.out.println("[3] Edit Recorder");
                                    System.out.println("[4] Delete Recorder");
                                    System.out.println("[5] Sort Recorder");
                                    System.out.println("[6] Search Recorder");
                                    System.out.println();
                                    System.out.println("[0] Back");
                                    System.out.print("Enter option: ");
                                    String adminRecorderOptions = scanner.nextLine();

                                    if (adminRecorderOptions.equals("1")) {
                                        boolean inAddRecorder = false;
                                        do {
                                            System.out.println("-------------------------------- Add Recorder -------------------------------");
                                            System.out.print("First name: ");
                                            String recFName = scanner.nextLine();
                                            System.out.print("Middle name: ");
                                            String recMName = scanner.nextLine();
                                            System.out.print("Last name: ");
                                            String recLName = scanner.nextLine();

                                            boolean confirmDuplicateRecorderUsername;
                                            do {
                                                System.out.print("Username: ");
                                                String recUName = scanner.nextLine();

                                                if(admin.isDuplicateRecorderUsername(recUName)){
                                                    System.out.println("Username already in use! Try Again.");
                                                    confirmDuplicateRecorderUsername = true;
                                                } else {
                                                    confirmDuplicateRecorderUsername = false;
                                                    boolean inPasswordVerification;
                                                    do {
                                                        System.out.print("Password: ");
                                                        String recPassword = scanner.nextLine();

                                                        if (admin.isPasswordNotCorrect(recPassword)) {//ilisanan pa, kadtong pinaka lisod para muhilak ang user
                                                            inPasswordVerification = true;
                                                        } else {
                                                            inPasswordVerification = false;
                                                            boolean inContactNumVerification;
                                                            do {
                                                                System.out.print("Contact number(09*********): ");
                                                                String recContactNum = scanner.nextLine();

                                                                if (admin.isContactNumNotCorrect(recContactNum)) {
                                                                    inContactNumVerification = true;
                                                                } else {
                                                                    inContactNumVerification = false;
                                                                    System.out.println("Do you want to add this recorder? [1] Yes, [2]No");
                                                                    String yesOrNo = scanner.nextLine();

                                                                    if (yesOrNo.equals("1")) {

                                                                        Recorder rec = new Recorder(recFName,recMName,recLName,recContactNum,recUName,recPassword);
                                                                        admin.addRecorders(rec);

                                                                        System.out.println("RECORDER ADDED SUCCESSFULLY!");
                                                                        System.out.println("There are currently " + recorderCount + " Recorder/s.");
                                                                        System.out.println("Do you want to add another recorder? [1] Yes , [2]No");
                                                                        String addorNah = scanner.nextLine();
                                                                        if (addorNah.equals("1")) {
                                                                            inAddRecorder = true;
                                                                        } else {
                                                                            inAddRecorder = false;
                                                                            inAdminRecorderOptions = true;
                                                                        }
                                                                    }
                                                                }
                                                            }while (inContactNumVerification);
                                                        }
                                                    }while(inPasswordVerification);
                                                }
                                            }while(confirmDuplicateRecorderUsername);

                                        }while(inAddRecorder);
                                    } else if (adminRecorderOptions.equals("2")) {
                                        if(recorderCount == 0){
                                            System.out.println("No data yet! Add recorder first.");
                                            inAdminRecorderOptions = true;
                                        } else {
                                            boolean inViewRecorder;
                                            do{
                                                System.out.println("------------------------------ View Recorders -------------------------------");
                                                System.out.print("Choose Recorder [0] Back: ");
                                                int viewRecorder = scanner.nextInt();
                                                scanner.nextLine();

                                                int m = viewRecorder -1;

                                                if(recorderCount <= m){
                                                    System.out.println("There are only " + recorderCount + " Recorder/s! Try again.");
                                                    inViewRecorder = true;
                                                } else if(m < 0){
                                                    inViewRecorder = false;
                                                    inAdminRecorderOptions = true;
                                                } else {
                                                    admin.viewSpecificRecorder(m);
                                                    pressAnyKeyToContinue();
                                                    inViewRecorder = true;
                                                }
                                            }while(inViewRecorder);
                                        }
                                    } else if (adminRecorderOptions.equals("3")) {
                                        if(recorderCount == 0){
                                            System.out.println("No data yet! Add recorder first.");
                                            inAdminRecorderOptions = true;
                                        } else {
                                            boolean inEditRecorder = false;
                                            do {
                                                System.out.println("------------------------------ Edit Recorders -------------------------------");
                                                System.out.print("Choose Recorder [0] Back: ");
                                                int editRecorder = scanner.nextInt();
                                                scanner.nextLine();

                                                int m = editRecorder - 1;

                                                if (recorderCount <= m) {
                                                    System.out.println("There are only " + recorderCount + " Recorder/s! Try again.");
                                                    inEditRecorder = true;
                                                } else if (m < 0) {
                                                    inEditRecorder = false;
                                                    inAdminRecorderOptions = true;
                                                } else {
                                                    boolean inEditRecorderOptions = false;
                                                    do {
                                                        admin.viewSpecificRecorder(m);
                                                        System.out.println("What do you want to edit?");
                                                        System.out.println("[1] Name");
                                                        System.out.println("[2] Username");
                                                        System.out.println("[3] Password");
                                                        System.out.println("[4] Contact Number");
                                                        System.out.println();
                                                        System.out.println("[0] Back");
                                                        System.out.print("Enter option: ");
                                                        String editOption = scanner.nextLine();

                                                        if (editOption.equals("1")) {
                                                            System.out.print("First name: ");
                                                            String recFName = scanner.nextLine();
                                                            System.out.print("Middle name: ");
                                                            String recMName = scanner.nextLine();
                                                            System.out.print("Last name: ");
                                                            String recLName = scanner.nextLine();

                                                            admin.editRecorderName(recFName, recMName, recLName, m);
                                                            System.out.println("NAME EDITED SUCCESSFULLY!");
                                                            System.out.println();
                                                            inEditRecorderOptions = true;

                                                        } else if (editOption.equals("2")) {
                                                            boolean inEditRecorderUsername;
                                                            do {
                                                                System.out.print("Enter new username: ");
                                                                String uname = scanner.nextLine();

                                                                if (admin.isDuplicateRecorderUsername(uname)) {
                                                                    System.out.println("Username already in use! Try again.");
                                                                    inEditRecorderUsername = true;
                                                                } else {
                                                                    admin.recorder[m].setRecUsername(uname);
                                                                    System.out.println("USERNAME EDITED SUCCESSFULLY!");
                                                                    System.out.println();
                                                                    inEditRecorderUsername = false;
                                                                    inEditRecorderOptions = true;
                                                                }
                                                            } while (inEditRecorderUsername);

                                                        } else if (editOption.equals("3")) {
                                                            boolean inEditRecorderPassword = false;
                                                            do {
                                                                System.out.print("Enter new password: ");
                                                                String pword = scanner.nextLine();

                                                                if (pword.length() < 4) {
                                                                    System.out.println("Password too short! Try again.");
                                                                    inEditRecorderPassword = true;
                                                                } else if (pword.equals(admin.recorder[m].getRecPassword())) {
                                                                    System.out.println("Input matches the previous password! [1] Try Again, [2] Cancel ");
                                                                    String tryOrCancel = scanner.nextLine();

                                                                    if (tryOrCancel.equals("1")) {
                                                                        inEditRecorderPassword = true;
                                                                    } else {
                                                                        inEditRecorderPassword = false;
                                                                        inEditRecorderOptions = true;
                                                                    }
                                                                } else {
                                                                    boolean confirmNewPassword;
                                                                    do {
                                                                        System.out.print("Confirm new password: ");
                                                                        String confirmPword = scanner.nextLine();

                                                                        if (pword.equals(confirmPword)) {
                                                                            admin.recorder[m].setRecPassword(pword);
                                                                            System.out.println("PASSWORD CHANGED SUCCESSFULLY!");
                                                                            System.out.println();
                                                                            confirmNewPassword = false;
                                                                            inEditRecorderPassword = false;
                                                                            inEditRecorderOptions = true;
                                                                        } else {
                                                                            System.out.println("Password does not match! Try again.");
                                                                            confirmNewPassword = true;
                                                                        }
                                                                    } while (confirmNewPassword);
                                                                }
                                                            } while (inEditRecorderPassword);

                                                        } else if (editOption.equals("4")) {
                                                            boolean inEditRecorderContactNum;
                                                            do {
                                                                System.out.print("Enter new contact number: ");
                                                                String newContactNum = scanner.nextLine();

                                                                if (admin.isContactNumNotCorrect(newContactNum)) {
                                                                    inEditRecorderContactNum = true;
                                                                } else {
                                                                    if (newContactNum.equals(admin.recorder[m].getContactNum())) {
                                                                        System.out.println("Input matches the previous contact number! [1] Try Again, [2] Cancel ");
                                                                        String tryOrCancel = scanner.nextLine();

                                                                        if (tryOrCancel.equals("1")) {
                                                                            inEditRecorderContactNum = true;
                                                                        } else {
                                                                            inEditRecorderContactNum = false;
                                                                            inEditRecorderOptions = true;
                                                                        }
                                                                    } else {
                                                                        admin.recorder[m].setContactNum(newContactNum);
                                                                        System.out.println("CONTACT NUMBER CHANGED SUCCESSFULLY!");
                                                                        System.out.println();
                                                                        inEditRecorderContactNum = false;
                                                                        inEditRecorderOptions = true;
                                                                    }
                                                                }
                                                            } while (inEditRecorderContactNum);

                                                        } else if (editOption.equals("0")) {
                                                            inEditRecorderOptions = false;
                                                            inEditRecorder = true;
                                                        } else {
                                                            invalidOption();
                                                            inEditRecorderOptions = true;
                                                        }
                                                    } while (inEditRecorderOptions);
                                                }
                                            } while (inEditRecorder);
                                        }
                                    } else if (adminRecorderOptions.equals("4")){
                                        if(recorderCount == 0){
                                            System.out.println("No data yet! Add recorder first.");
                                            inAdminRecorderOptions = true;
                                        } else {
                                            boolean inDeleteRecorder;
                                            do {
                                                System.out.println("------------------------------- Delete Recorder ------------------------------");
                                                System.out.print("Choose Recorder [0] Back: ");
                                                int deleteRecorder = scanner.nextInt();
                                                scanner.nextLine();

                                                int m = deleteRecorder - 1;

                                                if (recorderCount <= m) {
                                                    System.out.println("There are only " + recorderCount + " Recorder/s! Try again.");
                                                    inDeleteRecorder = true;
                                                } else if (m < 0) {
                                                    inDeleteRecorder = false;
                                                    inAdminRecorderOptions = true;
                                                } else {
                                                    admin.viewSpecificRecorder(m);
                                                    System.out.println("Are you sure you want to delete this recorder? [1] Yes, [2]No");
                                                    String yesOrNo = scanner.nextLine();

                                                    if (yesOrNo.equals("1")) {
                                                        admin.deleteSpecificRecorder(m);
                                                        System.out.println("RECORDER DELETED SUCCESSFULLY!");
                                                        System.out.println("There are currently " + recorderCount + " Recorder/s.");
                                                        pressAnyKeyToContinue();
                                                        inDeleteRecorder = true;

                                                    } else {
                                                        inDeleteRecorder = false;
                                                        inAdminRecorderOptions = true;
                                                    }
                                                }
                                            } while (inDeleteRecorder);
                                        }
                                    }else if (adminRecorderOptions.equals("5")){
                                        if(recorderCount == 0){
                                            System.out.println("No data yet! Add recorder first.");
                                            inAdminRecorderOptions = true;
                                        } else {
                                            boolean inSortRecorder = false;
                                            do {
                                                System.out.println("------------------------------ Sort Recorders -------------------------------");
                                                System.out.println("Sort by: ");
                                                System.out.println("[1] First Name");
                                                System.out.println("[2] Middle Name");
                                                System.out.println("[3] Last Name");
                                                System.out.println();
                                                System.out.println("[0] Back");
                                                System.out.print("Enter option: ");
                                                String sortOption = scanner.nextLine();

                                                if(sortOption.equals("1") ||sortOption.equals("2")||sortOption.equals("3")){
                                                    boolean inAscendDescend = false;
                                                    do {
                                                        System.out.println("Sort in: ");
                                                        System.out.println("[1] Ascending order");
                                                        System.out.println("[2] Descending order");
                                                        System.out.println("[0] Back");
                                                        System.out.print("Enter option: ");
                                                        String ascendOrDescend = scanner.nextLine();

                                                        if(ascendOrDescend.equals("1") || ascendOrDescend.equals("2")) {
                                                            admin.sortRecorders(sortOption, ascendOrDescend);
                                                            System.out.println("RECORDER SORTED SUCCESSFULLY!");
                                                        } else if (ascendOrDescend.equals("0")){
                                                            inAscendDescend = false;
                                                            inSortRecorder = true;
                                                        } else {
                                                            invalidOption();
                                                            inAscendDescend = true;
                                                        }
                                                    }while (inAscendDescend);
                                                }else if (sortOption.equals("0")){
                                                    inSortRecorder = false;
                                                    inAdminRecorderOptions = true;
                                                } else {
                                                    invalidOption();
                                                    inSortRecorder = true;
                                                }
                                            }while(inSortRecorder);
                                        }
                                    } else if (adminRecorderOptions.equals("6")){
                                        if(recorderCount == 0){
                                            System.out.println("No data yet! Add recorder first.");
                                            inAdminRecorderOptions = true;
                                        } else {
                                            System.out.println("------------------------------ Search Recorder ------------------------------");
                                            System.out.print("Search for: ");
                                            String name = scanner.nextLine();
                                            admin.searchRecorder(name);
                                        }

                                    } else if (adminRecorderOptions.equals("0")){
                                        inAdminRecorderOptions = false;
                                        inAdminOptions = true;

                                    } else {
                                        invalidOption();
                                        inAdminRecorderOptions = true;
                                    }
                                }while (inAdminRecorderOptions);

                            } else if (adminOptions.equals("2")) {
                                boolean inAdminPurokOptions = false;
                                do {
                                    admin.viewAllPurok();
                                    System.out.println("-------------------------- Admin Options for Purok --------------------------");
                                    System.out.println("[1] Add Purok");
                                    System.out.println("[2] View Purok ");
                                    System.out.println("[3] Edit Purok");
                                    System.out.println("[4] Delete Purok");
                                    System.out.println("[5] Sort Purok");
                                    System.out.println("[6] Search Purok");
                                    System.out.println();
                                    System.out.println("[0] Back");
                                    System.out.print("Enter option: ");
                                    String adminPurokOptions = scanner.nextLine();

                                    if (adminPurokOptions.equals("1")) {
                                        boolean inAddPurok;
                                        do {
                                            System.out.println("--------------------------------- Add Purok ---------------------------------");
                                            System.out.print("Purok name: ");
                                            String pName = scanner.nextLine();

                                            if(admin.isDuplicatePurokName(pName)){
                                                System.out.println("Purok name already in use! Try again.");
                                                inAddPurok = true;
                                            } else {
                                                System.out.println("Are you sure you want to add this Purok? [1] Yes, [2]No");
                                                String yesOrNo = scanner.nextLine();
                                                if(yesOrNo.equals("1")) {
                                                    Purok purok = new Purok(pName);
                                                    admin.addPurok(purok);
                                                    System.out.println("PUROK ADDED SUCCESSFULLY!");
                                                    System.out.println("There are currently " + purokCount + " Purok/s.");
                                                    System.out.println("Do you want to add another purok? [1] Yes , [2]No");
                                                    String addorNah = scanner.nextLine();

                                                    if (addorNah.equals("1")) {
                                                        inAddPurok = true;
                                                    } else {
                                                        inAddPurok = false;
                                                        inAdminPurokOptions = true;
                                                    }
                                                } else {
                                                    inAddPurok = false;
                                                    inAdminPurokOptions = true;
                                                }
                                            }
                                        }while(inAddPurok);
                                    } else if (adminPurokOptions.equals("2")) {
                                        if (purokCount == 0) {
                                            System.out.println("No data yet! Add purok first.");
                                            inAdminPurokOptions = true;
                                        } else {
                                            boolean inViewPurok;
                                            do {
                                                System.out.println("--------------------------------- View Purok ---------------------------------");
                                                System.out.print("Choose Purok [0] Back: ");
                                                int viewPurok = scanner.nextInt();
                                                scanner.nextLine();

                                                int m = viewPurok - 1;

                                                if (purokCount <= m) {
                                                    System.out.println("There are only " + purokCount + " Purok/s! Try again.");
                                                    inViewPurok = true;
                                                } else if (m < 0) {
                                                    inViewPurok = false;
                                                    inAdminPurokOptions = true;
                                                } else {
                                                    admin.viewSpecificPurokResAndSched(m);
                                                    pressAnyKeyToContinue();
                                                    inViewPurok = true;
                                                }
                                            } while (inViewPurok);
                                        }
                                    } else if (adminPurokOptions.equals("3")) {
                                        if (purokCount == 0) {
                                            System.out.println("No data yet! Add purok first.");
                                            inAdminPurokOptions = true;
                                        } else {
                                            boolean inEditPurok = false;
                                            do {
                                                System.out.println("--------------------------------- Edit Purok ---------------------------------");
                                                System.out.print("Choose Purok [0] Back: ");
                                                int editPurok = scanner.nextInt();
                                                scanner.nextLine();

                                                int m = editPurok - 1;

                                                if (purokCount <= m) {
                                                    System.out.println("There are only " + purokCount + " Purok/s! Try again.");
                                                    inEditPurok = true;
                                                } else if (m < 0) {
                                                    inEditPurok = false;
                                                    inAdminPurokOptions = true;
                                                } else {
                                                    boolean inEditPurokName;
                                                    do {
                                                        System.out.print("Enter new purok name: ");
                                                        String pname = scanner.nextLine();

                                                        if (pname.equalsIgnoreCase(admin.purok[m].getName())) {
                                                            System.out.println("Input matches the previous purok name! [1] Try Again, [2] Cancel");
                                                            String tryOrCancel = scanner.nextLine();

                                                            if (tryOrCancel.equals("1")) {
                                                                inEditPurokName = true;
                                                            } else {
                                                                inEditPurokName = false;
                                                                inEditPurok = true;
                                                            }
                                                        } else {
                                                            if (admin.isDuplicatePurokName(pname)) {
                                                                System.out.println("Purok name already in use! Try again.");
                                                                inEditPurokName = true;
                                                            } else {
                                                                admin.purok[m].setName(pname);
                                                                System.out.println("PUROK NAME CHANGED SUCCESSFULLY!");
                                                                System.out.println();
                                                                inEditPurokName = false;
                                                                inEditPurok = true;
                                                            }
                                                        }
                                                    } while (inEditPurokName);
                                                }
                                            } while (inEditPurok);
                                        }
                                    }else if (adminPurokOptions.equals("4")) {
                                        if (purokCount == 0) {
                                            System.out.println("No data yet! Add purok first.");
                                            inAdminPurokOptions = true;
                                        } else {
                                            boolean inDeletePurok;
                                            do {
                                                System.out.println("-------------------------------- Delete Purok --------------------------------");
                                                System.out.print("Choose Purok [0] Back: ");
                                                int deletePurok = scanner.nextInt();
                                                scanner.nextLine();

                                                int m = deletePurok - 1;

                                                if (purokCount <= m) {
                                                    System.out.println("There are only " + purokCount + " Purok/s! Try again.");
                                                    inDeletePurok = true;
                                                } else if (m < 0) {
                                                    inDeletePurok = false;
                                                    inAdminPurokOptions = true;
                                                } else {
                                                    System.out.println("Are you sure you want to delete this purok? All data will be lost including its schedules and list of residents. ");
                                                    System.out.println("[1] Yes, [2] No");
                                                    String yesOrNo = scanner.nextLine();

                                                    if (yesOrNo.equals("1")) {

                                                        admin.deleteAllResidentsOfPurok(m);
                                                        admin.deleteAllSchedulesOfPurok(m);
                                                        admin.deleteAllResidentinSched(m);
                                                        admin.purok[m].deletePurok();
                                                        admin.deleteSpecificPurok(m);
                                                        System.out.println("PUROK DELETED SUCCESSFULLY!");
                                                        System.out.println("There are currently " + purokCount + " Purok/s.");
                                                        pressAnyKeyToContinue();
                                                        inDeletePurok = true;

                                                    } else {
                                                        inDeletePurok = false;
                                                        inAdminPurokOptions = true;
                                                    }
                                                }
                                            } while (inDeletePurok);
                                        }
                                    } else if (adminPurokOptions.equals("5")) {
                                        if (purokCount == 0) {
                                            System.out.println("No data yet! Add purok first.");
                                            inAdminPurokOptions = true;
                                        } else {
                                            boolean inSortPurok;
                                            do {
                                                System.out.println("--------------------------------- Sort Purok ---------------------------------");
                                                System.out.println("Sort in: ");
                                                System.out.println("[1] Ascending order");
                                                System.out.println("[2] Descending order");
                                                System.out.println("[0] Back");
                                                System.out.println("Enter option: ");
                                                String ascendOrDescend = scanner.nextLine();

                                                if (ascendOrDescend.equals("1") || ascendOrDescend.equals("2")) {
                                                    admin.sortPurok(ascendOrDescend);
                                                    System.out.println("PUROK SORTED SUCCESSFULLY!");
                                                    inSortPurok = true;
                                                } else if (ascendOrDescend.equals("0")) {
                                                    inSortPurok = false;
                                                    inAdminPurokOptions = true;
                                                } else {
                                                    invalidOption();
                                                    inSortPurok = true;
                                                }
                                            } while (inSortPurok);
                                        }
                                    } else if (adminPurokOptions.equals("6")) {
                                        if(purokCount == 0){
                                            System.out.println("No data yet! Add purok first.");
                                            inAdminPurokOptions = true;
                                        } else {
                                            System.out.println("-------------------------------- Search Purok --------------------------------");
                                            System.out.print("Search for: ");
                                            String name = scanner.nextLine();
                                            admin.searchPurok(name);
                                        }

                                    } else if (adminPurokOptions.equals("0")) {
                                        inAdminPurokOptions = false;
                                        inAdminOptions = true;
                                    } else {
                                        invalidOption();
                                        inAdminPurokOptions = true;
                                    }
                                }while(inAdminPurokOptions);

                            } else if (adminOptions.equals("3")) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                Date currentDate = new Date(); //Current date, for comparing dates.
                                if(purokCount == 0){
                                    System.out.println("No data yet! Add purok first.");
                                    inAdminOptions = true;
                                } else {
                                    boolean inAdminScheduleOptions = false;
                                    do {
                                        admin.viewAllPurok();
                                        System.out.println("------------------------- Admin Options for Schedule -------------------------");
                                        System.out.println("[1] Add Schedule");
                                        System.out.println("[2] View Schedule");
                                        System.out.println("[3] Edit Schedule");
                                        System.out.println("[4] Delete Schedule");
                                        System.out.println("[5] Sort Schedule");
                                        System.out.println();
                                        System.out.println("[0] Back");
                                        System.out.print("Enter option: ");
                                        String adminScheduleOptions = scanner.nextLine();

                                        if (adminScheduleOptions.equals("1")) {
                                            boolean inAddSchedule = false;
                                            do {
                                                System.out.println("-------------------------------- Add Schedule --------------------------------");
                                                System.out.print("Choose Purok [0] Back: ");
                                                int purokIndex = scanner.nextInt();
                                                scanner.nextLine();

                                                int m = purokIndex - 1;

                                                if (purokCount <= m) {
                                                    System.out.println("There are only " + purokCount + " Purok/s! Try again.");
                                                    inAddSchedule = true;
                                                } else if (m < 0) {
                                                    inAddSchedule = false;
                                                    inAdminScheduleOptions = true;
                                                } else {
                                                    boolean addMoreSched;
                                                    System.out.println();
                                                    do {
                                                        admin.viewPurokAndSche(m);
                                                        System.out.println("Enter new purok schedule date");
                                                        System.out.print("dd: ");
                                                        int dd = scanner.nextInt();
                                                        scanner.nextLine();
                                                        System.out.print("MM: ");
                                                        int mm = scanner.nextInt();
                                                        scanner.nextLine();
                                                        System.out.print("yyyy: ");
                                                        int yyyy = scanner.nextInt();
                                                        scanner.nextLine();

                                                        Date temporary = dateFormat.parse( dd+ "-" + mm + "-" + yyyy);
                                                        if(admin.thisDateCompareToCurrentDate(temporary,currentDate)){
                                                            addMoreSched = true;
                                                        } else {
                                                            if(admin.isDuplicatePurokSched(temporary, m)){
                                                                System.out.println("Can't accept duplicate schedules! Try again.");
                                                                addMoreSched = true;
                                                            } else {
                                                                Schedule sched = new Schedule(dd,mm,yyyy,admin.purok[m].getName());
                                                                admin.addSchedule(sched);
                                                                admin.purok[m].addSchedule(sched);
                                                                scheduleCount++;
                                                                admin.viewPurokAndSche(m);

                                                                System.out.println("SCHEDULE ADDED SUCCESSFULLY!");
                                                                System.out.println("There are currently "+ admin.purok[m].schedCount+" Schedules in this purok.");
                                                                System.out.println("Do you want to add another schedule for this purok? [1] Yes, [2] No");
                                                                String addorNah = scanner.nextLine();

                                                                if(addorNah.equals("1")){
                                                                    addMoreSched = true;
                                                                } else {
                                                                    addMoreSched = false;
                                                                    inAddSchedule = true;
                                                                }
                                                            }
                                                        }
                                                    }while(addMoreSched);
                                                }
                                            }while(inAddSchedule);
                                        } else if (adminScheduleOptions.equals("2")) {
                                            if (purokCount == 0) {
                                                System.out.println("No data yet! Add purok first.");
                                                inAdminOptions = true;
                                            } else {
                                                boolean inViewSchedule;
                                                do {
                                                    admin.viewAllSchedules();
                                                    System.out.println("------------------------------- View Schedule --------------------------------");
                                                    System.out.print("Choose Purok [0] Back: ");
                                                    int purokIndex = scanner.nextInt();
                                                    scanner.nextLine();

                                                    int m = purokIndex - 1;

                                                    if (purokCount <= m) {
                                                        System.out.println("There are only " + purokCount + " Purok/s! Try again.");
                                                        inViewSchedule = true;
                                                    } else if (m < 0) {
                                                        inViewSchedule = false;
                                                        inAdminScheduleOptions = true;
                                                    } else {
                                                        admin.viewPurokAndSche(m);
                                                        pressAnyKeyToContinue();
                                                        inViewSchedule = true;
                                                    }
                                                } while (inViewSchedule);
                                            }
                                        } else if (adminScheduleOptions.equals("3")) {
                                            if (purokCount == 0) {
                                                System.out.println("No data yet! Add purok first.");
                                                inAdminOptions = true;
                                            } else {
                                                boolean inEditSchedule = false;
                                                do {
                                                    System.out.println("------------------------------- Edit Schedule --------------------------------");
                                                    System.out.print("Choose Purok [0] Back: ");
                                                    int purokIndex = scanner.nextInt();
                                                    scanner.nextLine();

                                                    int m = purokIndex - 1;

                                                    if (purokCount <= m) {
                                                        System.out.println("There are only " + purokCount + " Purok/s! Try again.");
                                                        inEditSchedule = true;
                                                    } else if (m < 0) {
                                                        inEditSchedule = false;
                                                        inAdminScheduleOptions = true;
                                                    } else {
                                                        System.out.println();
                                                        boolean inChooseSchedToEdit = false;
                                                        do {
                                                            admin.viewPurokAndSche(m);

                                                            System.out.print("Choose schedule [0] Back: ");
                                                            int schedIndex = scanner.nextInt();
                                                            scanner.nextLine();

                                                            int n = schedIndex - 1;

                                                            if (admin.purok[m].schedCount <= n) {
                                                                System.out.println("There are only " + admin.purok[m].schedCount + " Schedules in this purok.");
                                                                inChooseSchedToEdit = true;
                                                            } else if (n < 0) {
                                                                inChooseSchedToEdit = false;
                                                                inEditSchedule = true;
                                                            } else {
                                                                boolean trulyEditThisSh8;
                                                                do {
                                                                    System.out.println("Enter new purok schedule date");
                                                                    System.out.print("dd: ");
                                                                    int dd = scanner.nextInt();
                                                                    scanner.nextLine();
                                                                    System.out.print("MM: ");
                                                                    int mm = scanner.nextInt();
                                                                    scanner.nextLine();
                                                                    System.out.print("yyyy: ");
                                                                    int yyyy = scanner.nextInt();
                                                                    scanner.nextLine();

                                                                    Date temporary = dateFormat.parse(dd + "-" + mm + "-" + yyyy);
                                                                    if (temporary.compareTo(currentDate) < 0) {
                                                                        System.out.println("Date has already passed! Try again.");
                                                                        trulyEditThisSh8 = true;
                                                                    } else {
                                                                        admin.getSameSchedKey(m, n);
                                                                        Admin.schedule[getIndex()].editSchedule(dd, mm, yyyy);
                                                                        //admin.purok[m].schedule[n].editSchedule(dd, mm, yyyy);

                                                                        System.out.println(Admin.schedule[getIndex()].getSched());

                                                                        admin.viewPurokAndSche(m);

                                                                        System.out.println("SCHEDULE CHANGED SUCCESSFULLY!");
                                                                        System.out.println();
                                                                        trulyEditThisSh8 = false;
                                                                        inChooseSchedToEdit = true;
                                                                    }
                                                                } while (trulyEditThisSh8);
                                                            }

                                                        } while (inChooseSchedToEdit);
                                                    }
                                                } while (inEditSchedule);
                                            }
                                        } else if (adminScheduleOptions.equals("4")){
                                            boolean inDeleteSchedule = false;
                                            do{
                                                System.out.println("------------------------------ Delete Schedule -------------------------------");
                                                System.out.print("Choose Purok [0] Back: ");
                                                int purokIndex = scanner.nextInt();
                                                scanner.nextLine();

                                                int m = purokIndex - 1;

                                                if (purokCount <= m) {
                                                    System.out.println("There are only " + purokCount + " Purok/s! Try again.");
                                                    inDeleteSchedule = true;
                                                } else if (m < 0) {
                                                    inDeleteSchedule = false;
                                                    inAdminScheduleOptions = true;
                                                } else {
                                                    System.out.println();
                                                    boolean inChooseSchedToDelete;
                                                    do {
                                                        admin.viewPurokAndSche(m);
                                                        System.out.print("Choose schedule [0] Back: ");
                                                        int schedIndex = scanner.nextInt();
                                                        scanner.nextLine();

                                                        int n = schedIndex - 1;

                                                        if (admin.purok[m].schedCount <= n) {
                                                            System.out.println("There are only " + admin.purok[m].schedCount + " Schedules in this purok.");
                                                            inChooseSchedToDelete = true;
                                                        } else if (n < 0) {
                                                            inChooseSchedToDelete = false;
                                                            inDeleteSchedule = true;
                                                        } else {

                                                            admin.getSameSchedKey(m,n);
                                                            admin.deleteSpecificSchedule(getIndex());
                                                            admin.purok[m].deleteSchedule(n);

                                                            admin.viewPurokAndSche(m);

                                                            System.out.println("SCHEDULE DELETED SUCCESSFULLY!");
                                                            System.out.println();
                                                            inChooseSchedToDelete = true;
                                                        }
                                                    }while (inChooseSchedToDelete);
                                                }
                                            }while(inDeleteSchedule);
                                        } else if (adminScheduleOptions.equals("5")){
                                            if(purokCount == 0){
                                                System.out.println("No data yet! Add purok first.");
                                                inAdminOptions = true;
                                            } else {
                                                boolean inSortSchedule;
                                                do {
                                                    System.out.println("------------------------------- Sort Schedules -------------------------------");
                                                    admin.viewSchedulesByPurok();
                                                    System.out.println("[1] Ascending");
                                                    System.out.println("[2] Descending");
                                                    System.out.println();
                                                    System.out.println("[0] Back");
                                                    System.out.print("Enter Option: ");
                                                    String ascendOrDescend = scanner.nextLine();

                                                    if (ascendOrDescend.equals("1") || ascendOrDescend.equals("2")) {
                                                        admin.sortPurokSchedules(ascendOrDescend);
                                                        System.out.println("SCHEDULES SORTED SUCCESSFULLY!");
                                                        inSortSchedule = true;
                                                    } else if (ascendOrDescend.equals("0")) {
                                                        inSortSchedule = false;
                                                        inAdminScheduleOptions = true;
                                                    } else {
                                                        invalidOption();
                                                        inSortSchedule = true;
                                                    }

                                                } while (inSortSchedule);
                                            }
                                            } else if (adminScheduleOptions.equals("0")){
                                            inAdminScheduleOptions = false;
                                            inAdminOptions = true;
                                        } else {
                                            invalidOption();
                                            inAdminScheduleOptions = true;
                                        }
                                    }while(inAdminScheduleOptions);
                                }

                            } else if (adminOptions.equals("4")) {
                                if(purokCount == 0){
                                    System.out.println("No data yet! Add purok first.");
                                    inAdminOptions = true;
                                } else {
                                    boolean inAdminResidentOptions = false;
                                    do {
                                        admin.viewAllResident();
                                        System.out.println("------------------------- Admin Options for Residents ------------------------");
                                        System.out.println("[1] Add Resident");
                                        System.out.println("[2] View Resident");
                                        System.out.println("[3] Edit Resident");
                                        System.out.println("[4] Delete Resident");
                                        System.out.println("[5] Sort Resident");
                                        System.out.println("[6] Search Resident");
                                        System.out.println();
                                        System.out.println("[0] Back");
                                        System.out.print("Enter option: ");
                                        String adminResidentOptions = scanner.nextLine();

                                        if(adminResidentOptions.equals("1")) {
                                            boolean inAddResident = false;
                                            do{
                                                System.out.println("-------------------------------- Add Resident --------------------------------");
                                                System.out.print("First name: ");
                                                String inputResidentFirstName = scanner.nextLine();
                                                System.out.print("Middle name: ");
                                                String inputResidentMiddleName = scanner.nextLine();
                                                System.out.print("Last name: ");
                                                String inputResidentLastName = scanner.nextLine();
                                                boolean inContactNumVerification;
                                                do {
                                                    System.out.print("Contact number(09*********): ");
                                                    String inputResidentContactNum = scanner.nextLine();

                                                    if(admin.isContactNumNotCorrect(inputResidentContactNum)){
                                                        inContactNumVerification = true;
                                                    } else {
                                                        inContactNumVerification = false;
                                                        boolean purokVerification;
                                                        do{
                                                            System.out.print("Enter purok: ");
                                                            String pname = scanner.nextLine();

                                                            if(admin.isPurokPresent(pname)){
                                                                purokVerification = true;
                                                            } else {
                                                                purokVerification = false;
                                                                admin.getPurokIndex(pname);
                                                                System.out.println("Are you sure you want to add this resident? [1] Yes, [2]No");
                                                                String yesOrNo = scanner.nextLine();

                                                                if (yesOrNo.equals("1")) {
                                                                    Resident res = new Resident(inputResidentFirstName, inputResidentMiddleName, inputResidentLastName, inputResidentContactNum, pname);
                                                                    admin.addResident(res);
                                                                    admin.purok[getIndex()].addResident(res);
                                                                    residentCount++;

                                                                    System.out.println("RESIDENT ADDED SUCCESSFULLY!");
                                                                    System.out.println("There are currently " + residentCount + " Resident/s.");
                                                                    System.out.println("Do you want to add another resident? [1] Yes , [2]No");
                                                                    String addorNah = scanner.nextLine();
                                                                    if (addorNah.equals("1")) {
                                                                        inAddResident = true;
                                                                    } else {
                                                                        inAddResident = false;
                                                                        inAdminResidentOptions = true;
                                                                    }
                                                                } else {
                                                                    inAddResident = false;
                                                                    inAdminResidentOptions = true;
                                                                }
                                                            }
                                                        }while(purokVerification);
                                                    }
                                                }while(inContactNumVerification);
                                            }while(inAddResident);

                                        } else if (adminResidentOptions.equals("2")){
                                            if(residentCount == 0){
                                                System.out.println("No data yet! Add residents first.");
                                                inAdminResidentOptions = true;
                                            } else {
                                                boolean inViewResident;
                                                do{
                                                    System.out.println("------------------------------- View Residents -------------------------------");
                                                    System.out.print("Choose Resident [0] Back: ");
                                                    int viewResident = scanner.nextInt();
                                                    scanner.nextLine();

                                                    int m = viewResident -1;

                                                    if(residentCount <= m){
                                                        System.out.println("There are only " + residentCount + " Resident/s! Try again.");
                                                        inViewResident = true;
                                                    } else if(m < 0){
                                                        inViewResident = false;
                                                        inAdminResidentOptions = true;
                                                    } else {
                                                        admin.viewSpecificResident(m);
                                                        pressAnyKeyToContinue();
                                                        inViewResident = true;
                                                    }
                                                }while(inViewResident);
                                            }

                                        } else if (adminResidentOptions.equals("3")){
                                            if(residentCount == 0){
                                                System.out.println("No data yet! Add residents first.");
                                                inAdminResidentOptions = true;
                                            } else {
                                                boolean inEditResident = false;
                                                do {
                                                    System.out.println("------------------------------- Edit Schedules -------------------------------");
                                                    System.out.print("Choose Resident [0] Back: ");
                                                    int editResident = scanner.nextInt();
                                                    scanner.nextLine();

                                                    int m = editResident - 1;

                                                    if (residentCount <= m) {
                                                        System.out.println("There are only " + residentCount + " Resident/s! Try again.");
                                                        inEditResident = true;
                                                    } else if (m < 0) {
                                                        inEditResident = false;
                                                        inAdminResidentOptions = true;
                                                    } else {
                                                        boolean inEditResidentOptions = false;
                                                        do {
                                                            admin.viewSpecificResident(m);
                                                            System.out.println("What do you want to edit?");
                                                            System.out.println("[1] Name");
                                                            System.out.println("[2] Contact Number");
                                                            System.out.println("[3] Purok");
                                                            System.out.println();
                                                            System.out.println("[0] Back");
                                                            System.out.print("Enter option: ");
                                                            String editOption = scanner.nextLine();

                                                            if (editOption.equals("1")) {
                                                                System.out.print("First name: ");
                                                                String recFName = scanner.nextLine();
                                                                System.out.print("Middle name: ");
                                                                String recMName = scanner.nextLine();
                                                                System.out.print("Last name: ");
                                                                String recLName = scanner.nextLine();

                                                                admin.editResidentName(recFName, recMName, recLName, m);
                                                                System.out.println("NAME EDITED SUCCESSFULLY!");
                                                                System.out.println();
                                                                inEditResidentOptions = true;
                                                            } else if (editOption.equals("2")){
                                                                boolean inEditResidentContactNum;
                                                                do{
                                                                    System.out.print("Enter new contact number: ");
                                                                    String newContactNum = scanner.nextLine();

                                                                    if(admin.isContactNumNotCorrect(newContactNum)){
                                                                        inEditResidentContactNum = true;
                                                                    } else {
                                                                        if(newContactNum.equals(Admin.resident[m].getContactNum())){
                                                                            System.out.println("Input matches the previous contact number! [1] Try Again, [2] Cancel ");
                                                                            String tryOrCancel = scanner.nextLine();

                                                                            if(tryOrCancel.equals("1")){
                                                                                inEditResidentContactNum = true;
                                                                            } else {
                                                                                inEditResidentContactNum = false;
                                                                                inEditResidentOptions = true;
                                                                            }
                                                                        } else {
                                                                            admin.getPurokIndex(Admin.resident[m].getPurok());
                                                                            admin.getResIndex(m);

                                                                            Admin.resident[m].setContactNum(newContactNum);
                                                                            //admin.purok[getIndex()].resident[getIndex2()].setContactNum(newContactNum);
                                                                            System.out.println("CONTACT NUMBER CHANGED SUCCESSFULLY!");
                                                                            System.out.println();
                                                                            inEditResidentContactNum = false;
                                                                            inEditResidentOptions = true;
                                                                        }
                                                                    }
                                                                }while(inEditResidentContactNum);

                                                            } else if (editOption.equals("3")){
                                                                boolean inEditResidentPurok;
                                                                do {
                                                                    System.out.print("Enter new purok: ");
                                                                    String pname = scanner.nextLine();

                                                                    int check0 = 0;
                                                                    for (int i = 0; i < purokCount; i++) {
                                                                        if (admin.purok[i].getName().equalsIgnoreCase(pname)) {
                                                                            check0 = 1;
                                                                        }
                                                                    }
                                                                    if (check0 == 0) {
                                                                        System.out.println("There are no purok with name " + pname + " . Try again.");
                                                                        inEditResidentPurok = true;
                                                                    } else {
                                                                        if (pname.equalsIgnoreCase(Admin.resident[m].getPurok())) {
                                                                            System.out.println("Input matches the previous purok! [1] Try Again, [2] Cancel");
                                                                            String tryOrCancel = scanner.nextLine();

                                                                            if (tryOrCancel.equals("1")) {
                                                                                inEditResidentPurok = true;
                                                                            } else {
                                                                                inEditResidentPurok = false;
                                                                                inEditResidentOptions = true;
                                                                            }
                                                                        } else {
                                                                            admin.getPurokIndex(Admin.resident[m].getPurok());
                                                                            admin.getResIndex(m);
                                                                            admin.getPurokIndexToMove(pname);

                                                                            Admin.resident[m].setPurok(pname);
                                                                            //admin.purok[getIndex()].resident[getIndex2()].setPurok(pname);
                                                                            admin.purok[getIndex3()].addResident(Admin.resident[m]);
                                                                            admin.purok[getIndex()].deleteResident(getIndex2());
                                                                            System.out.println("PUROK CHANGED SUCCESSFULLY!");
                                                                            System.out.println();
                                                                            inEditResidentPurok = false;
                                                                            inEditResidentOptions = true;
                                                                        }
                                                                    }
                                                                }while(inEditResidentPurok);
                                                            } else if (editOption.equals("0")){
                                                                inEditResidentOptions = false;
                                                                inEditResident = true;
                                                            } else {
                                                                invalidOption();
                                                                inEditResidentOptions = true;
                                                            }
                                                        }while(inEditResidentOptions);
                                                    }
                                                }while(inEditResident);
                                            }
                                        } else if (adminResidentOptions.equals("4")){
                                            if(residentCount == 0){
                                                System.out.println("No data yet! Add residents first.");
                                                inAdminResidentOptions = true;
                                            } else {
                                                boolean inDeleteResident = false;
                                                do{
                                                    System.out.println("------------------------------- Delete Resident ------------------------------");
                                                    System.out.print("Choose Resident [0] Back: ");
                                                    int editResident = scanner.nextInt();
                                                    scanner.nextLine();

                                                    int m = editResident - 1;

                                                    if (residentCount <= m) {
                                                        System.out.println("There are only " + residentCount + " Resident/s! Try again.");
                                                        inDeleteResident = true;
                                                    } else if (m < 0) {
                                                        inDeleteResident = false;
                                                        inAdminResidentOptions = true;
                                                    } else {
                                                        admin.viewSpecificResident(m);
                                                        System.out.println("Are you sure you want to delete this resident? [1] Yes, [2]No");
                                                        String yesOrNo = scanner.nextLine();

                                                        if (yesOrNo.equals("1")) {
                                                            admin.deleteResidentInPurok(m);
                                                            admin.deletSpecificResident(m);
                                                            System.out.println("RESIDENT DELETED SUCCESSFULLY!");
                                                            System.out.println("There are currently " + residentCount + " Resident/s.");
                                                            pressAnyKeyToContinue();
                                                            inDeleteResident = true;
                                                        } else {
                                                            inDeleteResident = false;
                                                            inAdminResidentOptions = true;
                                                        }
                                                    }
                                                }while(inDeleteResident);
                                            }
                                        } else if (adminResidentOptions.equals("5")){
                                            if (residentCount == 0) {
                                                System.out.println("No data yet! Add residents first.");
                                                inAdminResidentOptions = true;
                                            } else {
                                                boolean inSortResident = false;
                                                do {
                                                    System.out.println("------------------------------- Sort Resident -------------------------------");
                                                    System.out.println("Sort by: ");
                                                    System.out.println("[1] First Name");
                                                    System.out.println("[2] Middle Name");
                                                    System.out.println("[3] Last Name");
                                                    System.out.println();
                                                    System.out.println("[0] Back");
                                                    System.out.print("Enter option: ");
                                                    String sortOption = scanner.nextLine();

                                                    if (sortOption.equals("1") || sortOption.equals("2") || sortOption.equals("3")) {
                                                        boolean inAscendDescend = false;
                                                        do {
                                                            System.out.println("Sort in: ");
                                                            System.out.println("[1] Ascending order");
                                                            System.out.println("[2] Descending order");
                                                            System.out.println("[0] Back");
                                                            System.out.print("Enter option: ");
                                                            String ascendOrDescend = scanner.nextLine();

                                                            if (ascendOrDescend.equals("1") || ascendOrDescend.equals("2")) {
                                                                admin.sortResidents(sortOption, ascendOrDescend);
                                                                System.out.println("RESIDENTS SORTED SUCCESSFULLY!");
                                                            } else if (ascendOrDescend.equals("0")) {
                                                                inAscendDescend = false;
                                                                inSortResident = true;
                                                            } else {
                                                                invalidOption();
                                                                inAscendDescend = true;
                                                            }
                                                        } while (inAscendDescend);
                                                    } else if (sortOption.equals("0")) {
                                                        inSortResident = false;
                                                        inAdminResidentOptions = true;
                                                    } else {
                                                        invalidOption();
                                                        inSortResident = true;
                                                    }
                                                }while(inSortResident);
                                            }
                                        } else if (adminResidentOptions.equals("6")){
                                            if(residentCount == 0){
                                                System.out.println("No data yet! Add resident first.");
                                                inAdminResidentOptions = true;
                                            } else {
                                                System.out.println("------------------------------- Search Resident ------------------------------");
                                                System.out.print("Search for: ");
                                                String name = scanner.nextLine();
                                                admin.searchResident(name);
                                            }
                                        } else if (adminResidentOptions.equals("0")){
                                            inAdminResidentOptions = false;
                                            inAdminOptions = true;
                                        } else {
                                            invalidOption();
                                            inAdminResidentOptions = true;
                                        }
                                    } while (inAdminResidentOptions);
                                }
                            } else if (adminOptions.equals("5")) {
                                boolean inAdminTreasury = false;
                                do{
                                    admin.viewAllPurok();
                                    System.out.println("---------------------------------- Treasury ----------------------------------");
                                    System.out.print("Choose Purok [0] Back: ");
                                    int editPurok = scanner.nextInt();
                                    scanner.nextLine();

                                    int m = editPurok - 1;

                                    if (purokCount <= m) {
                                        System.out.println("There are only " + purokCount + " Purok/s! Try again.");
                                        inAdminTreasury = true;
                                    } else if (m < 0) {
                                        inAdminTreasury = false;
                                        inAdminOptions = true;
                                    } else {
                                        admin.viewPurokResTotalPaid(m);
                                    }
                                }while(inAdminTreasury);
                            } else if (adminOptions.equals("6")) {
                                boolean inUpdateAdmin = false;
                                do {
                                    System.out.println("-------------------------------- Update Admin --------------------------------");
                                    System.out.println("What do you want to update?");
                                    System.out.println("[1] Username");
                                    System.out.println("[2] Password");
                                    System.out.println("[0] Back");
                                    System.out.print("Enter option: ");
                                    String choice = scanner.nextLine();

                                    if (choice.equals("1")) {
                                        boolean inEditUserName;
                                        do {
                                            System.out.print("Enter new username: ");
                                            String newAdminUsername = scanner.nextLine();
                                            if (newAdminUsername.equals(admin.getUsername())) {
                                                System.out.println("Input matches the previous username! Try again.");
                                                inEditUserName = true;
                                            } else {
                                                admin.setUsername(newAdminUsername);
                                                System.out.println("ADMIN USERNAME CHANGED SUCCESSFULLY!");
                                                inEditUserName = false;
                                                inUpdateAdmin = true;
                                            }
                                        }while(inEditUserName);
                                    } else if (choice.equals("2")){
                                        boolean inEditPassword;
                                        do {
                                            System.out.print("Enter new password: ");
                                            String newAdminPassword = scanner.nextLine();
                                            if(newAdminPassword.equals(admin.getPassword())){
                                                System.out.println("Input matches the previous password! Try again.");
                                                inEditPassword = true;
                                            } else {
                                                if(admin.isPasswordNotCorrect(newAdminPassword)){
                                                    inEditPassword = true;
                                                } else {
                                                    admin.setPassword(newAdminPassword);
                                                    System.out.println("ADMIN PASSWORD CHANGED SUCCESSFULLY!");
                                                    inEditPassword = false;
                                                    inUpdateAdmin = true;
                                                }
                                            }
                                        }while (inEditPassword);
                                    } else if (choice.equals("0")){
                                        inUpdateAdmin = false;
                                        inAdminOptions = true;
                                    } else {
                                        invalidOption();
                                        inUpdateAdmin = true;
                                    }
                                }while (inUpdateAdmin);

                            } else if (adminOptions.equals("0")) {
                                System.out.println("Are you sure you want to logout? [1] Yes, [2] No");
                                String logout = scanner.nextLine();

                                if (logout.equals("1")) {
                                    System.out.println("Logging out...");
                                    inAdminLogin = false;
                                    inMainLogin = true;
                                    inAdminOptions = false;
                                } else {
                                    inAdminOptions = true;
                                }
                            } else {
                                invalidOption();
                                inAdminOptions = true;
                            }
                        }while(inAdminOptions);

                    } else {
                        System.out.println("Incorrect Username or Password! [1] Try Again, [2]Cancel");
                        String choice = scanner.nextLine();
                        if(choice.equals("1")){
                            inAdminLogin = true;
                        } else {
                            inAdminLogin = false;
                            inMainLogin = true;
                        }
                    }
                }while(inAdminLogin);

            } else if (loginIptions.equals("2")) {
                if(recorderCount == 0){
                    System.out.println("No data yet! Add recorders first.");
                } else {
                    boolean inRecorderLogin = false;
                    do {
                        System.out.println("------------------------------- Login Recorder -------------------------------");
                        System.out.print("Username: ");
                        String inputRecorderUsername = scanner.nextLine();
                        System.out.print("Password: ");
                        String inputRecorderPassword = scanner.nextLine();
                        int check = 0;
                        for (int x = 0; x < recorderCount; x++) {
                            if (admin.recorder[x].getRecPassword().equals(inputRecorderPassword) && admin.recorder[x].getRecUsername().equals(inputRecorderUsername)) {
                                check = 1;
                                setUserIndex(x);
                                break;
                            }
                        }
                        if (check == 1) {
                            boolean choosePurok = false;
                            do {
                                admin.userProfile(getUserIndex());
                                admin.announcement();
                                admin.viewAllPurok();
                                System.out.print("Choose Purok [0] Back: ");
                                int choosePrk = scanner.nextInt();
                                scanner.nextLine();

                                int m = choosePrk - 1;

                                if (purokCount <= m) {
                                    System.out.println("There are only " + purokCount + " Purok/s! Try again.");
                                    choosePurok = true;
                                } else if (m < 0) {
                                    choosePurok = false;
                                    inMainLogin = true;
                                } else {
                                    Date current = new Date();
                                    boolean inRecorderOptions = false;
                                    do{
                                        admin.viewPurokAndSche(m);
                                        admin.sortPurokSchedules("1");
                                        System.out.println("------------------------------ Recorder Options ------------------------------");
                                        System.out.println("[1] Record");
                                        System.out.println("[2] Pay");
                                        System.out.println("[3] Record History");
                                        System.out.println();
                                        System.out.println("[0] Logout");
                                        System.out.print("Enter option: ");
                                        String recorderOptions = scanner.nextLine();

                                        if (recorderOptions.equals("1")) {
                                            if (residentCount == 0) {
                                                System.out.println("No data yet! Add residents first.");
                                                inRecorderOptions = true;
                                            } else {
                                                boolean inChooseSched = false;
                                                do {
                                                    admin.viewPurokAndSche(m);
                                                    System.out.print("Choose Schedule [0] Back : ");
                                                    int chooseSched = scanner.nextInt();
                                                    scanner.nextLine();

                                                    int c = chooseSched - 1;
                                                    setSchedIndex(c);

                                                    if (admin.purok[m].schedCount <= c) {
                                                        System.out.println("There are only " + admin.purok[m].schedCount + " Schedule/s in this purok! Try again.");
                                                        inChooseSched = true;
                                                    } else if (c < 0) {
                                                        inChooseSched = false;
                                                        choosePurok = true;
                                                    } else {
                                                        System.out.println("------------------------------ Record Resident -------------------------------");
                                                        System.out.print("Name: ");
                                                        String name = scanner.nextLine();
                                                        boolean inChooseRes = false;
                                                        do {
                                                            admin.searchResidentInPUrok(name, m);
                                                            System.out.print("Choose Resident [0] Back : ");
                                                            int recordRes = scanner.nextInt();
                                                            scanner.nextLine();

                                                            int n = recordRes - 1;

                                                            if (admin.purok[m].resCount <= n) {
                                                                System.out.println("There are only " + admin.purok[m].resCount + " Resident/s in this purok! Try again.");
                                                                inChooseRes = true;
                                                            } else if (n < 0) {
                                                                inChooseRes = false;
                                                                inRecorderOptions = true;
                                                            } else {
                                                                System.out.println("Garbage Details: ");
                                                                String garbageDetails = scanner.nextLine();
                                                                System.out.println("Do you want to record this resident? [1] Yes, [2] No");
                                                                String yesOrNo = scanner.nextLine();

                                                                if (yesOrNo.equals("1")) {
                                                                    if (admin.purok[m].schedule[c].duplicateResidentInSched(admin.purok[m].resident[n].getResKey())) {
                                                                        System.out.println("Resident has already been recorded today. ");
                                                                        inChooseRes = true;
                                                                    } else {
                                                                        admin.purok[m].schedule[c].addResident(admin.purok[m].resident[n], garbageDetails);

                                                                        System.out.println("RESIDENT RECORDED SUCCESSFULLY!");
                                                                        System.out.println("Do you want to record another resident? [1] Yes, [2] No");
                                                                        String yesOrNo1 = scanner.nextLine();
                                                                        if (yesOrNo1.equals("1")) {
                                                                            inRecorderOptions = true;
                                                                        } else {
                                                                            //inChooseSched = false;
                                                                            inChooseRes = false;
                                                                            inRecorderOptions = true;
                                                                        }
                                                                    }
                                                                } else {
                                                                    //inChooseSched = false;
                                                                    inChooseRes = false;
                                                                    inRecorderOptions = true;
                                                                }
                                                            }
                                                        }while(inChooseRes);
                                                    }
                                                }while(inChooseSched);
                                                /*if (admin.compareSchedulesForTodaysDateToRecord(m, current)) {
                                                    System.out.println("There's no schedule for recording today.");
                                                    inRecorderOptions = true;
                                                } else {
                                                    int c = getIndex();
                                                    System.out.println("------------------- Record Resident ---------------------");
                                                    System.out.print("Search for: ");
                                                    String name = scanner.nextLine();
                                                    boolean inChooseRes = false;
                                                    do {
                                                        admin.searchResidentInPUrok(name, m);
                                                        System.out.print("Choose Resident [0] Back : ");
                                                        int recordRes = scanner.nextInt();
                                                        scanner.nextLine();

                                                        int n = recordRes - 1;

                                                        if (admin.purok[m].resCount <= n) {
                                                            System.out.println("There are only " + admin.purok[m].resCount + " Resident/s in this purok! Try again.");
                                                            inChooseRes = true;
                                                        } else if (n < 0) {
                                                            inChooseRes = false;
                                                            inRecorderOptions = true;
                                                        } else {
                                                            System.out.println("Garbage Details: ");
                                                            String garbageDetails = scanner.nextLine();
                                                            System.out.println("Do you want to record this resident? [1] Yes, [2] No");
                                                            String yesOrNo = scanner.nextLine();

                                                            if (yesOrNo.equals("1")) {
                                                                if(admin.purok[m].schedule[c].duplicateResidentInSched(admin.purok[m].resident[n].getResKey())){
                                                                    System.out.println("Resident has already been recorded today. ");
                                                                    inChooseRes = true;
                                                                } else {
                                                                    admin.purok[m].schedule[c].addResident(admin.purok[m].resident[n], garbageDetails);

                                                                    System.out.println("RESIDENT RECORDED SUCCESSFULLY!");
                                                                    System.out.println("Do you want to record another resident? [1] Yes, [2] No");
                                                                    String yesOrNo1 = scanner.nextLine();
                                                                    if (yesOrNo1.equals("1")) {
                                                                        inRecorderOptions = true;
                                                                    } else {
                                                                        //inChooseSched = false;
                                                                        inChooseRes = false;
                                                                        inRecorderOptions = true;
                                                                    }
                                                                }
                                                            } else {
                                                                //inChooseSched = false;
                                                                inChooseRes = false;
                                                                inRecorderOptions = true;
                                                            }
                                                        }
                                                    }while (inChooseRes);
                                                }*/
                                            }
                                        } else if (recorderOptions.equals("2")) {
                                            System.out.println("------------------------------ Resident Payment ------------------------------");
                                            System.out.print("Name: ");
                                            String name = scanner.nextLine();
                                            boolean inPay = false;
                                            do{
                                                admin.searchResidentInPUrok(name, m);
                                                System.out.print("Choose Resident [0] Back : ");
                                                int recordRes = scanner.nextInt();
                                                scanner.nextLine();

                                                int n = recordRes - 1;

                                                if (admin.purok[m].resCount <= n) {
                                                    System.out.println("There are only " + admin.purok[m].resCount + " Resident/s in this purok! Try again.");
                                                    inPay = true;
                                                } else if (n < 0) {
                                                    inPay = false;
                                                    inRecorderOptions = true;
                                                } else {
                                                    admin.purok[m].viewSpecificResident(n);
                                                    System.out.println("Enter amount: ");
                                                    int paymentAmount = scanner.nextInt();
                                                    scanner.nextLine();
                                                    admin.purok[m].viewSpecificResidentAfterPaying(n, paymentAmount);
                                                }
                                            }while(inPay);

                                        } else if (recorderOptions.equals("3")) {
                                            System.out.println("------------------------------- Record History -------------------------------");
                                            boolean inChooseSchedHistoria = false;
                                            do {
                                                admin.viewPurokAndSche(m);
                                                System.out.print("Choose Schedule [0] Back : ");
                                                int chooseSched = scanner.nextInt();
                                                scanner.nextLine();

                                                int c = chooseSched - 1;

                                                if (admin.purok[m].schedCount <= c) {
                                                    System.out.println("There are only " + admin.purok[m].schedCount + " Schedule/s in this purok! Try again.");
                                                    inChooseSchedHistoria = true;
                                                } else if (c < 0) {
                                                    inChooseSchedHistoria = false;
                                                    choosePurok = true;
                                                } else {
                                                    admin.purok[m].schedule[c].viewRecordedResidents();
                                                }
                                            }while (inChooseSchedHistoria);
                                        } else if (recorderOptions.equals("0")) {
                                            System.out.println("End session now? Ending session will automatically add fines for unrecorded residents. [1] Yes, [2] No");
                                            int endSesh = scanner.nextInt();
                                            scanner.nextLine();
                                            if (endSesh == 1) {
                                                admin.purok[m].setResidentFines();//[getScedIndex()].setResidentFines();
                                                /*if (admin.compareSchedulesForTodaysDateToRecord(m, current)) {
                                                    inRecorderOptions = true;
                                                } else {*/
                                                    int c = getIndex();
                                                    System.out.println("Unrecorded resident's fines are updated!");
                                                    System.out.println("Log out? [1]Yes, [2]No");
                                                    int logout = scanner.nextInt();
                                                    scanner.nextLine();
                                                    if (logout == 1) {
                                                        admin.purok[m].resetRecorded();
                                                        choosePurok = false;
                                                        inRecorderLogin = false;
                                                        inRecorderOptions = false;
                                                        inMainLogin = true;
                                                    } else {
                                                        inRecorderOptions = true;
                                                    }

                                            } else {
                                                System.out.println("Log out? [1]Yes, [2]No");
                                                int logout = scanner.nextInt();
                                                scanner.nextLine();
                                                if (logout == 1) {
                                                    choosePurok = false;
                                                    inRecorderLogin = false;
                                                    inRecorderOptions = false;
                                                    inMainLogin = true;
                                                } else {
                                                    inRecorderOptions = true;
                                                }
                                            }
                                        } else {
                                            invalidOption();
                                            inRecorderOptions = true;
                                        }
                                    }while(inRecorderOptions);
                                }
                            }while(choosePurok);
                        }

                    } while(inRecorderLogin);
                }

            } else if (loginIptions.equals("0")) {
            System.out.println("aight, adios!");
            System.exit(0);
            } else {
                invalidOption();
                inMainLogin = true;
            }
        }while(inMainLogin);

    }

    static int schedIndex;
    public static void setSchedIndex(int c){
        schedIndex = c;
    }

    public static int getScedIndex(){
        return schedIndex;
    }


    //Legit totoo ni


    //----------------------------------------------Add Methods---------------------------------------------------------
    //----------------------------------------------View Methods--------------------------------------------------------
    //----------------------------------------------Edit Methods--------------------------------------------------------
    //----------------------------------------------Sort Methods--------------------------------------------------------
    //---------------------------------------------Search Methods-------------------------------------------------------
    //-------------------------------------------------Getters----------------------------------------------------------
    //-------------------------------------------------Setters----------------------------------------------------------


}