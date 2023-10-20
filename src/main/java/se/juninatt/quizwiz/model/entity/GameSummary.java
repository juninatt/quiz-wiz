package se.juninatt.quizwiz.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Summarizes a {@link Quiz} round.
 */
@Entity
@Table(name = "game_summary")
public class GameSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int totalScore;
    private int timeUsedPercentage;
    private int completionPercentage;
    private String date;

    // Constructors

    public GameSummary() {}

    public GameSummary(int totalScore,
                       int timeUsedPercentage,
                       int completionPercentage,
                       String date) {
        this.totalScore = totalScore;
        this.timeUsedPercentage = timeUsedPercentage;
        this.completionPercentage = completionPercentage;
        this.date = date;
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTimeUsedPercentage() {
        return timeUsedPercentage;
    }

    public void setTimeUsedPercentage(int timeUsedPercentage) {
        this.timeUsedPercentage = timeUsedPercentage;
    }

    public int getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(int completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Hashcode, equals and toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSummary that = (GameSummary) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GameSummary{" +
                "id=" + id +
                ", totalScore=" + totalScore +
                ", timeUsedPercentage=" + timeUsedPercentage +
                ", completionPercentage=" + completionPercentage +
                ", date='" + date + '\'' +
                '}';
    }
}
