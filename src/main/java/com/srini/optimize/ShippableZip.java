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
		int len2 = tempZipcodeArr.size();
		ArrayList<Zipcode> arrZipcode2 = new ArrayList<Zipcode>();
		for ( i2 = 0; i2 < len2; i2++ ) {
			Zipcode firstZipcode2 = tempZipcodeArr.get(i2);
			//breaking here....
			upper2 = firstZipcode2.getUpperbound();
			lower2 = firstZipcode2.getLowerbound();
			for ( j2 = i2+1; j2 < len2; j2++) {
				Zipcode nextZipcode2 = tempZipcodeArr.get(j2);
				if (( nextZipcode2.getLowerbound() >= lower2) && (nextZipcode2.getLowerbound() < upper2) ) {
					if ( nextZipcode2.getUpperbound() > upper2) {
						upper2 = nextZipcode2.upperbound;
						i2 =j2;
					}
				} else {
					break;
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
