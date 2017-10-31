//Class that is used for saving the ZIP_CODE & CMTE_ID pair along with its getters and setters

//hashCode() and equals() methods are implemented so that the class can be used as a key for a HashMap

public class ZipRecipient {
	private String cmteId;
    private String zip;
     
    public ZipRecipient(String id, String code){
        this.cmteId = id;
        this.zip = code;
    }
     
    public int hashCode(){
        int hashcode = 0;
        hashcode = ((int)Math.random())*20;
        hashcode += cmteId.hashCode();
        return hashcode;
    }
     
    public boolean equals(Object obj){
        if (obj instanceof ZipRecipient) {
        	ZipRecipient toCompare = (ZipRecipient) obj;
            return (toCompare.cmteId.equals(this.cmteId) && toCompare.zip.equals(this.zip));
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
    public String getZip() {
        return zip;
    }
    public void setZip(String code) {
        this.zip = code;
    }
     
    public String toString(){
        return "id: "+cmteId+"  code: "+zip;
    }
}
