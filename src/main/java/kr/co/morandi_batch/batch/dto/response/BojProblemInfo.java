package kr.co.morandi_batch.batch.dto.response;

import java.util.List;

public class BojProblemInfo {
    private int problemId;
    private String titleKo;
    private List<TitleDTO> titles;
    private boolean isSolvable;
    private boolean isPartial;
    private int acceptedUserCount;
    private int level;
    private int votedUserCount;
    private boolean sprout;
    private boolean givesNoRating;
    private boolean isLevelLocked;
    private double averageTries;
    private boolean official;
    private List<TagDTO> tags;

}
