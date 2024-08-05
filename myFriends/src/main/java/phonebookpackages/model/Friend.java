package phonebookpackages.model;

public class Friend {

    private String name;
    private long phoneNumber;

    public Friend(String name, long phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        return name + ", phoneNumber: 0" + phoneNumber + "";
    }

}
