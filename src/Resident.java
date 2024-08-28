class Resident{

    private String firstName;
    private String middleName;
    private String lastName;
    private String contactNum;
    private String fullName;
    private String purok;

    private String garbageDetails = "";
    private int recorded;
    int resKey = Main.residentCount;

    public int fines = 500;
    public int totalFines;
    public int totalPaid;

    public String finedOn = "";



    public Resident(String firstName, String middleName, String lastName, String contactNum, String purok) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNum = contactNum;
        this.purok = purok;
        recorded = 0;
        fullName = firstName + " " + middleName + " " + lastName;
        setResKey(resKey);
        totalPaid=0;
        totalFines = 0;
    }

    public void pay(int payment){
        totalFines = totalFines - payment;
        updateTotalPaid(payment);
    }

    //----------------------------------------------Add Methods---------------------------------------------------------
    //----------------------------------------------View Methods--------------------------------------------------------
    //----------------------------------------------Edit Methods--------------------------------------------------------
    //----------------------------------------------Sort Methods--------------------------------------------------------
    //---------------------------------------------Search Methods-------------------------------------------------------
    //-------------------------------------------------Getters----------------------------------------------------------
    //-------------------------------------------------Setters----------------------------------------------------------
    public void setFullName(){ fullName = firstName + " " + middleName + " " + lastName; }
    public String getFirstName() {
        return Main.capitalize(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return Main.capitalize(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return Main.capitalize(middleName);
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getFullName() {
        return fullName;
    }

    /*public void setFullName(String fullName) {
        this.fullName = fullName;
    }*/

    public String getPurok() {
        return Main.capitalize(purok);
    }

    public void setPurok(String purok) {
        this.purok = purok;
    }

    public int getResKey() {
        return resKey;
    }

    public void setResKey(int resKey) {
        this.resKey = resKey;
    }

    public String getGarbageDetails() {
        return garbageDetails;
    }

    public void setGarbageDetails(String garbageDetails) {
        this.garbageDetails = garbageDetails;
    }

    public int getRecorded() {
        return recorded;
    }

    public void setRecorded(int recorded) {
        this.recorded = recorded;
    }

    public int getTotalFines(){
        return totalFines;
    }

    public void updateFines(){
        totalFines = totalFines + fines;
    }

    public int getTotalPaid(){
        return totalPaid;
    }

    public void updateTotalPaid(int pymnt){
        totalPaid = totalPaid+pymnt;
    }

    public void setFinedOn(String a){
        finedOn = a;
    }

    public String getFinedOn() {
        return finedOn;
    }
}