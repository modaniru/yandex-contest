package com.modaniru.platform.list;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ThirdExcersice {
    private static String NAME_CONTAINS;
    private static int PRICE_GREATER_THAN;
    private static int PRICE_LESS_THAN;
    private static LocalDate DATE_AFTER;
    private static LocalDate DATE_BEFORE;
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);
        String jsonFile = scanner.nextLine();
        NAME_CONTAINS = scanner.nextLine().split(" ")[1];
        PRICE_GREATER_THAN = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        PRICE_LESS_THAN = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        DATE_AFTER = LocalDate.parse(scanner.nextLine().split(" ")[1], FORMATTER);
        DATE_BEFORE = LocalDate.parse(scanner.nextLine().split(" ")[1], FORMATTER);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(jsonFile);
        List<JSONObject> jsonObjects = sort(jsonArray);
        JSONArray js = new JSONArray();
        for(JSONObject o: jsonObjects){
            Map map = new LinkedHashMap();
            map.put("id", o.get("id"));
            map.put("name",  o.get("name"));
            map.put("price",o.get("price"));
            map.put("date", o.get("date"));
            js.add(map);
        }
        System.out.println(js);
    }
    public static List<JSONObject> sort(JSONArray array){
        List<JSONObject> sortedByName = new ArrayList<>();
        for(int i = 0; i < array.size(); i++){
            JSONObject jsonObject = (JSONObject) array.get(i);
            if(((String) jsonObject.get("name")).contains(NAME_CONTAINS)){
                sortedByName.add(jsonObject);
            }
        }
        List<JSONObject> sortedByAll = new ArrayList<>();
        for(int i = 0; i < sortedByName.size(); i++){
            Long price = (Long) sortedByName.get(i).get("price");
            LocalDate date = LocalDate.parse((String)sortedByName.get(i).get("date"), FORMATTER);
            if(price < PRICE_LESS_THAN && price > PRICE_GREATER_THAN){
                sortedByAll.add(sortedByName.get(i));
            }
            if(!(date.isAfter(DATE_AFTER) && !date.equals(DATE_AFTER) || date.isBefore(DATE_BEFORE) && !date.equals(DATE_BEFORE))){
                sortedByAll.add(sortedByName.get(i));
            }
        }
        return sortedByAll;
    }
}
