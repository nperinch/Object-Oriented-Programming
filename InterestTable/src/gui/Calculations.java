package gui;

public class Calculations {
	public static double compoundInterest(double rate, double principal, double years) {  
		return principal * java.lang.Math.pow((1+rate/100.00),years);	//compound interest calculation	
	}
	
	public static double simpleInterest(double rate, double principal, double years) {  
		return principal + (principal * (rate / 100.00) * years);  //simple interest calculation
	}


}

