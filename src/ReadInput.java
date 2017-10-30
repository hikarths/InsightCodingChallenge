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
		// TODO Auto-generated method stub
		String fileName = "../input/itcont.txt";
        String line = null;
        try 
        {
            FileReader fileReader =  new FileReader(fileName);
	    
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
	    
	    
            BufferedWriter writer = new BufferedWriter(new FileWriter("../output/medianvals_by_zip.txt"));
            HashMap<ZipRecipient,valuesOfContributions> zipCodeDetails = new HashMap<ZipRecipient,valuesOfContributions>();
            detailsToSort = new TreeSet<DateRecipient>(new DateRecepientComp());
            dateDetails = new HashMap<DateRecipient,valuesOfContributions>();
            while((line = bufferedReader.readLine()) != null) 
            {
                //System.out.println(line);
            	String fields[] = line.split("\\|");
            	//System.out.print(fields.length+" ");
            	String cmptId = fields[0];
                //System.out.println(fields[10]);
            	String zipCode = fields[10].substring(0, 5);
                //System.out.println(zipCode);
            	String date = fields[13];
            	float donationAmount = Float.valueOf(fields[14]);
            	String otherField = fields[15];
            	writer.write("");
            	if (otherField.length()==0)
            	{
            		ZipRecipient newRecord = new ZipRecipient(cmptId, zipCode);
            		
            		if(!zipCodeDetails.containsKey(newRecord))
            		{
                        ArrayList<Float> values = new ArrayList<>();
            			values.add(donationAmount);
            			//System.out.println(donationAmount);
            			zipCodeDetails.put(newRecord, new valuesOfContributions(1,donationAmount,values));
            			writer.write(cmptId+"|"+zipCode+"|"+(int)donationAmount+"|"+"1"+"|"+(int)donationAmount);
            			writer.write("\n");
            		}
            		else
            		{
                        valuesOfContributions val = zipCodeDetails.get(newRecord);
            			int count = val.getCount();
            			++count;
            			float total = val.getTotal();
            			total+=donationAmount;
            			ArrayList<Float> contrib = val.getContributions();
                        contrib.add(donationAmount);
                        Collections.sort(contrib);
            			float median = 0;
            			if(count%2==1)
            				median = contrib.get(count/2+1);
            			else
            				median = (int)(Math.ceil((Double.valueOf(contrib.get(count/2)+contrib.get(count/2-1))/2)));
            			//System.out.println(median);
            			//System.out.println(total);
            			zipCodeDetails.put(newRecord, new valuesOfContributions(count,total,contrib));
            			writer.write(cmptId+"|"+zipCode+"|"+(int)median+"|"+count+"|"+(int)total);
            			writer.write("\n");
            			
            		}
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
                        //System.out.println(total);
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

    static void writeMedialValsByDate(){
        BufferedWriter writer = null;
        try{
        writer = new BufferedWriter(new FileWriter("../output/medianvals_by_date.txt"));
        String path = System.getProperty("user.dir");
        path = path.replace("jars", "");
        //System.out.println(path);

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
                median = values.get(count/2+1);
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
