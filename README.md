# InsightCodingChallenge

## Approach

### -medianvals_by_zip.txt
For every record that is parsed, the current median for the CMTE_ID & ZIP_CODE pair is calculated.  
A HashMap is used to store the values that are seen so far.  
They key and value pair of the HashMap created are custom class objects that are created.   
The key is the CMTE_ID & ZIP_CODE pair.  
The values is a class that stores count of donations, total of donations, median and two heaps to store values of donations.  
Every time a new donation value is parsed, the corresponding new median has to be computed.   
There are two heaps used: 1 Max heap on the left side which stores all the values of donations that are less than the median and a min heap on the right side to store the elements that are greater than the median.  
If the left and right heap have the same number of elements, the new median will be the top most element where the new donation value is inserted.  
In other cases, it is made sure that the left and right heap differ by atmost 1 element.   
It is achieved by removing top most one value from one of the heaps and putting it in the other heap based on the comparison of the new value and median. The new median will be the average of the top most elements of both the heaps.

### -medianvals_by_date.txt
This file output is calculated only after the input is read completely.  
When the input is read line by line, the details are stored in a HashMap similar to the previous method.  
The key is the CMTE_ID & TRANSACTION_DT pair.  
The values is a class that stores donations, total of donations so far and all the values of donations.  
When the input is being read a TreeSet that has the same key used in the HashMap mentioned previously is used.  
The comparator is written so that the order will be sorted by CMTE_ID and then chronologically by TRANSACTION_DT.    
For each value in the TreeSet, the list of donations is retrieved and the median is computed by sorting the ArrayList of donation values and stored.

## Dependencies
Java 1.8 (Only works in version 1.8)

## Run Instructions

- Place file itcont.txt that contains the test dataset in the input folder
- Execute the run.sh(Please make sure that it has the permission to be executed) to get the output medianvals_by_date.txt and medianvals_by_zip.txt which can be found in the output folder
- Execute the run_tests.sh(Please make sure that it has the permission to be executed) inside insight_testsuite folder to run the code against the test cases that are written in the tests folder
