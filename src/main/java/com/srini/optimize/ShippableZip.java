package com.srini.optimize;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class ShippableZip extends Zipcode {

	public static void main(String[] args) {


		String line;
		int keyval = 0;
		ArrayList<Zipcode> arrZipcodeObj = new ArrayList<Zipcode>();
		try {
			Path path = Paths.get(ShippableZip.class.getResource("/zipcode.txt").toURI());
			BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				String[] split = line.split(",");

				Zipcode objZipcode = new Zipcode();
				objZipcode.key = keyval++;
				objZipcode.lowerbound = Integer.parseInt(split[0]);
				objZipcode.upperbound = Integer.parseInt(split[1]);
				arrZipcodeObj.add(objZipcode);

			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(arrZipcodeObj.get(0).lowerbound);

		// 2. Sort and Remove duplicates
		System.out.println("\n");
		// Remove Duplicates
		HashSet<Zipcode> dedupZipcode = new HashSet<Zipcode>();
		dedupZipcode.addAll(arrZipcodeObj);
		// Sort
		ArrayList<Zipcode> tempZipcodeArr = new ArrayList<Zipcode>();
		tempZipcodeArr.addAll(dedupZipcode);
		Collections.sort(tempZipcodeArr, new ZipcodeCompByLower());
		Collections.sort(tempZipcodeArr, new ZipcodeComp());
		for (Zipcode z : tempZipcodeArr) {
			System.out.println(z.lowerbound + "|" + z.upperbound);
		}
		
		// 3. check lowbound matching
		int upper1 = 0;
		int lower1 = 0;
		// System.out.println(tempZipcodeArr.size());
		int len1 = tempZipcodeArr.size();
		int i1, j1 = 0;
		ArrayList<Zipcode> arrZipcode1 = new ArrayList<Zipcode>();
		outerloop: 
		for (i1 = 0; i1 < len1; i1++) {
			Zipcode firstZipcode1 = tempZipcodeArr.get(i1);
			lower1 = firstZipcode1.getLowerbound();
			upper1 = firstZipcode1.getUpperbound();
			// System.out.print(i + "=>");
			// System.out.println(upper);

			innerloop: 
			for (j1 = i1 + 1; j1 < len1; j1++) {
				// System.out.print(j +",");
				Zipcode nextZipcode1 = tempZipcodeArr.get(j1);
				
					
				if (firstZipcode1.lowerbound == nextZipcode1.lowerbound) {
					// System.out.println( nextZipcode.getUpperbound());
					upper1 = nextZipcode1.getUpperbound();
					i1 = j1;
				}
				

			}

			// System.out.println("Upper:" + upper);
			Zipcode zipcode1 = new Zipcode();
			zipcode1.setKey(0);
			zipcode1.setLowerbound(lower1);
			zipcode1.setUpperbound(upper1);
			arrZipcode1.add(zipcode1);
		}
		System.out.println("\n");
		for (Zipcode f : arrZipcode1) {
			System.out.println(f.getLowerbound() + "|" + f.getUpperbound());

		}
		
		/*
		1. Take a range
		2. Search for lower bound + 1
		3. If found, set upper bound
		*/
		
		int i2 = 0;
		int j2 = 0;
		int upper2 = 0;
		int lower2 = 0;
		int checkLower = 0;
		int len2 = arrZipcode1.size();
		ArrayList<Zipcode> arrZipcode2 = new ArrayList<Zipcode>();
		for ( i2 = 0; i2 < len2; i2++ ) {
			Zipcode firstZipcode2 = arrZipcode1.get(i2);
			upper2 = firstZipcode2.getUpperbound();
			lower2 = firstZipcode2.getLowerbound();
			
			for ( j2 = i2+1; j2 < len2; j2++) {
				Zipcode nextZipcode2 = arrZipcode1.get(j2);
				if (( nextZipcode2.getLowerbound() > firstZipcode2.getLowerbound()) && (nextZipcode2.getLowerbound() < firstZipcode2.getUpperbound()) ) {
					
					if ( nextZipcode2.getUpperbound() > firstZipcode2.getUpperbound()) {
						upper2 = nextZipcode2.upperbound;
						
					}
					i2 =j2;
				}
				
			}
			Zipcode zipcode2 = new Zipcode();
			zipcode2.setKey(0);
			zipcode2.setLowerbound(lower2);
			zipcode2.setUpperbound(upper2);
			arrZipcode2.add(zipcode2);
			
			
		}
		System.out.println("\n");
		for (Zipcode f : arrZipcode2) {
			System.out.println(f.getLowerbound() + "|" + f.getUpperbound());

		}
		
		
	}

}
