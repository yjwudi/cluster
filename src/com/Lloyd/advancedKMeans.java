package com.Lloyd;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class advancedKMeans {
	/** the number of the clusters */
	public int K;
	/** the points to be clustered */
	public Point[] points;
	/** the centroids of K clusters */
	public Point[] centroids;
	/** the fixed size in each cluster */
	public int countPerCluster;
	/** the size of each cluster */
	public int[] countInCluster;
	/** sum of squared error*/
	public double sseValue;

	/**
	 * constructor
	 * 
	 * @param k
	 * @param points
	 */
	public advancedKMeans(int k, Point[] points) {
		this.K = k;
		this.points = points;
		if (points.length % k == 0)
			countPerCluster = points.length / k;
		else
			countPerCluster = points.length / k + 1;
		countInCluster = new int[k];
		for (int i = 0; i < k; i++)
			countInCluster[i] = 0;
		generateInitialCentroids();
	}

	/**
	 * generate the initial K centroids
	 */
	public void generateInitialCentroids() {
		int i, j, len = points.length, dimension = points[0].dim;
		double[] minNum = new double[dimension], maxNum = new double[dimension];
		centroids = new Point[K];
		for (i = 0; i < dimension; i++) {
			minNum[i] = 1000000000.0;
			maxNum[i] = -1000000000.0;
		}
		for (i = 0; i < len; i++) {
			for (j = 0; j < dimension; j++) {
				if (points[i].number[j] < minNum[j])
					minNum[j] = points[i].number[j];
				if (points[i].number[j] > maxNum[j])
					maxNum[j] = points[i].number[j];
			}
		}
		for (i = 0; i < K; i++) {
			centroids[i] = new Point();
		}
		Random random = new Random();
		for (i = 0; i < K; i++) {
			for (j = 0; j < dimension; j++) {
				double scale = random.nextDouble();
				centroids[i].number[j] = minNum[j] + scale
						* (maxNum[j] - minNum[j]);
			}
		}
	}

	/**
	 * perform the k-means algorithm
	 */
	public void getCluster() {
		int i, j, k;
		int len = points.length, dimension = points[0].dim;
		Boolean clusterChanged = true;
		Vector candidateIndex = new Vector(K + 1);
        int nums = 0;
		while (clusterChanged) {
			nums++;
			clusterChanged = false;
			Boolean[] pointFlag = new Boolean[len];
			for(i = 0; i < len; i++){
				pointFlag[i] = false;
			}
			/** each cluster finds the nearest neighbors*/
			for(i = 0; i < K; i++){
				double[] pointDist = new double[len];
				for(j = 0; j < len; j++){
					pointDist[j] = points[j].distEuclid(centroids[i]);
				}
				for(int s = 0; s < countPerCluster; s++){
					double min_value = 1000000000;
					int minIndex = -1;
					for(j = 0; j < len; j++){
						if(!pointFlag[j]){
							if(pointDist[j] < min_value){
								min_value = pointDist[j];
								minIndex = j;
							}
						}
					}
					if(minIndex != -1){
						if(points[minIndex].clusterIndex != i){
							clusterChanged = true;
							points[minIndex].clusterIndex = i;
						}
						pointFlag[minIndex] = true;
					}
				}
			}
			updateCentroids();
			if(nums == 1){
				sseValue = 0.0;
				for(i = 0; i < len; i++){
					sseValue += points[i].distEuclid(centroids[points[i].clusterIndex]);
				}
				System.out.println("The SSE of initial state is: " + sseValue);
			}
		}
	}

	/**
	 * update the centroid of every cluster
	 */
	public void updateCentroids() {
		int i, j, k;
		int len = points.length, dimension = points[0].dim;
		for (i = 0; i < K; i++) {
			for (j = 0; j < dimension; j++) {
				double sum = 0.0;
				int count = 0;
				for (k = 0; k < len; k++) {
					if (points[k].clusterIndex == i) {
						sum += points[k].number[j];
						count++;
					}
				}
				centroids[i].number[j] = sum / (double) count;
			}
		}
	}

	public static void main(String[] args) {
		int i, j;
		int n, k, dim;
		advancedKMeans cluster;
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		n = sc.nextInt();
		dim = sc.nextInt();
		Point[] tmpPoints = new Point[n];
		for (i = 0; i < n; i++) {
			double[] num = new double[dim];
			for (j = 0; j < dim; j++)
				num[j] = sc.nextDouble();
			tmpPoints[i] = new Point(dim, num);
		}
		cluster = new advancedKMeans(k, tmpPoints);
		cluster.getCluster();
		cluster.sseValue = 0.0;
		for(i = 0; i < n; i++){
			cluster.sseValue += cluster.points[i].distEuclid(cluster.centroids[cluster.points[i].clusterIndex]);
		}
		System.out.println("The SSE of after clustering is: " + cluster.sseValue);
		/*int[] clusterCount = new int[k];
		for (i = 0; i < k; i++) {
			clusterCount[i] = 0;
		}
		for (i = 0; i < cluster.points.length; i++) {
			clusterCount[cluster.points[i].clusterIndex]++;
			System.out.print(cluster.points[i].clusterIndex);
			System.out.print("  ");
		}
		System.out.println("  ");
		for (i = 0; i < k; i++) {
			System.out.println(clusterCount[i]);
		}*/

	}

}
