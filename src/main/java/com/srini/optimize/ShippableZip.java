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
		// Remove Duplicates
		HashSet<Zipcode> dedupZipcode = new HashSet<Zipcode>();
		dedupZipcode.addAll(arrZipcodeObj);
		// Sort
		ArrayList<Zipcode> tempZipcodeArr = new ArrayList<Zipcode>();
		tempZipcodeArr.addAll(dedupZipcode);
		Collections.sort(tempZipcodeArr, new ZipcodeCompByLower());
		Collections.sort(tempZipcodeArr, new ZipcodeComp());
		
		System.out.println("\nSorted Output:" + "( Size=" + tempZipcodeArr.size() + ")");
		for (Zipcode z : tempZipcodeArr) {
			System.out.println(z.lowerbound + "|" + z.upperbound);
		}
			
	
		//Clean up 
		int i2 = 0;
		int j2 = 0;
		int upper2 = 0;
		int lower2 = 0;
	
		int len2 = tempZipcodeArr.size();
		ArrayList<Zipcode> arrZipcode2 = new ArrayList<Zipcode>();
		for ( i2 = 0; i2 < len2; i2++ ) {
			Zipcode firstZipcode2 = tempZipcodeArr.get(i2);
			upper2 = firstZipcode2.getUpperbound();
			lower2 = firstZipcode2.getLowerbound();
			
			for ( j2 = i2+1; j2 < len2; j2++) {
				Zipcode nextZipcode2 = tempZipcodeArr.get(j2);
				
				if ( ( nextZipcode2.getLowerbound() >= lower2) && (nextZipcode2.getLowerbound() <= upper2) ) {
					if ( nextZipcode2.getUpperbound() > upper2) {
						upper2 = nextZipcode2.upperbound;
						
					}
					i2 =j2;
				} else {
				  break;
				}
				
			}
			Zipcode zipcode2 = new Zipcode();
			zipcode2.setKey(arrZipcode2.size()+1);
			zipcode2.setLowerbound(lower2);
			zipcode2.setUpperbound(upper2);
			arrZipcode2.add(zipcode2);
		}
		System.out.println("\nFirst Cleanup:" + "( Size=" + arrZipcode2.size() +")");
		for (Zipcode f : arrZipcode2) {
			System.out.println(f.getLowerbound() + "|" + f.getUpperbound());

		}
		
		//Join consecutive rows
		int i3 = 0;
		int j3 = 0;
		int lower3 = 0;
		int upper3 = 0;
		int len3 = arrZipcode2.size();
		ArrayList<Zipcode> arrZipcode3 = new ArrayList<Zipcode>();
		for ( i3 = 0; i3 < len3 ; i3++) {
			
			Zipcode firstZipcode3 = arrZipcode2.get(i3);
			lower3 = firstZipcode3.getLowerbound();
			upper3 = firstZipcode3.getUpperbound();
			
			for ( j3 = i3+1 ; j3 < len3 ; j3++ ) {
				Zipcode nextZipcode3 = arrZipcode2.get(j3);
				if ( nextZipcode3.getLowerbound() == upper3+1 || nextZipcode3.getLowerbound() == upper3 ) {
					upper3 = nextZipcode3.getUpperbound();
					i3 = j3;
				} else {
					break;
				}
				
			}
			Zipcode zipcode3 = new Zipcode();
			zipcode3.setKey(arrZipcode3.size()+1);
			zipcode3.setLowerbound(lower3);
			zipcode3.setUpperbound(upper3);
			arrZipcode3.add(zipcode3);
		}
		
		
		System.out.println("\nOptimized Entries:" + "( Size=" + arrZipcode3.size() + ")");
		for (Zipcode f : arrZipcode3) {
			System.out.println(f.getLowerbound() + "|" + f.getUpperbound());

		}
		
		
	}

}
