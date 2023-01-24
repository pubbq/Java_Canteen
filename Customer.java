//Name: Supithcha Jongphoemwatthanaphon
//ID: 6488045
//Section: 2

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	//*********************** DO NOT MODIFY ****************************//
	public static enum CustomerType{DEFAULT, STUDENT, PROFESSOR, ATHLETE, ICTSTUDENT};	//Different types of customers 
	private static int customerRunningNumber = 1;	//static variable for assigning a unique ID to a customer
	private CanteenICT canteen = null;	//reference to the CanteenICT object
	private int customerID = -1;		//this customer's ID
	protected CustomerType customerType = CustomerType.DEFAULT;	//the type of this customer, initialized with a DEFAULT customer.
	protected List<FoodStall.Menu> requiredDishes = new ArrayList<FoodStall.Menu> ();	//List of required dishes
	//*****************************************************************//
	
	private int eatingTime = 0;
        public void eat(){
            this.eatingTime--;
        }
        
        public boolean isFinished(){
            if(this.eatingTime<0){
                return true;
            } else {
                return false;
            }
        }
        
	Customer(CanteenICT _canteen)
	{
		//******************* YOUR CODE HERE **********************
		this.customerID = customerRunningNumber++;
                for(FoodStall.Menu menu: FoodStall.Menu.values()){
                    this.requiredDishes.add(menu);
                    this.eatingTime+=FoodStall.EAT_TIME[menu.ordinal()];
                }
                this.canteen = _canteen;
		//*****************************************************
	}
	
	
	
	public void takeAction()
	{
		//************************** YOUR CODE HERE **********************//
		this.canteen.move1(this);
		this.canteen.orderFood(this);
                this.canteen.takeFood(this);
		this.canteen.findTable(this);
                this.canteen.eating(this);
		//**************************************************************//
		
	}

	//***************For hashing, equality checking, and general purposes. DO NOT MODIFY **************************//	
	
	public CustomerType getCustomerType()
	{
		return this.customerType;
	}
	
	public int getCustomerID()
	{
		return this.customerID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerID != other.customerID)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerType=" + customerType +"]";
	}

	public String getCode()
	{
		return this.customerType.toString().charAt(0)+""+this.customerID;
	}
	
	/**
	 * print something out if VERBOSE is true 
	 * @param str
	 */
	public void jot(String str)
	{
		if(CanteenICT.VERBOSE) System.out.println(str);
		
		if(CanteenICT.WRITELOG) CanteenICT.append(str, canteen.name+"_state.log");
	}
	
	//*************************************************************************************************//
}