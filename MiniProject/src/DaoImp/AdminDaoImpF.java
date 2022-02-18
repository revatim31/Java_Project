package DaoImp;
import Entity.AccountantPojo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import Entity.AccountantPojo;

public class AdminDaoImpF implements AdminDao{

	static AccountantPojo ap=new AccountantPojo();
	static ArrayList<AccountantPojo> accountants= new ArrayList<AccountantPojo>(); 
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	 static ObjectOutputStream oo = null;
	 static ObjectInputStream oi = null;
	 @SuppressWarnings("rawtypes")
	 ListIterator li = null;
	 
	 static File file = new File("C:/Users/Admin/Desktop/test/Accountant.txt"); //create file
	
	@SuppressWarnings("unchecked")
	public boolean save(AccountantPojo ap) {	//Enter Accountant Details in AddAccountant() method
		boolean status= false;
		try
		   {
			oi = new ObjectInputStream(new FileInputStream(file)); //write data in file
			accountants = (ArrayList<AccountantPojo>)oi.readObject(); //create list of data
			
			accountants.add(ap);  //add data in array list
			oo = new ObjectOutputStream(new FileOutputStream(file)); //read data from file
			oo.writeObject(accountants);
			oo.close();
			
			System.out.println("Accountant added successfully!");
			}catch (IOException  e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		  }catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return status;
		}

	
	@SuppressWarnings("unchecked")
	public List<AccountantPojo> view(AccountantPojo ap){  //view data from file
		
		try
		{
			oi = new ObjectInputStream(new FileInputStream(file));
			accountants = (ArrayList<AccountantPojo>)oi.readObject();
			oi.close();
		}
		catch(Exception e){ 
			e.printStackTrace();
		}
	
		return accountants;
}

}
