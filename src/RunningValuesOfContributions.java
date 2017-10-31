import java.util.TreeSet;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;
public class RunningValuesOfContributions {
	private int count;
    private float total;
    private float median;
    PriorityQueue<Float> maxHeap;
    PriorityQueue<Float> minHeap;
    public RunningValuesOfContributions(int count,float total){
        this.count = count;
        this.total = total;
        this.median = 0;
        this.maxHeap = new PriorityQueue<Float>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<Float>();
    }
     
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public float getMedian() {
        return median;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
  

    public void insert(float donation) {
        if(this.maxHeap.size()==0&&this.minHeap.size()==0)
        {
            this.median = donation;
            this.minHeap.offer(donation);
        }
        else if(this.maxHeap.size()==this.minHeap.size())
        {
            if(donation < this.median)
            {
                this.maxHeap.offer(donation);
                this.median = this.maxHeap.peek();
            }
            else
            {
                this.minHeap.offer(donation);
                this.median = this.minHeap.peek();
            }
        }
        else if(this.maxHeap.size()>this.minHeap.size())
        {
                if(donation < this.median)
                {
                    this.minHeap.offer(this.maxHeap.poll());
                    this.maxHeap.offer(donation);
                }
                else
                {
                    this.minHeap.offer(donation);
                }
                this.median = (float)Math.ceil((Double.valueOf(this.minHeap.poll()) + Double.valueOf(this.maxHeap.poll()))/2);
        }
        else
        {
                if(donation < this.median)
                {
                    this.maxHeap.offer(donation);
                }
                else
                {
                    this.maxHeap.offer(this.minHeap.poll());
                    this.minHeap.offer(donation);
                }
                this.median = (float)Math.ceil((Double.valueOf(this.minHeap.poll()) + Double.valueOf(this.maxHeap.poll()))/2);
        }

    }
     
}
