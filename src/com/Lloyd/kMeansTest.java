package com.Lloyd;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class kMeansTest {

	kMeans cluster;
	@Before
	public void setUp() throws Exception {
		int i, j, k = 2;
		Point[] points = new Point[4];
		double[] numbers = new double[2];
		for(i = 0; i < 4; i++){
			numbers[0] = i;
			numbers[1] = i+1;
			points[i] = new Point(2, numbers);
		}
		cluster = new kMeans(k, points);
		for(i = 0; i < cluster.K; i++){
			System.out.println(cluster.centroids[i].number[0] + " " + cluster.centroids[i].number[1]);
		}
	}

	@Test
	public void testGenerateInitialCentroids() {
		assertNotNull("centroids initiation has some problem", cluster.centroids);
	}
	
	@Test
	public void testGetCluster(){
		cluster.getCluster();
		for(int i = 0; i < cluster.points.length; i++){
			assertFalse("cluster algorithm has some problem", cluster.points[i].clusterIndex==-1);
		}
	}

}



