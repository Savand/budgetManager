package org.savand.budget_manager.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Data;

import org.hibernate.Hibernate;
import org.savand.budget_manager.util.LocalDateTimeAttributeConverter;

import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;


@MappedSuperclass
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE,
        isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public abstract @Data class BaseEntity {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    protected Integer id;

    @Column(name = "creation_date", columnDefinition = "timestamp default now()")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    protected LocalDateTime creationDate;

    @Column(name = "last_update")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    protected LocalDateTime lastUpdate;

    public BaseEntity() {
        this.creationDate = LocalDateTime.now();
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;

        return null != id && id.equals(that.id);
    }

    @Override
    public String toString() {
        return "id=" + id + ", ";
    }

}
