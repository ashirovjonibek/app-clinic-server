package uz.napa.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.napa.clinic.entity.template.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MessageCenter extends BaseEntity {

    @ManyToOne
    private User to;

    @ManyToOne
    private User from;

    private String message;

    private boolean edit=false;

    private boolean read=false;

    @ManyToOne
    private Chat chat;
}
