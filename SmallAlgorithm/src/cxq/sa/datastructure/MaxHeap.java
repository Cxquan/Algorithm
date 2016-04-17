package cxq.sa.datastructure;

import java.util.Arrays;
import java.util.List;

public class MaxHeap {

	List<Integer> maxHeap;

	// construct
	public MaxHeap(List<Integer> array) {
		maxHeap = array;
		buildMaxHeap();
	}

	public List<Integer> heapSort() {//破坏了maxHeap结构,可通过buildMaxHeap重构
		int heapSize = maxHeap.size();
		for (int i = maxHeap.size()-1; i > 0; i--) {
			swap(0, i);
			heapSize--;
			doMaxHeapify(0, heapSize);
		}
		return maxHeap;
	}
	
	public void buildMaxHeap() {
		for (int i = maxHeap.size()/2 -1; i >= 0; i--) {
			doMaxHeapify(i, maxHeap.size());
		}
	}

	public void doMaxHeapify(int i, int heapSize) {
		if (i > heapSize/2 -1) {
			return;
		}
		int l = getLeftchild(i);
		int r = getRightChild(i);

		int max = (l > -1 && l < heapSize && maxHeap.get(l) > maxHeap.get(i)) ? l : i;
		max = (r > -1 && r < heapSize && maxHeap.get(r) > maxHeap.get(max)) ? r : max;

		if (max != i) {
			swap(max, i);
			doMaxHeapify(max, heapSize);
		}
	}

	private void swap(int a, int b) {
		int tmp = maxHeap.get(a);
		maxHeap.set(a, maxHeap.get(b));
		maxHeap.set(b, tmp);
	}

	private int getParent(int i) {
		return (i+1)>>1 < 0 ? -1 : ((i+1)>>1) -1;
	}

	private int getLeftchild(int i) {
		return (i+1)<<1 > maxHeap.size() ? -1 : ((i+1)<<1) -1;
	}

	private int getRightChild(int i) {
		return ((i+1)<<1) + 1 > maxHeap.size() ? -1 : (i+1)<<1;
	}

	public static void main(String[] args) {
		Integer a[] = {1,5,2,6,9,9};
		List<Integer> array = Arrays.asList(a);
		MaxHeap maxHeap = new MaxHeap(array);
		array = maxHeap.heapSort();
		for (int i = 0; i < array.size(); i++) {
			System.out.print(array.get(i)+" ");
		}

	}

}
