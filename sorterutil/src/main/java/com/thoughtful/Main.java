package com.thoughtful;

public class Main {

    //Quick Run of the function
    public static void main(String[] args) {
        PackageSorter sorter = new PackageSorter();
        
        System.out.println("Package Sorter Demo\n");
        
        testPackage(sorter, 50, 50, 50, 5);
        testPackage(sorter, 100, 100, 100, 10);
        testPackage(sorter, 200, 50, 50, 5);
        testPackage(sorter, 50, 50, 50, 25);
        testPackage(sorter, 200, 100, 100, 25);
    }
    
    private static void testPackage(PackageSorter sorter, double w, double h, double l, double m) {
        String result = sorter.sort(w, h, l, m);
        System.out.printf("%.0fx%.0fx%.0f cm, %.0f kg -> %s%n", w, h, l, m, result);
    }
}