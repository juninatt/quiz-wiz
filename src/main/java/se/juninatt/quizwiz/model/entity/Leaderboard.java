package se.juninatt.quizwiz.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Summarizes a {@link Quiz} round.
 */
@Entity
@Table(name = "leaderboard")
public class Leaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "player")
    private String player;
    @Column(name = "topic")
    private String topic;
    @Column(name = "score")
    private String score;
    @Column(name = "total_score_percentage")
    private int totalScorePercentage;
    @Column(name = "time_used_percentage")
    private int timeUsedPercentage;
    @Column(name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    // Constructors

    public Leaderboard() {}

    public Leaderboard(String player,
                       String score,
                       int timeUsedPercentage,
                       int totalScorePercentage,
                       String date) {
        this.player = player;
        this.score = score;
        this.timeUsedPercentage = timeUsedPercentage;
        this.totalScorePercentage = totalScorePercentage;
        this.date = date;
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String totalScore) {
        this.score = totalScore;
    }

    public int getTimeUsedPercentage() {
        return timeUsedPercentage;
    }

    public void setTimeUsedPercentage(int timeUsedPercentage) {
        this.timeUsedPercentage = timeUsedPercentage;
    }

    public int getCompletionPercentage() {
        return totalScorePercentage;
    }

    public void setCompletionPercentage(int completionPercentage) {
        this.totalScorePercentage = completionPercentage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    // Hashcode, equals and toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leaderboard that = (Leaderboard) o;
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
                ", totalScore=" + score +
                ", timeUsedPercentage=" + timeUsedPercentage +
                ", completionPercentage=" + totalScorePercentage +
                ", date='" + date + '\'' +
                '}';
    }
}
