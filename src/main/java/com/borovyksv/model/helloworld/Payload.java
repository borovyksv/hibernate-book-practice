package com.borovyksv.model.helloworld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

//@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Payload {

    boolean hidden;

    @Access(value = AccessType.PROPERTY)
    @Column(nullable = false)
    @NotNull
    String title;

    @Column(nullable = false)
    @NotNull
    String data;


    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getTitle() {
        System.out.println(Thread.currentThread()+ "getTitle() " + title);
        return "GETTER "+title;
    }

    public void setTitle(String title) {
        System.out.println(Thread.currentThread()+"setTitle() " + title);
        this.title = "SETTER "+title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
