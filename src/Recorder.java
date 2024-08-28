class Recorder extends Admin{

    private String firstName;
    private String middleName;
    private String lastName;
    private String contactNum;
    private String fullName;
    private String recUsername;
    private String recPassword;

    public Recorder(String firstName, String middleName, String lastName, String contactNum, String recUsername, String recPassword) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNum = contactNum;
        fullName = firstName + " " + middleName + " " + lastName;
        this.recUsername = recUsername;
        this.recPassword = recPassword;
    }
    //----------------------------------------------Add Methods---------------------------------------------------------
    //----------------------------------------------View Methods--------------------------------------------------------
    //----------------------------------------------Edit Methods--------------------------------------------------------
    //----------------------------------------------Sort Methods--------------------------------------------------------
    //---------------------------------------------Search Methods-------------------------------------------------------
    //-------------------------------------------------Getters----------------------------------------------------------
    public String getRecUsername() {
        return recUsername;
    }
    public String getRecPassword() {
        return recPassword;
    }
    public String getFirstName() {
        return Main.capitalize(firstName);
    }
    public String getMiddleName() {
        return Main.capitalize(middleName);
    }
    public String getLastName() {
        return Main.capitalize(lastName);
    }
    public String getContactNum() {
        return contactNum;
    }
    public String getFullName() {
        return fullName;
    }
    //-------------------------------------------------Setters----------------------------------------------------------
    public void setRecUsername(String recUsername) {
        this.recUsername = recUsername;
    }
    public void setRecPassword(String recPassword) {
        this.recPassword = recPassword;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
    public void setFullName(){ fullName = firstName + " " + middleName + " " + lastName; }

}