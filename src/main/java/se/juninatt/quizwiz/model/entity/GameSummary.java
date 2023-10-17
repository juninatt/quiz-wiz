package se.juninatt.quizwiz.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Summarizes a {@link Quiz} round.
 */
@Entity
public class GameSummary {
    @Id
    private long id;
    int totalScore;
    int timeUsedPercentage;
    double completionPercentage;
    LocalDate date;


    // Constructors

    public GameSummary() {}

    public GameSummary(int totalScore,
                       int timeUsedPercentage,
                       double completionPercentage,
                       LocalDate date) {
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

    public double getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Hashcode and equals

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
}
