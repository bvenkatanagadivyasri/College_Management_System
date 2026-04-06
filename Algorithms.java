package ds_project;

class SelectionSort {
    static void sortRoll(Student[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j].rollNo < arr[min].rollNo)
                    min = j;
            Student t = arr[i];
            arr[i] = arr[min];
            arr[min] = t;
        }
    }

    static void sortCGPA(Student[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j].cgpa > arr[max].cgpa)
                    max = j;
            Student t = arr[i];
            arr[i] = arr[max];
            arr[max] = t;
        }
    }
}

class HeapSort {
    static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int l = 2*i+1, r = 2*i+2;
        if (l < n && arr[l].name.compareTo(arr[largest].name) > 0)
            largest = l;
        if (r < n && arr[r].name.compareTo(arr[largest].name) > 0)
            largest = r;
        if (largest != i) {
            Student t = arr[i];
            arr[i] = arr[largest];
            arr[largest] = t;
            heapify(arr, n, largest);
        }
    }

    static void sortName(Student[] arr) {
        int n = arr.length;
        for (int i = n/2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n-1; i > 0; i--) {
            Student t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;
            heapify(arr, i, 0);
        }
    }
}

class BinarySearch {
    static int search(Student[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid].rollNo == key) return mid;
            else if (arr[mid].rollNo < key) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}