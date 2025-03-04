package test;

// To test: System.out.println(test("..P.....P...P.O."));
// P: pause, O: obstacle (change the direction), 5: fully open, 0: fully close
public class OpenCloseDoor {
    public static String move(String s) {
        String response = "";

        if (s.isEmpty()) {
            return response;
        }

        //1: opening, 2: closing
        int previousState = 0;

        //0: paused, 1: opening, 2: closing
        int direction = 0;

        //0: closed, 5: open
        int position = 0;

        int contPauses = 0;
        String[] events = s.split("");

        for(String e: events) {
            if (e.equals(".")) {
                position = direction == 1 ? position + 1 : direction != 0 ? position - 1 : position;
            } else if (e.equals("P") && ++contPauses % 2 != 0) {
                direction = direction == 0 && position == 0 ? 1 : direction == 0 && position == 5 ? 2 : direction;

                position = direction != 0 ? direction == 1 ? position + 1 : position - 1 : position;

                previousState = direction;
            } else if (e.equals("O")) {
                contPauses = 0;
                position = direction != 0 ? direction == 1 ? position - 1 : position + 1 : position;

                if (direction == 0 && position > 0 && position < 5) {
                    position = previousState == 1 ? position - 1 : position + 1;
                } else {
                    direction = direction == 1 ? 2 : 1;
                }
            }

            if (direction != 0 && contPauses > 1 && contPauses % 2 == 0) {
                direction = 0;
            }

            if (position >= 5 || position <= 0) {
                direction = 0;
                contPauses = 0;
            }

            response += position;
        }

        return response;
    }
}
