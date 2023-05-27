package com.project.hospital;

public enum Curing {
   flue (3), measles(10), broken_limb(15), treatment_of_skin_disease(2),
    eye_surgery(3), surgery_for_appendicitis(7),plastic_surgery(10), stent_placement_surgery(7);
   private Integer days;

    Curing(Integer days) {
        this.days = days;
    }
    public Integer getDays() {
        return days;
    }
    public void setDays(Integer days) {
        this.days = days;
    }
}
