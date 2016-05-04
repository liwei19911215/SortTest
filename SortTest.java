package Test;

import java.util.Random;

public class SortTest {
	public static void main(String[] args) {
		int max = 20000;
		int min = 10000;
		
		int[] a = new int[200];
		for (int i = 0; i < a.length; i++) {
			a[i] = new Random().nextInt(max)%(max-min+1) + min;
		}
//		int[] b = new int[200];
//		for (int i = 0; i < a.length; i++) {
//			b[i] = a[i];
//		}
		long start = System.currentTimeMillis();
//		heapSort(a);
//		mergeSort(a);
//		charuSort(a);
//		shellSort(a);
//		xuanzeSort_improve(a);
//		xuanzeSort(a);
//		maopaoSort(a);
//		kuaisuSort(a, 0, a.length-1);
		
		
		
		
		
		long end = System.currentTimeMillis();
		System.out.println("kuaisuSort  "+(end-start)+"/n") ;
		
		
//		
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	public static void charuSort(int[] arr){
		 for (int i = 0; i < arr.length; i++) {
			 in :	for (int j = i; j >0; j--) {
				if(arr[j]<arr[j-1]){
					change(arr, j, j-1);
				}else{
					break in;
				}
					
			}
		}
	}
	
	public static void  shellSort(int[] arr){
		for (int step = arr.length/2; step > 0; step/=2) {
			for (int i = step; i < arr.length; i++) {
				for (int k = i; k-step >= 0&&arr[k-step]>arr[k] ; k-=step) {
					change(arr, k-step, k);
				}
			}
		}
	}
	
	
	
	public static void  xuanzeSort_improve(int[] arr){
		for (int i = 0; i < arr.length/2; i++) {
			int minIndex = i;
			int maxIndex = i;
			for (int j = i+1; j < arr.length-i; j++) {
				if(arr[minIndex]>arr[j]){
					minIndex = j;
					continue;
				}
				if(arr[maxIndex]<arr[j]){
					maxIndex = j;
				}
			}
			change(arr, i, minIndex);
			change(arr, arr.length-1-i, maxIndex);
		}
	}
	
	public static void  xuanzeSort(int[] arr){
		for (int i = 0; i < arr.length-1; i++) {
			int minIndex = i;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[minIndex]>arr[j]){
					minIndex = j;
				}
			}
			change(arr, i, minIndex);
		}
	}
	
	public static void maopaoSort(int[] arr){
		for (int i = arr.length-1; i >0 ; i--) {
			for (int j = 0; j < i; j++) {
				if(arr[j]>arr[j+1]){
					change(arr, j, j+1);
				}
			}
		}
	}
	
	public static void kuaisuSort(int[] arr, int low, int high){
		int index = low; 
		if(low<high){
			index = partition(arr,low,high);
			kuaisuSort(arr, low, index-1);
			kuaisuSort(arr, index+1, high);
		}
	}
	private static int partition(int[] arr, int low, int high) {
		int index = low;
		while(low<high){
			while(arr[index]<=arr[high]&&low<high){
				high--;
			}
			change(arr, low, high);
			index = high;	
			while(arr[index]>=arr[low]&&low<high){
				low++;
			}
			change(arr, high, low);
			index = low;	
		}	
		return index;
	}

	private static void heapSort(int[] arr) {
		buildHeap(arr);
		for (int i = arr.length-1; i > 0; i--) {
			change(arr, 0, i);
			heapAdjust(arr, 0,i);
		}
		
	}
	private static void heapAdjust(int[] arr,int s,int length) {
		int child = 2*s+1;
		while(child<length){
			if(child+1<length&&arr[child]<arr[child+1]){
				child++;
			}
			if(arr[s]<arr[child]){
				change(arr, s, child);
				s = child;
				child = 2*s+1;
			}else{
				break;
			}
		}
	}
	private static void buildHeap(int[] arr) {
		int s =arr.length/2;
		while(s>=0){
			heapAdjust(arr, s,arr.length-1);
			s--;
		}
	}
	
	private static void merge(int[] arr,int first,int last,int[] store) {
		int i = first;
		int mid = (first+last)/2;
		int j = mid+1;
		int k = i;
		while(i<=mid&&j<=last){
			if(arr[i]<arr[j]){
				store[k++] = arr[i++];
			}else{
				store[k++] = arr[j++];
			}
		}
		while(i<=mid){
			store[k++] = arr[i++];
		}
		while(j<=last){
			store[k++] = arr[j++];
		}
		for (int k2 = first; k2 <= last; k2++) {
			arr[k2] = store[k2];
		}
	}
	private static void mergeSorts(int[] arr,int first,int last,int[] store) {
		if(first<last){
			int mid = (first+last)/2;
			mergeSorts(arr, first,  mid, store);
			mergeSorts(arr, mid+1,  last, store);
			merge(arr, first, last, store);
		}
	}

	private static void mergeSort(int[] arr) {
		int[] store = new int[arr.length];
		mergeSorts(arr, 0, arr.length-1, store);
	}
	public static void change(int[]a,int x,int y){
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp ;
	}
}
