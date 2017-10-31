//Class that is used for saving the TRANSACTION_DT & CMTE_ID pair along with its getters and setters

//hashCode() and equals() methods are implemented so that the class can be used as a key for a HashMap

public class DateRecipient {
	private String cmteId;
    private String date;
     
    public DateRecipient(String id, String date){
        this.cmteId = id;
        this.date = date;
    }
     
    public int hashCode(){
        int hashcode = 0;
        hashcode = Integer.valueOf(date)/100*20;
        hashcode += cmteId.hashCode();
        return hashcode;
    }
     
    public boolean equals(Object obj){
        if (obj instanceof DateRecipient) {
        	DateRecipient toCompare = (DateRecipient) obj;
            return (toCompare.cmteId.equals(this.cmteId) && toCompare.date.equals(this.date));
        } else {
            return false;
        }
    }
     
    public String getId() {
        return cmteId;
    }
    public void setId(String id) {
        this.cmteId = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
     
    public String toString(){
        return "id: "+cmteId+"  date: "+date;
    }
}
