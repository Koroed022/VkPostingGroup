import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class OurTurns extends Solution {

    //http://hentai-chan.me/
    public static String Turn(String hours, String addINF, Boolean plus, String countST, String everyTime,String albumText) throws IOException, ParseException, InterruptedException {
        int count;
        String number;
        int photosINT = 0;
        String photos = null;
        if (countST == null)
            count = 1;
        else
            count = Integer.parseInt(countST);

        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Vlad\\Desktop\\numberPhoto.txt"), StandardCharsets.UTF_8);
        for(String line: lines){
            photos = line;
        }
        System.out.println(photos);
        Document allINF;
        //  Connection.Response login = Jsoup
        //          .connect("http://hentai-chan.me/")
        //          .data("login_name","Koroed022")
        //          .data("login_password","772e85888a5")
        //          .method(Connection.Method.POST)
        //          .execute();
        long plusH = 0;
        if (plus == true)
            plusH = 1800;
        long longEveryTime;
        long longHours;
        long outHours;
        String connectForm = null;
        longEveryTime = Long.parseLong(everyTime);
        longHours = Long.parseLong(hours);
        //System.out.println(longHours);
        outHours = System.currentTimeMillis() / 1000 + longHours * 3600 + plusH;//7200 - 2hour

        photos = photos.split("https://vk.com/")[1];
        //System.out.println(outHours);
        int n = 0;
        //System.out.println(photos.split("-")[0]);
        if (!albumText.equals("")){
            connectForm = "https://api.vk.com/method/wall.post?v=5.52&access_token=" + Solution.token + "&owner_id=-" + owneredIDv1 + "&from_group=1&message=/" + addINF.replaceAll(" ","%20").replaceAll("#","%23")+"%0aВышла%20новая%20хентай%20манга%0aв%20ОТКРЫТОМ%20ДОСТУПЕ%20ТОЛЬКО%20У%20НАС" + "&publish_date=" + outHours + "&attachments=" + albumText.split("https://vk.com/")[1] + "";
            allINF =  Jsoup
                    .connect(connectForm)
                    .ignoreContentType(true)
                    .get();
        }
        else{
            photos = photos.split("\\?")[0];
        if (count == 1 ) {
            photos = photos.split("_")[1];
            photosINT = Integer.parseInt(photos);
            connectForm = "https://api.vk.com/method/wall.post?v=5.52&access_token=" + Solution.token + "&owner_id=-" + owneredIDv1 + "&from_group=1&message="+ addINF.replaceAll(" ","%20").replaceAll("#","%23") + "&publish_date=" + outHours + "&attachments=photo85252068_" + photos + "";
            System.out.println(connectForm);

            allINF =  Jsoup
                    .connect(connectForm)
                    .ignoreContentType(true)
                    .get();
            photosINT++;
        } else {
            photos = photos.split("_")[1];
            photosINT = Integer.parseInt(photos);
            while (n < count) {
                connectForm = "https://api.vk.com/method/wall.post?v=5.52&access_token=" + Solution.token + "&owner_id=-" + owneredIDv1 + "&from_group=1&message="+ addINF.replaceAll(" ","%20").replaceAll("#","%23") + "&publish_date=" + outHours + "&attachments=photo85252068_" + photosINT + "";
                System.out.println(connectForm);

                allINF = Jsoup
                        .connect(connectForm)
                        .ignoreContentType(true)
                        .get();


                // .cookies(login.cookies()) //use this with any page you parse. it will log you in
                outHours = outHours + longEveryTime * 3600;
                photosINT++;
                n++;
                allINF = null;
                //Thread.sleep(3000);
            }
        }

        number = "https://vk.com/photo85252068_" + photosINT;
        try(FileWriter writer = new FileWriter("C:\\Users\\Vlad\\Desktop\\numberPhoto.txt", false))
        {
            writer.write(number);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        }
        //System.out.println(allINF.toString());
        return null;
    }
}
