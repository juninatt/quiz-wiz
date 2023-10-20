package se.juninatt.quizwiz.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * This class represents the answer options of a specific {@link Question}.
 */
@Entity
@Table(name = "answer_option")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String optionText;
    private boolean isCorrectAnswer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    // Constructors

    public AnswerOption() {}

    public AnswerOption(String optionText, boolean isCorrectAnswer) {
        this.optionText = optionText;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    // Getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Question getQuestion() {
        return question;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    // Hashcode, equals and toString

    @Override
    public int hashCode() {
        return Objects.hash(id, optionText, question, isCorrectAnswer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AnswerOption that = (AnswerOption) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(optionText, that.optionText) &&
                Objects.equals(question, that.question) &&
                Objects.equals(isCorrectAnswer, that.isCorrectAnswer);
    }

    @Override
    public String toString() {
        return "AnswerOption{" +
                "id=" + id +
                ", optionText='" + optionText + '\'' +
                ", isCorrectAnswer=" + isCorrectAnswer +
                ", question=" + question +
                '}';
    }
}
