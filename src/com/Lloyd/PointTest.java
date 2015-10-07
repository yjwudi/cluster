package com.Lloyd;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PointTest {

	Point p1;
	
	@Before
    public void setUp() throws Exception {
		int dim = 2;
		double[] num = new double[2];
		num[0] = 1.0;
		num[1] = 2.0; 
        p1 = new Point(dim, num);
    }
	@Test
	public void testDistEuclid() {
		int dim = 2;
		double[] num = new double[2];
		num[0] = 11.0;
		num[1] = 12.0;
		Point p2 = new Point(dim, num);
		double real = p1.distEuclid(p2);
		assertEquals("distance calculation has some problem", 200.0, real, 0.001);
	}

}



