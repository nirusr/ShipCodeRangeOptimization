package com.srini.optimize;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;


import junit.framework.TestCase;

public class NonShippableZipTest extends TestCase {
	File fileName;
	@Test
	public void testNonShippableZip() {
		try {
			Path path = Paths.get(NonShippableZip.class.getResource("/zipcode.txt").toURI());
			fileName = path.toFile();
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] args = {fileName.toString()};
		NonShippableZip.main(args);
	
		Assert.assertEquals(5, NonShippableZip.getResults().size());
	}
	
}
