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
               return x1.compareTo(x2);
            }
          }
}   