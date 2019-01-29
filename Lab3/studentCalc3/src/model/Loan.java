package model;

public class Loan {
	
	public Loan()
	{
		
	}

	public static double computePayment(double principal, double interest, double period, 
			double fixedInterest, boolean grace, double graceInterest, 
			double gracePeriod ) throws Exception {
		double monthlyPay = 0;
		interest = interest/100;
		fixedInterest = fixedInterest/100;
		//standard calculation
		monthlyPay = (interest/12) * ( principal / (1 -  Math.pow((1 + (interest/12)), (-1 * period))));
		
		//Advanced calculation
		if(grace) {
			monthlyPay = monthlyPay + (graceInterest/gracePeriod);
		}
	
		return monthlyPay;
	}
	
	
	public static double computeGraceInterest(double principal, double interest, double fixedInterest, 
			boolean grace, double gracePeriod ) throws Exception {
//		System.out.println(principal);
//		System.out.println(interest);
//		System.out.println(fixedInterest);
//		System.out.println(grace);
//		System.out.println(gracePeriod);
		double graceInterest = 0;
		interest = interest/100;
		fixedInterest = fixedInterest/100;
		if(grace) {
			graceInterest = principal * ((interest + fixedInterest)/12) * gracePeriod;
		}
		return graceInterest;
	}
}
