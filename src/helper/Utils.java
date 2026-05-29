package helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.DecimalFormatSymbols;

public class Utils {
       static SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
       static NumberFormat nf = new DecimalFormat("R$ #,##0.00 ",new DecimalFormatSymbols(new Locale("pt","BR")));
       
       public static String dateParString(Date data) {
    	   return Utils.sdf.format(data);
       }
       public static String doubleParaString(Double Valor) {
    		   return Utils.nf.format(Valor); 
    	   
       }
       public static Double StringParaDouble(String Valor) {
    	   try {
    		   return (Double)Utils.nf.parse(Valor);
    	   }catch(ParseException e) {
    		   return null;
    	   }
       }
       public static void pausar (int Segundos) {
    	   try {
    		   TimeUnit.SECONDS.sleep(Segundos);
    	   }catch(InterruptedException e){
    		   System.out.println("Erro ao pausssar por "+Segundos+" sengundos");
    		   
    	   }
       }
	public static Date stringParaData(String Data) {
		try {
			return Utils.sdf.parse(Data);
		}catch(ParseException e) {
			return null;
		}
		
	}
}
