package se.juninatt.quizwiz.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Objects;

/**
 * This class represents the actual quiz.
 */
@Entity
public class Quiz {
    @Id
    private Long id;
    private String topic;
    private List<Question> questions;


    // Constructors
    public Quiz() {}

    public Quiz(String topic, List<Question> questions) {
        this.topic = topic;
        this.questions = questions;
    }


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // Hashcode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

