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

    private Activity p_activities_list[] = {new Activity("biking")
                                        , new Activity("running")
                                        , new Activity("workingout")
                                        , new Activity("walking")};

    public static final User[] USERS = {
            new User("Bob", "Parker")
    };

    public User(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Activity[] getM_activities_list() {
        return m_activities_list;
    }

    public void setM_activities_list(Activity[] m_activities_list) {
        this.m_activities_list = m_activities_list;
    }

    public Activity[] getP_activities_list() {
        return p_activities_list;
    }

    public void setP_activities_list(Activity[] p_activities_list) {
        this.p_activities_list = p_activities_list;
    }
}
