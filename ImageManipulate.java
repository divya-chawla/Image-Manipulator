/* 
 * THIS METHOD DEALS WITH IMAGE MANIPULATION(REFLECT ACROSS X-ACROSS, REFLECT ACROSS Y-AXIS, INVERT IMAGE,SCALE IMAGE OR ROTATE AN IMAGE) OR OUTPUT 
   TRANSFORMATION (ASCII-ART VERSION OF IMAGE) INTO A TEXT FILE.
   THE PROGRAM WILL ALSO USE COMMAND-LINE INTERFACE TO PASS IN THE NAME OF THE INPUT FILE, OUTPUT FILE AND OPERATION FOR IMAGE MANIPULATION.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageManipulate {
	/**
	 * The main method uses the args array to determine and call
	 * the correct image manipulation method. Your program should be run
	 * the following way: java ImageManipulate <inputFile> <outputFile> <operation>.
	 * Index 0 of the args array contains the name of the input file, index 1 
	 * the name of the output file, and index 2 the name of the operation.
	 * Possible operation names: invert, scale, mirrorVertical, mirrorHorizontal, 
	 * and rotate.
	 *
	 * @param args The array of Strings 
	 */
	public static void main(String[] args) {
	//to check if sufficient arguments are provided in the command prompt
		if(args.length < 3){
		    System.out.println("sorry not enough arguments...");
		    System.out.println("java ImageManipulate <inputFile> <outputFile> <operation>");
        }
	    else{
			String inputImageName =  args[0];
            String fileToCreate   =  args[1];
            String methodToCall   =  args[2];			
     		// to get the 2-d array of the image passed as a parameter
			int image[][] = readGrayscaleImage(inputImageName);
			if(methodToCall.equals("normalMirror")){
				int[][] newImage = normalMirror(image);
				writeGrayscaleImage(fileToCreate,newImage);
			}
			else if(methodToCall.equals("floorMirror")){
				int[][] newImage = floorMirror(image);
				writeGrayscaleImage(fileToCreate,newImage);
			}
			else if(methodToCall.equals("invert")){
				int[][]newImage = invert(image);
				writeGrayscaleImage(fileToCreate,newImage);
			}
			else if(methodToCall.equals("makeAscii")){
				try{
			        makeAscii(image,fileToCreate);
				}catch (FileNotFoundException ex){
	       			System.out.println("File not Found.......Try again");
				}
			}
			else if(methodToCall.equals("rotate")){
				int[][] newImage = rotate(image);
				writeGrayscaleImage(fileToCreate,newImage);
			}
			else if(methodToCall.equals("scale")){
				double num = Double.parseDouble(args[3]);
				int[][] newImage = scale(image,num);
				writeGrayscaleImage(fileToCreate,newImage);
			}
			else{
				System.out.println("Sorry wrong operation!!");
			}
	    }
	}
 
    public static int[][] normalMirror(int[][] arr){
		//creating new array with same dimensions as the passed array
	    int[][]newArr = new int [arr.length][arr[0].length];
	    for(int i = 0 ; i < arr.length ; i++){
		    for(int j = 0 ; j < arr[i].length ; j++){
				//to flip all of the values across each row 
			    newArr[i][j] = arr[i][arr[i].length-1-j];
		    }
    	}
		//to return the new reversed array around y-axis
	    return newArr;
    }
	
	public static int[][] floorMirror(int[][] arr){
		//creating a new array with same dimensions as the passed array
	    int[][]newArr = new int [arr.length][arr[0].length];
	    for(int i = 0 ; i < arr.length ; i++){
		    for(int j = 0 ; j < arr[i].length ; j++){
				//to flip all of the values down each column 
		      	newArr[i][j] = arr[arr.length-1-i][j];
		    }
	    }
		//to return the new reversed array around x-axis
	    return newArr;	
    }
	
    public static int[][] invert(int[][] arr){
		//creating a new array with same dimensions as the passed array
	    int[][]newArr = new int[arr.length][arr[0].length];
	    for(int i = 0 ; i < arr.length ; i++ ){
		    for(int j = 0 ; j < arr[i].length ; j++){
				//inverting each value in a 2-d array
		        newArr[i][j] = 255 - arr[i][j];
		    }
	    }
		//returns a new 2d array with inverted values
	    return newArr;
    }
	
    public static void makeAscii(int[][] arr, String outName)throws FileNotFoundException{
        PrintStream output = new PrintStream(new File("outName"));
        //going through each element in a 2d array and printing the equivalent ASCII character of the value into a text file
	    for(int i = 0 ; i < arr.length ; i++){
		    for(int j = 0 ; j<arr[i].length ; j++){
			    if(arr[i][j] >= 0 && arr[i][j] <= 20){
				    output.print("M");
			    }
			    if(arr[i][j] >= 21 && arr[i][j] <= 40){
				    output.print("L");
			    }
			    if(arr[i][j] >= 41 && arr[i][j] <= 60){
				    output.print("I");
			    }
			    if(arr[i][j] >= 61 && arr[i][j] <= 80){
				    output.print("o");
			    }
			    if(arr[i][j] >= 81 && arr[i][j] <= 100){
				    output.print("|");
			    } 
			    if(arr[i][j] >= 101 && arr[i][j] <= 120){
				    output.print("=");
			    }
			    if(arr[i][j] >= 121 && arr[i][j] <= 140){
				    output.print("*");
			    }
			    if(arr[i][j] >= 141 && arr[i][j] <= 160){
				    output.print(":");
			    }
			    if(arr[i][j] >= 161 && arr[i][j] <= 180){
				    output.print("-");
			    }
			    if(arr[i][j] >= 181 && arr[i][j] <= 200){
				    output.print(",");
			    }
			    if(arr[i][j] >= 201 && arr[i][j] <= 220){
				    output.print(".");
			    }
			    if(arr[i][j] >= 221 && arr[i][j] <= 255){
				    output.print(" ");
			    }
		    }
		    output.println();
	    }
    } 
	
    public static int[][] rotate(int[][] arr){
        //creating a new 2d array with original arry's height as new array's width and vice-versa
		int[][]newArr = new int [arr[0].length][arr.length];
		for(int i = 0 ; i < arr[0].length ; i++){
			for(int j = 0 ; j < arr.length ; j++){
				//rotating the values
			    newArr[i][j] = arr[arr.length-j-1][i];
			}
		}
		//returning the rotated array
		return newArr;
	}	

	public static int[][] scale(int[][] arr, double percentage ){
		//creating a new 2d array by multiplying the dimensions of passed array with the scale value
		int[][] newArr = new int[(int)(percentage * arr.length)][(int)(percentage * arr[0].length)];
		// resizing the 2d array according to the scale value provided by the user
		for(int i = 0 ; i < (int)(percentage * arr.length) ; i++){
			for(int j = 0 ; j < (int)(percentage * arr[0].length) ; j++ ){
				newArr[i][j] = arr[(int)(i/percentage)][(int)(j/percentage)];
			}
		}
		//returning the resized array
		return newArr;
	}
	
	
    /** 
     * This method reads an image file and returns a 2D array
	 * of integers. Each value in the array is a representation
	 * of the corresponding pixel's grayscale value.
	 *
	 * @param filename The name of the image file
	 * @return A 2D array of integers.
	 */
    public static int[][] readGrayscaleImage(String filename) {
        int[][] result = null;
        try {
            File imageFile = new File(filename);
            BufferedImage image = ImageIO.read(imageFile);
            int height = image.getHeight();
            int width  = image.getWidth();
            result = new int[height][width];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = image.getRGB(x, y);
                    result[y][x] = rgb & 0xff;
                }
            }
        }
        catch (IOException ioe) {
            System.err.println("Problems reading file named " + filename);
            System.err.println("Please ensure the image file is saved in the same directory as your java file.");
            System.exit(1);
        }
        return result;
    }


    /** 
     * This method reads a 2D array of integers and creates
	 * a grayscale image. Each pixel's grayscale value is
	 * based on the corresponding value in the 2D array.
	 *
	 * @param filename The name of the image file to create
	 * @param array The 2D array of integers
	 */
    public static void writeGrayscaleImage(String filename, int[][] array) {
        int width = array[0].length;
        int height = array.length;

      	try {
            BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
					int rgb = array[y][x];
					rgb |= rgb << 8;
					rgb |= rgb << 16;
					image.setRGB(x, y, rgb);
                }
            }
            File imageFile = new File(filename);
            ImageIO.write(image, "jpg", imageFile);
        }
        catch (IOException ioe) {
            System.err.println("Problems writing file named " + filename);
            System.exit(1);
        }
    }
}






























