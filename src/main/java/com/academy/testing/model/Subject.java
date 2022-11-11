package com.academy.testing.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(title, subject.title) && Objects.equals(description, subject.description) && Objects.equals(status, subject.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, status);
    }
}
