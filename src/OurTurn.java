import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

class OurTurns extends Solution {

    //http://hentai-chan.me/
    public static String Turn(String hours, String addINF, Boolean plus, String countST, String everyTime, String albumText) throws IOException, ParseException, InterruptedException {
        int count, numberSexHistory;
        String number, sexHistory = "";
        int photosINT = 0;
        String photos = null;
        if (countST == null)
            count = 1;
        else
            count = Integer.parseInt(countST);

        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Vlad\\Desktop\\numberPhoto.txt"), StandardCharsets.UTF_8);
        for (String line : lines) {
            photos = line.split(" ")[0];
            STnumberSexHistory = line.split(" ")[1];
        }
        System.out.println(photos);
        numberSexHistory = Integer.parseInt(STnumberSexHistory);
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

        photos = photos.split("https://vk\\.com/")[1];
        //System.out.println(outHours);
        int n = 0;
        //System.out.println(photos.split("-")[0]);
        if (!albumText.equals("")) {
                connectForm = "https://api.vk.com/method/wall.post?v=5.52&access_token=" + Solution.token + "&owner_id=-" + owneredIDv1 + "&from_group=1&message=/" + addINF.replaceAll(" ", "%20").replaceAll("#", "%23") + "%0aВышла%20новая%20хентай%20манга%0aв%20ОТКРЫТОМ%20ДОСТУПЕ%20ТОЛЬКО%20У%20НАС" + "&publish_date=" + outHours + "&attachments=" + albumText.split("https://vk.com/")[1] + "";
                allINF = Jsoup
                        .connect(connectForm)
                        .ignoreContentType(true)
                        .get();
        } else {
            photos = photos.split("\\?")[0];
            if (count == 1) {
                photos = photos.split("_")[1];
                photosINT = Integer.parseInt(photos);
                connectForm = "https://api.vk.com/method/wall.post?v=5.52&access_token=" + Solution.token + "&owner_id=-" + owneredIDv1 + "&from_group=1&message=" + addINF.replaceAll(" ", "%20").replaceAll("#", "%23") + "&publish_date=" + outHours + "&attachments=photo85252068_" + photos + "";
                System.out.println(connectForm);

                allINF = Jsoup
                        .connect(connectForm)
                        .ignoreContentType(true)
                        .get();
                photosINT++;
            } else {
                photos = photos.split("_")[1];
                photosINT = Integer.parseInt(photos);
                while (n < count) {
                    connectForm = "https://api.vk.com/method/wall.post?v=5.52&access_token=" + Solution.token + "&owner_id=-" + owneredIDv1 + "&from_group=1&message=" + addINF.replaceAll(" ", "%20").replaceAll("#", "%23") + "&publish_date=" + outHours + "&attachments=photo85252068_" + photosINT + "";
                    System.out.println(connectForm);

                    try {
                        allINF = Jsoup
                                .connect(connectForm)
                                .ignoreContentType(true)
                                .get();

                        // .cookies(login.cookies()) //use this with any page you parse. it will log you in
                        outHours = outHours + longEveryTime * 3600;
                        photosINT++;
                        n++;
                        allINF = null;
                        Thread.sleep(300);
                    } catch (Exception error414) {
                        System.out.println(error414.toString());
                        numberSexHistory++;
                        System.out.println(n);
                    }
                }
            }

            number = "https://vk.com/photo85252068_" + photosINT;
            try (FileWriter writer = new FileWriter("C:\\Users\\Vlad\\Desktop\\numberPhoto.txt", false)) {
                writer.write(number + " " + (numberSexHistory + (n / 4)));
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        //System.out.println(allINF.toString());
        return null;
    }

    public static String downloadVideo(String site) throws IOException, ParseException {

        String fullName = null;
        String connectForm = null;
        Document allINF = Jsoup
                .connect(site)
                //.cookies(login.cookies()) //use this with any page you parse. it will log you in
                .get();


        //System.out.println(allINF.toString());

        String outURL = null;
        String nextCont = allINF.toString().split("<div id=\"intro\"> ")[1].split("<snap class=\"name_anime\">")[0];
        String url[] = (allINF.toString()).split("video\": \"");

        String imgName = allINF.toString().split("<title>")[1].split("</title> ")[0].replaceAll("\\?", "").replaceAll(":", "");
        int number = 1;
        imgName = imgName
                .replaceAll(" ", "_")
                .replaceAll("\\.", "")
                .replaceAll("/", "_");
        System.out.println(imgName);

        File folder = new File("C:\\Users\\Vlad\\Desktop\\atrs for GLEDB\\hentaiVIDEO\\" + imgName);
        folder.mkdir();


        while (number <= url.length - 1) {
            outURL = url[number]
                    .split("\",\n" +
                            "\t\t\"preview")[0];


            System.out.println(url[number]);
            System.out.println(outURL);

            try (InputStream in = new URL(outURL).openStream()) {
                Files.copy(in, Paths.get("C:\\Users\\Vlad\\Desktop\\atrs for GLEDB\\hentaiVIDEO\\" + imgName + "\\" + imgName + "-" + number + "{Хентай}" + ".mp4"));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            number = number + 1;

        }
        return "SDELANO";
    }

//    public static String pushPhoto(String url) throws IOException, ParseException, InterruptedException {
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setDoOutput(true);
//        connection.setRequestMethod("POST");
//
//        FileBody fileBody = new FileBody(new File(fileName));
//        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT);
//        multipartEntity.addPart("file", fileBody);
//
//        connection.setRequestProperty("Content-Type", multipartEntity.getContentType().getValue());
//        OutputStream out = connection.getOutputStream();
//        try {
//            multipartEntity.writeTo(out);
//        } finally {
//            out.close();
//        }
//        int status = connection.getResponseCode();
//    }

    public static void Sort(String folder) throws IOException, ParseException, InterruptedException {
        long len1, len2;
        String name;
        File f = new File(folder);
        File[] folderEn = f.listFiles();
        for (int i = 0; i < folderEn.length; i++) {
            if (!folderEn[i].isDirectory()) {
                len1 = folderEn[i].length();
                if (len1 != 0) {
                    for (int j = i + 1; j < folderEn.length; j++) {
                        len2 = folderEn[j].length();
                        if (len1 == len2) {
                            folderEn[j].delete();
                        }
                    }
                } else {
                    folderEn[i].delete();
                }
            }

        }
    }

    public static void Invite(String groupURL) throws IOException, ParseException, InterruptedException {


    }


    public static String downloadHentai(String syte) throws IOException, ParseException, InterruptedException {
        String[] coloredORuncensored = new String[20];
        String genres = "";

        String connectForm;
        int counterSite = 0, sett;

        sett = 1;
        genres = "";
        Document allINF = Jsoup
                .connect(syte.split("vol")[0] + "vol1/" + (Integer.parseInt((syte.split("vol")[1]).split("/")[1]) + counterSite))
                // .cookies(login.cookies()) //use this with any page you parse. it will log you in
                .get();

        Document CensorDoc = Jsoup
                .connect(syte.split("vol")[0])
                // .cookies(login.cookies()) //use this with any page you parse. it will log you in
                .get();
        //цензура
        coloredORuncensored = (((CensorDoc.toString()).split("Переводчик")[0]).split("<a href=\"/list/genre/"));
        while (sett < coloredORuncensored.length) {
            coloredORuncensored[sett] = (coloredORuncensored[sett].split("\">")[1].split("</a>")[0]).replaceAll(" ", "_");
            System.out.println(coloredORuncensored[sett]);
            sett++;
        }

        sett = 1;
        while (sett < coloredORuncensored.length) {
            if (coloredORuncensored[sett] != null && !coloredORuncensored[sett].equals("") && !coloredORuncensored[sett].equals(null)) {
                if (sett == 1)
                    genres = "(" + genres + "#" + coloredORuncensored[sett];
                else
                    genres = genres + " ,#" + coloredORuncensored[sett];
            }
            sett++;
        }
        if (!genres.equals(""))
            genres = genres + ")";
        System.out.println(genres);

//конец цензуры
        Document createAlb;
        String title;

        String outSyte = allINF.toString();

        System.out.println(outSyte);
        String outAll;

        String folderName;
        folderName = allINF.toString().split("<title>Читать хентай мангу ")[1]
                .split(" онлайн: ")[0]
                .replaceAll(":", "")
                .replaceAll("/", " ")
                .replaceAll("\\?", "");
        //System.out.println(allINF.toString());
        String folderNum[] = allINF.toString().split("</a> Глава")[1].split("</h1>");
        //System.out.println(folderName);
        if (folderName.split("\\(").length >= 2)
            title = folderName.split("\\(")[0] + folderNum[0];
        else
            title = folderName + folderNum[0];
        System.out.println(title);

        File folder = new File("C:\\Users\\Vlad\\Desktop\\atrs for GLEDB\\new\\" + title);
        folder.mkdir();

            connectForm = "https://api.vk.com/method/photos.createAlbum?v=" + Version + "&access_token=" + token + "&title=" + ((genres.replaceAll(" ", "").replaceAll("#", "")).split(",")[0])+ ")" + title.replaceAll(" ", "%20") + "&group_id=" + owneredIDv1 + "&upload_by_admins_only=1";
            System.out.println(connectForm);
            createAlb = Jsoup
                    .connect(connectForm)
                    .ignoreContentType(true)
                    .get();
            System.out.println(createAlb.toString());

            connectForm = "https://api.vk.com/method/wall.post?v=" + Version +
                    "&access_token=" + token +
                    "&owner_id=-" + owneredIDv1 +
                    "&from_group=1" +
                    "&message=" + "https://vk.com/album-155689035_" + (createAlb.toString()).split("response\":\\{\"id\":")[1].split(",\"thumb_id")[0] + "%0a" + title.replaceAll(" ", "%20") + "%0a" + (genres.replaceAll(" ", "%20").replaceAll("#", "%23") + (" %0aНовая хентай манга, читать всем :3 %0aЕще больше хентайчика у нас https://vk.com/hentai_sen_pai %0a#hentai #ecchi #manga")).replaceAll(" ", "%20").replaceAll("#", "%23") +
                    "&attachments=album-155689035_" +  (createAlb.toString()).split("response\":\\{\"id\":")[1].split(",\"thumb_id")[0];
            System.out.println(connectForm);
            allINF = Jsoup
                    .connect(connectForm)
                    .ignoreContentType(true)
                    .get();


            String albumID = createAlb.toString().split("\"id\":")[1].split(",\"thumb")[0] + "";
            System.out.println(albumID);


        String[] link;
        outAll = (outSyte
                .split("var pictures = \\[\\{url:")[1].split("var prevLink")[0]);

        System.out.println(outAll + " this is outAll");
        int number = 1;
        link = outAll.split("http:");

        while (number < link.length) {
            link[number] = "http:" + link[number].split("\",w:")[0].replaceAll("c\\.allhentai", "a.allhentai").replaceAll("b\\.allhentai", "a.allhentai");
            System.out.println(number + "  " + link[number]);

                   try (InputStream in = new URL(link[number].replaceAll("c\\.allhentai","a.allhentai").replaceAll("b\\.allhentai","a.allhentai")).openStream()) {
                       Files.copy(in, Paths.get("C:\\Users\\Vlad\\Desktop\\atrs for GLEDB\\new\\" + title + "\\" + link[number].split("/")[7]));
                   } catch (Exception e2) {
                       System.out.println("Not today");
                       e2.printStackTrace();
                   }
            number++;
        }
        return "Глава скачана";
    }




    public static String downloadPixiv(String syte) throws IOException, ParseException, InterruptedException, URISyntaxException {
        System.setProperty("http.agent", "Chrome");
        //https://www.pixiv.net/stacc/ging1993
        String refILLIST = "0";
        String refSTATUS = "0";
        while (true) {
            Document allINF = Jsoup
                    .connect(syte)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
                    .get();


            String outSyte = allINF.toString();

            System.out.println(outSyte);
            String outIMG;
            String[] outURL;
            String imgURL;


            String[] img = allINF.toString()
                    .split("illust.php\\?mode=medium&amp;illust_id=");
            int number = 1;


            int count = img.length - 1;
            String prevIMG = "0";
            System.out.println(count);

            int I = 100;

            while (number <= count) {
                //System.out.println(img[number]);
                refILLIST = img[number].split("\" class=\" _work ")[0];

                if (!refILLIST.equals(prevIMG)) {

                    imgURL = "https://www.pixiv.net/member_illust.php?mode=medium&illust_id=" + refILLIST;
                    System.out.println(imgURL);
                    prevIMG = refILLIST;
                    Document imgINF = Jsoup
                            .connect(imgURL)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36")
                            .get();

                    outURL = imgINF.toString().split("src=\"https://i.pximg.net/c/600x600");
                    outURL = outURL[1].split("_master1200.jpg\" alt=\"");
                    outIMG = "https://i.pximg.net/c/600x600" + outURL[0] + "_master1200.jpg";
                    System.out.println(outIMG);
                    Desktop.getDesktop().browse(new URI(imgURL));
                    Thread.sleep(2000);
                    Desktop.getDesktop().browse(new URI(outIMG));

                    try {

                        Thread.sleep(1500);
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_CONTROL);
                        robot.keyPress(KeyEvent.VK_S);
                        robot.keyRelease(KeyEvent.VK_CONTROL);
                        robot.keyRelease(KeyEvent.VK_S);

                        Thread.sleep(1000);
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyRelease(KeyEvent.VK_ENTER);

                        Thread.sleep(1000);
                        robot.keyPress(KeyEvent.VK_CONTROL);

                        robot.keyPress(KeyEvent.VK_W);
                        robot.keyRelease(KeyEvent.VK_CONTROL);
                        robot.keyRelease(KeyEvent.VK_W);

                        robot.keyPress(KeyEvent.VK_CONTROL);
                        robot.keyPress(KeyEvent.VK_W);
                        robot.keyRelease(KeyEvent.VK_CONTROL);
                        robot.keyRelease(KeyEvent.VK_W);
                    } catch (AWTException e) {

                        e.printStackTrace();

                    }

                }
                number++;
            }
            return "Работа сделана";
        }

    }

    public static String downloadDanbooru(String syte) throws IOException, InterruptedException {
        int page = Integer.parseInt(syte.split("page=")[1].split("&tags")[0]);
        while (page != 1) {
            String pageArt;
            System.setProperty("http.agent", "Chrome");
            Document allINF = Jsoup
                    .connect(syte.split("page=")[0] + "page=" + page + "&tags=" + syte.split("&tags=")[1])
                    // .cookies(login.cookies()) //use this with any page you parse. it will log you in
                    .get();

            String doc[] = allINF.toString().split("data-large-file-url=\"");
            for (int i = 1; i < doc.length ; i++) {
                pageArt = doc[i].split("\" data-preview-file-url=\"")[0];
                System.out.println(pageArt + "   " + i);

                try (InputStream in = new URL(pageArt).openStream()) {
                    Files.copy(in, Paths.get("C:\\Users\\Vlad\\Desktop\\atrs for GLEDB\\newVID\\" + pageArt.split("us/data/")[1]));
                } catch (NoSuchFileException nofile) {
                    try (InputStream in = new URL(pageArt).openStream()) {
                        Files.copy(in, Paths.get("C:\\Users\\Vlad\\Desktop\\atrs for GLEDB\\newVID\\" + pageArt.split("sample/sample-")[1]));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            page--;
            System.out.println("СТРАНИЦА =========   " + page + "   ========= СТРАНИЦА");
            Thread.sleep(30000);
        }
        return "all done";
    }
}
