package com.example.instagram;

import android.os.Build;

import com.example.instagram.model.FeedModel;
import com.example.instagram.model.PhotoModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedRepository {
    private static List<FeedModel> feedList;

    private static int postCount = 1;


    public static void addFeed(FeedModel feed) {
        if (feedList == null) {
            feedList = new ArrayList<>();
        }
        feedList.add(0, feed); // Tambahkan di posisi paling atas
    }

    public static void setFeedList(List<FeedModel> list) {
        if (list == null) {
            feedList = new ArrayList<>();
        } else {
            feedList = list;
        }
    }

    public static int getPostCount(){
        return postCount++;
    }

    public static List<FeedModel> getFeedList() {
        if (feedList == null) {
            feedList = new ArrayList<>();

            List<PhotoModel> leclerc1 = Arrays.asList(
                    new PhotoModel(R.drawable.charlesleclerc1),
                    new PhotoModel(R.drawable.charlesleclerc2),
                    new PhotoModel(R.drawable.charlesleclerc3),
                    new PhotoModel(R.drawable.charlesleclerc4),
                    new PhotoModel(R.drawable.charlesleclerc5)
            );

            List<PhotoModel> leclerc2 = Arrays.asList(
                    new PhotoModel(R.drawable.charlesleclerc6),
                    new PhotoModel(R.drawable.charlesleclerc7),
                    new PhotoModel(R.drawable.charlesleclerc8),
                    new PhotoModel(R.drawable.charlesleclerc9),
                    new PhotoModel(R.drawable.charlesleclerc10),
                    new PhotoModel(R.drawable.charlesleclerc11),
                    new PhotoModel(R.drawable.charlesleclerc12)
            );

            List<PhotoModel> alonso = Arrays.asList(
                    new PhotoModel(R.drawable.fernandoalonso1),
                    new PhotoModel(R.drawable.fernandoalonso2),
                    new PhotoModel(R.drawable.fernandoalonso3),
                    new PhotoModel(R.drawable.fernandoalonso4),
                    new PhotoModel(R.drawable.fernandoalonso5),
                    new PhotoModel(R.drawable.fernandoalonso6)
            );

            List<PhotoModel> lando = List.of(
                    new PhotoModel(R.drawable.landonorris1),
                    new PhotoModel(R.drawable.landonorris2),
                    new PhotoModel(R.drawable.landonorris3),
                    new PhotoModel(R.drawable.landonorris4),
                    new PhotoModel(R.drawable.landonorris5)
            );

            List<PhotoModel> lewis1 = Arrays.asList(
                    new PhotoModel(R.drawable.lewishamilton1)
            );

            List<PhotoModel> lewis2 = Arrays.asList(
                    new PhotoModel(R.drawable.lewishamilton2),
                    new PhotoModel(R.drawable.lewishamilton3),
                    new PhotoModel(R.drawable.lewishamilton4)
            );

            List<PhotoModel> lewis3 = Arrays.asList(
                    new PhotoModel(R.drawable.lewishamilton5),
                    new PhotoModel(R.drawable.lewishamilton6),
                    new PhotoModel(R.drawable.lewishamilton7)
            );

            List<PhotoModel> max = Arrays.asList(
                    new PhotoModel(R.drawable.maxverstappen1),
                    new PhotoModel(R.drawable.maxverstappen2),
                    new PhotoModel(R.drawable.maxverstappen3),
                    new PhotoModel(R.drawable.maxverstappen4),
                    new PhotoModel(R.drawable.maxverstappen5)
            );

            List<PhotoModel> Oscar = Arrays.asList(
                    new PhotoModel(R.drawable.oscarpiastri1),
                    new PhotoModel(R.drawable.oscarpiastri2),
                    new PhotoModel(R.drawable.oscarpiastri3),
                    new PhotoModel(R.drawable.oscarpiastri4),
                    new PhotoModel(R.drawable.oscarpiastri5)
            );

            List<PhotoModel> highlightlando = Arrays.asList(
                    new PhotoModel(R.drawable.landonorris5),
                    new PhotoModel(R.drawable.landonorris6),
                    new PhotoModel(R.drawable.landonorris7),
                    new PhotoModel(R.drawable.landonorris8),
                    new PhotoModel(R.drawable.landonorris9)
            );

            List<PhotoModel> highlightleclerc = Arrays.asList(
                    new PhotoModel(R.drawable.charlesleclerc2),
                    new PhotoModel(R.drawable.charlesleclerc3),
                    new PhotoModel(R.drawable.charlesleclerc4),
                    new PhotoModel(R.drawable.charlesleclerc5),
                    new PhotoModel(R.drawable.charlesleclerc6),
                    new PhotoModel(R.drawable.charlesleclerc7)
                    );

            List<PhotoModel> highlightalonso = Arrays.asList(
                    new PhotoModel(R.drawable.fernandoalonso8),
                    new PhotoModel(R.drawable.fernandoalonso9),
                    new PhotoModel(R.drawable.fernandoalonso10),
                    new PhotoModel(R.drawable.fernandoalonso11),
                    new PhotoModel(R.drawable.fernandoalonso7),
                    new PhotoModel(R.drawable.fernandoalonso2),
                    new PhotoModel(R.drawable.fernandoalonso3)

            );

            List<PhotoModel> highlightlewis = Arrays.asList(
                    new PhotoModel(R.drawable.lewishamilton8),
                    new PhotoModel(R.drawable.lewishamilton9),
                    new PhotoModel(R.drawable.lewishamilton10)
            );

            List<PhotoModel> highlightmax = Arrays.asList(
                    new PhotoModel(R.drawable.maxverstappen7),
                    new PhotoModel(R.drawable.maxverstappen8),
                    new PhotoModel(R.drawable.maxverstappen9),
                    new PhotoModel(R.drawable.maxverstappen10),
                    new PhotoModel(R.drawable.maxverstappen11)
            );

            List<PhotoModel> highlightOscar= Arrays.asList(
                    new PhotoModel(R.drawable.oscarpiastri6),
                    new PhotoModel(R.drawable.oscarpiastri7),
                    new PhotoModel(R.drawable.oscarpiastri8),
                    new PhotoModel(R.drawable.oscarpiastri9),
                    new PhotoModel(R.drawable.oscarpiastri10)
            );

            feedList.add(new FeedModel(R.drawable.charlesleclerc1, "charlesleclerc", leclerc1, "", 2039812, 8346, "charles\n" +
                    "ferrari",
                    "me",
                    R.drawable.charlesleclerc2,
                    highlightleclerc,
                    2,
                    12613714,
                    4
            ));
            feedList.add(new FeedModel(R.drawable.fernandoalonso8, "fernandoalonso", alonso, "Aston Martin", 24096, 117, "F1\n",
                        "me",
                        R.drawable.fernandoalonso9,
                        highlightalonso,
                        1,
                        108827,
                        367
                ));

                feedList.add(new FeedModel(R.drawable.charlesleclerc1, "charlesleclerc", leclerc2, "", 2039812, 8346, "charles\n" +
                        "ferrari",
                        "me",
                        R.drawable.charlesleclerc2,
                        highlightleclerc,
                        1,
                        12613714,
                        4));

                feedList.add(new FeedModel(R.drawable.landonorris3, "landonorris", lando, "", 44444, 3760, "F1 driver \n",
                        "me",
                        R.drawable.landonorris9,
                        highlightlando,
                        1,
                        107488,
                        982
                ));

                feedList.add(new FeedModel(R.drawable.lewishamilton10, "lewishamilton", lewis1, "brazil" +
                        "F1\n",
                        3000,
                        1200,
                        "F1 driver",
                        "lewis",
                        R.drawable.lewishamilton1,
                        highlightlewis,
                        3,
                        313147,
                        179));

                feedList.add(new FeedModel(R.drawable.lewishamilton10, "lewishamilton", lewis2, "world champion",
                        3000,
                        1200,
                        "F1 driver",
                        "lewis",
                        R.drawable.lewishamilton1,
                        highlightlewis,
                        3,
                        313147,
                        179));

                feedList.add(new FeedModel(R.drawable.lewishamilton10, "lewishamilton", lewis3, "GOAT",
                        11101,
                        24,
                        "F1 driver",
                        "lewis",
                        R.drawable.lewishamilton1,
                        highlightlewis,
                        3,
                        313147,
                        179));


                feedList.add(new FeedModel(R.drawable.maxverstappen6, "maxverstappen", max, "redbull\n",
                        710339,
                        10800,
                        "Redbull Driver",
                        "me",
                        R.drawable.maxverstappen11,
                        highlightmax,
                        1,
                        6470781,
                        420
                ));

                feedList.add(new FeedModel(R.drawable.oscarpiastri6, "oscarpiastri", Oscar, "mclaren",
                        490688,
                        99200,
                        "F1 Driver",
                        "piastri",
                        R.drawable.oscarpiastri3,
                        highlightOscar,
                        1,
                        33909686,
                        398
                ));
        }
        return feedList;
    }
}
