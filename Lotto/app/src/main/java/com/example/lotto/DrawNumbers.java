package com.example.lotto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DrawNumbers
{
    static List<Integer> rawDrawNumbersList = new ArrayList<Integer>();
    static Integer[] rawDrawNumbers;
    static int[] diagramData;
    static int[] diagramFrequencies;
    static int diagramDataSize = 2;
    static int [] dData;
    static int [] dFrequencies;
    static int clicks = 0;

    static void createDiagramData()
    {
        diagramDataSize = 0;
        Collections.sort(rawDrawNumbersList, Collections.<Integer>reverseOrder());
        rawDrawNumbers = new Integer[rawDrawNumbersList.size()];
        rawDrawNumbers = rawDrawNumbersList.toArray(rawDrawNumbers);

        //diagramData = new int[rawDrawNumbers.length];
        diagramFrequencies = new int[rawDrawNumbers.length];

        int visited = -1;
        for (int i = 0; i < rawDrawNumbers.length; i++)
        {
            int count = 1;
            for (int j = i + 1; j < rawDrawNumbers.length; j++)
            {
                if (rawDrawNumbers[i] == rawDrawNumbers[j])
                {
                    count++;
                    diagramFrequencies[j] = visited;
                }
            }
            if (diagramFrequencies[i] != visited)
                diagramFrequencies[i] = count;
        }
        dData = new int [diagramFrequencies.length];
        dFrequencies = new int [diagramFrequencies.length];
        diagramDataSize = diagramFrequencies.length;

        for (int i = 0; i < diagramFrequencies.length; i++)
        {
            if (diagramFrequencies[i] != visited)
            {
                dData[i] = rawDrawNumbers[i];
                dFrequencies[i] = diagramFrequencies[i];
            }
        }

    }
}