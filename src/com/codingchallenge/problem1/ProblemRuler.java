package com.codingchallenge.problem1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ProblemRuler {

  private static Map<String, Map<String, Integer>> animalCount(Map<String, String> kingdomDetails) {
    Map<String, Map<String, Integer>> animalCountMap = new HashMap<String, Map<String, Integer>>();

    for (Map.Entry<String, String> kingdomDetailsValues : kingdomDetails.entrySet()) {
      String[] findStrArr = kingdomDetailsValues.getValue().split("");
      String msg = kingdomDetailsValues.getValue();
      Map<String, Integer> emblemCount = new HashMap<String, Integer>();
      for (String findStr : findStrArr) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

          lastIndex = msg.indexOf(findStr, lastIndex);

          if (lastIndex != -1) {
            count++;
            lastIndex += findStr.length();
          }
        }
        emblemCount.put(findStr.toLowerCase(), count);
      }
      animalCountMap.put(msg.toLowerCase(), emblemCount);
    }
    return animalCountMap;

  }

  public static void main(String args[]) {
    System.out.println("Send Messages to Kingdom");
    Scanner sc = new Scanner(System.in);
    Scanner scString = new Scanner(System.in);
    Set<String> liMsgs = new HashSet<String>();

    System.out.println("Enter the Number of Messages that you need to Share with other Kingdoms");
    int msgsCount = sc.nextInt();
    if (msgsCount > 2) {
      for (int i = 0; i < msgsCount; i++) {
        System.out.println("Enter The Message");
        String msgInput = scString.nextLine();
        String msgArray[] = msgInput.split(",");
        String kingdom = msgArray[0].toLowerCase();
        String msg = msgArray[1].toLowerCase();
        boolean valueCheck = validatingMsg(msg, kingdom);
        if (valueCheck) {
          liMsgs.add(kingdom);
        }
      }
      System.out.println("Who is the Ruler of Southeros?");
      if (liMsgs.size() > 2) {
        System.out.println("King Shan");
        System.out.println("Allies of the Ruler");
        System.out.println(liMsgs);
      } else {
        System.out.println("None");
        System.out.println("Allies of Ruler?");
        System.out.println("None");
      }
    } else {
      System.out.println("Who is the Ruler of Southeros?");
      System.out.println("None");
      System.out.println("Allies of Ruler?");
      System.out.println("None");
    }
  }

  private static boolean validatingMsg(String msg, String kingdom) {
    Map<String, String> animalDetails = new HashMap<String, String>();

    Map<String, Map<String, Integer>> kingdomAnimalMapping = new HashMap<String, Map<String, Integer>>();
    List<Boolean> liSuccess = new ArrayList<Boolean>();
    List<Boolean> liFailure = new ArrayList<Boolean>();
    Map<String, Integer> checkWord = new HashMap<String, Integer>();


    animalDetails.put("land", "panda");
    animalDetails.put("water", "octopus");
    animalDetails.put("ice", "mammoth");
    animalDetails.put("air", "owl");
    animalDetails.put("fire", "dragon");
    animalDetails.put("space", "gorilla");
    kingdomAnimalMapping = animalCount(animalDetails);

    String kingdomValue = animalDetails.get(kingdom).toLowerCase();
    String[] kingdomValueArray = kingdomValue.split("");

    for (String str : kingdomValueArray) {
      int lastIndex = 0;
      int count = 0;

      while (lastIndex != -1) {

        lastIndex = msg.indexOf(str, lastIndex);

        if (lastIndex != -1) {
          count++;
          lastIndex += str.length();
        }
      }

      checkWord.put(str, count);
    }

    for (Map.Entry<String, Integer> kingdomValues : kingdomAnimalMapping.get(kingdomValue).entrySet()) {
      if (kingdomValues.getValue() <= checkWord.get(kingdomValues.getKey())) {
        liSuccess.add(true);
      } else {
        liFailure.add(false);
      }
    }

    return liFailure.isEmpty() ? true : false;

  }

}
