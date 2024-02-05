package com.beiwel.model.entity;

import com.beiwel.model.view.AbstractView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false, insertable = false)
  @JsonView({AbstractView.Generic.class})
  private long id;

  @Column
  @JsonIgnore
  @CreationTimestamp
  private Timestamp createdDate;

  @Column
  @JsonIgnore
  @UpdateTimestamp
  private Timestamp updatedDate;

  public AbstractEntity(Long id) {
    this.id = id;
  }

}
