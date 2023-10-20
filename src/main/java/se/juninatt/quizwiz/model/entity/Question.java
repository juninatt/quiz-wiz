package se.juninatt.quizwiz.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Class that represent a question in a {@link Quiz}.
 */
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    private int timeLimit;
    private int points;
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<AnswerOption> answerOptions;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    // Constructors

    public Question() {}

    public Question(String questionText,
                    List<AnswerOption> answerOptions,
                    int timeLimit,
                    int points) {
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.timeLimit = timeLimit;
        this.points = points;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<AnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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
        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", answerOptions=" + answerOptions +
                ", timeLimit=" + timeLimit +
                ", points=" + points +
                ", quiz=" + quiz +
                '}';
    }
}

