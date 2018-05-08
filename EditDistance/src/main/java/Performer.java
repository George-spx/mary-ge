/*
 * Copyright (C) 2018 Lorenzo Marietta
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import java.util.ArrayList;

/**
 *
 * @author Lorenzo Marietta
 */
public class Performer extends Thread {

    private final ArrayList<String> textArray;
    private final ArrayList<String> dictionaryArray;
    private final ArrayList<String> correctedText;
    private int start;
    private final int end;

    public Performer(ArrayList a, ArrayList b, int start , int end) {
        textArray = a;
        dictionaryArray = b;
        correctedText=new ArrayList<>();
        this.start= start;
        this.end= end;
    }

    @Override
    public void run() {
        String t;
        for (int i=start ; start<=end; start++) {
            t = textArray.get(start);
            boolean flag = true;
            int lastEdit;
            int tmpLastEdit = -1;
            String corrected=t+" -->(";
            for (int j = 0; flag && j < dictionaryArray.size(); j++) {
              try{
                if ((lastEdit = EditDistance.iterativeStringDistance(t, dictionaryArray.get(j))) <= tmpLastEdit
                        || tmpLastEdit == -1) {
                    if (lastEdit < tmpLastEdit) {
                        corrected= t+" -->(";
                    }
                    tmpLastEdit = lastEdit;
                    corrected=corrected+"-"+dictionaryArray.get(j);
                    if (lastEdit == 0) {
                        flag = false;
                    }
                }
              }catch (EditDistanceException e){
                System.out.println(e);
              }
            }
            corrected= corrected+")";
            correctedText.add(corrected);
        }
    }

    public ArrayList getCorrectedText() {
        return correctedText;
    }
}