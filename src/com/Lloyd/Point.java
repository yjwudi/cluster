package com.Lloyd;

public class Point {

	/** the dimension of the point*/
	public int dim;
	/** the coordinate of the point*/
	public double[] number;
	/** which cluster the point belongs to*/
	public int clusterIndex;
	
	public Point(){
		this.dim = 2;
		this.number = new double[10];
		this.clusterIndex = -1;
	}
	/**
	 * constructor with parameters;
	 * @param dim
	 * @param number
	 */
	public Point(int dim, double[] number) {
		this.dim = dim;
		this.number = number;
		this.clusterIndex = -1;
	}
	/**
	 * calculate the distance between two points
	 * @param p
	 * @return
	 */
	public double distEuclid(Point p){
		double dist = 0.0;
		for(int i = 0; i < dim; i++)
			dist += (number[i] - p.number[i])*(number[i] - p.number[i]);
		return dist;
	}
	
}
