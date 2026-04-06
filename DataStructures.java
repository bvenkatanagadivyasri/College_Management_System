package ds_project;

import java.io.*;
import java.util.*;

class StudentList {
    Node head;

    void insert(Student s) {
        Node n = new Node(s);
        if (head == null) head = n;
        else {
            Node t = head;
            while (t.next != null) t = t.next;
            t.next = n;
        }
    }

    boolean delete(int roll) {
        if (head == null) return false;

        if (head.data.rollNo == roll) {
            head = head.next;
            return true;
        }

        Node t = head;
        while (t.next != null && t.next.data.rollNo != roll)
            t = t.next;

        if (t.next != null) {
            t.next = t.next.next;
            return true;
        }

        return false;
    }
    
    
}

/* PRIORITY QUEUE */
class Node2 {
    Student data;
    Node2 link;

    Node2(Student s) {
        data = s;
        link = null;
    }
}

class PriorityQueueLL {
    Node2 front = null;
    Node2 rear = null;

    void insert(Student data) {
        Node2 newnode = new Node2(data);

        if (front == null) {
            front = rear = newnode;
            return;
        }

        if (data.cgpa >= front.data.cgpa) {
            newnode.link = front;
            front = newnode;
            return;
        } else {
            Node2 temp = front;
            while (temp.link != null && temp.link.data.cgpa >= data.cgpa) {
                temp = temp.link;
            }
            newnode.link = temp.link;
            temp.link = newnode;

            if (newnode.link == null) {
                rear = newnode;
            }
        }
    }

    Student delete() {
        if (front == null) return null;

        Student s = front.data;
        front = front.link;

        if (front == null) rear = null;

        return s;
    }
}

/* HASH TABLE */
class HashNode {
    Student data;
    HashNode next;

    HashNode(Student s) { data = s; }
}

class HashTable {
    int size = 10;
    HashNode[] table = new HashNode[size];

    int hash(String name) {
        int sum = 0;
        for (int i = 0; i < name.length(); i++)
            sum += name.charAt(i);
        return sum % size;
    }

    void insert(Student s) {
        int index = hash(s.name);
        HashNode n = new HashNode(s);
        if (table[index] == null) table[index] = n;
        else {
            HashNode t = table[index];
            while (t.next != null) t = t.next;
            t.next = n;
        }
    }

    Student search(String name) {
        int index = hash(name);
        HashNode t = table[index];
        while (t != null) {
            if (t.data.name.equals(name))
                return t.data;
            t = t.next;
        }
        return null;
    }

    void rebuild(Student[] arr) {
        table = new HashNode[size];
        for (int i = 0; i < arr.length; i++)
            insert(arr[i]);
    }
}