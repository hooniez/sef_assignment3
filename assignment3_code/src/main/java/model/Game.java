package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Games played in a school.
 */
public class Game {
    /**
     * Name the game
     */
    private String name = "Football";

    /**
     * Match date
     */
    private Date matchDate;

    /**
     * Number of team member
     */
    private int teamMembers;

    /**
     * Duration of the match in minutes
     */
    private int matchDuration;

    /**
     * Team allocated to the game
     */
    private List<Student> team = new ArrayList<Student>();

    public String getGame() {
        return name;
    }

    public void setGame(String name) {
        this.name = name;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDuration(int matchDuration) {
        this.matchDuration = matchDuration;
    }
    
    public int getMatchDuration() {
        return matchDuration;
    }

    public int getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(int teamMembers) {
        this.teamMembers = teamMembers;
    }



    public List<Student> getTeam() {
        return team;
    }



}
