package com.dcm.examples.junitmavenexample;
import org.springframework.beans.factory.annotation.Autowired;

public class Shop {
	
	@Autowired 
	Arithmetic arithmetic;
	Shop (){
		
	}
	
	public void demo () {
		float result = arithmetic.add(34, 40);
		System.out.print(result);
		
	}
	

}