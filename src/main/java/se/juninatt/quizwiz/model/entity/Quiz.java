package se.juninatt.quizwiz.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * This class represents the actual quiz.
 */
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String topic;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Question> questions;

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

    // Hashcode, equals and toString

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

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", questions=" + questions +
                '}';
    }
}

