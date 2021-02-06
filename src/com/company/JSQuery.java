package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.toIntExact;


public class JSQuery {

    public static void objectFiller(JSONObject object,  List<QueryData> list){

        //Here we go through all the elements of the JSON object to fill the list of queries
        String name = (String) object.get("Name");
        Long alarmColorFloat = (Long) object.get("AlarmColor");
        int alarmColor = toIntExact(alarmColorFloat);
        Long idFloat = (Long) object.get("Id");
        int id = toIntExact(idFloat);
        Long datasourceCountFloat = (Long) object.get("DatasourcesCount");
        int datasourceCount = toIntExact(datasourceCountFloat);
        String _alertIcon = (String) object.get("_alertIcon");
        Long elementCountFloat = (Long) object.get("ElementCount");
        int elementCount = toIntExact(elementCountFloat);
        String uniqueId = (String) object.get("UniqueId");

        List<Parameter> parameterList = new ArrayList<>();
        JSONArray params = (JSONArray) object.get("Parameters");

        //Filling all the parameters in a list
        for (Object param :  params ) {

            JSONObject paramObject = (JSONObject) param;
            String key = (String)paramObject.get("Key");
            String value = (String) paramObject.get("Value");
            Parameter jsonParam = new Parameter(key,value );
            parameterList.add(jsonParam);

        }

        QueryData query = new QueryData(name, alarmColor, id, datasourceCount, _alertIcon, elementCount, uniqueId, parameterList);

        list.add(query);
    }

    public static void fileReader (List<QueryData> list,File file){
        //Parsing of the file, since it is a JSON we use the library json.simple
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(file))
        {

            //We parse through the file, which is a JSONArray involving all the objects(queries)
            Object obj = parser.parse(reader);

            JSONArray sqls = (JSONArray) obj;

            for (Object o :  sqls ) {
                //We go through every object and turn them into QueryData Objects that have all the information
                //forr the later creation of the query
                JSONObject object = (JSONObject) o;
                objectFiller(object, list);

            }
            

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public static void fileWritter(String outputFile, List<QueryData> list){


        //This writer will create the query, if there is no output file to write it
        //it will show it in the command prompt
        String tableCreation = "CREATE TABLE Input ( " +
                "`list_Name` VARCHAR(6) CHARACTER SET utf8, " +
                "`list_AlarmColor` INT, " +
                "`list_Id` INT, " +
                "`list_Parameters_Key` VARCHAR(40) CHARACTER SET utf8, " +
                "`list_Parameters_Value` VARCHAR(11) CHARACTER SET utf8, " +
                "`list_DatasourcesCount` INT, " +
                "`list_alertIcon` VARCHAR(14) CHARACTER SET utf8, " +
                "`list_ElementCount` INT, " +
                "`list_UniqueID` VARCHAR(36) CHARACTER SET utf8 " +
                "); \r";

        if("".equalsIgnoreCase(outputFile) || outputFile.isEmpty()){
            System.out.println("Since you didn't input a fileName the queries will appear on the console.");

            System.out.println(tableCreation);

            for (QueryData elem : list ) {

                List<Parameter> aux = elem.getParameterList();
                for (Parameter param : aux) {

                    String values = "INSERT INTO Input Values (";
                    values += elem.getName() + ",";
                    values += elem.getAlarmColor()+ ",";
                    values += elem.getId()+ ",";
                    values += param.getKey()+ ",";
                    values += param.getValue()+ ",";
                    values += elem.getDatasourceCount();
                    values += elem.get_alertIcon();
                    values += elem.getElementCount();
                    values += elem.getUniqueId();
                    values += "); \n";
                    System.out.println(values);
                }

            }



        }else{

            try {
                FileWriter myWriter = new FileWriter(outputFile);
                myWriter.write(tableCreation);
                myWriter.write(System.lineSeparator());

                for (QueryData elem : list ) {

                    List<Parameter> aux = elem.getParameterList();
                    for (Parameter param : aux) {

                        String values = "INSERT INTO Input Values (";
                        values += elem.getName() + ",";
                        values += elem.getAlarmColor()+ ",";
                        values += elem.getId()+ ",";
                        values += param.getKey()+ ",";
                        values += param.getValue()+ ",";
                        values += elem.getDatasourceCount();
                        values += elem.get_alertIcon();
                        values += elem.getElementCount();
                        values += elem.getUniqueId();
                        values += "); \n";
                        myWriter.write(values);
                        myWriter.write(System.lineSeparator());
                    }



                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){

        //Introducing the input and output files as requested
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce Input File name");
        File file = new File(scanner.nextLine());

        if(file.length() == 0){
            System.out.println("File is empty!!!");
        }else{

            List<QueryData> list = new ArrayList<>();

            System.out.println("Please introduce name of the OutputFile : ");
            String outputFileName = scanner.nextLine();

            fileReader(list,file);
            fileWritter(outputFileName, list);
        }


    }


}
