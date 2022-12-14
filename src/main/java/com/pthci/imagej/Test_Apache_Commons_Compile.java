/*
 * To the extent possible under law, the developer has waived
 * all copyright and related or neighbouring rights to this tutorial code.
 *
 * See the CC0 1.0 Universal license for details:
 *     http://creativecommons.org/publicdomain/zero/1.0/
 */

package com.pthci.imagej;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.plugin.*;

import ij.io.OpenDialog;
import ij.io.Opener;
import ij.io.FileSaver;

import java.io.*;
import org.apache.commons.io.*;



/**
 * Simple example to text Maven build with external Apache Commons Library
 *
 * @author Johannes Schindelin
 */
public class Test_Apache_Commons_Compile implements PlugIn {

	@Override
	public void run(String arg) {
		OpenDialog od = new OpenDialog( "Open test file", null);
		String inputDatafilePath = od.getPath();
		if ( inputDatafilePath == null ) {
			//IJ.log("File open dialog box was cancelled");
			return;
		}
		//
		String inputDatafileName      = FilenameUtils.removeExtension( od.getFileName() );
		String inputDatafileDirectory = od.getDirectory();
		String resultsFilesDirectory  = inputDatafileDirectory + inputDatafileName + " Results\\" ;
		IJ.log("inputDatafileName      = " + inputDatafileName      );		
		IJ.log("inputDatafileDirectory = " + inputDatafileDirectory );
		IJ.log("inputDatafilePath      = " + inputDatafilePath      );		
		IJ.log("resultsFilesDirectory  = " + resultsFilesDirectory  );
		
		File directory = new File( resultsFilesDirectory );
		if( !directory.exists() ) directory.mkdir();

		Opener oi = new Opener();
		ImagePlus image = oi.openImage( inputDatafilePath );
		image.show();
		FileSaver fs = new FileSaver( image );
		fs.saveAsTiff( resultsFilesDirectory + inputDatafileName + " OUT.tif" );
	}  //end @Override public void run(String)
	//--------------------------------------------------------------------------------------------


	/**
	 * Main method for debugging.
	 * @param args unused
	 */
	public static void main(String[] args) throws Exception {
		// set the plugins.dir property to make the plugin appear in the Plugins menu
		// see: https://stackoverflow.com/a/7060464/1207769
		Class<?> clazz = Test_Apache_Commons_Compile.class;
		new ImageJ();
		IJ.runPlugIn(clazz.getName(), "");
	}  //end public static void main(String[] args)
	//--------------------------------------------------------------------------------------------


}  //end public class Test_Apache_Commons_Compile
//========================================================================================
//                         end public class Test_Apache_Commons_Compile
//========================================================================================
