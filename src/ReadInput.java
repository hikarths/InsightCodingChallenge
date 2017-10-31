import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.io.File;
import java.util.Collections;
import java.text.NumberFormat;

public class ReadInput {
    static HashMap<DateRecipient,valuesOfContributions> dateDetails;
    static TreeSet<DateRecipient> detailsToSort;
	public static void main(String[] args) {
		String fileName = "../input/itcont.txt";
        String line = null;
        try 
        {
            FileReader fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            BufferedWriter writer = new BufferedWriter(new FileWriter("../output/medianvals_by_zip.txt"));
            HashMap<ZipRecipient,RunningValuesOfContributions> zipCodeDetails = new HashMap<ZipRecipient,RunningValuesOfContributions>();
            detailsToSort = new TreeSet<DateRecipient>(new DateRecepientComp());
            dateDetails = new HashMap<DateRecipient,valuesOfContributions>();
            while((line = bufferedReader.readLine()) != null) 
            {
                //Parse Input
            	String fields[] = line.split("\\|");
            	String cmptId = fields[0];
                String zipCode = null;
            	if(fields[10]!=null&&fields[10].length()>=5)
                    zipCode = fields[10].substring(0, 5);
            	String date = fields[13];
                float donationAmount=0;
                if(fields[14]!=null&&fields[14].length()>0)
            	donationAmount = Float.valueOf(fields[14]);
            	String otherField = fields[15];
            	writer.write("");
                //Input Validation
            	if (otherField.length()==0&&fields[14]!=null&&fields[14].length()>0&&cmptId.length()!=0&&zipCode!=null&&zipCode.length()==5)
            	{
            		ZipRecipient newRecord = new ZipRecipient(cmptId, zipCode);
            		//Computing the output for medianvals_by_zip.txt
            		if(!zipCodeDetails.containsKey(newRecord))
            		{
                        RunningValuesOfContributions values = new RunningValuesOfContributions(1,donationAmount);
                        values.insert(donationAmount);
            			zipCodeDetails.put(newRecord, values);
            			writer.write(cmptId+"|"+zipCode+"|"+(int)donationAmount+"|"+"1"+"|"+(int)donationAmount);
            			writer.write("\n");
            		}
            		else
            		{
                        RunningValuesOfContributions val = zipCodeDetails.get(newRecord);
            			int count = val.getCount();
            			++count;
                        val.setCount(count);
            			float total = val.getTotal();
            			total+=donationAmount;
                        val.setTotal(total);
            			val.insert(donationAmount);
            			zipCodeDetails.put(newRecord, val);
            			writer.write(cmptId+"|"+zipCode+"|"+(int)val.getMedian()+"|"+val.getCount()+"|"+(int)val.getTotal());
            			writer.write("\n");
            			
            		}
                }
                
                //Saving the values of donations that is to be used for finding the values for medianvals_by_date.txt

                if(otherField.length()==0&&fields[14]!=null&&fields[14].length()>0&&cmptId.length()!=0&&date.length()==8)
                {
                    DateRecipient newEntry = new DateRecipient(cmptId, date);
                    if(!dateDetails.containsKey(newEntry))
                    {
                        ArrayList<Float> values = new ArrayList<>();
                        values.add(donationAmount);
                        valuesOfContributions val = new valuesOfContributions(1,donationAmount,values);
                        dateDetails.put(newEntry,val);
                    }
                    else
                    {
                        valuesOfContributions val = dateDetails.get(newEntry);
                        ArrayList<Float> details = new ArrayList<>();
                        int count = val.getCount();
                        ++count;
                        float total = val.getTotal();
                        total+=donationAmount;
                        ArrayList<Float> contributions = val.getContributions();
                        contributions.add(donationAmount);
                        dateDetails.put(newEntry,new valuesOfContributions(count,total,contributions));
                    }
                    detailsToSort.add(newEntry);
            	}	
            }   
            writer.close();
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) 
        {
            System.out.println("Unable to open file '" +fileName + "'");                
        }
        catch(IOException ex) 
        {
            System.out.println("Error reading file '"+ fileName + "'");                  
        }
        writeMedialValsByDate();
	}

    //Function that is used to compute the output for medianvals_by_date.txt
    static void writeMedialValsByDate(){
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter("../output/medianvals_by_date.txt"));
            for(DateRecipient record:detailsToSort)
            {
                valuesOfContributions val = dateDetails.get(record);
                ArrayList<Float> details = new ArrayList<>();
                int count = val.getCount();
                float total = val.getTotal();
                ArrayList<Float> values = new ArrayList<>();
                values = val.getContributions();
                Collections.sort(values);
                float median = 0;
                if(count%2==1)
                    median = values.get(count/2);
                else
                    median = (int)(Math.ceil((Double.valueOf(values.get(count/2-1)+values.get(count/2))/2)));
                writer.append(record.getId()+"|"+record.getDate()+"|"+(int)median+"|"+count+"|"+(int)total);
                writer.append("\n");
            }
            writer.close();
        }
        catch(IOException ex) 
        {
            System.out.println("Error reading file '" + "'");                  
        }
    }
}
