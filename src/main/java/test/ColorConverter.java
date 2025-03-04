package test;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// To test:
//System.out.println(toRgbColor("#1525ad"));
//System.out.println(toHexColor("rgb(0,0,0)"));
public class ColorConverter {
    private static String toHexColor(String s) {
        StringBuilder resp = new StringBuilder("#");

        Pattern c = Pattern.compile("rgb *\\( *[0-9]+, *[0-9]+, *[0-9]+\\)");

        if (c.matcher(s.toLowerCase()).matches()) {
            String[] vs = s.split(",");

            int r = Integer.parseInt(vs[0].split("\\(")[1].trim());
            int g = Integer.parseInt(vs[1].trim());
            int b = Integer.parseInt(vs[2].split("\\)")[0].trim());

            if (r > 255 || g > 255 || b > 255) {
                return "Range not allowed";
            }

            String rh = Integer.toHexString(r).length() == 2? Integer.toHexString(r) : "0" + Integer.toHexString(r);
            String gh = Integer.toHexString(g).length() == 2? Integer.toHexString(g) : "0" + Integer.toHexString(g);
            String bh = Integer.toHexString(b).length() == 2? Integer.toHexString(b) : "0" + Integer.toHexString(b);

            resp.append(rh).append(gh).append(bh);

            System.out.println(r + "," + g + "," + b);
        } else {
            return "Bad Format";
        }

        return resp.toString().toUpperCase();
    }

    private static String toRgbColor(String s) {
        if (s.length() != 7) {
            return "Bad Format";
        }

        StringBuilder resp = new StringBuilder("rgb(");

        Pattern c = Pattern.compile("#[0-9a-fA-F]+");

        if (c.matcher(s.toLowerCase()).matches()) {
            final String s2 = s.substring(1);
            List<String> ls = IntStream.range(0, (s2.length() - 1) / 2 + 1).mapToObj(i -> s2.substring(i * 2, i * 2 + 2)).collect(Collectors.toList());

            int r = Integer.decode("0x" + ls.get(0));
            int g = Integer.decode("0x" + ls.get(1));
            int b = Integer.decode("0x" + ls.get(2));

            resp.append(r).append(",").append(g).append(",").append(b). append(")");

            System.out.println(ls);
        } else {
            return "Bad Format";
        }

        return resp.toString().toUpperCase();
    }
}
