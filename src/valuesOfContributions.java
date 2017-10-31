import java.util.TreeSet;
import java.util.ArrayList;

//Class that is used to store the count of donations, total of donations and the list of all donations so far along with its getters and setters
public class valuesOfContributions {
	private int count;
    private float total;
    private ArrayList<Float> contributions;
     
    public valuesOfContributions(int count,float total, ArrayList<Float> contributions){
        this.count = count;
        this.total = total;
        this.contributions = contributions;
    }
     
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public ArrayList<Float> getContributions() {
        return contributions;
    }
    public void setContributions(ArrayList<Float> contributions) {
        this.contributions = contributions;
    }
     
}
