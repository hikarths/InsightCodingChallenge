import java.util.Comparator;
class DateRecepientComp implements Comparator<DateRecipient>{
 
    @Override
    public int compare(DateRecipient o1, DateRecipient o2) {

            String id1 = ((DateRecipient) o1).getId();
            String id2 = ((DateRecipient) o2).getId();
            int sComp = id1.compareTo(id2);

            if (sComp != 0) 
            {
               return sComp;
            } 
            else 
            {
               String x1 = ((DateRecipient) o1).getDate();
               String x2 = ((DateRecipient) o2).getDate();
               String s1 = x1.substring(5,8)+x1.substring(0,2)+x1.substring(2,4);
               String s2 = x2.substring(5,8)+x2.substring(0,2)+x2.substring(2,4);
               return x1.compareTo(x2);
            }
          }
}   