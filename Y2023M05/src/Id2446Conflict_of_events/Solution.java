package Id2446Conflict_of_events;

class Solution {
    public boolean haveConflict(String[] event1, String[] event2) {
        String maxStart;
        if (event1[0].compareTo(event2[0]) < 0) {
            maxStart = event2[0];
        } else {
            maxStart = event1[0];
        }

        String minEnd;
        if (event1[1].compareTo(event2[1]) < 0) {
            minEnd = event1[1];
        } else {
            minEnd = event2[1];
        }

        return !(maxStart.compareTo(minEnd) < 0);
    }
}