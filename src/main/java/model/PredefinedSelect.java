package model;

import com.google.common.collect.ImmutableList;

import java.util.List;

public enum PredefinedSelect {
    MLODSZY_PROGRAMISTA_JAVA_JUNIOR_JAVA("m³odszy programista java","junior java"),
    SENIOR_JAVA_SENIOR_JAVA_DEVELOPER("senior java", "senior java developer");

    private List<String> keywords;

    PredefinedSelect(String... keywords){
        this.keywords = ImmutableList.copyOf(keywords); //com.google.common.collect.ImmutableList
    }

    public List<String> getKeywords(){
        return keywords;
    }
}
