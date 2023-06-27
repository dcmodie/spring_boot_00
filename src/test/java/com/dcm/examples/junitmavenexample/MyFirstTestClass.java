package com.dcm.examples.junitmavenexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
 
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class MyFirstTestClass {				

    @Test		
    public void myFirstMethod(){					
        String str= "JUnit is working fine";					
        assertEquals("JUnit is working fine",str);					
    }
}		