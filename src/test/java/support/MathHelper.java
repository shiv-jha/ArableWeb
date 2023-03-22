package support;

import java.util.List;

import org.testng.Assert;

public class MathHelper {
	
   public boolean verifyDescInt(List<Integer> listValue) {
	  Assert.assertTrue(listValue.size()>0, "Size of list is not zero");
	  if(listValue.size()==0) {
		  return true;
	  }
	  for(int i=0;i<listValue.size()-1;i++) {
		  if(listValue.get(i)<listValue.get(i+1)) {
			  return false;
		  }
	  }
	  return true;
   }
   
   public boolean verifyAscInt(List<Integer> listValue) {
	  Assert.assertTrue(listValue.size()>0, "Size of list is not zero");
	  if(listValue.size()==0) {
		  return true;
	  }
	  for(int i=0;i<listValue.size()-1;i++) {
		  if(listValue.get(i)>listValue.get(i+1)) {
			  return false;
		  }
	  }
	  return true;
   }
   
   public boolean verifyDescDouble(List<Double> listValue) {
		  Assert.assertTrue(listValue.size()>0, "Size of list is not zero");
		  if(listValue.size()==0) {
			  return true;
		  }
		  for(int i=0;i<listValue.size()-1;i++) {
			  if(listValue.get(i)<listValue.get(i+1)) {
				  return false;
			  }
		  }
		  return true;
	   }
	   
	   public boolean verifyAscDouble(List<Double> listValue) {
		  Assert.assertTrue(listValue.size()>0, "Size of list is not zero");
		  if(listValue.size()==0) {
			  return true;
		  }
		  for(int i=0;i<listValue.size()-1;i++) {
			  if(listValue.get(i)>listValue.get(i+1)) {
				  return false;
			  }
		  }
		  return true;
	   }
}
