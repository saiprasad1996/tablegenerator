/*
 * This Command line app was developed by SAI PRASAD
 *
 * You are free to use and remix the software as long as you abide with following terms
 * MIT License

    Copyright (c) 2017 SAI PRASAD

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */


import java.io.*;
class TableGenerator{
    public static void main(String args[]){

        System.out.flush();
        System.out.println(credits());
       try{
        if(args[0].equalsIgnoreCase("-o")){
            
            if(args[1]==null){
                System.out.println("Invalid option used.. ");
            System.out.println("Please provide any one of the arguments below");
            System.out.println("\n -o <filename> -> Save the table to a <filename> file\n -d -> Display the html table on to the console only");

            }else
            printTableToConsole(true,args[1]);
        }
        else if(args[0].equalsIgnoreCase("-d")){
            System.out.println("Output the Table on to the console");
            printTableToConsole(false,"");
        }else {
            System.out.println("Invalid option used.. ");
            System.out.println("Please provide any one of the arguments below");
            System.out.println("\n -o <filename> -> Save the table to a <filename> file\n -d -> Display the html table on to the console only");

        }

    }catch(ArrayIndexOutOfBoundsException boundException){
        System.out.println("Please provide any one of the arguments below");
        System.out.println("\n -o <filename> -> Save the table to a <filename> file\n -d -> Display the html table on to the console only");

    }
}
    public static String credits(){
        String credit="\n\n\tThis Table Generator CommandLine App is developed by SAI PRASAD \n\tLicensed Under MIT License"+
                        "\n\tFeel free to provide your feeback @ saiprasad1996@hotmail.com\n";
                        return credit;
    }
    public static void printTableToConsole(boolean write,String filename){

        boolean next=true;
        int numOfColumns=0;
        StringBuilder builder=new StringBuilder();

        try{
        BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
        System.out.println();
        System.out.println("Enter the number of Columns : ");
        numOfColumns=Integer.parseInt(br.readLine());
        String []columns=new String[numOfColumns];
        System.out.println("Enter the names of Columns : ");
        for (int i=0;i<numOfColumns;i++){
            System.out.println("Column["+(i+1)+"] : ");
            columns[i]=br.readLine();
        }
        
        System.out.println("These are the information you've just entered : ");
        System.out.print("Number of Columns : "+numOfColumns+"\nColumn Names : ");
        for(int k=0;k<numOfColumns;k++){
            System.out.print(columns[k]+"   ");
        }
        System.out.println("\n");
        //Generating Head of the Table
        builder.append("<table border=\"2px\">\n");
        builder.append("\t<thead>\n");
        for(int i=0;i<numOfColumns;i++){
          builder.append("\t\t<th>"+columns[i]+"</th>\n");
      }
      builder.append("\t</thead>\n");
      //End of Head generation
      //Body generation
        builder.append("\t<tbody>\n");
        int rowCounter=1;
        do{
            System.out.println("Enter data for row - "+rowCounter);
            builder.append("\t\t<tr>\n");
            for(int col=0;col<numOfColumns;col++){
              System.out.println(columns[col]+" : ");
              String data=br.readLine();
              builder.append("\t\t\t<td>"+data+"</td>\n");
            }
            builder.append("\t\t</tr>\n");
            System.out.println("\nDo you want to enter another Row to the table : (y/n)");
            String choice=(br.readLine());
            switch(choice){
              case "y" :
              next=true;
              break;
              case "Y":
              next=true;
              break;
              default:
              next=false;
              break;
            }
            rowCounter++;
        }while(next);
        builder.append("\t</tbody>\n");
        builder.append("</table>");
        System.out.println("\nYour table structure is : \n");
        System.out.println(builder.toString());
        if(write){
            writeToFile(builder.toString(),filename+".htm");
            
        }
        System.out.println("Thank you for using TableGenerator :-)");
    }catch(NumberFormatException nfe){
        System.out.println("Oops I was expecting a Number but you might have entered a string");
    }catch(IOException ioe){
        System.out.println("Oops! Something went wrong.. \n"+ioe.toString());
    }
    }

    public static void writeToFile(String table,String filename){
       

        try{
            FileWriter fw=new FileWriter(filename);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(table);
            bw.close();
            fw.close();
            System.out.println("File successfully written to "+filename);
        }catch(IOException ioe){
            System.out.println("Error writing to file.. Try running with administrative previliages");
        }
        
    }
}
