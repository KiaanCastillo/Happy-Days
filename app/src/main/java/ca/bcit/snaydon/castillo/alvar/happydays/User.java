package ca.bcit.snaydon.castillo.alvar.happydays;

public class User {

    public static final int MAX_FAVS = 3;

    private String first_name;

    private String last_name;

    private Activity m_activities_list[] = {new Activity("reading")
                                          , new Activity("meditating")
                                          , new Activity("mindmap")
                                          , new Activity("stretching")
                                          , new Activity("journaling")};

    private Activity m_favourites_list[];

    private Activity p_activities_list[] = {new Activity("biking")
                                        , new Activity("running")
                                        , new Activity("workingout")
                                        , new Activity("walking")};

    private Activity p_favourites_list[];

    public User(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        m_favourites_list = new Activity[MAX_FAVS];
        p_favourites_list = new Activity[MAX_FAVS];
    }

}
