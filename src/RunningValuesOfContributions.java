import java.util.TreeSet;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

//Class that is used to store the count of donations, median, total of donations so far along with its getters and setters

//The values of donations are stored in two heaps(1 Min Heap and 1 Max Heap) which are created with regard to median of the donations

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
  
//Function that updates the median so far and stores the new donation amount
//There are two heaps which help us to find the median
//->left heap which is a max heap
//->right heap which is a min heap
// Both the heaps are created with respect to median

    public void insert(float donation) {
        //When both the heaps are empty, this is when the first element is inserted
        if(this.maxHeap.size()==0&&this.minHeap.size()==0)
        {
            this.median = donation;
            this.minHeap.offer(donation);
        }
        //When both the heaps are of same size
        else if(this.maxHeap.size()==this.minHeap.size())
        {
            if(donation < this.median)
            {
                //Current element is less than the median and hence is suitable for left heap
                //Left heap now has more elements and hence has the median
                this.maxHeap.offer(donation);
                this.median = this.maxHeap.peek();
            }
            else
            {
                this.minHeap.offer(donation);
                this.median = this.minHeap.peek();
            }
        }
        //When the max heap(left heap) is bigger than the min heap(right heap)
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
                //Since both the heaps now have equal number of elements
                this.median = (float)Math.ceil((Double.valueOf(this.minHeap.peek()) + Double.valueOf(this.maxHeap.peek()))/2);
        }
        //When the min heap(right Heap) is bigger than the max heap(left heap)
        else
        {
                if(donation < this.median)
                {
                    this.maxHeap.offer(donation);
                }
                else
                {
                    //Top element of the min(right) heap is inserted into the max(left) heap
                    this.maxHeap.offer(this.minHeap.poll());
                    //Current element is greater than median and is hence inserted into the max(left) heap
                    this.minHeap.offer(donation);
                }
                this.median = (float)Math.ceil((Double.valueOf(this.minHeap.peek()) + Double.valueOf(this.maxHeap.peek()))/2);
        }

    }
     
}
