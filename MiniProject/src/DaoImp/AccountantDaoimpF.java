package DaoImp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import Entity.AccountantPojo;
import Entity.StudentPojo;
import FeeReportUI.Accountant;


public class AccountantDaoimpF implements AccountantDao {
	
		static StudentPojo sPojo = new StudentPojo();
		boolean flag = false;
		static ArrayList<AccountantPojo> accountants= new ArrayList<AccountantPojo>(); 

		static ArrayList<StudentPojo> Students = new ArrayList<StudentPojo>();
		static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		static ObjectOutputStream oo = null;
		static ObjectInputStream oi = null;
		@SuppressWarnings("rawtypes")
		static
		ListIterator li = null;
		static File file = new File("C:/Users/Admin/Desktop/test/Student.txt");
		 static File file2 = new File("C:/Users/Admin/Desktop/test/Accountant.txt");
		
		@SuppressWarnings("unchecked")
		public boolean validate(String name,String password) { //check accountant login is correct or not
			boolean b = false;
			try
			{
				oi = new ObjectInputStream(new FileInputStream(file2));
				accountants=(ArrayList<AccountantPojo>)oi.readObject();
				oi.close();
				li=accountants.listIterator();
				while(li.hasNext())
				{
					AccountantPojo ap=(AccountantPojo)li.next();
					if(ap.getName().equals(ap.getName()))
						if(ap.getPassword().equals(ap.getPassword()))
							b=true;
					else
					{
						b=false;
						System.out.println(ap.getName());
						System.out.println(ap.getPassword());
					}
					
				}
		
			}
			catch(Exception e){System.out.println(e);}
			return b;
		}	
	
		
		@SuppressWarnings("unchecked")
		public boolean save(StudentPojo sPojo ) {
		 try
		   {
			 oi = new ObjectInputStream(new FileInputStream(file));
			 Students = (ArrayList<StudentPojo>)oi.readObject();
			 Students.add(sPojo);
			 oo = new ObjectOutputStream(new FileOutputStream(file));
			 oo.writeObject(Students);
			 oo.close();	
			
			 System.out.println("Student added successfully!\n");
		   }
		   catch(Exception e){
		    System.out.println(e);
		    System.out.println("Sorry, Unable to add student!\n");
		   }
		 return false;
		}

		
	@SuppressWarnings("unchecked")
	public ArrayList<StudentPojo> view(StudentPojo sPojo) {
		
		try
		{
			oi = new ObjectInputStream(new FileInputStream(file));
			Students =(ArrayList<StudentPojo>)oi.readObject();
			oi.close();
		}
		catch(Exception e)
		  	{System.out.println(e);
		  	}
		 return Students;
		
	}
		  
		@SuppressWarnings("unchecked")
		public boolean update(StudentPojo sPojo) {
			
			boolean status=false;
			try
			{ 
				li = Students.listIterator();
				int a = 0;
				while(li.hasNext())
				{
					StudentPojo s=(StudentPojo)li.next();
					
						if((s.getRollno()==(sPojo.getRollno())))
						{
						li.remove();
						oo = new ObjectOutputStream(new FileOutputStream(file));
						oo.writeObject(Students);
						oo.close();
				            a++;
				            System.out.println(" Student Data");
						}
					
						if((s.getRollno())==(sPojo.getRollno()))
						{
						try
						   {
							oi = new ObjectInputStream(new FileInputStream(file));
							Students = (ArrayList<StudentPojo>)oi.readObject();
							Students.add(sPojo);	
							oo = new ObjectOutputStream(new FileOutputStream(file));
							oo.writeObject(Students);
							oo.close();
				            System.out.println("\n student data updated in File \n");
				            }
						catch(Exception e){System.out.println(e);}
					    }
				}
				
				if(a==0) {
					System.out.println(" Student Data Not Found");
					}
			}
				catch(Exception e)
				{System.out.println(e);}
				return status;
		}	
		
   public List<StudentPojo> due(StudentPojo sPojo) {
	   try
		{ 
			li = Students.listIterator();
			while(li.hasNext())
			{
				StudentPojo s=(StudentPojo)li.next();
				if(s.getDue()>0)
					{
						oi = new ObjectInputStream(new FileInputStream(file));
						Students =(ArrayList<StudentPojo>)oi.readObject();
						oi.close();
						}
				}
			
		}catch(Exception e){System.out.println(e);}
	   return Students;
   }
}