import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SkierServlet", value = "/SkierServlet")
public class SkierServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        String urlPath = req.getPathInfo();

        // check we have a URL!
        if (urlPath == null || urlPath.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.getWriter().write("missing paramterers");
            return;
        }

        String[] urlParts = urlPath.split("/");
        // and now validate url path and return the response status code
        // (and maybe also some value if input is valid)

        if (!isUrlValid(urlParts)) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            res.setStatus(HttpServletResponse.SC_OK);
            // do any sophisticated processing with urlParts which contains all the url params
            // TODO: process url params in `urlParts`
            String year = urlParts[3];
            String season = urlParts[1];
            String day = urlParts[5];
            String skierID = urlParts[7];
            res.getWriter().write("This is a GET!");
            res.getWriter().write("\nYear: " + year);
            res.getWriter().write("\nSeason: " + season);
            res.getWriter().write("\nDay: " + day);
            res.getWriter().write("\nSkierID: " + skierID);
        }
    }

    private boolean isUrlValid(String[] urlPath) {
        // TODO: validate the request url path according to the API spec
        // urlPath  = "/1/seasons/2019/day/1/skier/123"
        // urlParts = [, 1, seasons, 2019, day, 1, skier, 123]
        if (urlPath.length != 8) return false;
        return true;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        String urlPath = req.getPathInfo();

        // check we have a URL!
        if (urlPath == null || urlPath.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.getWriter().write("missing parameters");
            return;
        }

        String[] urlParts = urlPath.split("/");
        // and now validate url path and return the response status code
        // (and maybe also some value if input is valid)

        if (!isUrlValid(urlParts)) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            res.setStatus(HttpServletResponse.SC_OK);
            // do any sophisticated processing with urlParts which contains all the url params
            // TODO: process url params in `urlParts`
            BufferedReader reader = req.getReader();
            String json_material = ReadBigStringIn(reader);
            HashMap<String, String> skier = ProcessRandomSkierJSON(json_material);

            // print out POST info.
//            res.getWriter().write("\nThis is a POST");
//            for (Map.Entry<String, String> entry : skier.entrySet()) {
//                res.getWriter().write("\n" + entry.getKey() +": " + entry.getValue());
//            }
//            res.getWriter().write("\nJSONfile:" + json_material);
        }
    }

    public String ReadBigStringIn(BufferedReader buffIn) throws IOException {
        StringBuilder everything = new StringBuilder();
        String line;
        while( (line = buffIn.readLine()) != null) {
            everything.append(line);
        }
        return everything.toString();
    }

    public HashMap<String, String> ProcessRandomSkierJSON(String json) {
        HashMap<String, String> skier = new HashMap<>();
        String[] json_divided = json.split(", ");

        //remove { and }
        json_divided[0] = json_divided[0].substring(1);
        int n = json_divided.length;
        json_divided[n-1] = json_divided[n-1].substring(0, json_divided[n-1].length() - 1);


        for (String s : json_divided) {
            String[] s_trimed = s.split(":");
            switch(s_trimed[0]) {
                case "\"skierID\"":
                    skier.put("skierID", s_trimed[1]);
                    break;
                case "\"resortID\"":
                    skier.put("resortID", s_trimed[1]);
                    break;
                case "\"liftID\"":
                    skier.put("liftID", s_trimed[1]);
                    break;
                case "\"seasonID\"":
                    skier.put("seasonID", s_trimed[1]);
                    break;
                case "\"dayID\"":
                    skier.put("dayID", s_trimed[1]);
                    break;
                case "\"time\"":
                    skier.put("time", s_trimed[1]);
                    break;
            }
        }

        return skier;
    }
}


