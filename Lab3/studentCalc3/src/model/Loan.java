package model;

public class Loan {
	
	public Loan()
	{
		
	}

	public double computePayment(String principal, String interest, String period, 
			String fixedInterest, boolean grace, String graceInterest, 
			String gracePeriod ) throws Exception {
		
		double principalVal;
		double interestVal;
		double periodVal;
		double fixedInterestVal;
		double gracePeriodVal;
		double graceInterestVal;
		try {
			fixedInterestVal = Double.parseDouble(fixedInterest);
			gracePeriodVal =  Double.parseDouble(gracePeriod);
			graceInterestVal = Double.parseDouble(graceInterest);
			if(fixedInterestVal < 0 | gracePeriodVal < 0 | graceInterestVal < 0) {
				throw new Exception("IDK WHAT HAPPENED VALUES OUT OF YOUR CONTROL ARE ZEROOOOOOOO!");
			}
		} catch(Exception e) {
			throw new Exception("IDK WHAT HAPPENED FAILED TO PARSE STRINGS OUT OF YOUR CONTROL");
		}
		
		try {
			principalVal = Double.parseDouble(principal);

		} catch(Exception e) {
			throw new Exception("Principal must be a valid number. (cannot be left blank)");
		}
		
		try {
			interestVal = Double.parseDouble(interest);
		} catch(Exception e) {
			throw new Exception("Interest must be a valid number. (cannot be left blank)");
		}
		
		try {
			periodVal =  Double.parseDouble(period);
		} catch(Exception e) {
			throw new Exception("Period must be a valid number. (cannot be left blank)");
		}
		//validate inputs
		if(principalVal <= 0) {
			throw new Exception("Principal must be greater than zero!");
		}
		if(interestVal <= 0) {
			throw new Exception("Interest must be greater than zero!");
		}
		if(periodVal <= 0 ) {
			throw new Exception("Period must be greater than zero!");
		}
		
		
		
		double monthlyPay = 0;
		interestVal = interestVal/100;
		fixedInterestVal = fixedInterestVal/100;
		//standard calculation
		monthlyPay = (interestVal/12) * ( principalVal / (1 -  Math.pow((1 + (interestVal/12)), (-1 * periodVal))));
		
		//Advanced calculation
		if(grace) {
			monthlyPay = monthlyPay + (graceInterestVal/gracePeriodVal);
		}
		
	
		return monthlyPay;
	}
	
	
	public double computeGraceInterest(String principal, String interest, String fixedInterest, 
			boolean grace, String gracePeriod ) throws Exception {
		double principalVal;
		double interestVal;
		double fixedInterestVal;
		double gracePeriodVal;
		try {
			fixedInterestVal = Double.parseDouble(fixedInterest);
			gracePeriodVal =  Double.parseDouble(gracePeriod);
			if(fixedInterestVal < 0 | gracePeriodVal < 0) {
				throw new Exception("IDK WHAT HAPPENED VALUES OUT OF YOUR CONTROL ARE ZEROOOOOOOO!");
			}
		} catch(Exception e) {
			throw new Exception("IDK WHAT HAPPENED FAILED TO PARSE STRINGS OUT OF YOUR CONTROL");
		}
		
		try {
			principalVal = Double.parseDouble(principal);

		} catch(Exception e) {
			throw new Exception("Principal must be a valid number. (cannot be left blank)");
		}
		
		try {
			interestVal = Double.parseDouble(interest);
		} catch(Exception e) {
			throw new Exception("Interest must be a valid number. (cannot be left blank)");
		}
		
		//validate inputs
		if(principalVal <= 0) {
			throw new Exception("Principal must be greater than zero!");
		}
		if(interestVal <= 0) {
			throw new Exception("Interest must be greater than zero!");
		}
		double graceInterest = 0;
		interestVal = interestVal/100;
		fixedInterestVal = fixedInterestVal/100;
		if(grace) {
			graceInterest = principalVal * ((interestVal + fixedInterestVal)/12) * gracePeriodVal;
		}
		return graceInterest;
	}
}
